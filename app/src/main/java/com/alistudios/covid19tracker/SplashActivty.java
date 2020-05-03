package com.alistudios.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivty extends AppCompatActivity {
   private ImageView logoImageSplash;
    private TextView titleID;
    private static final int SPLASH_TIME=4000;
    private Animation topAnimation,bottomAnimaton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activty);
        logoImageSplash=(ImageView) findViewById(R.id.logo_image_splash);
        titleID=(TextView) findViewById(R.id.title_label);
        topAnimation= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomAnimaton= AnimationUtils.loadAnimation(this,R.anim.bottom_anim);


        logoImageSplash.setAnimation(topAnimation);
        titleID.setAnimation(bottomAnimaton);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivty.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIME);

    }
}
