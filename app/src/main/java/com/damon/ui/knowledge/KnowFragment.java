package com.damon.ui.knowledge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damon.R;
import com.damon.helper.ResourceHelper;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


/**
 * @author: DamonJiang
 * @date: 2018/9/30 0030
 * @description:
 */
public class KnowFragment extends Fragment {
    private AdView mAdView;
    public static KnowFragment getInstance() {
        KnowFragment fragment = new KnowFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_knowledge,container,false);
        MobileAds.initialize(getActivity(), "ca-app-pub-6977020316114172~6873668067");
        mAdView = view.findViewById(R.id.id_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        return view;
    }
}
