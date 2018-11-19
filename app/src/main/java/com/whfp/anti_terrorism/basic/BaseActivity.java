package com.whfp.anti_terrorism.basic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogLoading;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.utils.HttpUtils;
import com.whfp.anti_terrorism.utils.To;

import org.king.utils.DialogUtils;
import org.king.utils.LogUtils;
import org.xutils.x;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 张明杨 on 2018-11-01-0001.
 */

public class BaseActivity extends AppCompatActivity {
    protected Context context = this;
    protected Handler handler = new Handler();
    protected ProgressDialog progressDialog = null;
    protected boolean isFinish = false;
    protected boolean isRegister = false;
    protected static boolean isShowTitle = false;
    private static final String ACTION_EXIT = "jenly_action_exit_";

    protected HttpUtils httpUtils;

    private ProgressDialog dialog;

    private RxDialogLoading rxDialogLoading;


    protected View.OnClickListener onBackListener = new View.OnClickListener() {
        public void onClick(View v) {
            BaseActivity.this.finish();
        }
    };
    private BroadcastReceiver exitReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            BaseActivity.this.isFinish = true;
            BaseActivity.this.exit();
        }
    };

    public BaseActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isShowTitle) {
            this.requestWindowFeature(1);
        }
        getSupportActionBar().hide();
          /* 不允许横竖屏切换 */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        x.view().inject(this);
        httpUtils = HttpUtils.getInstance();
        init();

    }

    /**
     * oncreate中的初始化方法
     */
    protected void init() {
    }

    protected void showToast(String message) {
        DialogUtils.showToast(this.context, message, this.handler);
    }

    protected void showToast(String message, int duration) {
        DialogUtils.showToast(this.context, message, duration, this.handler);
    }

    protected void showProgressDialog() {
        this.progressDialog = DialogUtils.showProgressDialog(this.context);
    }

    protected void showProgressDialog(String message) {
        this.progressDialog = DialogUtils.showProgressDialog(this.context, message);
    }

    protected void dismissProgressDialog() {
        DialogUtils.dismissProgressDialog(this.progressDialog, this.handler);
    }

    protected void logI(String message) {
        LogUtils.logI(message);
    }

    protected void logV(String message) {
        LogUtils.logV(message);
    }

    protected void logD(String message) {
        LogUtils.logD(message);
    }

    protected void logW(String message) {
        LogUtils.logW(message);
    }

    protected void logE(String message) {
        LogUtils.logE(message);
    }

    protected void print(String message) {
        LogUtils.print(message);
    }

    protected void println(String message) {
        LogUtils.println(message);
    }

    protected void startActivity(Class<?> clazz) {
        this.startActivity(new Intent(this.context, clazz));
    }

    protected void startActivity(Class<?> clazz, int flags) {
        Intent intent = new Intent(this.context, clazz);
        intent.setFlags(flags);
        this.startActivity(intent);
    }

    protected void startActivityFinish(Class<?> clazz) {
        super.finish();
        this.startActivity(new Intent(this.context, clazz));
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    public void finish() {
        super.finish();
    }

    protected void exit() {
        Intent intent = new Intent("jenly_action_exit_");
        this.sendBroadcast(intent);
        this.finish();
    }

    protected void exitDialog() {
        DialogUtils.showAlertDialog(this.context, "确定退出吗？", "确定", "取消", this.handler, new android.content.DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case -1:
                        BaseActivity.this.exit();
                    default:
                }
            }
        });
    }

    protected void onResume() {
        super.onResume();

        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction("jenly_action_exit_");
            this.registerReceiver(this.exitReceiver, filter);
            this.isRegister = true;
        } catch (Exception var2) {
            this.logE("异常：" + var2.getMessage());
        }

    }

    protected void onStop() {
        super.onStop();
        this.unregisterReceiver(this.exitReceiver);
        this.isRegister = false;
    }

    protected void onDestroy() {
        super.onDestroy();
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
    public void showAlertDialog(String title, String message, String positiveButton,
                                DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context).setCancelable(false).setTitle(title).setMessage(message)
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
    public void showAlertDialog(String title, String message, String positiveButton, String negativeButton,
                                DialogInterface.OnClickListener positiveClickListener,
                                DialogInterface.OnClickListener negativeClickListener) {
        new AlertDialog.Builder(context).setCancelable(false).setTitle(title).setMessage(message)
                .setPositiveButton(positiveButton, positiveClickListener)
                .setNegativeButton(negativeButton, negativeClickListener).show();

    }

    /**
     * 不带点击事件的消息弹出框
     *
     * @param title          弹框标题
     * @param message        弹框消息内容
     * @param positiveButton 弹框按钮文字
     */
    public void showAlertDialog(String title, String message, String positiveButton) {
        new AlertDialog.Builder(context)
                // 设置按系统返回键的时候按钮弹窗不取消
                .setCancelable(false).setTitle(title).setMessage(message)
                .setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
    public void showAlertDialog(boolean cancelable, String title, String items[],
                                DialogInterface.OnClickListener selectListener) {
        new AlertDialog.Builder(context)
                // 设置按系统返回键的时候按钮弹窗不取消
                .setCancelable(cancelable).setTitle(title).setItems(items, selectListener).show();
    }

    /**
     * 普通的加载进度条
     */
    protected void startRxLodingDialog() {
        if (rxDialogLoading == null) {
            rxDialogLoading = new RxDialogLoading(context);
            rxDialogLoading.setLoadingText("加载中，请稍后");
            rxDialogLoading.setCancelable(false);
            rxDialogLoading.show();
        } else if (!rxDialogLoading.isShowing()) {
            rxDialogLoading.setLoadingText("加载中，请稍后");
            rxDialogLoading.setCancelable(false);
            rxDialogLoading.show();
        }
    }

    /**
     * 超时自动取消的加载进度条
     *
     * @param content   内容
     * @param cancelble 是否可手动取消
     */
    protected void startRxLodingDialog(String content, boolean cancelble) {
        if (rxDialogLoading == null) {
            rxDialogLoading = new RxDialogLoading(context);
            rxDialogLoading.setLoadingText(content);
            rxDialogLoading.setCancelable(cancelble);
            rxDialogLoading.show();
        } else if (!rxDialogLoading.isShowing()) {
            rxDialogLoading.setLoadingText(content);
            rxDialogLoading.setCancelable(cancelble);
            rxDialogLoading.show();
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (rxDialogLoading != null) {
                    if (rxDialogLoading.isShowing()) {
                        RxToast.error("请求超时,请检查网络！");
                        rxDialogLoading.dismiss();
                    }
                }
            }
        }, 10000);
    }


    protected void stopRxLodingDialog() {
        if (rxDialogLoading != null) {
            rxDialogLoading.dismiss();
        }
    }

    /**
     * 不带超时自动取消的旋转圆圈进度条
     *
     * @param title
     * @param content
     */
    protected void startDialogd(String title, String content) {
        if (dialog == null) {
            dialog = ProgressDialog.show(this, title, content, false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                        dialog.dismiss();
                        finish();
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            dialog.setCancelable(false);
        } else if (!dialog.isShowing()) {
            dialog = ProgressDialog.show(this, title, content, false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                        dialog.dismiss();
                        finish();
                        return true;
                    } else {
                        return false;
                    }
                }
            });
            dialog.setCancelable(false);
        }
    }

    /**
     * 可设置是否能手动取消进度条
     *
     * @param title
     * @param content
     * @param cancelble
     */
    protected void startDialog(String title, String content, boolean cancelble) {
        if (dialog == null) {
            dialog = ProgressDialog.show(this, title, content, false);
            dialog.setCancelable(cancelble);
        } else if (!dialog.isShowing()) {
            dialog = ProgressDialog.show(this, title, content, false);
            dialog.setCancelable(cancelble);
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (dialog != null) {
                    if (dialog.isShowing()) {
                        To.showShort(context, "超时..");
                        dialog.dismiss();
                    }
                }
            }
        }, 10000);
    }

    protected void startDialog(String title, String content) {
        if (dialog == null) {
            dialog = ProgressDialog.show(this, title, content, false);
            dialog.setCancelable(true);
        } else if (!dialog.isShowing()) {
            dialog = ProgressDialog.show(this, title, content, false);
            dialog.setCancelable(true);
        }
    }

    protected void stopDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }


    // ---------------------------------------------------------------设置列表数据为空时的提示内容-------------------------------------------------------------------------

    /**
     * 设置列表数据为空时展示的提示内容
     *
     * @param v         提示内容View
     * @param lv_jxz_hd 需要提示的列表控件
     * @param width     提示内容宽度
     * @param height    提示内容高度
     * @param prompText 提示文字
     * @param listener  提示点击事件
     */
    public void setEmptyView(View v, ListView lv_jxz_hd, int width, int height, String prompText,
                             View.OnClickListener listener) {
        if (v == null) {
            logI("tam");
        }
        if (lv_jxz_hd == null) {
            logE("为空了");
        }
        v.setLayoutParams(new ViewGroup.LayoutParams(width, height));// tv_prompt
        TextView tv_promp = (TextView) v.findViewById(R.id.tv_prompt);
        tv_promp.setText(prompText);
        v.setVisibility(View.GONE);
        ((ViewGroup) lv_jxz_hd.getParent()).addView(v);
        lv_jxz_hd.setEmptyView(v);
        v.setOnClickListener(listener);
    }

