package com.damon.app;

import android.app.Application;

import com.damon.di.component.AppComponent;
import com.damon.di.component.DaggerAppComponent;
import com.damon.di.module.AppModule;
import com.damon.utils.SharedPrefUtils;

/**
 * @author: DamonJiang
 * @date: 2018/9/24 0024
 * @description:
 */
public class App extends Application{
    private static App context;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 sp 工具类
        SharedPrefUtils.init(this);

        context = (App) this.getApplicationContext();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(context))
                .build();
    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
    /**
     * 获取全局的 context
     *
     * @return
     */
    public static synchronized App getContext() {
        return context;
    }
}
