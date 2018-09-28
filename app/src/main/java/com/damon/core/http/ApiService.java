package com.damon.core.http;


import com.damon.core.bean.BaseResponse;
import com.damon.core.bean.LoginData;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

}
