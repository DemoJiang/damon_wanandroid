package com.damon.helper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.damon.utils.CollectionUtil;

import java.util.List;


/**
 * @anthor DamoJiang
 * @date 2018/4/27
 * @describe describe
 **/
public class FragmentHelper {
    private FragmentManager fragmentManager;
    private int layoutId;//要添加进布局的ID
    private List<Fragment> fragments;//fragment列表

    public FragmentHelper(FragmentManager fragmentManager, int layoutId, List<Fragment> fragments) {
        this.fragmentManager = fragmentManager;
        this.layoutId = layoutId;
        this.fragments = fragments;
    }



    /**
     * 添加fragment，显示指定fragment
     * @param showFragmentIndex 要显示的fragment
     */
    public void addAndShowFragment(int showFragmentIndex) {
        if (CollectionUtil.isEmpty(fragments) || fragmentManager == null) return;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            transaction.add(layoutId, fragments.get(i));
            if (i == showFragmentIndex) {
                transaction.show(fragments.get(i));
            } else {
                transaction.hide(fragments.get(i));

            }
        }

        transaction.commit();
    }

    /**
     * 显示指定fragment
     *
     * @param showFragmentIndex 要显示的fragment
     */
    public void showCurrentFragment(int showFragmentIndex) {

        if (CollectionUtil.isEmpty(fragments) || fragmentManager == null) return;

        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            Fragment f = fragments.get(i);
            if (i == showFragmentIndex) {
                beginTransaction.show(f);
            } else {
                beginTransaction.hide(f);
            }
        }
        beginTransaction.commit();
    }


}
