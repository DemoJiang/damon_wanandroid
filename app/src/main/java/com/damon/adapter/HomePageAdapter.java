package com.damon.adapter;

import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.damon.R;
import com.damon.core.bean.ArticleData;

import java.util.List;

/**
 * @author: DamonJiang
 * @date: 2018/10/9 0009
 * @description:
 */
public class HomePageAdapter extends BaseQuickAdapter<ArticleData,BaseViewHolder> {

    private boolean isCollectPage;

    public HomePageAdapter(int layoutId,@Nullable List<ArticleData> data) {
        super(layoutId, data);
    }

    /**
     * 收藏取消UI刷新
     */
    public void isCollectPage() {
        isCollectPage = true;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleData item) {
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.id_tv_title, item.getTitle());
        }
        if (item.isCollect() || isCollectPage) {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like);
        } else {
            helper.setImageResource(R.id.item_search_pager_like_iv, R.drawable.icon_like_article_not_selected);
        }

        if (!TextUtils.isEmpty(item.getAuthor())) {
            helper.setText(R.id.id_tv_author, item.getAuthor());
        }

        helper.setText(R.id.id_tv_flag,item.getChapterName())
                .setText(R.id.id_tv_time, item.getNiceDate());
    }


}
