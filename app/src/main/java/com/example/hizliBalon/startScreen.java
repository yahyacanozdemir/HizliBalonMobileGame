package com.example.hizliBalon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class startScreen extends AppCompatActivity {

    TextView bestScore ;
    String Score;
    LottieAnimationView play,info,contact ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        play = (LottieAnimationView) findViewById(R.id.PlayButton);
        info = (LottieAnimationView) findViewById(R.id.HowToUseButton);
        contact = (LottieAnimationView) findViewById(R.id.ConnectButton);

    }

    public void OyunAcici(View view) {

        Intent play = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(play);

    }

    public void iletisimAcici(View view) {

        Intent connect = new Intent(getApplicationContext(),iletisim.class);
        startActivity(connect);


    }

    public void KılavuzAcici(View view) {
        Intent info = new Intent(getApplicationContext(),kilavuz.class);
        startActivity(info);

    }

}
