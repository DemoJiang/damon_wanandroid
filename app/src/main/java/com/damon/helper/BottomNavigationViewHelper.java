package com.damon.helper;

import android.annotation.SuppressLint;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import android.util.Log;

import java.lang.reflect.Field;

/**
 * @author: DamonJiang
 * @date: 2018/9/30 0030
 * @description:  处理 BottomNavigationView 导航大于3个时的问题
 */
public class BottomNavigationViewHelper {
    @SuppressLint("RestrictedApi")
    public static void removeNavigationShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        menuView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        menuView.buildMenuView();
    }
}
