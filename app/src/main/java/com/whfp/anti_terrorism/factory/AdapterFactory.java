package com.whfp.anti_terrorism.factory;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.chad.library.adapter.base.BaseViewHolder;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.activity.CameraListActivity;
import com.whfp.anti_terrorism.adapter.MyBaseRecyclerAdapter;
import com.whfp.anti_terrorism.bean.BusPersonBean;
import com.whfp.anti_terrorism.bean.CameraBean;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.bean.ControlUnitBean;
import com.whfp.anti_terrorism.bean.MenuBean;
import com.whfp.anti_terrorism.bean.MonitorHistoryBean;
import com.whfp.anti_terrorism.bean.SchoolBusListBean;

import java.util.List;
import java.util.Map;


/**
 * Adapter初始化（仅限RecyclerView）
 * Created by 张明杨 on 2018-04-11-0011.
 */
public class AdapterFactory {

    /**
     * 根据类型初始化RecyclerView的Adapter
     *
     * @param context            上下文
     * @param type               类型
     * @param list               传入的数据 (可为空)
     * @param listener           Item点击事件(可为空)
     * @param childClickListener 子控件点击事件（可为空）
     * @param object             附加参数（可为空）
     * @return
     */
    public static MyBaseRecyclerAdapter getAdapterByType(Context context, int type, @NonNull List list,
                                                         @Nullable OnItemClickListener listener,
                                                         @Nullable OnItemChildClickListener childClickListener,
                                                         @Nullable Object object) {
        MyBaseRecyclerAdapter adapter = null;
        switch (type) {
            case Constants.ADAPTER_SJSB_MENU://事件上报菜单
                adapter = getAdapterSJSBMenu(context);
                break;
            case Constants.ADAPTER_JKCK_MENU://寄递物流主页菜单
                adapter = getAdapterJKCKMenu(context);
                break;
            case Constants.ADAPTER_XY_MENU://校园主页菜单
                adapter = getAdapterXYMenu(context);
                break;
            case Constants.ADAPTER_SDYQ_MENU://水电油气主页菜单
                adapter = getAdapterSDYQMenu(context);
                break;
            case Constants.ADAPTER_DZJG_MENU://党政机关主页菜单
                adapter = getAdapterDZJGMenu(context);
                break;
            case Constants.ADAPTER_HGQY_MENU://化工企业主页菜单
                adapter = getAdapterHGQYMenu(context);
                break;
            case Constants.ADAPTER_ZDDW_MENU://重点单位主页菜单
                adapter = getAdapterZDDWMenu(context);
                break;
            case Constants.ADAPTER_LSJK://历史监控录像列表
                adapter = getAdapterLSJK(context, list, listener, childClickListener);
                break;
            case Constants.ADAPTER_CCRY://乘车人员列表
                adapter = getAdapterCCRY(context, list);
                break;
            case Constants.ADAPTER_XCLB://校车列表
                adapter = getAdapterXCLB(context, list, listener, object);
                break;
            case Constants.ADAPTER_HK_CONTROL_LIST://海康组织列表
                adapter = getAdapterHKControlList(context, list, object);
                break;
            case Constants.ADAPTER_HK_CAMERA_LIST://海康监控点列表
                adapter = getAdapterHKCameraList(context, list, listener);
                break;
        }
        return adapter;
    }

