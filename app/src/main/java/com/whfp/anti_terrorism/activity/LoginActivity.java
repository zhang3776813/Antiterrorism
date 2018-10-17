package com.whfp.anti_terrorism.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.view.RxToast;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

/**
 * 登录
 * Created by wantao on 2018/6/15.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BasicActivity {

    //用户名
    @ViewInject(R.id.et_username)
    private EditText et_username;

    //密码
    @ViewInject(R.id.et_password)
    private EditText et_password;

    //注册
    @ViewInject(R.id.tv_register)
    private TextView tv_register;

    //忘记密码
    @ViewInject(R.id.tv_forget_pwd)
    private TextView tv_forget_pwd;

    //登录
    @ViewInject(R.id.btn_login)
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.with(this).init();
    }

    @Override
    protected void init() {
        super.init();
        et_username.setText("张三");
        et_password.setText("123456");
    }


    @Event(value = {R.id.tv_register, R.id.tv_forget_pwd, R.id.btn_login})
    private void OnClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register://注册账号
                break;
            case R.id.tv_forget_pwd://忘记密码
                break;
            case R.id.btn_login://登录
                if(et_username.getText().toString()==null||et_password.getText().toString()==null){
                    RxToast.error("用户名或密码不可为空");
                    return;
                }
                if(et_username.getText().toString().equals("")||et_password.getText().toString().equals("")){
                    RxToast.error("用户名或密码不可为空");
                    return;
                }
                if(!et_username.getText().toString().equals("张三")||!et_password.getText().toString().equals("123456")){
                    RxToast.error("用户名或密码错误");
                    return;
                }
                RxActivityTool.skipActivity(context, MainActivity.class);
                break;
        }
    }
}
