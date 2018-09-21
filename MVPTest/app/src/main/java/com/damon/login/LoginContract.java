package com.damon.login;

import com.damon.base.AbstractPresenter;
import com.damon.base.AbstractView;

/**
 * @author: DamonJiang
 * @date: 2018/9/21 0021
 * @description:
 */
public interface LoginContract {

    interface View extends AbstractView{
        void initView();
    }

    interface Presenter extends AbstractPresenter<View>{
        void initLoginData();
    }

}
