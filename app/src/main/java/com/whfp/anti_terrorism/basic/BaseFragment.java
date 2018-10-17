package com.whfp.anti_terrorism.basic;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vondear.rxtools.view.RxToast;
import com.vondear.rxtools.view.dialog.RxDialogLoading;
import com.whfp.anti_terrorism.utils.HttpUtils;

import org.xutils.x;

/**
 * Fragment基类
 */
public class BaseFragment extends Fragment {

    private boolean injected = false;
    protected Context mContext;
    protected HttpUtils httpUtils;
    protected RxDialogLoading rxDialogLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
        mContext = getContext();
        httpUtils = HttpUtils.getInstance();
        init();
    }

    /**
     * onviewCreated 中进行 初始化操作
     */
    protected void init() {
    }

    /**
     * 普通的加载进度条
     */
    protected void startRxLodingDialog(){
        if(rxDialogLoading==null){
            rxDialogLoading = new RxDialogLoading(mContext);
            rxDialogLoading.setLoadingText("加载中，请稍后");
            rxDialogLoading.setCancelable(false);
            rxDialogLoading.show();
        }else if(!rxDialogLoading.isShowing()){
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
            rxDialogLoading = new RxDialogLoading(mContext);
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


    protected void stopRxLodingDialog(){
        if(rxDialogLoading!=null){
            rxDialogLoading.dismiss();
        }
    }
}
