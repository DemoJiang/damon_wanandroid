package com.damon.helper;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.damon.app.App;


/**
 * 获取目录中的资源文件
 *
 * 包括 color、drawable、
 *
 */
public class ResourceHelper {

    private static final ResourceHelper ourInstance = new ResourceHelper();


    private ResourceHelper() {
    }

    public static int getColor(@ColorRes int colorId) {
        return App.getContext().getResources().getColor(colorId);
    }

    public static Drawable getDrawable(@DrawableRes int drawableId) {
        return App.getContext().getResources().getDrawable(drawableId);
    }
}
