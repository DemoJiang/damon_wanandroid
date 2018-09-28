package com.damon.core.http;



import com.damon.app.App;
import com.damon.core.http.Cookies.CookieManger;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2017/4/10.
 */

public class HttpClient {

    private HttpClient() {
    }

    private static class ClientHolder {

        private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //添加对Cookies的管理
                .cookieJar(new CookieManger(App.getContext()))
                .build();


        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static Retrofit getInstance() {
        return ClientHolder.retrofit;
    }
}
