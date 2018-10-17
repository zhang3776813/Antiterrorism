package com.whfp.anti_terrorism.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 案件上报
 * Created by wantao on 2018/6/14.
 */
@ContentView(R.layout.activity_ajsb)
public class AJSBActivity extends BasicActivity {

    @ViewInject(R.id.tv_case_type)
    private TextView tvType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.with(this).init();
    }

    @Override
    protected void init() {
        super.init();
        setTitleText("案件上报");
    }

    @Event(value = {R.id.ll_case_type, R.id.btn_submit})
    private void OnClick(View v) {
        switch (v.getId()) {
            case R.id.ll_case_type://选择案件类型
                break;
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
