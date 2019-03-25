package com.damon.core;


import com.damon.core.bean.ArticleListData;
import com.damon.core.bean.BannerData;
import com.damon.core.bean.BaseResponse;
import com.damon.core.bean.LoginData;
import com.damon.core.http.ApiService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * @author: DamonJiang
 * @date: 2018/9/25 0025
 * @description: 数据中心类
 * <p>
 * 包括网络数据，本地 sp 数据，本地数据库数据，
 * 缓存数据，均在这个类中做管理
 * </p>
 */
@Singleton
public class DataManager {
    private ApiService mApiService;

    @Inject
    public DataManager(ApiService apiService) {
        this.mApiService = apiService;
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public Observable<BaseResponse<LoginData>> getLoginData(String username, String password) {
        return mApiService.getLoginData(username, password);
    }

    /**
     * 广告轮播
     *
     * @return
     */
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mApiService.getBannerData();
    }

    /**
     * 首页文章列表
     *
     * @return
     */
    public Observable<BaseResponse<ArticleListData>> getHomeArticleData(int num) {
        return mApiService.getHomeArticleData(num);
    }
}
