package com.damon.core.http;


import com.damon.core.bean.ArticleListData;
import com.damon.core.bean.BannerData;
import com.damon.core.bean.BaseResponse;
import com.damon.core.bean.LoginData;
import com.damon.core.bean.ProjectTabData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 网络请求接口设置
 * Created by DamonJiang on 2018/8/13 0013.
 */
public interface ApiService {
    /**
     * 登录
     * @return
     */
    @FormUrlEncoded
    @POST(Api.LOGIN)
    Observable<BaseResponse<LoginData>> getLoginData(@Field("username") String username,
                                                     @Field("password") String password);

    /**
     * 首页广告
     * @return
     */
    @GET(Api.Banner)
    Observable<BaseResponse<List<BannerData>>> getBannerData();

    /**
     * 首页文章列表数据
     * @return
     */
    @GET(Api.ARTICLE+"{num}/json")
    Observable<BaseResponse<ArticleListData>> getHomeArticleData(@Path("num") int num);

    /**
     * 项目分类 tab 数据
     * @return
     */
    @GET(Api.PROJECT_TAB)
    Observable<BaseResponse<List<ProjectTabData>>> getProjectTabData();

}
