package com.damon.ui.home;

import android.util.Log;

import com.damon.base.AYBaseObserver;
import com.damon.base.presenter.BasePresenter;
import com.damon.core.DataManager;
import com.damon.core.bean.ArticleListData;
import com.damon.core.bean.BannerData;
import com.damon.core.bean.BaseResponse;
import com.google.gson.Gson;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: DamonJiang
 * @date: 2018/10/9 0009
 * @description:
 */
public class HomePresenter extends BasePresenter<HomeContact.View> implements HomeContact.Presenter {
    private DataManager mDataManager;

    @Inject
    public HomePresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void initBannerData() {
        addSubscribe(mDataManager.getBannerData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new AYBaseObserver<BaseResponse<List<BannerData>>>(mView, "轮播图获取失败") {
                    @Override
                    public void onNext(BaseResponse<List<BannerData>> value) {

                        if (mView == null) return;
                        if (value.getErrorCode() == 0) {
                            mView.onShowBannerData(value.getData());
                        }else{
                            mView.onShowError(value.getErrorMsg());
                        }
                    }
                }));
    }

    @Override
    public void initArticleData() {
        addSubscribe(mDataManager.getHomeArticleData(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new AYBaseObserver<BaseResponse<ArticleListData>>(mView, "文章获取失败") {
                    @Override
                    public void onNext(BaseResponse<ArticleListData> value) {

                        Gson gson = new Gson();
                        String str = gson.toJson(value.getData());
                        Log.d("jk", "onNext: "+str);
                        if (mView == null) return;
                        if (value.getErrorCode() == 0) {
                            mView.onShowArticleData(value.getData());
                        }else{
                            mView.onShowError(value.getErrorMsg());
                        }
                    }
                }));
    }
}
