package com.damon.ui.login;

import com.damon.base.presenter.IPresenter;
import com.damon.base.view.IView;
import com.damon.core.bean.LoginData;

/**
 * @author: DamonJiang
 * @date: 2018/9/24 0024
 * @description:
 */
public interface LoginContact {

    interface View extends IView{
        String getUserName();                                          // 获取用户名
        String getPassword();                                          // 获取用户密码
        void onLoginSuccess(LoginData loginData);                      // 登录成功
        void onLoginFailed(String errorMsg);                           // 登录失败
    }
    interface Presenter extends IPresenter<View>{
        void initLoginData(String userName, String passWorld);         // 加载登录数据
    }
}
