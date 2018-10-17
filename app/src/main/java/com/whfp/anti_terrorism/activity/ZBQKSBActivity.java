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
 * 值班情况上报
 * Created by wantao on 2018/6/14.
 */
@ContentView(R.layout.activity_zbqksb)
public class ZBQKSBActivity extends BasicActivity {

    //值班人员姓名
    @ViewInject(R.id.et_zbryxm)
    private EditText et_zbryxm;

    //值班人员职位
    @ViewInject(R.id.et_zbryzw)
    private EditText et_zbryzw;

    //上班按钮
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
        setTitleText("值班情况上报");
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
