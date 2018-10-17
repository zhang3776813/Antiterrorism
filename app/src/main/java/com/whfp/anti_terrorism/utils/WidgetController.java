package com.whfp.anti_terrorism.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.widget.RelativeLayout;

/**
 * 控件位置工具类
 * Created by 张明杨 on 2018-04-11-0011.
 */
public class WidgetController {
    /*
* 获取控件宽
*/
    public static int getWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredWidth());
    }

    /*
    * 获取控件高
    */
    public static int getHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        return (view.getMeasuredHeight());
    }

    /*
    * 设置控件所在的位置X，并且不改变宽高，
    * X为绝对位置，此时Y可能归0
    */
    public static void setLayoutX(View view, int x) {
        MarginLayoutParams margin = new MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x, margin.topMargin, x + margin.width, margin.bottomMargin);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }

    /*
    * 设置控件所在的位置Y，并且不改变宽高，
    * Y为绝对位置，此时X可能归0
    */
    public static void setLayoutY(View view, int y) {
        MarginLayoutParams margin = new MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(margin.leftMargin, y, margin.rightMargin, y + margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }

    /*
    * 设置控件所在的位置YY，并且不改变宽高，
    * XY为绝对位置
    */
    public static void setLayout(View view, int x, int y) {
        MarginLayoutParams margin = new MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x, y, x + margin.width, y + margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }

    /**
     * 获取屏幕宽度
     * @param activity
     * @return
     */
    public static int getScreenWidth(Activity activity){
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
//        int height = outMetrics.heightPixels;
        return width;
    }
}
