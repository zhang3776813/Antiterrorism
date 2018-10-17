package com.whfp.anti_terrorism.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 安全防范情况上报
 * Created by wantao on 2018/6/14.
 */
@ContentView(R.layout.activity_aqffsb)
public class AQFFSBActivity extends BasicActivity {

    //消防情况
    @ViewInject(R.id.et_desc_xf)
    private EditText et_desc_xf;

    //技防情况
    @ViewInject(R.id.et_desc_jf)
    private EditText et_desc_jf;

    //人防情况
    @ViewInject(R.id.et_desc_rf)
    private EditText et_desc_rf;

    //上报按钮
    @ViewInject(R.id.btn_submit)
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.with(this).init();
    }

    @Override
    protected void init() {
        super.init();
        setTitleText("安全防范情况上报");
    }

    @Event(value = R.id.btn_submit)
    private void OnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit://上报
                showAlertDialog("提示", "上报成功", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                });
                break;
        }
    }
}
