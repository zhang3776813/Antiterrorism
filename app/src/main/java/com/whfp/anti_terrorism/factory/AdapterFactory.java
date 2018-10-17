package com.whfp.anti_terrorism.factory;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemChildClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener;
import com.chad.library.adapter.base.BaseViewHolder;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.adapter.MyBaseRecyclerAdapter;
import com.whfp.anti_terrorism.bean.BusPersonBean;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.bean.MenuBean;
import com.whfp.anti_terrorism.bean.MonitorHistoryBean;
import com.whfp.anti_terrorism.bean.SchoolBusListBean;

import java.util.List;


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
     * @return
     */
    public static MyBaseRecyclerAdapter getAdapterByType(Context context, int type, @Nullable List list,
                                                         @Nullable OnItemClickListener listener,
                                                         @Nullable OnItemChildClickListener childClickListener) {
        MyBaseRecyclerAdapter adapter = null;
        switch (type) {
            case Constants.ADAPTER_GAS_MENU://加油站主页菜单
                adapter = getAdapterGASMenu(context);
                break;
            case Constants.ADAPTER_JDWL_MENU://寄递物流主页菜单
                adapter = getAdapterJDWLMenu(context);
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
                adapter = getAdapterXCLB(context, list);
                break;
        }
        return adapter;
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
     * 寄递物流主页列表菜单适配器
     *
     * @param context
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterJDWLMenu(final Context context) {
        return new MyBaseRecyclerAdapter<MenuBean>(context, R.layout.item_recycler_list_menu, MyFactory.getBaseListDatas(Constants.JDWL_MENU_LIST), new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyFactory.ListClick(context, Constants.JDWL_MENU_LIST, position, null);
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
     * 加油站主页列表菜单适配器
     *
     * @param context
     * @return
     */
    private static MyBaseRecyclerAdapter getAdapterGASMenu(final Context context) {
        return new MyBaseRecyclerAdapter<MenuBean>(context, R.layout.item_recycler_list_menu, MyFactory.getBaseListDatas(Constants.GAS_MENU_LIST), new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyFactory.ListClick(context, Constants.GAS_MENU_LIST, position, null);
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
    private static MyBaseRecyclerAdapter getAdapterCCRY(Context context, List<BusPersonBean> list) {
        return new MyBaseRecyclerAdapter<BusPersonBean>(context, R.layout.item_recycler_ccry, list, null, null) {
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
    private static MyBaseRecyclerAdapter getAdapterXCLB(final Context context, List<SchoolBusListBean.DeviceBean> list) {
        return new MyBaseRecyclerAdapter<SchoolBusListBean.DeviceBean>(context, R.layout.item_recycler_xclb, list, null, null) {
            @Override
            protected void convert(BaseViewHolder helper, SchoolBusListBean.DeviceBean item) {
                helper.setText(R.id.tv_car_number, "车牌号码："+item.getCarlicence());
                helper.setText(R.id.tv_channel, "通道数量："+item.getChannelcount());
                helper.setText(R.id.tv_device_number, "设备编号："+item.getDeviceid());
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
