package com.whfp.anti_terrorism.utils;

import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

public class XUtil {
    /**
     * 使用get方式访问
     * @param <T>
     */
    public static <T> Cancelable Get(String url, Map<String,String> map, CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        params.setConnectTimeout(1000*20);
        if(null!=map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }
    
    /**
     * 使用get方式访问     带Header验证
     * @param <T>
     */
    public static <T> Cancelable Get(String url, String headerKey, String headerValue, Map<String,String> map, CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        params.setConnectTimeout(1000*20);
        params.setHeader(headerKey, headerValue);
        if(null!=map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }

    
    public static <T> Cancelable GetHead(String url, Map<String,String> map, CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        params.setConnectTimeout(1000*20);
        if(null!=map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                params.addHeader(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }
    
    /**
     * 使用post访问
     * @param <T>
     */
    public static <T> Cancelable Post(String url, Map<String,Object> map, CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        params.setConnectTimeout(1000*20);
        if(null!=map){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;        
    }
    
    /**
     * 使用post访问   带Header验证
     * @param <T>
     */
    public static <T> Cancelable Post(String url, String headerKey, String headerValue, Map<String,Object> map, CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        params.setConnectTimeout(1000*20);
        params.setHeader(headerKey, headerValue);
        if(null!=map){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;        
    }


    
    
    /**
     * 文件上传
     * @param <T>
     */
    public static <T> Cancelable UpLoadFile(String url, Map<String,Object> map, CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        params.setConnectTimeout(1000*20);
        if(null!=map){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }
    
    /**
     * 文件上传   带Header验证
     * @param <T>
     */
    public static <T> Cancelable UpLoadFile(String url, String headerKey, String headerValue, Map<String,Object> map, CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        params.setConnectTimeout(1000*20);
        params.setHeader(headerKey, headerValue);
        if(null!=map){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }

    /**
     * 文件下载
     * @param <T>
     */
    public static <T> Cancelable DownLoadFile(String url, String filepath, Map<String,String> map, CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        params.setConnectTimeout(1000*20);
        //���öϵ���
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        if(null!=map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }
       
}