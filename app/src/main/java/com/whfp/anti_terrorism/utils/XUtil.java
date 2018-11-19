package com.whfp.anti_terrorism.utils;

import com.vondear.rxtools.RxLogTool;

import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XUtil {
    /**
     * 使用get方式访问
     *
     * @param <T>
     */
    public static <T> Cancelable Get(String url, Map<String, String> map, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(1000 * 20);
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }

    /**
     * 使用get方式访问     带Header验证
     *
     * @param <T>
     */
    public static <T> Cancelable Get(String url, String headerKey, String headerValue, Map<String, String> map, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(1000 * 20);
        params.setHeader(headerKey, headerValue);
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }


    public static <T> Cancelable GetHead(String url, Map<String, String> map, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(1000 * 20);
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }

    /**
     * 使用post访问
     *
     * @param <T>
     */
    public static <T> Cancelable Post(String url, Map<String, Object> map, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(1000 * 20);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }

    /**
     * 使用post访问   带Header验证
     *
     * @param <T>
     */
    public static <T> Cancelable Post(String url, String headerKey, String headerValue, Map<String, Object> map, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(1000 * 20);
        params.setHeader(headerKey, headerValue);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }


    /**
     * 文件上传
     *
     * @param <T>
     */
    public static <T> Cancelable UpLoadFile(String url, Map<String, Object> map, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(1000 * 20);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }

    /**
     * 文件上传   带Header验证
     *
     * @param <T>
     */
    public static <T> Cancelable UpLoadFile(String url, String headerKey, String headerValue, Map<String, Object> map, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(1000 * 20);
        params.setHeader(headerKey, headerValue);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }


    /**
     * 批量上传文件
     *
     * @param url            上传地址
     * @param map            上传的参数
     * @param parameter_name 上传的文件参数名
     * @param fileList       上传的文件集合
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> Cancelable UpLoadFiles(String url, Map<String, Object> map, String parameter_name, List<File> fileList, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(1000 * 20);
        List<KeyValue> list = new ArrayList<>();
        //添加参数
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                params.addParameter(entry.getKey(), entry.getValue());
                list.add(new KeyValue(entry.getKey(), entry.getValue()));
            }
        }
        //添加文件
        if (null != fileList) {
            for (int i = 0; i < fileList.size(); i++) {
                list.add(new KeyValue(parameter_name, fileList.get(i)));
            }
            RxLogTool.i("一共要上传" + fileList.size() + "张图片");
        }
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);
        params.setMultipart(true);
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }

    /**
     * 批量上传文件
     * @param url            上传地址
     * @param map            上传的参数
     * @param files          要上传的文件
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> Cancelable UpLoadFiles(String url, Map<String, Object> map, Map<String, List<File>> files, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(1000 * 20);
        List<KeyValue> list = new ArrayList<>();
        //添加参数
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new KeyValue(entry.getKey(), entry.getValue()));
            }
        }
        //添加文件
        if (null != files) {
            for (Map.Entry<String, List<File>> entry : files.entrySet()) {
                List<File> fileList = entry.getValue();
                for (int i = 0; i < fileList.size(); i++) {
                    list.add(new KeyValue(entry.getKey(), fileList.get(i)));
                }
                RxLogTool.i(entry.getKey() + "类别要上传" + fileList.size() + "张图片");
            }
        }
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);
        params.setMultipart(true);
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }

    /**
     * 文件下载
     *
     * @param <T>
     */
    public static <T> Cancelable DownLoadFile(String url, String filepath, Map<String, String> map, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(1000 * 20);
        //���öϵ���
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        if (null != map) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }

}