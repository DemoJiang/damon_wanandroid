package com.damon.ui.home;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.damon.R;
import com.damon.adapter.HomePageAdapter;
import com.damon.base.fragment.MVPBaseFragment;
import com.damon.core.bean.ArticleListData;
import com.damon.core.bean.BannerData;
import com.damon.helper.GlideLoaderHelper;
import com.damon.utils.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author: DamonJiang
 * @date: 2018/9/30 0030
 * @description:
 */
public class HomeFragment extends MVPBaseFragment<HomePresenter> implements HomeContact.View {
    @BindView(R.id.id_rv)
    RecyclerView mRecycleView;
    private Banner mBanner;

    public static HomeFragment getInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mBanner != null){
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mBanner != null){
            mBanner.stopAutoPlay();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    protected void inject() {
        fragmentComponent.inject(this);
    }

    @Override
    protected void onAttachView() {
        super.onAttachView();
        mBanner = (Banner) getActivity().getLayoutInflater().inflate(R.layout.container_banner,null);
        mBanner.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getContext().getResources().getDisplayMetrics().heightPixels / 4));
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mPresenter.initBannerData();
        mPresenter.initArticleData();
    }

    @Override
    public void onShowBannerData(List<BannerData> bannerData) {
        List<String> bannerImageList = new ArrayList<>();     // 轮播图篇片地址
        List<String> bannerTitleList = new ArrayList<>();     // 轮播标题
        List<String> bannerUrlList = new ArrayList<>();       // 轮播图跳转的网页地址
        for(BannerData banner:bannerData){
            bannerImageList.add(banner.getImagePath());
            bannerTitleList.add(banner.getTitle());
            bannerUrlList.add(banner.getUrl());
        }
        mBanner.setImages(bannerImageList);
        mBanner.setBannerTitles(bannerTitleList);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.setImageLoader(new GlideLoaderHelper());
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(3500);
        mBanner.setOnBannerListener(position ->
                ToastUtil.showToast(getActivity(), "你点击了第 " + position + " 条广告"));

        mBanner.start();
    }

    @Override
    public void onShowArticleData(ArticleListData articleData) {
        HomePageAdapter homePageAdapter = new HomePageAdapter(articleData.getDatas());
        homePageAdapter.setHeaderView(mBanner);
        mRecycleView.setAdapter(homePageAdapter);
    }
}
