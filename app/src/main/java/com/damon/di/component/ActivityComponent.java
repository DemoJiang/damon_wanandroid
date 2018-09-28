package com.damon.di.component;



import com.damon.di.module.FragmentModule;
import com.damon.di.scope.ActivityScope;
import com.damon.ui.login.LoginActivity;

import dagger.Component;

/**
 * @author: DamonJiang
 * @date: 2018/9/25 0025
 * @description:
 */
@ActivityScope
@Component(modules = FragmentModule.class,dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(LoginActivity Activity);
}
