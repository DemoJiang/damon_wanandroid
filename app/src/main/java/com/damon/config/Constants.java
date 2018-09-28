package com.damon.config;

import com.damon.app.App;

import java.io.File;

/**
 * @author: DamonJiang
 * @date: 2018/9/28 0028
 * @description:
 */
public class Constants {
    public static final boolean DEBUG = false;          // log 打印

    /**
     * Path
     */
    public static final String PATH_DATA = App.getContext().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";
}
