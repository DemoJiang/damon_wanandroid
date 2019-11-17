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

    public static final String USER_NAME = "username";

    public static final String USER_PASSWORD = "password";


    public static final String IS_COMMON_SITE = "is_common_site";
    public static final String MENU_BUILDER = "MenuBuilder";
    public static final String ARTICLE_TITLE = "article_title";
    public static final String ARTICLE_LINK = "article_link";


    public static final String PROJECTLIST_ID = "projectlist_id";
    public static final String PROJECTLIST_STR = "projectlist_str";
}
