package com.whfp.anti_terrorism.basic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.utils.HttpUtils;

import org.xutils.x;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基类
 *
 * @author coky
 */
public class BasicFragmentActivity extends FragmentActivity {

    protected HttpUtils httpUtils;
    protected Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 不允许横竖屏切换 */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        x.view().inject(this);
        context = getBaseContext();
        httpUtils = HttpUtils.getInstance();
        init();
    }

    /**
     * oncreate中的初始化方法
     */
    protected void init() {
    }

    // -----------------------------------------------------------判断文件是否存在-----------------------------------------------------------------------//
    // 判断文件是否存在
    public boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    // ----------------------------------------------------各种消息弹窗--------------------------------------------------------------------------//

    /**
     * 带点击事件的单按钮AlertDialog
     *
     * @param title           弹框标题
     * @param message         弹框消息内容
     * @param positiveButton  弹框单按钮文字
     * @param onClickListener 弹框按钮响应事件
     */
    public void showAlertDialog(String title, String message,
                                String positiveButton,
                                DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(this).setCancelable(false).setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton, onClickListener).show();
    }

    /**
     * 带点击事件的双按钮AlertDialog
     *
     * @param title                 弹框标题
     * @param message               弹框消息内容
     * @param positiveButton        弹框第一个按钮的文字
     * @param negativeButton        弹框第二个按钮的文字
     * @param positiveClickListener 弹框第一个按钮的单击事件
     * @param negativeClickListener 弹框第二个按钮的单击事件
     */
    public void showAlertDialog(String title, String message,
                                String positiveButton, String negativeButton,
                                DialogInterface.OnClickListener positiveClickListener,
                                DialogInterface.OnClickListener negativeClickListener) {
        new AlertDialog.Builder(this).setCancelable(false).setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton, positiveClickListener)
                .setNegativeButton(negativeButton, negativeClickListener)
                .show();

    }

    /**
     * 不带点击事件的消息弹出框
     *
     * @param title          弹框标题
     * @param message        弹框消息内容
     * @param positiveButton 弹框按钮文字
     */
    public void showAlertDialog(String title, String message,
                                String positiveButton) {
        new AlertDialog.Builder(this)
                // 设置按系统返回键的时候按钮弹窗不取消
                .setCancelable(false)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButton,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        }).show();
    }

    /**
     * 单选列表类型的弹出框
     *
     * @param cancelable     设置是否能让用户主动取消弹窗
     * @param title          弹窗标题
     * @param items          弹窗的列表数据源
     * @param selectListener 弹窗列表选择事件
     */
    public void showAlertDialog(boolean cancelable, String title,
                                String items[], DialogInterface.OnClickListener selectListener) {
        new AlertDialog.Builder(this)
                // 设置按系统返回键的时候按钮弹窗不取消
                .setCancelable(cancelable).setTitle(title)
                .setItems(items, selectListener).show();
    }

    // ----------------------------------------------------全局事件--------------------------------------------------------------------------//

    /**
     * 点击按钮后退事件（销毁当前ACtivity）
     *
     * @param v
     */
    public void backClick(View v) {
        finish();
    }

    /**
     * 设置顶部标题文字
     *
     * @param Text 标题文字
     */
    public void setTitleText(String Text) {
        TextView tv = (TextView) findViewById(R.id.tv_title);
        tv.setText(Text);
    }

    /**
     * 获取顶部标题文字
     *
     * @return
     */
    public String getTitleText() {
        String txt = "";
        TextView tv = (TextView) findViewById(R.id.tv_title);
        if (tv.getText().toString() != null) {
            txt = tv.getText().toString();
        }
        return txt;
    }

    // -----------------------------------------------------判断当前网络是否可用-----------------------------------------------------------------------------//
    /**
     * 判断当前网络是否可用
     *
     * @return
     */


    // -----------------------------------------------------------------各种提示方法------------------------------------------------------------------------------------------------------
    private ProgressDialog dialog;

    protected void startDialog(String title, String content) {
        if (dialog == null) {
            dialog = ProgressDialog.show(this, title, content, false);
            dialog.setCancelable(true);
        } else if (!dialog.isShowing()) {
            dialog = ProgressDialog.show(this, title, content, false);
            dialog.setCancelable(true);
        }
    }

    public void stopDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

// —————————————————————————————————————————————————————获取当前系统时间———————————————————————————————————————————————————————————————————————————————————————————————————//

    /**
     * 获取当前系统时间
     *
     * @return 当前系统时间
     */
    public static String getTime() {
        /* 获取当前系统时间 */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String time = sdf.format(curDate);
        return time;
    }


    /**
     * 获取当前系统时间
     *
     * @param type 自定义到哪，如：HH：mm
     * @return
     */
    public static String getTime(String type) {
        /* 获取当前系统时间 */
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String time = sdf.format(curDate);
        return time;
    }

    // ----------------------------------------------------------------------------通用方法-------------------------------------------------------------------------------------

    /**
     * 将字符串截取“,”转换成int数组
     *
     * @param datas 要转换的字符串
     * @return 转换后的数组
     */
    public int[] string2Int(String datas) {
        int[] datasInt = new int[18];
        String[] dataStrings = datas.split(",");
        for (int i = 0; i < dataStrings.length; i++) {
            datasInt[i] = Integer.parseInt(dataStrings[i]);
        }
        return datasInt;
    }

    /**
     * 将int数组通过“,”分割转换成字符串
     *
     * @param netAna 要转换的数组
     * @return 转换得到的字符串
     */
    public String ints2String(int[] netAna) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < netAna.length; i++) {
            sb.append(netAna[i]);
            if (i != netAna.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}