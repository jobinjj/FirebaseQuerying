package com.example.doctorbooking.main;

import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.doctorbooking.R;

public class SplashActivity extends AppCompatActivity {
    AnimatedVectorDrawable avd;
    private ImageView imageView;
    int count = 0;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final PreferencesHelper preferencesHelper = new PreferencesHelper(this);

        imageView = findViewById(R.id.imageView);
        avd  = (AnimatedVectorDrawable) imageView.getDrawable();
        //startAct();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (preferencesHelper.getBoolean("isLoggedIn")){
                    Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashActivity.this,LoginScreen.class);
                    startActivity(intent);
                    finish();
                }

            }
        },2000);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void startAct() {
        avd.registerAnimationCallback(new Animatable2.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                startAct();
                if (count < 3){
                    count++;
                }
                if (count == 2){
                    Intent intent = new Intent(SplashActivity.this,LoginScreen.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onAnimationStart(Drawable drawable) {
                super.onAnimationStart(drawable);
            }
        });
        avd.start();
    }
}

