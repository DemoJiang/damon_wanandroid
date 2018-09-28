package com.damon.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author: DamonJiang
 * @date: 2018/9/26 0026
 * @description:
 */
@Documented
@Scope
@Retention(value = RetentionPolicy.RUNTIME)
public @interface FragmentScope {
}
