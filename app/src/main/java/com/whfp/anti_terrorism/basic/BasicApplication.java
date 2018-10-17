package com.whfp.anti_terrorism.basic;

import android.app.Application;

import com.vondear.rxtools.RxTool;

import org.xutils.x;

public class BasicApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 初始化Xutils
         */
        x.Ext.init(this);
        x.Ext.setDebug(false); // 是否输出debug日志, 开启debug会影响性能.
        /**
         * 初始化RxTools
         */
        RxTool.init(this);
    }

}
