package com.simonepirozzi.smartbettingtips.ui.main;

import android.content.Context;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.simonepirozzi.smartbettingtips.utils.Constants;

public class MainActivityPresenter implements MainActivityContract {
    private Context context;

    MainActivityPresenter(Context context) {
        this.context = context;
    }


    @Override
    public void initializeBanner() {
        MobileAds.initialize(context, Constants.BANNER);
    }

    @Override
    public InterstitialAd initializeInterstitial() {
        InterstitialAd mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(Constants.INTERSTITIAL);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        return mInterstitialAd;
    }

}
