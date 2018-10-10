package com.damon.ui.home;

import com.damon.base.presenter.IPresenter;
import com.damon.base.view.IView;
import com.damon.core.bean.ArticleData;
import com.damon.core.bean.ArticleListData;
import com.damon.core.bean.BannerData;

import java.util.List;

/**
 * @author: DamonJiang
 * @date: 2018/10/9 0009
 * @description:
 */
public interface HomeContact {
    interface View extends IView {
//        void initView();
        void onShowBannerData(List<BannerData> bannerData);
        void onShowArticleData(ArticleListData articleListData);
    }
    interface Presenter extends IPresenter<HomeContact.View> {
        void initBannerData();           // 加载广告数据
        void initArticleData();          // 加载文章列表数据
    }
}
