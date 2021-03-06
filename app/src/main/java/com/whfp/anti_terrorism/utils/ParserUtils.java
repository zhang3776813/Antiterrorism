package com.whfp.anti_terrorism.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.whfp.anti_terrorism.bean.BasicBean;
import com.whfp.anti_terrorism.bean.CameraBean;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.bean.ControlUnitBean;
import com.whfp.anti_terrorism.bean.HlsBean;
import com.whfp.anti_terrorism.bean.LoginBean;
import com.whfp.anti_terrorism.bean.SJSBBean;
import com.whfp.anti_terrorism.bean.SchoolBusGpsBean;
import com.whfp.anti_terrorism.bean.SchoolBusListBean;
import com.whfp.anti_terrorism.bean.SchoolKeyBean;

import java.lang.reflect.Type;

/**
 * 接口对接类，解析json的最底层。 json ——> bean
 *
 * @author coky
 */
public class ParserUtils {

    private Context context;

    public ParserUtils(Context context) {
        this.context = context;
    }

    // 根据不同的类型来调用解析方法
    public Object parseAllByType(String result, int type) {
        Object obj = null;
        // 暂时通用，根据接口来剥离bean

        // ReceiveBean bean = null;
        switch (type) {
            // 登陆
            case Constants.LOGIN://登录解析
                obj = parserLogin(result);
                break;
            case Constants.SCHOOL_BUS_LIST://校车列表解析
                obj = parserSchoolBusList(result);
                break;
            case Constants.SJSB://通用事件上报解析
                obj = parserSJSB(result);
                break;
            case Constants.HK_ZZ_LIST://海康组织树列表解析
                obj = parserHKControlList(result);
                break;
            case Constants.HK_JKD_LIST://海康监控点列表解析
                obj = parserHKCameraList(result);
                break;
            case Constants.HK_HLS_PATH://海康HLS地址
                obj = parserHKHlsPath(result);
                break;
            case Constants.SCHOOL_KEY://获取校车WEb平台的KEY
                obj = parserSchoolKey(result);
                break;
            case Constants.SCHOOL_GPS://获取校车GPS信息
                obj = parserSchoolBusGps(result);
                break;

            case Constants.DEFAULT://默认解析
                obj = parserBasic(result);
                break;

        }
        return obj;
    }


    /**
     * 获取校车的GPS数据解析
     *
     * @param result
     * @return
     */
    private Object parserSchoolBusGps(String result) {
        SchoolBusGpsBean bean = new Gson().fromJson(result, SchoolBusGpsBean.class);
        return bean;
    }

    /**
     * 获取校车WEb平台的Key解析
     *
     * @param result
     * @return
     */
    private Object parserSchoolKey(String result) {
        SchoolKeyBean bean = new Gson().fromJson(result, SchoolKeyBean.class);
        return bean;
    }


    /**
     * 海康HLS地址获取解析
     *
     * @param result
     * @return
     */
    private Object parserHKHlsPath(String result) {
        HlsBean bean = new Gson().fromJson(result, HlsBean.class);
        return bean;
    }


    /**
     * 登录返回解析
     *
     * @param result
     * @return
     */
    private Object parserLogin(String result) {
        LoginBean bean = new Gson().fromJson(result, LoginBean.class);
        if (bean != null) {
            return bean;
        }
        return null;
    }

    /**
     * 校车列表解析
     *
     * @param result
     * @return
     */
    private Object parserSchoolBusList(String result) {
        SchoolBusListBean bean = new Gson().fromJson(result, SchoolBusListBean.class);
        if (bean != null) {
            return bean;
        }
        return null;
    }

    /**
     * 事件上报解析
     *
     * @param result
     * @return
     */
    private Object parserSJSB(String result) {
        SJSBBean bean = new Gson().fromJson(result, SJSBBean.class);
        if (bean != null) {
            return bean;
        }
        return null;
    }

    /**
     * 海康组织树列表解析
     *
     * @param result
     * @return
     */
    private Object parserHKControlList(String result) {
        ControlUnitBean controlUnitBean = new Gson().fromJson(result, ControlUnitBean.class);
        return controlUnitBean;
    }

    /**
     * 海康监控点列表解析
     *
     * @param result
     * @return
     */
    private Object parserHKCameraList(String result) {
        CameraBean cameraBean = new Gson().fromJson(result, CameraBean.class);
        return cameraBean;
    }
    // ------------------------------------各类分类解析方法---------------------------------------

    private BasicBean parser(String json, Type type) {
        BasicBean bean = new Gson().fromJson(json, type);
        if (checkBean(bean)) {
            return bean;
        }
        return null;
    }

    // 解析接受到的消息类型
    private <T> BasicBean<T> parserBasic(String json) {
        return new Gson().fromJson(json, BasicBean.class);
    }

    // 检验消息Bean的成功还是失败
    private boolean checkBean(BasicBean bean) {
        if (bean.getResult() != Constants.SUCCESS) {
            To.showShort(context, "错误代码：" + bean.getResult() + "，错误消息：" + bean.getMsg());
            return false;
        }
        return true;
    }

    // 获取成功消息
    public void ToastSuccess(String result) {
        BasicBean bean = parserBasic(result);
        if (bean.getResult() == Constants.SUCCESS) {
            To.showShort(context, bean.getMsg());
        }
    }

}
