package com.simonepirozzi.smartbettingtips.ui.main;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.simonepirozzi.smartbettingtips.R;
import com.simonepirozzi.smartbettingtips.ui.homepage.HomeFragment;
import com.simonepirozzi.smartbettingtips.ui.homepage.info.InfoFragment;
import com.simonepirozzi.smartbettingtips.ui.homepage.old.OldFragment;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private ScheduledExecutorService scheduler;
    private boolean isVisible;
    private MainActivityPresenter mPresenter;
    private static final int time1 = 30, time2 = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainActivityPresenter(this);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        FirebaseApp.initializeApp(getApplicationContext());
        mPresenter.initializeBanner();
        mInterstitialAd = mPresenter.initializeInterstitial();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getFragmentManager().beginTransaction().replace(R.id.contenitore, new HomeFragment()).commit();

    }

    protected void onStart() {
        super.onStart();
        createScheduler();
    }

    @Override
    protected void onStop() {
        super.onStop();
        scheduler.shutdownNow();
        scheduler = null;
        isVisible = false;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;
                case R.id.navigation_old:
                    fragment = new OldFragment();
                    break;
                case R.id.navigation_info:
                    fragment = new InfoFragment();
                    break;
            }
            if (fragment != null) {
                getFragmentManager().beginTransaction().replace(R.id.contenitore, fragment).commit();
            }
            return true;
        }
    };

    public void createScheduler() {
        isVisible = true;
        if (scheduler == null) {
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
                                        mInterstitialAd = mPresenter.initializeInterstitial();
                                        scheduler = null;
                                        createScheduler();
                                    }

                                    @Override
                                    public void onAdFailedToLoad(int i) {
                                        super.onAdFailedToLoad(i);
                                        mInterstitialAd = mPresenter.initializeInterstitial();
                                        onStart();
                                        scheduler = null;
                                        createScheduler();
                                    }
                                });
                            }


                        }
                    });
                }
            }, time1, time2, TimeUnit.SECONDS);

        }
    }

}