    /**
     * 海康监控点列表
     *
     * @param context
     * @param list
     * @param listener
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterHKCameraList(final Context context, List<CameraBean.DataBean> list, OnItemClickListener listener) {
        return new MyBaseRecyclerAdapter<CameraBean.DataBean>(context, R.layout.item_recycler_list_control, list, listener, null) {
            @Override
            protected void convert(BaseViewHolder helper, CameraBean.DataBean item) {
                helper.setText(R.id.tv_menu_text, item.getName());
                int isOnline = item.getIsOnline();
                switch (isOnline) {
                    case 1://在线
                        helper.setText(R.id.tv_status, "██  在线");
                        helper.setTextColor(R.id.tv_status, ContextCompat.getColor(context, R.color.green));
                        break;
                    case 0://不在线
                        helper.setText(R.id.tv_status, "██  离线");
                        helper.setTextColor(R.id.tv_status, ContextCompat.getColor(context, R.color.text_color_gray));
                        break;
                }
            }
        };
    }


    /**
     * 海康组织和区域列表
     *
     * @param context
     * @param list
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterHKControlList(final Context context, final List<ControlUnitBean.DataBean> list, final Object object) {
        return new MyBaseRecyclerAdapter<ControlUnitBean.DataBean>(context, R.layout.item_recycler_list_menu, list, new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ControlUnitBean.DataBean dataBean = (ControlUnitBean.DataBean) adapter.getData().get(position);
                Intent intent = new Intent(context, CameraListActivity.class);
                intent.putExtra(Constants.HK_CONTROL_NAME, dataBean.getName());
                intent.putExtra(Constants.CONTROL_INDEX_CODE, dataBean.getIndexCode());
                intent.putExtra(Constants.HK_OPERATE_TYPE, (int) object);
                context.startActivity(intent);
            }
        }, null) {
            @Override
            protected void convert(BaseViewHolder helper, ControlUnitBean.DataBean item) {
                helper.setText(R.id.tv_menu_text, item.getName());
            }
        };
    }


    /**
     * 历史监控录像列表
     *
     * @param context
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterLSJK(final Context context, List<MonitorHistoryBean> list, OnItemClickListener listener, OnItemChildClickListener childClickListener) {
        return new MyBaseRecyclerAdapter<MonitorHistoryBean>(context, R.layout.item_recycler_lsjk, list, listener, childClickListener) {
            @Override
            protected void convert(BaseViewHolder helper, MonitorHistoryBean item) {
                helper.setText(R.id.tv_video_name, item.getVideoName());
                helper.setText(R.id.tv_video_time, item.getTime());
                helper.setImageResource(R.id.iv_video_preview, R.mipmap.video_preview);
            }
        };
    }

    /**
     * 重点单位主页列表菜单适配器
     *
     * @param context
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterZDDWMenu(final Context context) {
        return new MyBaseRecyclerAdapter<MenuBean>(context, R.layout.item_recycler_list_menu, MyFactory.getBaseListDatas(Constants.ZDDW_MENU_LIST), new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyFactory.ListClick(context, Constants.ZDDW_MENU_LIST, position, null);
            }
        }, null) {
            @Override
            protected void convert(BaseViewHolder helper, MenuBean item) {
                helper.setImageResource(R.id.iv_menu_img, item.getImg());
                helper.setText(R.id.tv_menu_text, item.getText());
            }
        };
    }

    /**
     * 化工企业主页列表菜单适配器
     *
     * @param context
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterHGQYMenu(final Context context) {
        return new MyBaseRecyclerAdapter<MenuBean>(context, R.layout.item_recycler_list_menu, MyFactory.getBaseListDatas(Constants.HGQY_MENU_LIST), new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyFactory.ListClick(context, Constants.HGQY_MENU_LIST, position, null);
            }
        }, null) {
            @Override
            protected void convert(BaseViewHolder helper, MenuBean item) {
                helper.setImageResource(R.id.iv_menu_img, item.getImg());
                helper.setText(R.id.tv_menu_text, item.getText());
            }
        };
    }

    /**
     * 党政机关主页列表菜单适配器
     *
     * @param context
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterDZJGMenu(final Context context) {
        return new MyBaseRecyclerAdapter<MenuBean>(context, R.layout.item_recycler_list_menu, MyFactory.getBaseListDatas(Constants.DZJG_MENU_LIST), new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyFactory.ListClick(context, Constants.DZJG_MENU_LIST, position, null);
            }
        }, null) {
            @Override
            protected void convert(BaseViewHolder helper, MenuBean item) {
                helper.setImageResource(R.id.iv_menu_img, item.getImg());
                helper.setText(R.id.tv_menu_text, item.getText());
            }
        };
    }

    /**
     * 水电油气主页列表菜单适配器
     *
     * @param context
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterSDYQMenu(final Context context) {
        return new MyBaseRecyclerAdapter<MenuBean>(context, R.layout.item_recycler_list_menu, MyFactory.getBaseListDatas(Constants.SDYQ_MENU_LIST), new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyFactory.ListClick(context, Constants.SDYQ_MENU_LIST, position, null);
            }
        }, null) {
            @Override
            protected void convert(BaseViewHolder helper, MenuBean item) {
                helper.setImageResource(R.id.iv_menu_img, item.getImg());
                helper.setText(R.id.tv_menu_text, item.getText());
            }
        };
    }

    /**
     * 校园主页列表菜单适配器
     *
     * @param context
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterXYMenu(final Context context) {
        return new MyBaseRecyclerAdapter<MenuBean>(context, R.layout.item_recycler_list_menu, MyFactory.getBaseListDatas(Constants.XY_MENU_LIST), new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyFactory.ListClick(context, Constants.XY_MENU_LIST, position, null);
            }
        }, null) {
            @Override
            protected void convert(BaseViewHolder helper, MenuBean item) {
                helper.setImageResource(R.id.iv_menu_img, item.getImg());
                helper.setText(R.id.tv_menu_text, item.getText());
            }
        };
    }

    /**
     * 监控查看列表菜单适配器
     *
     * @param context
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterJKCKMenu(final Context context) {
        return new MyBaseRecyclerAdapter<MenuBean>(context, R.layout.item_recycler_list_menu, MyFactory.getBaseListDatas(Constants.JKCK_MENU_LIST), new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyFactory.ListClick(context, Constants.JKCK_MENU_LIST, position, null);
            }
        }, null) {
            @Override
            protected void convert(BaseViewHolder helper, MenuBean item) {
                helper.setImageResource(R.id.iv_menu_img, item.getImg());
                helper.setText(R.id.tv_menu_text, item.getText());
            }
        };
    }

    /**
     * 事件上报列表菜单适配器
     *
     * @param context
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterSJSBMenu(final Context context) {
        return new MyBaseRecyclerAdapter<MenuBean>(context, R.layout.item_recycler_list_menu, MyFactory.getBaseListDatas(Constants.SJSB_LIST), new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MenuBean menuBean = (MenuBean) MyFactory.getBaseListDatas(Constants.SJSB_LIST).get(position);
                MyFactory.ListClick(context, Constants.SJSB_LIST, position, menuBean.getText());
            }
        }, null) {
            @Override
            protected void convert(BaseViewHolder helper, MenuBean item) {
                helper.setImageResource(R.id.iv_menu_img, item.getImg());
                helper.setText(R.id.tv_menu_text, item.getText());
            }
        };
    }

    /**
     * 校车 乘车人员列表 适配器
     *
     * @param context
     * @param list
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterCCRY(Context context, final List<BusPersonBean> list) {
        return new MyBaseRecyclerAdapter<BusPersonBean>(context, R.layout.item_recycler_ccry, list, null
                , null) {
            @Override
            protected void convert(BaseViewHolder helper, BusPersonBean item) {
                helper.setText(R.id.tv_name, item.getName());
                helper.setText(R.id.tv_identity, item.getIdentity());
            }
        };
    }

    /**
     * 校车设备列表
     *
     * @param context
     * @param list
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterXCLB(final Context context, List<SchoolBusListBean.DeviceBean> list, OnItemClickListener listener, final Object object) {
        return new MyBaseRecyclerAdapter<SchoolBusListBean.DeviceBean>(context, R.layout.item_recycler_xclb, list, listener, null) {
            @Override
            protected void convert(BaseViewHolder helper, SchoolBusListBean.DeviceBean item) {
                Map<Integer, String> group_dictionary = (Map<Integer, String>) object;
                String gs = group_dictionary.get(item.getGroupid());

                helper.setText(R.id.tv_car_number, "车牌号码：" + item.getCarlicence());
                helper.setText(R.id.tv_channel, "通道数量：" + item.getChannelcount());
                helper.setText(R.id.tv_device_number, "设备编号：" + item.getDeviceid());
                helper.setText(R.id.tv_logistic_company, "运营公司：" + gs);
                if (item.getStatus() == 1) {
                    helper.setText(R.id.tv_status, "在线");
                    helper.setTextColor(R.id.tv_status, ContextCompat.getColor(context, R.color.green));
                } else {
                    helper.setText(R.id.tv_status, "离线");
                    helper.setTextColor(R.id.tv_status, ContextCompat.getColor(context, R.color.text_color_gray));
                }
            }
        };
    }
}
