package com.damon.di.component;



import com.damon.di.module.ActivityModule;
import com.damon.di.scope.FragmentScope;

import dagger.Component;

/**
 * @author: DamonJiang
 * @date: 2018/9/26 0026
 * @description:
 */
@FragmentScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {
//    void inject(HomeFragment fragment);
}
