package com.whfp.anti_terrorism.factory;

import android.content.Context;
import android.content.Intent;

import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.view.RxToast;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.activity.AboutActivity;
import com.whfp.anti_terrorism.activity.ControlListActivity;
import com.whfp.anti_terrorism.activity.JKCKMenuActivity;
import com.whfp.anti_terrorism.activity.MonitorActivity;
import com.whfp.anti_terrorism.activity.MonitorHistoryActivity;
import com.whfp.anti_terrorism.activity.SJSBActivity;
import com.whfp.anti_terrorism.activity.SJSBMenuActivity;
import com.whfp.anti_terrorism.activity.SZQYJYBGActivity;
import com.whfp.anti_terrorism.activity.SchoolBusListActivity;
import com.whfp.anti_terrorism.activity.SettingActivity;
import com.whfp.anti_terrorism.activity.XYActivity;
import com.whfp.anti_terrorism.activity.XYZSSBActivity;
import com.whfp.anti_terrorism.bean.BusPersonBean;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.bean.MenuBean;
import com.whfp.anti_terrorism.bean.MonitorHistoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView数据初始化及选中事件分发
 */
public class MyFactory {

    /**
     * list数据初始化
     *
     * @param type 类型
     * @return
     */
    public static List getBaseListDatas(int type) {
        List list = null;
        switch (type) {
            case Constants.IMAGE_LIST://主页轮播测试图片
                list = new ArrayList<Integer>();
                list.add(R.mipmap.banner1);
                list.add(R.mipmap.banner2);
                list.add(R.mipmap.banner3);

                break;
            case Constants.GRID_LIST://主页九宫格菜单
                list = new ArrayList<MenuBean>();
                list.add(new MenuBean(R.mipmap.icon_main1, "事件上报"));
                list.add(new MenuBean(R.mipmap.icon_main2, "监控查看"));
                list.add(new MenuBean(R.mipmap.icon_main3, "校园"));
                list.add(new MenuBean(R.mipmap.icon_main4, "访客"));
                list.add(new MenuBean(R.mipmap.icon_main8, "设置"));
                list.add(new MenuBean(R.mipmap.icon_main9, "关于"));
                break;
            case Constants.SJSB_LIST://事件上报菜单
                list = new ArrayList<MenuBean>();
                list.add(new MenuBean(R.mipmap.icon_menu1, "散装汽油加油报告"));
                list.add(new MenuBean(R.mipmap.icon_menu2, "收寄可疑物品上报"));
                list.add(new MenuBean(R.mipmap.icon_menu3, "访客系统数据上报"));
                list.add(new MenuBean(R.mipmap.icon_menu4, "案件上报"));
                list.add(new MenuBean(R.mipmap.icon_menu5, "可疑情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu6, "其他异常情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu7, "值班情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu7, "安全防范情况上报"));
                break;
            case Constants.JKCK_MENU_LIST://监控查看主页菜单
                list = new ArrayList<MenuBean>();
                list.add(new MenuBean(R.mipmap.icon_menu1, "实时监控查看"));
                list.add(new MenuBean(R.mipmap.icon_menu2, "历史录像查看"));
                break;
            case Constants.XY_MENU_LIST://校园主页菜单
                list = new ArrayList<MenuBean>();
                list.add(new MenuBean(R.mipmap.icon_menu1, "校车监控查看"));
                list.add(new MenuBean(R.mipmap.icon_menu4, "校车监控历史录像"));
                list.add(new MenuBean(R.mipmap.icon_menu3, "校车运行路线查看"));
                list.add(new MenuBean(R.mipmap.icon_menu2, "校园走失情况上报"));
                break;
            case Constants.SDYQ_MENU_LIST://水电油气主页菜单
            case Constants.DZJG_MENU_LIST://党政机关主页菜单
            case Constants.ZDDW_MENU_LIST://重点单位主页菜单
                list = new ArrayList<MenuBean>();
                list.add(new MenuBean(R.mipmap.icon_menu1, "实时监控查看"));
                list.add(new MenuBean(R.mipmap.icon_menu2, "历史录像查看"));
                list.add(new MenuBean(R.mipmap.icon_menu10, "访客系统数据上报"));
                list.add(new MenuBean(R.mipmap.icon_menu4, "案件上报"));
                list.add(new MenuBean(R.mipmap.icon_menu5, "其他异常情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu6, "值班情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu7, "安全防范情况上报"));
                break;
            case Constants.HGQY_MENU_LIST://化工企业主页菜单
                list = new ArrayList<MenuBean>();
                list.add(new MenuBean(R.mipmap.icon_menu1, "实时监控查看"));
                list.add(new MenuBean(R.mipmap.icon_menu2, "历史录像查看"));
                list.add(new MenuBean(R.mipmap.icon_menu10, "访客系统数据上报"));
                list.add(new MenuBean(R.mipmap.icon_menu4, "案件上报"));
                list.add(new MenuBean(R.mipmap.icon_menu5, "其他异常情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu6, "值班情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu7, "安全防范情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu12, "排污情况"));
                break;
            case Constants.LSJK_LIST://历史监控录像列表
                list = new ArrayList<MonitorHistoryBean>();
                list.add(new MonitorHistoryBean("历史录像1", "2018-06-06", R.mipmap.video_preview));
                list.add(new MonitorHistoryBean("历史录像2", "2018-06-07", R.mipmap.video_preview));
                list.add(new MonitorHistoryBean("历史录像3", "2018-06-08", R.mipmap.video_preview));
                list.add(new MonitorHistoryBean("历史录像4", "2018-06-09", R.mipmap.video_preview));
                list.add(new MonitorHistoryBean("历史录像5", "2018-06-10", R.mipmap.video_preview));
                list.add(new MonitorHistoryBean("历史录像6", "2018-06-11", R.mipmap.video_preview));
                list.add(new MonitorHistoryBean("历史录像7", "2018-06-12", R.mipmap.video_preview));
                break;
            case Constants.CCRY_LIST:
                list = new ArrayList<BusPersonBean>();
                list.add(new BusPersonBean("James", "司机"));
                list.add(new BusPersonBean("Jays", "老师"));
                list.add(new BusPersonBean("Keys", "学生"));
                list.add(new BusPersonBean("Andy", "学生"));
                list.add(new BusPersonBean("Kevin", "学生"));

                break;
            default:
                break;
        }
        return list;
    }

    /**
     * list点击事件分发
     *
     * @param context
     * @param type     类型
     * @param position list 的posision
     * @param obj      list position位置的数据
     */
    public static void ListClick(final Context context, int type, int position, Object obj) {
        Intent intent = null;
        switch (type) {
            case Constants.GRID_LIST://主页九宫格菜单
                switch (position) {
                    case 0://事件上报
                        RxActivityTool.skipActivity(context, SJSBMenuActivity.class);
                        break;
                    case 1://监控查看
                        RxToast.info("暂未开放");
                        RxActivityTool.skipActivity(context, JKCKMenuActivity.class);
                        break;
                    case 2://校园
                        RxActivityTool.skipActivity(context, XYActivity.class);
                        break;
                    case 3://访客
                        RxToast.info("暂未开放");
                        break;
                    case 4://设置
                        RxActivityTool.skipActivity(context, SettingActivity.class);
                        break;
                    case 5://关于
                        RxActivityTool.skipActivity(context, AboutActivity.class);
                        break;
                }
                break;
            case Constants.SJSB_LIST://事件上报菜单
                intent = new Intent(context, SJSBActivity.class);
                switch (position) {
                    case 0://散装汽油加油报告
                        intent = new Intent(context, SZQYJYBGActivity.class);
                        break;
                    case 1://收寄可疑物品上报
                    case 2://访客系统数据上报
                        intent.putExtra(Constants.SJSB_TYPE, Constants.TYPE_MULT);
                        intent.putExtra(Constants.SJSB_TITLE, (String) obj);
                        break;
                    case 3://案件上报
                        intent.putExtra(Constants.SJSB_TYPE, Constants.TYPE_AJSB);
                        intent.putExtra(Constants.SJSB_TITLE, (String) obj);
                        break;
                    case 4://可疑情况上报
                    case 5://其他异常情况上报
                        intent.putExtra(Constants.SJSB_TYPE, Constants.TYPE_MULT_KY);
                        intent.putExtra(Constants.SJSB_TITLE, (String) obj);
                        break;
                    case 6://值班情况上报
                        intent.putExtra(Constants.SJSB_TYPE, Constants.TYPE_ZB);
                        intent.putExtra(Constants.SJSB_TITLE, (String) obj);
                        break;
                    case 7://安全防范情况上报
                        intent.putExtra(Constants.SJSB_TYPE, Constants.TYPE_AQFF);
                        intent.putExtra(Constants.SJSB_TITLE, (String) obj);
                        break;
                }
                context.startActivity(intent);
                break;
            case Constants.JKCK_MENU_LIST://监控查看主页菜单菜单
                intent = new Intent(context,ControlListActivity.class);
                switch (position) {
                    case 0://实时监控查看
                        intent.putExtra(Constants.HK_OPERATE_TYPE,Constants.HK_OPERATE_TYPE_JKCK);
                        context.startActivity(intent);
                        break;
                    case 1://历史录像查看
                        intent.putExtra(Constants.HK_OPERATE_TYPE,Constants.HK_OPERATE_TYPE_LSLX);
                        context.startActivity(intent);
                        break;
                }
                break;
            case Constants.XY_MENU_LIST://校园主页菜单
                intent = new Intent(context, SchoolBusListActivity.class);
                switch (position) {
                    case 0://校车监控查看
                        intent.putExtra(Constants.SCHOOL_BUS_OPERATE_TYPE, Constants.SCHOOL_OPERATE_TYPE_JKCK);
                        context.startActivity(intent);
                        break;
                    case 1://校车监控历史录像
                        intent.putExtra(Constants.SCHOOL_BUS_OPERATE_TYPE, Constants.SCHOOL_OPERATE_TYPE_LSLX);
                        context.startActivity(intent);
                        break;
                    case 2://校车运行路线查看
                        intent.putExtra(Constants.SCHOOL_BUS_OPERATE_TYPE, Constants.SCHOOL_OPERATE_TYPE_YXLX);
                        context.startActivity(intent);
                        break;
                    case 3://校园走失情况上报
                        RxActivityTool.skipActivity(context, XYZSSBActivity.class);
                }
                break;
            case Constants.SDYQ_MENU_LIST://水电油气主页菜单
                switch (position) {
                    case 0://实时监控查看
                        RxActivityTool.skipActivity(context, MonitorActivity.class);
                        break;
                    case 1://历史录像查看
                        RxActivityTool.skipActivity(context, MonitorHistoryActivity.class);
                        break;
                    case 2://访客系统数据上报
                        break;
                }
                break;
            case Constants.DZJG_MENU_LIST://党政机关主页菜单
                switch (position) {
                    case 0://实时监控查看
                        RxActivityTool.skipActivity(context, MonitorActivity.class);
                        break;
                    case 1://历史录像查看
                        RxActivityTool.skipActivity(context, MonitorHistoryActivity.class);
                        break;
                    case 2://访客系统数据上报
                        break;
                }
                break;
            case Constants.HGQY_MENU_LIST://化工企业主页菜单
                switch (position) {
                    case 0://实时监控查看
                        RxActivityTool.skipActivity(context, MonitorActivity.class);
                        break;
                    case 1://历史录像查看
                        RxActivityTool.skipActivity(context, MonitorHistoryActivity.class);
                        break;
                    case 2://访客系统数据上报
                        break;
                }
                break;
            case Constants.ZDDW_MENU_LIST://重点单位主页菜单
                switch (position) {
                    case 0://实时监控查看
                        RxActivityTool.skipActivity(context, MonitorActivity.class);
                        break;
                    case 1://历史录像查看
                        RxActivityTool.skipActivity(context, MonitorHistoryActivity.class);
                        break;
                    case 2://访客系统数据上报
                        break;
                }
                break;
        }
    }

}
