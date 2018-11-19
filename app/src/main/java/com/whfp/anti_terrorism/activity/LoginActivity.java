package com.whfp.anti_terrorism.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.view.RxToast;
import com.whfp.anti_terrorism.R;
import com.whfp.anti_terrorism.basic.BaseCallback;
import com.whfp.anti_terrorism.basic.BasicActivity;
import com.whfp.anti_terrorism.bean.Constants;
import com.whfp.anti_terrorism.bean.LoginBean;
import com.whfp.anti_terrorism.utils.PreferencesUtils;
import com.whfp.anti_terrorism.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

/**
 * 登录
 * Created by wantao on 2018/6/15.
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends BasicActivity implements Validator.ValidationListener {

    //用户名
    @NotEmpty(message = "用户名不可为空")
    @ViewInject(R.id.et_username)
    private EditText et_username;

    //密码
    @NotEmpty(message = "密码不可为空")
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

    //返回来的登录信息
    private LoginBean loginBean;

    //表单验证工具对象
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.with(this).init();
    }

    @Override
    protected void init() {
        super.init();
        validator = new Validator(this);
        validator.setValidationListener(this);
        String username = PreferencesUtils.getUserName(context);
        String password = PreferencesUtils.getPassWord(context);
        if (!username.equals("") && !password.equals("")) {
            et_username.setText(username);
            et_password.setText(password);
            validator.validate();
        }
    }


    @Event(value = {R.id.tv_register, R.id.tv_forget_pwd, R.id.btn_login})
    private void OnClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register://注册账号
                break;
            case R.id.tv_forget_pwd://忘记密码
                break;
            case R.id.btn_login://登录
                validator.validate();
                break;
        }
    }

    /**
     * 连接玩过开始登录
     */
    private void httpLogin() {
        startRxLodingDialog("登录中，请稍后", true);
        httpUtils.doLogin(et_username.getText().toString(), et_password.getText().toString(), new BaseCallback(context, Constants.LOGIN) {
            @Override
            public void showDatas(Object obj) {
                super.showDatas(obj);
                stopRxLodingDialog();
                loginBean = (LoginBean) obj;
                if (loginBean != null && loginBean.getStatusCode() == 200) {
                    //保存用户登录信息
                    PreferencesUtils.setUserInfo(context, loginBean.getUser());
                    RxActivityTool.skipActivityAndFinish(context, MainActivity.class);
                    RxToast.success(loginBean.getMessage());
                } else {
                    if (loginBean != null) {
                        RxToast.info(loginBean.getMessage());
                    } else {
                        RxToast.error("登录失败，请检查网络或稍后重试");
                    }
                }
            }
        });
    }

    /**
     * 验证成功
     */
    @Override
    public void onValidationSucceeded() {
        httpLogin();
    }

    /**
     * 验证失败
     *
     * @param errors
     */
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            }
        }
    }
}
