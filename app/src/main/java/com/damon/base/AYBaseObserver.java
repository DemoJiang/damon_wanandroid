package com.damon.base;


import android.util.Log;


import com.damon.base.view.IView;

import io.reactivex.observers.ResourceObserver;

/**
 * @author: DamonJiang
 * @date: 2018/9/25 0025
 * @description:  DisposableObserver 的封装
 */
public abstract class AYBaseObserver<T> extends ResourceObserver<T>{
    private IView mView;
    private String mErrorMsg;


    protected AYBaseObserver(IView view, String errorMsg) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }


    @Override
    public void onComplete() {
        Log.d("===========", "onComplete");
    }

    @Override
    public void onError(Throwable e) {
        Log.d("===========", "onError");
        if (mView == null) {
            return;
        }
       mView.onShowError(mErrorMsg);
    }
}
