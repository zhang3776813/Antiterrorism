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
    //海康组织树列表
    public static final int HK_ZZ_LIST = 5;
    //海康监控点列表
    public static final int HK_JKD_LIST = 6;
    //海康HLS视频播放地址
    public static final int HK_HLS_PATH = 7;
    //获取校车web平台的key
    public static final int SCHOOL_KEY = 8;
    //获取校车的GPS信息
    public static final int SCHOOL_GPS = 9;
    //获取校车最后一条GPS信息
    public static final int SCHOOL_GPS_LAST = 10;


    /**
     * ListView数据初始化及选中事件分发
     */
    //主页轮播图片
    public static final int IMAGE_LIST = 101;
    //主页九宫格菜单
    public static final int GRID_LIST = 102;
    //事件上报菜单
    public static final int SJSB_LIST = 103;
    //监控主页菜单
    public static final int JKCK_MENU_LIST = 104;
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
    //事件上报类型
    public static final String SJSB_TYPE = "sjsb_type";
    //事件上报标题
    public static final String SJSB_TITLE = "sjsb_title";
    //校车操作类型
    public static final String SCHOOL_BUS_OPERATE_TYPE = "school_bus_operate_type";
    //校车设备对象
    public static final String SCHOOL_BUS_DEVICE = "school_bus_device";
    //组织或区域编号
    public static final String CONTROL_INDEX_CODE = "control_index_code";
    //监控点编号
    public static final String CAMERA_INDEX_CODE = "camera_index_code";
    //海康监控操作类型
    public static final String HK_OPERATE_TYPE = "hikvision_operate_type";
    //海康区域名称
    public static final String HK_CONTROL_NAME = "hikvision_control_name";
    //校车设备ID
    public static final String SCHOOL_DEVICE_ID = "school_id";
    //校车车牌号
    public static final String SCHOOL_BUS_NUMBER = "school_bus_number";
    //HLS播放地址
    public static final String HK_HLS ="hikvision_hls_path";


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
    //事件上报菜单
    public static final int ADAPTER_SJSB_MENU = 301;
    //监控查看菜单
    public static final int ADAPTER_JKCK_MENU = 302;
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
    //海康组织或区域列表
    public static final int ADAPTER_HK_CONTROL_LIST = 311;
    //海康监控点列表
    public static final int ADAPTER_HK_CAMERA_LIST = 312;


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

    /**
     * 校车的操作类型
     */
    public static final int SCHOOL_OPERATE_TYPE_JKCK = 401;//校车监控查看
    public static final int SCHOOL_OPERATE_TYPE_LSLX = 402;//校车监控历史录像
    public static final int SCHOOL_OPERATE_TYPE_YXLX = 403;//校车运行路线查看

    /**
     * 监控查看操作类型
     */
    public static final int HK_OPERATE_TYPE_JKCK = 501;//监控查看
    public static final int HK_OPERATE_TYPE_LSLX = 502;//监控历史录像

}
