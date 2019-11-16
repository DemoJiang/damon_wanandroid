package com.damon.ui.article;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.damon.R;
import com.damon.base.activity.BaseActivity;
import com.damon.config.Constants;
import com.damon.utils.CommonUtils;
import com.just.agentweb.AgentWeb;

import java.lang.reflect.Method;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;


public class ArticleDetailActivity extends BaseActivity {
    private static final String TAG = "ArticleDetailActivity";
    @BindView(R.id.article_detail_web_view)
    FrameLayout mWebViewBox;
    @BindView(R.id.article_detail_toolbar)
    Toolbar mToolbar;

    private Bundle bundle;
    private MenuItem mCollectItem;
    private String mArticleLink;
    private String mArticleTitle;
    private boolean isCommonSite;
    private boolean isCollect;
    private boolean isCollectPage;
    private AgentWeb mAgentWeb;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }

    @Override
    protected void onAttachView() {
        setSupportActionBar(mToolbar);
        String mArticleLink = getIntent().getStringExtra(Constants.ARTICLE_LINK);
        String mArticleTitle = getIntent().getStringExtra(Constants.ARTICLE_TITLE);
        getSupportActionBar().setTitle(mArticleTitle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mWebViewBox, new LinearLayout.LayoutParams(-1,-1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(mArticleLink);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article_common, menu);
//        bundle = getIntent().getExtras();
//        assert bundle != null;
//        isCommonSite = (boolean) bundle.get(Constants.IS_COMMON_SITE);
//        if (!isCommonSite) {
//            unCommonSiteEvent(menu);
//        } else {
//            getMenuInflater().inflate(R.menu.menu_article_common, menu);
//        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_share:
                CommonUtils.showToast(this,"分享");
//                mPresenter.shareEventPermissionVerify(new RxPermissions(this));
                break;
            case R.id.item_collect:
                CommonUtils.showToast(this,"收藏");
//                collectEvent();
                break;
            case R.id.item_system_browser:
                CommonUtils.showToast(this,"在浏览器打开");
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(articleLink)));
                break;
            case android.R.id.home:
                finish();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void unCommonSiteEvent(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acticle, menu);
        mCollectItem = menu.findItem(R.id.item_collect);
        if (isCollect) {
            mCollectItem.setTitle(getString(R.string.cancel_collect));
            mCollectItem.setIcon(R.mipmap.ic_toolbar_like_p);
        } else {
            mCollectItem.setTitle(getString(R.string.collect));
            mCollectItem.setIcon(R.mipmap.ic_toolbar_like_n);
        }
    }

    /**
     * 让菜单同时显示图标和文字
     *
     * @param featureId Feature id
     * @param menu Menu
     * @return menu if opened
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (Constants.MENU_BUILDER.equalsIgnoreCase(menu.getClass().getSimpleName())) {
                try {
                    @SuppressLint("PrivateApi")
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    /**
     * 监听手机返回按钮
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
    }



    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
