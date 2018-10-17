package com.whfp.anti_terrorism.utils;


import com.vondear.rxtools.RxLogTool;
import com.whfp.anti_terrorism.basic.MyCallback;
import com.whfp.anti_terrorism.basic.MyCallback2;

import org.xutils.http.body.MultipartBody;

import java.io.File;
import java.util.HashMap;
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

    //正式地址
    private final static String baseUrl = "http://219.138.150.225:8081/ablt/interface/";

    //图片的地址头
    private final static String basePicUrl = "http://192.168.1.252:8080/sharedMachine/";


    // 各个url地址
    private static final String LOGIN = baseUrl + "sy_login/interface_userInfo.do";// 登陆
    private static final String EVENT_REPORT = baseUrl + "eventReport.do";//通用的事件上报


    public String getPicUrl(String addr) {
        return basePicUrl + addr;
    }

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
                               String address, String details, MultipartBody[] files,
                               MyCallback<String> callback) {
        Map<String, Object> map = new HashMap<>();
        map.put("eventType", eventType);
        map.put("createUser", createUser);
        map.put("zw", zw);
        map.put("phone", phone);
        map.put("address", address);
        map.put("details", details);
        map.put("files", files);
        RxLogTool.i("通用事件上报的请求地址：" + EVENT_REPORT + map.toString());
        doNetPost(EVENT_REPORT, map, callback);
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
