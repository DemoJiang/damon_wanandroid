package com.damon.ui.navigation;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damon.R;


/**
 * @author: DamonJiang
 * @date: 2018/9/30 0030
 * @description:
 */
public class NavigationFragment extends Fragment {
    public static NavigationFragment getInstance() {
        NavigationFragment fragment = new NavigationFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation,container,false);
        return view;
    }
}
