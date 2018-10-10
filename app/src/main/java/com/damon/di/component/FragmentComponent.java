package com.damon.di.component;



import com.damon.di.module.FragmentModule;
import com.damon.di.scope.FragmentScope;
import com.damon.ui.home.HomeFragment;

import dagger.Component;

/**
 * @author: DamonJiang
 * @date: 2018/9/26 0026
 * @description:
 */
@FragmentScope
@Component(modules = FragmentModule.class, dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(HomeFragment fragment);
}