//	public void setEmptyView(View v, PullToRefreshListView lv_jxz_hd, int width, int height, String prompText,
//                             OnClickListener listener) {
//		v.setLayoutParams(new1 LayoutParams(width, height));// tv_prompt
//		TextView tv_promp = (TextView) v.findViewById(R.id.tv_prompt);
//		tv_promp.setText(prompText);
//		v.setVisibility(View.GONE);
//		((ViewGroup) lv_jxz_hd.getParent()).addView(v);
//		lv_jxz_hd.setEmptyView(v);
//		v.setOnClickListener(listener);
//	}

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


    // —————————————————————————————————————————————————————获取当前系统时间———————————————————————————————————————————————————————————————————————————————————————————————————//

    /**
     * 获取当前系统时间
     *
     * @return 当前系统时间
     */
    public static String getTime() {
        /* 获取当前系统时间 */
        return getTime("yyyy-MM-dd HH:mm:ss");

    }

    public static String getTime(String type) {
        /* 获取当前系统时间 */
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String time = sdf.format(curDate);
        return time;
    }

    /**
     * 获取当前系统时间(到分)
     *
     * @return 当前系统时间
     */
    public static String getTimeToMinute() {
        /* 获取当前系统时间 */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmm");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String time = sdf.format(curDate);
        return time;
    }

    // -----------------------------------------------------------------版本更新相关------------------------------------------------------------------------------------------------------

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
