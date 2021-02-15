package com.simonepirozzi.smartbettingtips.ui.main;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.google.android.gms.ads.InterstitialAd;

public interface MainActivityContract {
    void initializeBanner();
    InterstitialAd initializeInterstitial();
}
