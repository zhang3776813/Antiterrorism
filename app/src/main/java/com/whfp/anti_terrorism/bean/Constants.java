package com.whfp.anti_terrorism.bean;

/**
 * 公共变量类
 *
 * @author 张明杨
 */
public class Constants {


    // 网络请求，result为1时成功
    public static final int SUCCESS = 1;

    // 分页大小
    public static final int PAGE_SIZE = 20;

    //刷新
    public static final int REFRESH = 501;
    //加载更多
    public static final int LOADMORE = 502;
    //首次加载数据
    public static final int FIRSTLOAD = 503;


    /**
     * 解析相关 http标记
     */
    // 不做任何处理
    public static final int DEFAULT = -8;
    // 仅仅基础解析
    public static final int BASIC = -6;
    //注册
    public static final int REGISTER = 1;
    // 登录
    public static final int LOGIN = 2;
    //校车列表
    public static final int SCHOOL_BUS_LIST = 3;
    //通用事件上报
    public static final int SJSB = 4;


    /**
     * ListView数据初始化及选中事件分发
     */
    //主页轮播图片
    public static final int IMAGE_LIST = 101;
    //主页九宫格菜单
    public static final int GRID_LIST = 102;
    //加油站主页菜单
    public static final int GAS_MENU_LIST = 103;
    //寄递物流主页菜单
    public static final int JDWL_MENU_LIST = 104;
    //校园主页菜单
    public static final int XY_MENU_LIST = 105;
    //水电油气主页菜单
    public static final int SDYQ_MENU_LIST = 106;
    //党政机关主页菜单
    public static final int DZJG_MENU_LIST = 107;
    //化工企业主页菜单
    public static final int HGQY_MENU_LIST = 108;
    //重点单位主页菜单
    public static final int ZDDW_MENU_LIST = 109;
    //历史监控监控录像列表
    public static final int LSJK_LIST = 110;
    //校车乘车人员列表
    public static final int CCRY_LIST = 111;


    // sp保存使用
    public static final String TEMPBEAN = "ZHGXKYJ";


    /**
     * Intent传递相关
     */
    /**
     * 列表类型
     * 1：加油站
     * 2：寄递物流
     * 3：校园
     * 4：水电油气
     * 5：党政机关
     * 6：化工企业
     * 7：重点单位
     */
    public static final String LIST_TYPE = "list_type";


    /**
     * Fragment相关
     */
    //代理商首页
    public static final int FRAGMENT_MAIN_DLS = 201;
    //工厂首页
    public static final int FRAGMENT_MAIN_GC = 202;


    /**
     * RecyclerAdapter相关
     */
    //加油站主页菜单
    public static final int ADAPTER_GAS_MENU = 301;
    //寄递物流主页菜单
    public static final int ADAPTER_JDWL_MENU = 302;
    //校园主页菜单
    public static final int ADAPTER_XY_MENU = 303;
    //水电油气主页菜单
    public static final int ADAPTER_SDYQ_MENU = 304;
    //党政机关主页菜单
    public static final int ADAPTER_DZJG_MENU = 305;
    //化工企业主页菜单
    public static final int ADAPTER_HGQY_MENU = 306;
    //重点单位主页菜单
    public static final int ADAPTER_ZDDW_MENU = 307;
    //历史监控录像列表
    public static final int ADAPTER_LSJK = 308;
    //乘车人员列表
    public static final int ADAPTER_CCRY = 309;
    //校车列表
    public static final int ADAPTER_XCLB = 310;


    /**
     * 数据存储相关
     */
    public static final String USER_INFO = "USER_INFO";//用户登录数据


    /**
     * 事件上报的事件类型
     */
    public static final String TYPE_MULT = "1";//散装汽油加油报告/收寄可疑物品上报/访客系统数据
    public static final String TYPE_AJSB = "2";//案件上报
    public static final String TYPE_MULT_KY = "3";//可疑情况上报/其它异常情况上报
    public static final String TYPE_ZB = "4";//值班情况上报
    public static final String TYPE_AQFF = "5";//安全防范情况上报


}
