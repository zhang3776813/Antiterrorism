package com.whfp.anti_terrorism.factory;

import android.content.Context;
import android.content.Intent;

import com.vondear.rxtools.RxActivityTool;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.activity.AJSBActivity;
import com.whfp.anti_terrorism.activity.AQFFSBActivity;
import com.whfp.anti_terrorism.activity.AboutActivity;
import com.whfp.anti_terrorism.activity.DZJGActivity;
import com.whfp.anti_terrorism.activity.GASActivity;
import com.whfp.anti_terrorism.activity.HGQYActivity;
import com.whfp.anti_terrorism.activity.JDWLActivity;
import com.whfp.anti_terrorism.activity.MonitorActivity;
import com.whfp.anti_terrorism.activity.MonitorHistoryActivity;
import com.whfp.anti_terrorism.activity.KYQKSBActivity;
import com.whfp.anti_terrorism.activity.PWQKActivity;
import com.whfp.anti_terrorism.activity.QTYCSBActivity;
import com.whfp.anti_terrorism.activity.SDYQActivity;
import com.whfp.anti_terrorism.activity.SJKYWPSBActivity;
import com.whfp.anti_terrorism.activity.SZQYJYBGActivity;
import com.whfp.anti_terrorism.activity.SchoolBusListActivity;
import com.whfp.anti_terrorism.activity.SettingActivity;
import com.whfp.anti_terrorism.activity.XCYXQKActivity;
import com.whfp.anti_terrorism.activity.XYActivity;
import com.whfp.anti_terrorism.activity.XYZSSBActivity;
import com.whfp.anti_terrorism.activity.ZBQKSBActivity;
import com.whfp.anti_terrorism.activity.ZDDWActivity;
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
            case Constants.IMAGE_LIST://代理商主页轮播测试图片
                list = new ArrayList<Integer>();
                list.add(R.mipmap.banner1);
                list.add(R.mipmap.banner2);
                list.add(R.mipmap.banner3);

                break;
            case Constants.GRID_LIST://主页九宫格菜单
                list = new ArrayList<MenuBean>();
                list.add(new MenuBean(R.mipmap.icon_main1, "加油站"));
                list.add(new MenuBean(R.mipmap.icon_main2, "寄递物流"));
                list.add(new MenuBean(R.mipmap.icon_main3, "校园"));
                list.add(new MenuBean(R.mipmap.icon_main4, "水电油气"));
                list.add(new MenuBean(R.mipmap.icon_main5, "党政机关"));
                list.add(new MenuBean(R.mipmap.icon_main6, "化工企业"));
                list.add(new MenuBean(R.mipmap.icon_main7, "重点单位"));
                list.add(new MenuBean(R.mipmap.icon_main8, "设置"));
                list.add(new MenuBean(R.mipmap.icon_main9, "关于"));
                break;
            case Constants.GAS_MENU_LIST://加油站主页菜单
                list = new ArrayList<MenuBean>();
                list.add(new MenuBean(R.mipmap.icon_menu1, "实时监控查看"));
                list.add(new MenuBean(R.mipmap.icon_menu2, "历史录像查看"));
                list.add(new MenuBean(R.mipmap.icon_menu3, "散装汽油加油报告"));
                list.add(new MenuBean(R.mipmap.icon_menu4, "案件上报"));
                list.add(new MenuBean(R.mipmap.icon_menu5, "可疑情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu6, "值班情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu7, "安全防范情况上报"));
                break;
            case Constants.JDWL_MENU_LIST://寄递物流主页菜单
                list = new ArrayList<MenuBean>();
                list.add(new MenuBean(R.mipmap.icon_menu1, "实时监控查看"));
                list.add(new MenuBean(R.mipmap.icon_menu2, "历史录像查看"));
                list.add(new MenuBean(R.mipmap.icon_menu9, "收寄可疑物品上报"));
                list.add(new MenuBean(R.mipmap.icon_menu4, "案件上报"));
                list.add(new MenuBean(R.mipmap.icon_menu5, "可疑情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu6, "值班情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu7, "安全防范情况上报"));
                break;
            case Constants.XY_MENU_LIST://校园主页菜单
                list = new ArrayList<MenuBean>();
                list.add(new MenuBean(R.mipmap.icon_menu1, "实时监控查看"));
                list.add(new MenuBean(R.mipmap.icon_menu2, "历史录像查看"));
                list.add(new MenuBean(R.mipmap.icon_menu10, "访客系统数据上报"));
                list.add(new MenuBean(R.mipmap.icon_menu4, "案件上报"));
                list.add(new MenuBean(R.mipmap.icon_menu5, "校园走失情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu6, "值班情况上报"));
                list.add(new MenuBean(R.mipmap.icon_menu11, "校车运行情况"));
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
                    case 0://加油站
                        RxActivityTool.skipActivity(context, GASActivity.class);
                        break;
                    case 1://寄递物流
                        RxActivityTool.skipActivity(context, JDWLActivity.class);
                        break;
                    case 2://校园
                        RxActivityTool.skipActivity(context, XYActivity.class);
                        break;
                    case 3://水电油气
                        RxActivityTool.skipActivity(context, SDYQActivity.class);
                        break;
                    case 4://党政机关
                        RxActivityTool.skipActivity(context, DZJGActivity.class);
                        break;
                    case 5://化工企业
                        RxActivityTool.skipActivity(context, HGQYActivity.class);
                        break;
                    case 6://重点单位
                        RxActivityTool.skipActivity(context, ZDDWActivity.class);
                        break;
                    case 7://设置
                        RxActivityTool.skipActivity(context, SettingActivity.class);
                        break;
                    case 8://关于
                        RxActivityTool.skipActivity(context, AboutActivity.class);
                        break;
                }
                break;
            case Constants.GAS_MENU_LIST://加油站主页菜单
                switch (position) {
                    case 0://实时监控查看
                        RxActivityTool.skipActivity(context, MonitorActivity.class);
                        break;
                    case 1://历史录像查看
                        RxActivityTool.skipActivity(context, MonitorHistoryActivity.class);
                        break;
                    case 2://散装汽油加油报告
                        RxActivityTool.skipActivity(context, SZQYJYBGActivity.class);
                        break;
                    case 3://案件上报
                        RxActivityTool.skipActivity(context, AJSBActivity.class);
                        break;
                    case 4://可疑情况上报
                        RxActivityTool.skipActivity(context, KYQKSBActivity.class);
                        break;
                    case 5://值班情况上报
                        RxActivityTool.skipActivity(context, ZBQKSBActivity.class);
                        break;
                    case 6://安全防范情况上报
                        RxActivityTool.skipActivity(context, AQFFSBActivity.class);
                        break;
                }
                break;
            case Constants.JDWL_MENU_LIST://寄递物流主页菜单
                switch (position) {
                    case 0://实时监控查看
                        RxActivityTool.skipActivity(context, MonitorActivity.class);
                        break;
                    case 1://历史录像查看
                        RxActivityTool.skipActivity(context, MonitorHistoryActivity.class);
                        break;
                    case 2://收寄可疑物品上报
                        RxActivityTool.skipActivity(context, SJKYWPSBActivity.class);
                        break;
                    case 3://案件上报
                        RxActivityTool.skipActivity(context, AJSBActivity.class);
                        break;
                    case 4://可疑情况上报
                        RxActivityTool.skipActivity(context, KYQKSBActivity.class);
                        break;
                    case 5://值班情况上报
                        RxActivityTool.skipActivity(context, ZBQKSBActivity.class);
                        break;
                    case 6://安全防范情况上报
                        RxActivityTool.skipActivity(context, AQFFSBActivity.class);
                        break;
                }
                break;
            case Constants.XY_MENU_LIST://校园主页菜单
                switch (position) {
                    case 0://实时监控查看
//                        RxActivityTool.skipActivity(context, MonitorActivity.class);
                        RxActivityTool.skipActivity(context, SchoolBusListActivity.class);
                        break;
                    case 1://历史录像查看
                        RxActivityTool.skipActivity(context, MonitorHistoryActivity.class);
                        break;
                    case 2://访客系统数据上报
                        break;
                    case 3://案件上报
                        RxActivityTool.skipActivity(context, AJSBActivity.class);
                        break;
                    case 4://校园走失情况上报
                        RxActivityTool.skipActivity(context, XYZSSBActivity.class);
                        break;
                    case 5://值班情况上报
                        RxActivityTool.skipActivity(context, ZBQKSBActivity.class);
                        break;
                    case 6://校车运行情况
                        RxActivityTool.skipActivity(context, XCYXQKActivity.class);
                        break;
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
                    case 3://案件上报
                        RxActivityTool.skipActivity(context, AJSBActivity.class);
                        break;
                    case 4://其他异常情况上报
                        RxActivityTool.skipActivity(context, QTYCSBActivity.class);
                        break;
                    case 5://值班情况上报
                        RxActivityTool.skipActivity(context, ZBQKSBActivity.class);
                        break;
                    case 6://安全防范情况上报
                        RxActivityTool.skipActivity(context, AQFFSBActivity.class);
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
                    case 3://案件上报
                        RxActivityTool.skipActivity(context, AJSBActivity.class);
                        break;
                    case 4://其他异常情况上报
                        RxActivityTool.skipActivity(context, QTYCSBActivity.class);
                        break;
                    case 5://值班情况上报
                        RxActivityTool.skipActivity(context, ZBQKSBActivity.class);
                        break;
                    case 6://安全防范情况上报
                        RxActivityTool.skipActivity(context, AQFFSBActivity.class);
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
                    case 3://案件上报
                        RxActivityTool.skipActivity(context, AJSBActivity.class);
                        break;
                    case 4://其他异常情况上报
                        RxActivityTool.skipActivity(context, QTYCSBActivity.class);
                        break;
                    case 5://值班情况上报
                        RxActivityTool.skipActivity(context, ZBQKSBActivity.class);
                        break;
                    case 6://安全防范情况上报
                        RxActivityTool.skipActivity(context, AQFFSBActivity.class);
                        break;
                    case 7://排污情况
                        RxActivityTool.skipActivity(context, PWQKActivity.class);
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
                    case 3://案件上报
                        RxActivityTool.skipActivity(context, AJSBActivity.class);
                        break;
                    case 4://其他异常情况上报
                        RxActivityTool.skipActivity(context, QTYCSBActivity.class);
                        break;
                    case 5://值班情况上报
                        RxActivityTool.skipActivity(context, ZBQKSBActivity.class);
                        break;
                    case 6://安全防范情况上报
                        RxActivityTool.skipActivity(context, AQFFSBActivity.class);
                        break;
                }
                break;
        }
    }

}
