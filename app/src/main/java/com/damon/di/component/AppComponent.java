package com.damon.di.component;



import com.damon.app.App;
import com.damon.core.DataManager;
import com.damon.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author: DamonJiang
 * @date: 2018/9/25 0025
 * @description: 项目中 Dagger 注入桥梁
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    App getApp();

    DataManager getDataManager();

}
