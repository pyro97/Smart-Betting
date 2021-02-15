package com.simonepirozzi.smartbettingtips;


import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

public class Splash extends AppCompatActivity {
        private TextView testo;
        private AppCompatImageView img;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        img= findViewById(R.id.vipImage);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytransition);
       // testo.startAnimation(animation);
        img.startAnimation(animation);
        final Intent intent=new Intent(this,MainActivity.class);
        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
            timer.start();
    }
}
