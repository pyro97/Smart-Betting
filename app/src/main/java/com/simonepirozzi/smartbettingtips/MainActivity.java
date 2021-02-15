package com.simonepirozzi.smartbettingtips;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;


import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {
    private InterstitialAd mInterstitialAd;
    private ScheduledExecutorService scheduler;
    private boolean isVisible;
    BillingProcessor bp;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment=new HomeFragment();
                    break;
                case R.id.navigation_old:
                    fragment=new OldFragment();
                    break;
                case R.id.navigation_info:
                    fragment=new InfoFragment();
                    break;
                case R.id.navigation_premium:
                    if(bp.isSubscribed("premium_tips")) fragment=new PremiumFragment();
                    else    bp.subscribe(MainActivity.this,"premium_tips");

                    break;
            }
            if(fragment!=null)  getFragmentManager().beginTransaction().replace(R.id.contenitore,fragment).commit();
            return true;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the helper, passing it our context and the public key to verify signatures with
        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu/C1pTjfGMwRJ2kE6AGKJk+XKRPRCld4czM0ny51O1qat9uVd8zZTlTT00LETNoytdxFaDs2085RL2yscfpEyYc1QoaiNLSWNcUpvLzP6YeOOSuOxSw1Jf7W53wTIiitrnjLwh7eSL/mU+t9rjsVI0DhY6HigW0+LmDkAHDaiDbU2vkGwkdpuh9nuglEMutWmeJYoc2DtD6aFfw0R5ZWZMTGPAJEEwzAycPibDfIr6MjjZKINW0OuPf/sEjjVjfUni+k3d0PUc5uEUKg3DTT3e8n+mL7861QD7CHfrwj5IYkkjdfG4p0EW1OG+xoyam4m+uWVaVaNMAXewYn1UeNnQIDAQAB", this);
        bp.initialize();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        FirebaseApp.initializeApp(getApplicationContext());
        if(!bp.isSubscribed("premium_tips")){
            MobileAds.initialize(this, "ca-app-pub-9751551150368721~3403703012");
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId("ca-app-pub-9751551150368721/7301202533");
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getFragmentManager().beginTransaction().replace(R.id.contenitore,new HomeFragment()).commit();

    }

    protected void onStart(){
        super.onStart();
        if(!bp.isSubscribed("premium_tips")) {

            creaScheduler();
        }

    }
    //.. code

    @Override
    protected void onStop() {
        super.onStop();
        scheduler.shutdownNow();
        scheduler = null;
        isVisible =false;
    }


    public void creaScheduler(){
        isVisible = true;
        if(scheduler == null){
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            if (mInterstitialAd.isLoaded() && isVisible) {
                                mInterstitialAd.show();
                                scheduler.shutdownNow();
                                mInterstitialAd.setAdListener(new AdListener() {
                                    @Override
                                    public void onAdClosed() {
                                        mInterstitialAd = new InterstitialAd(MainActivity.this);
                                        mInterstitialAd.setAdUnitId("ca-app-pub-9751551150368721/7301202533");
                                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                        scheduler=null;
                                        creaScheduler();
                                    }

                                    @Override
                                    public void onAdFailedToLoad(int i) {
                                        super.onAdFailedToLoad(i);
                                        mInterstitialAd = new InterstitialAd(MainActivity.this);
                                        mInterstitialAd.setAdUnitId("ca-app-pub-9751551150368721/7301202533");
                                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                        onStart();
                                        scheduler=null;
                                        creaScheduler();
                                    }
                                });
                            }


                        }
                    });
                }
            }, 30, 30, TimeUnit.SECONDS);

        }
    }


    @Override
    public void onProductPurchased(String productId, TransactionDetails details) {

    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }
}
