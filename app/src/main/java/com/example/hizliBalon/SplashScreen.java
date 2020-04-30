package com.example.hizliBalon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashScreen extends AppCompatActivity {

    FrameLayout biggestFrameLY;
    LinearLayout imageLY;

    ImageView maintext ;
    Animation frombottom ;
    int gosterim_suresi = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        imageLY=(LinearLayout) findViewById(R.id.imageLineerLY);

        maintext = (ImageView) findViewById(R.id.imageView) ;
        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom) ;
        maintext.setAnimation(frombottom);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), startScreen.class);
                startActivity(i);
                finish();
            }
        }, gosterim_suresi);
    }

}

