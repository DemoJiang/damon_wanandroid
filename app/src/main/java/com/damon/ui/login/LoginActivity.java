package com.damon.ui.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.damon.R;
import com.damon.base.activity.MVPBaseActivity;
import com.damon.core.bean.LoginData;
import com.damon.utils.CommonUtils;
import com.damon.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author: DamonJiang
 * @date: 2018/9/24 0024
 * @description:
 */
public class LoginActivity extends MVPBaseActivity<LoginPresenter> implements LoginContact.View {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.id_btn_login)
    Button mBtnLogin;
    @BindView(R.id.id_et_userName)
    EditText mUserNameView;
    @BindView(R.id.id_et_password)
    EditText mPasswordView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void inject() {
        activityComponent.inject(this);
        StatusBarUtil.immersive(this);
    }

    @OnClick(R.id.id_btn_login)
    public void onClick() {
        mPresenter.initLoginData(getUserName(), getPassword());
    }


    @Override
    public String getUserName() {
        return mUserNameView.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return mPasswordView.getText().toString().trim();
    }

    /**
     * 登录成功之后的操作，保存用户信息，跳转目标页面
     */
    @Override
    public void onLoginSuccess(LoginData loginData) {
        CommonUtils.showToast(this, loginData.getUsername()+" 欢迎您");
    }

    /**
     * 登录失败，作出友情提示
     */
    @Override
    public void onLoginFailed(String errorMsg) {
        CommonUtils.showToast(this, errorMsg);
    }
}
