package com.whfp.anti_terrorism.basic;

import android.content.Context;
import android.util.Log;

import com.whfp.anti_terrorism.bean.BasicBean;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.utils.ParserUtils;
import com.whfp.anti_terrorism.utils.To;

import org.king.activity.BaseActivity;


/**
 * 网络请求通用基本回调 适用于返回值为
 *
 * @author coky
 */
public class BaseCallback extends MyCallback<String> {

    protected Context context;
    // 处理数据类型
    protected int type;
    // 是否需要成功弹窗，默认不需要
    private boolean successToast;

    private ParserUtils parserUtils;

    private String code;// 用于家居回调加载命令
    private int codeType;// 指令的类型，查询，普通控制

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }

    public BaseCallback(Context context, int type) {
        this(context, type, false);
    }

    public BaseCallback(Context context, int type, boolean successToast) {
        this.context = context;
        this.type = type;
        this.successToast = successToast;
        parserUtils = new ParserUtils(context);
    }

    public BaseCallback(Context context) {
        this(context, Constants.DEFAULT);
    }

    @Override
    public void onCancelled(CancelledException arg0) {
        // TODO Auto-generated method stub

    }

    private boolean errorTo = false;

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {
        super.onError(ex, isOnCallback);
        if (context instanceof BasicActivity) {
            ((BasicActivity) context).stopDialog();
        }
//		if (!errorTo) {
//			To.showShort(context, "网络通讯异常..."+type);
//			errorTo = true;
//		}
        Log.e("onError", ex.toString());
    }

    @Override
    public void onFinished() {
        if (context instanceof BasicActivity) {
            ((BasicActivity) context).stopDialog();
        }
    }

    // onsuccess中的处理交给创建本对象的类去实现，封装的
    @Override
    public void onSuccess(String result) {
        //可能会有log过长的问题所以采取分段打印
        if (result.length() > 4000) {
            for (int i = 0; i < result.length(); i += 4000) {
                if (i + 4000 < result.length())
                    Log.i("onSuccess" + i, result.substring(i, i + 4000));
                else
                    Log.i("onSuccess" + i, result.substring(i, result.length()));
            }
        } else {
            Log.i("resinfo", result);
        }

        Object obj = parserUtils.parseAllByType(result, type);
        if (type == Constants.DEFAULT) {
            BasicBean<String> bean = (BasicBean<String>) obj;
            To.showShort(context, bean.getMsg());
            if (bean.getResult() == 1) {
                defSuccess();
                ((BaseActivity) context).finish();
            }
        }
        showDatas(obj);
        if (successToast) {
            System.err.println(".........successToast.." + successToast);
            parserUtils.ToastSuccess(result);
        }
    }

    /**
     * 主要数据剥离出来交给子类
     *
     * @param obj
     */
    public void showDatas(Object obj) {

    }

    public void defSuccess() {

    }
}