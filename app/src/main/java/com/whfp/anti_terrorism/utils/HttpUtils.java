package com.whfp.anti_terrorism.utils;


import com.luck.picture.lib.entity.LocalMedia;
import com.vondear.rxtools.RxLogTool;
import com.whfp.anti_terrorism.basic.MyCallback;
import com.whfp.anti_terrorism.basic.MyCallback2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 网络请求工具类说明： GET：不带头的用doNetGet， 带头的，拼接url后，用doGetHead POST：常规用法。
 *
 * @author coky
 */
public class HttpUtils {
    //本地测试地址(有线)
//    private final static String baseUrl = "http://192.168.1.252:8080/sharedMachine/";

    //本地测试地址（无线）
//    private final static String baseUrl = "http://192.168.1.250:8080/sharedMachine/";

    //校车Web接口地址
    private final static String baseUrlBus = "http://119.96.239.107:12056/api/v1/basic/";

    //正式地址
  private final static String baseUrl = "http://219.138.150.225:8081/ablt/interface/";

    //飞哥本地地址
//    private final static String baseUrl = "http://192.168.1.49:8080/ablt/interface/";

    //图片的地址头
//    private final static String basePicUrl = "http://192.168.1.252:8080/sharedMachine/";

    //海康视频平台登录参数
    public static final String host = "223.75.122.15:1443";
    public static final String appkey = "23749488";
    public static final String appsecet = "rBhRv6Pva2J7aylYlwxn";

    // 各个url地址
    private static final String LOGIN = baseUrl + "userLogin.do";// 登陆
    private static final String EVENT_REPORT = baseUrl + "eventReport.do";//通用的事件上报
    private static final String SCHOOL_LOST = baseUrl + "schoolLost.do";//校园走失上报
    private static final String GASOLINE_EVENT = baseUrl + "gasolineEvent.do";//散装汽油加油报告
    //校车Web相关
    private static final String KEY = baseUrlBus + "key";//校车Web获取认证Key
    private static final String GPS_DETAIL = baseUrlBus + "gps/detail";//获取GPS明细信息

//    public String getPicUrl(String addr) {
//        return basePicUrl + addr;
//    }

    private HttpUtils() {
    }

    private static HttpUtils httpUtils;

    public static HttpUtils getInstance() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

    /**
     * POST方式访问网络
     *
     * @param url      访问接口
     * @param map      传入的参数
     * @param callback 回调接口
     */
    private void doNetPost(String url, Map<String, Object> map, MyCallback<String> callback) {
        XUtil.Post(url, map, callback);
    }

    /**
     * POST方式访问网络 带Header验证
     *
     * @param url      访问接口
     * @param map      传入的参数
     * @param callback 回调接口
     */
    private void doNetPost(String url, String headerValue, Map<String, Object> map, MyCallback<String> callback) {
        XUtil.Post(url, "Authorization", headerValue, map, callback);
    }

    /**
     * GET方式访问网络
     *
     * @param url      访问接口
     * @param map      传入的参数
     * @param callback 回调接口
     */
    private void doNetGet(String url, Map<String, String> map, MyCallback<String> callback) {
        XUtil.Get(url, map, callback);
    }

    /**
     * Get方式访问网络 带Header验证
     *
     * @param url
     * @param headerValue
     * @param map
     * @param callback
     */
    private void doNetGet(String url, String headerValue, Map<String, String> map, MyCallback<String> callback) {
        XUtil.Get(url, "Authorization", headerValue, map, callback);
    }

    /**
     * 访问网络 下载文件
     *
     * @param url      访问接口
     * @param filepath 下载到本地的文件保存路径
     * @param map      传入的参数
     * @param callback 回调接口
     */
    private void doDownLoad(String url, String filepath, Map<String, String> map, MyCallback2<File> callback) {
        XUtil.DownLoadFile(url, filepath, map, callback);
    }

    /**
     * 访问网络 上传文件
     *
     * @param url      访问接口
     * @param map      传入的参数
     * @param callback 回调接口
     */
    private void doUpLoadFile(String url, Map<String, Object> map, MyCallback<String> callback) {
        XUtil.UpLoadFile(url, map, callback);
    }

    /**
     * 访问网络 上传文件 带Header验证
     *
     * @param url         访问接口
     * @param headerValue Header的值
     * @param map         传入的参数
     * @param callback    回调接口
     */
    private void doUpLoadFile(String url, String headerValue, Map<String, Object> map, MyCallback<String> callback) {
        XUtil.UpLoadFile(url, "Authorization", headerValue, map, callback);
    }


    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param callback
     */
    public void doLogin(String username, String password, MyCallback<String> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        RxLogTool.i("登录的地址：" + LOGIN + map.toString());
        XUtil.Post(LOGIN, map, callback);
    }

