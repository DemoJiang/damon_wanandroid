package com.damon.ui.login;


import android.text.TextUtils;
import android.util.Log;

import com.damon.base.AYBaseObserver;
import com.damon.base.presenter.BasePresenter;
import com.damon.core.DataManager;
import com.damon.core.bean.BaseResponse;
import com.damon.core.bean.LoginData;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: DamonJiang
 * @date: 2018/9/24 0024
 * @description:
 */
public class LoginPresenter extends BasePresenter<LoginContact.View> implements LoginContact.Presenter {
    private DataManager mDataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void initLoginData(String userName, String passworld) {
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passworld)) {
            mView.onLoginFailed("登录名或密码不能为空");
            return;
        }
        Disposable mDisposable = mDataManager.getLoginData(userName, passworld)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new AYBaseObserver<BaseResponse<LoginData>>(mView, "登录失败") {
                    @Override
                    public void onNext(BaseResponse<LoginData> value) {
                        if (mView == null) return;
                        Log.d("===========", "onNext==" + value.getData().getUsername());
                        if (value.getErrorCode() == 0) {
                            mView.onLoginSuccess(value.getData());
                        } else if (value.getErrorCode() == -1) {
                            mView.onLoginFailed(value.getErrorMsg());
                        } else {
                            mView.onLoginFailed("不知名的错误");
                        }
                    }
                });
        addSubscribe(mDisposable);
    }
}
