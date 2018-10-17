package com.whfp.anti_terrorism.utils;

import android.text.TextUtils;

/**
 * Text工具类
 * Created by Wantao on 2018/5/5.
 */

public class TextUtil {

    /**
     * 字符串非空判断
     * @param str
     * @return
     */
    public static String isEmpty(String str){
        if(TextUtils.isEmpty(str)){
            return "";
        }
        return str;
    }
}