    /**
     * 校车Web获取认证Key
     *
     * @param callback 回调事件
     */
    public void doKey(MyCallback<String> callback) {
        Map<String, String> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "123456");
        RxLogTool.i("校车Web获取认证Key的地址：" + KEY + map.toString());
        doNetGet(KEY, map, callback);
    }


    /**
     * 获取校车GPS信息
     *
     * @param key
     * @param terid
     * @param starttime
     * @param endtime
     * @param callback
     */
    public void doGPSDetail(String key, String terid, String starttime, String endtime, MyCallback<String> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("terid", terid);
        map.put("starttime", starttime);
        map.put("endtime", endtime);
        RxLogTool.i("获取校车GPS数据的地址是：" + GPS_DETAIL + map.toString());
        doNetPost(GPS_DETAIL, map, callback);
    }


    /**
     * 通用的事件上报
     *
     * @param eventType  事件类型  说明：1：散装汽油加油报告/收寄可疑物品上报/访客系统数据   2：案件上报  3：可疑情况上报/其它异常情况上报  4：值班情况上报  5：安全防范情况上报
     * @param createUser 用户ID
     * @param zw         上报人职位
     * @param phone      上报人电话
     * @param address    上报地址
     * @param details    事件说明
     * @param files      上报的图片文件
     * @param callback
     */
    public void doEVENT_REPORT(String eventType, String createUser, String zw, String phone,
                               String address, String details, List<LocalMedia> files,
                               MyCallback<String> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("eventType", eventType);
        map.put("createUser", createUser);
        map.put("zw", zw);
        map.put("phone", phone);
        map.put("address", address);
        map.put("details", details);
        RxLogTool.i("通用事件上报的请求地址：" + EVENT_REPORT + map.toString());

        List<File> fileList = null;
        if (files != null && files.size() > 0) {
            fileList = new ArrayList<>();
            for (int i = 0; i < files.size(); i++) {
                fileList.add(new File(files.get(i).getCompressPath()));
            }
        }
        XUtil.UpLoadFiles(EVENT_REPORT, map, "files", fileList, callback);
    }

    /**
     * 校园走失上报
     *
     * @param createUser   用户ID
     * @param zw           用户职位
     * @param lostUserinfo 走失人信息描述
     * @param lostUsername 走失人姓名
     * @param lostZw       走失人身份
     * @param lostTime     走失时间
     * @param phone        上报人电话
     * @param address      走失地址
     * @param files        走失人图片信息
     * @param callback
     */
    public void doSchoolLost(String createUser, String zw, String lostUserinfo, String lostUsername, String lostZw, String lostTime, String phone, String address, List<LocalMedia> files,
                             MyCallback<String> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("createUser", createUser);
        map.put("zw", zw);
        map.put("lostUserinfo", lostUserinfo);
        map.put("lostUsername", lostUsername);
        map.put("lostZw", lostZw);
        map.put("lostTime", lostTime);
        map.put("phone", phone);
        map.put("address", address);
        RxLogTool.i("校园走失上报地址：" + SCHOOL_LOST + map.toString());

        List<File> fileList = null;
        if (files != null && files.size() > 0) {
            fileList = new ArrayList<>();
            for (int i = 0; i < files.size(); i++) {
                fileList.add(new File(files.get(i).getCompressPath()));
            }
        }
        XUtil.UpLoadFiles(SCHOOL_LOST, map, "files", fileList, callback);
    }

    /**
     * 散装汽油加油报告
     *
     * @param createUser 用户ID
     * @param ml         加油量
     * @param purpose    加油用途
     * @param pcszm      派出所证明照片
     * @param cardMain   身份证正面照片
     * @param cardBack   身份证反面照片
     * @param sceneImage 现场照片（最多6张）
     * @param callback   回调事件
     */
    public void doGasolineEvent(String createUser, String ml, String purpose, List<LocalMedia> pcszm, List<LocalMedia> cardMain, List<LocalMedia> cardBack,
                                List<LocalMedia> sceneImage, MyCallback<String> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("createUser", createUser);
        map.put("ml", ml);
        map.put("purpose", purpose);
        RxLogTool.i("散装汽油加油报告地址：" + GASOLINE_EVENT + map.toString());

        //创建装载文件的容器
        Map<String, List<File>> fileMap = new HashMap<>();
        List<File> pcszm_list = new ArrayList<>();
        List<File> cardMain_list = new ArrayList<>();
        List<File> cardBack_list = new ArrayList<>();
        List<File> sceneImage_list = new ArrayList<>();
        //将文件添加到对应的容器
        pcszm_list.add(new File(pcszm.get(0).getCompressPath()));
        cardMain_list.add(new File(cardMain.get(0).getCompressPath()));
        cardBack_list.add(new File(cardBack.get(0).getCompressPath()));
        for (LocalMedia localMedia : sceneImage) {
            sceneImage_list.add(new File(localMedia.getCompressPath()));
        }
        //将添加了文件的容器添加到总容器
        fileMap.put("pcszm", pcszm_list);
        fileMap.put("cardMain", cardMain_list);
        fileMap.put("cardBack", cardBack_list);
        fileMap.put("sceneImage", sceneImage_list);
        //开始上传
        XUtil.UpLoadFiles(GASOLINE_EVENT, map, fileMap, callback);
    }


    /**
     * 带header验证演示
     *
     * @param context
     * @param deal_id  交易ID
     * @param act_type 交易类型,100:立即还款，-100：延期还款
     * @param act_date 操作日期（还款日，延期至日期），eg:yyyy-MM-dd
     * @param req_msg  申请交易附加信息
     * @param callback
     */
//    public void doReqDealAct(Context context, int deal_id, int act_type, String act_date, String req_msg, MyCallback<String> callback) {
//        Log.i("提交交易状态申请的地址是：", REQ_DEAL_ACT);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("deal_id", deal_id);
//        map.put("act_type", act_type);
//        map.put("act_date", act_date);
//        map.put("req_msg", req_msg);
//        doNetPost(REQ_DEAL_ACT, getToken(context), map, callback);
//    }
}
