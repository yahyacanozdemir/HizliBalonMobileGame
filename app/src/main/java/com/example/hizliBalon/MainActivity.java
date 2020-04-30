package com.example.hizliBalon;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Animation fromside,fromside2,frombottom,fastfrombottom,fromup ;
    boolean isEnd = false  ;
    boolean isFirstGame = true ;
    int lastLevel =0 ;

    RelativeLayout startLY,showLevelLY,whiteBallonsLY,redBallonsLY,gameOverLY,scoreLY;
    FrameLayout biggestFrameLY,otherFrameLY;


    MediaPlayer mp ;
    MediaPlayer go ;

    ImageView gameOverImage, startButton;
    ImageView ScoreScreen,gameOverText;

    ImageView whiteBallon1,whiteBallon2,whiteBallon3,whiteBallon4,whiteBallon5,whiteBallon6,whiteBallon7,whiteBallon8,whiteBallon9,whiteBallon10,whiteBallon11,whiteBallon12;
    ImageView redBallon1,redBallon2,redBallon3,redBallon4,redBallon5,redBallon6,redBallon7,redBallon8,redBallon9,redBallon10,redBallon11,redBallon12;

    SharedPreferences sharedPreferences1;

    ImageView[] whiteBallonList ;
    ImageView[] redBallonList ;

    Handler handler ;
    Runnable runnable ;

    TextView scoreText ,infoText,againText,showLevelText;

    SeekBar seekBar ;
    LottieAnimationView sadFace ;

    int j ;
    int score=0 ;
    int time ;
    int Zorluk=1200;
    int imkansiz_plusplus=12,imkansiz_plus=11,imkansiz=10;
    int zor =40,normal_plus=35 , normal=30 , kolay_plus=25 , kolay=20 ,basit=15 , antrenman=10 ;
    int kalan ;
    boolean frst_imkansız_plusplus=false ,frst_imkansız_plus=false, frst_imkansız=false ,
            frst_zor=false , frst_normalplus=false, frst_normal=false , frst_kolayplus=false ,
            frst_kolay=false, frst_basit=false, frst_antrenman=false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences1 = this.getSharedPreferences("com.example.hizliBalon", Context.MODE_PRIVATE);
        lastLevel = sharedPreferences1.getInt("levelCount",0);

        scoreText = (TextView) findViewById(R.id.ScoreSayaci);
        infoText = (TextView) findViewById(R.id.info) ;
        gameOverImage = findViewById(R.id.gameOverText);
        againText = (TextView) findViewById(R.id.again) ;
        startButton = findViewById(R.id.baslaButton);
        showLevelText = (TextView) findViewById(R.id.showLevelCounterOnGame);
        seekBar = (SeekBar) findViewById(R.id.seekBar2);

        mp = MediaPlayer.create(this,R.raw.balloonsound);
        go = MediaPlayer.create(this,R.raw.gameover);

        biggestFrameLY=(FrameLayout) findViewById(R.id.biggestLY);
        otherFrameLY=(FrameLayout) findViewById(R.id.otherFrameLY);

        startLY=(RelativeLayout) findViewById(R.id.baslangicLY);
        whiteBallonsLY=(RelativeLayout) findViewById(R.id.whiteBallonLY);
        redBallonsLY=(RelativeLayout) findViewById(R.id.redBallonLY);
        gameOverLY=(RelativeLayout) findViewById(R.id.gameOverScreenLY);
        showLevelLY=(RelativeLayout) findViewById(R.id.showLevelCounterOnGameLY);
        scoreLY=(RelativeLayout) findViewById(R.id.scoreSayaciLY);

        sadFace=(LottieAnimationView) findViewById(R.id.gameOverAnimationLT);

        score = 0 ;

        whiteBallon1 = findViewById(R.id.whiteBallon1);     whiteBallon2 = findViewById(R.id.whiteBallon2);
        whiteBallon3 = findViewById(R.id.whiteBallon3);     whiteBallon4 = findViewById(R.id.whiteBallon4);
        whiteBallon5 = findViewById(R.id.whiteBallon5);     whiteBallon6 = findViewById(R.id.whiteBallon6);
        whiteBallon7 = findViewById(R.id.whiteBallon7);     whiteBallon8 = findViewById(R.id.whiteBallon8);
        whiteBallon9 = findViewById(R.id.whiteBallon9);     whiteBallon10 = findViewById(R.id.whiteBallon10);
        whiteBallon11 = findViewById(R.id.whiteBallon11);   whiteBallon12 = findViewById(R.id.whiteBallon12);
        whiteBallonList = new ImageView[] {whiteBallon1,whiteBallon2,whiteBallon3,whiteBallon4,whiteBallon5,whiteBallon6,whiteBallon7,whiteBallon8,whiteBallon9,whiteBallon10,whiteBallon11,whiteBallon12} ;

        redBallon1 =findViewById(R.id.redBallon2);      redBallon2 =findViewById(R.id.redBallon1);
        redBallon3 =findViewById(R.id.redBallon3);      redBallon4 =findViewById(R.id.redBallon4);
        redBallon5 =findViewById(R.id.redBallon5);      redBallon6 =findViewById(R.id.redBallon6);
        redBallon7 =findViewById(R.id.redBallon7);      redBallon8 =findViewById(R.id.redBallon8);
        redBallon9 =findViewById(R.id.redBallon9);      redBallon10 =findViewById(R.id.redBallon10);
        redBallon11 =findViewById(R.id.redBallon11);    redBallon12 =findViewById(R.id.redBallon12);
        redBallonList = new ImageView[] {redBallon1,redBallon2,redBallon3,redBallon4,redBallon5,redBallon6,redBallon7,redBallon8,redBallon9,redBallon10,redBallon11,redBallon12};


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                if (lastLevel==0) {
                    if (i >= 1 || i < 10) {
                        infoText.setText("IMKANSIZ+++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 10 && i < 20) {
                        infoText.setText("IMKANSIZ++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 20 && i < 30) {
                        infoText.setText("IMKANSIZ");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 30 && i < 40) {
                        infoText.setText("ZOR");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 40 && i < 50) {
                        infoText.setText("NORMAL++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 50 && i < 60) {
                        infoText.setText("NORMAL");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 60 && i < 70) {
                        infoText.setText("KOLAY++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 70 && i < 80){
                        infoText.setText("KOLAY");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 80 && i < 90){
                        infoText.setText("BASİT");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i>=90 && i<=100) {
                        infoText.setTextSize(30);
                        infoText.setText("ANTRENMAN");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i * 12;
                    }
                }
                if (lastLevel==1) {
                    if (i >= 1 || i < 10) {
                        infoText.setText("IMKANSIZ+++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 10 && i < 20) {
                        infoText.setText("IMKANSIZ++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 20 && i < 30) {
                        infoText.setText("IMKANSIZ");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 30 && i < 40) {
                        infoText.setText("ZOR");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 40 && i < 50) {
                        infoText.setText("NORMAL++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 50 && i < 60) {
                        infoText.setText("NORMAL");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 60 && i < 70) {
                        infoText.setText("KOLAY++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 70 && i < 80) {
                        infoText.setText("KOLAY");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 80 && i < 90){
                        infoText.setTextSize(30);
                        infoText.setText("BASİT");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i>=90 && i<=100) {
                        infoText.setTextSize(30);
                        infoText.setText("ANTRENMAN");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                }
                if (lastLevel==2) {
                    if (i >= 1 || i < 10) {
                        infoText.setText("IMKANSIZ+++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 10 && i < 20) {
                        infoText.setText("IMKANSIZ++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 20 && i < 30) {
                        infoText.setText("IMKANSIZ");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 30 && i < 40) {
                        infoText.setText("ZOR");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 40 && i < 50) {
                        infoText.setText("NORMAL++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 50 && i < 60) {
                        infoText.setText("NORMAL");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 60 && i < 70) {
                        infoText.setText("KOLAY++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 70 && i < 80){
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 80 && i < 90){
                        infoText.setTextSize(30);
                        infoText.setText("BASİT");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i>=90 && i<=100) {
                        infoText.setTextSize(30);
                        infoText.setText("ANTRENMAN");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                }
                if (lastLevel==3) {
                    if (i >= 1 || i < 10) {
                        infoText.setText("IMKANSIZ+++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 10 && i < 20) {
                        infoText.setText("IMKANSIZ++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 20 && i < 30) {
                        infoText.setText("IMKANSIZ");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 30 && i < 40) {
                        infoText.setText("ZOR");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 40 && i < 50) {
                        infoText.setText("NORMAL++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 50 && i < 60) {
                        infoText.setText("NORMAL");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 60 && i < 70) {
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 70 && i < 80){
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 80 && i < 90){
                        infoText.setTextSize(30);
                        infoText.setText("BASİT");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i>=90 && i<=100) {
                        infoText.setTextSize(30);
                        infoText.setText("ANTRENMAN");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                }
                if (lastLevel==4) {
                    if (i >= 1 || i < 10) {
                        infoText.setText("IMKANSIZ+++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 10 && i < 20) {
                        infoText.setText("IMKANSIZ++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 20 && i < 30) {
                        infoText.setText("IMKANSIZ");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 30 && i < 40) {
                        infoText.setText("ZOR");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 40 && i < 50) {
                        infoText.setText("NORMAL++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 50 && i < 60) {
                        infoText.setTextSize(30);
                        infoText.setText("NORMAL");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 60 && i < 70) {
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 70 && i < 80){
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 80 && i < 90){
                        infoText.setTextSize(30);
                        infoText.setText("BASİT");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i>=90 && i<=100) {
                        infoText.setTextSize(30);
                        infoText.setText("ANTRENMAN");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                }
                if (lastLevel==5) {
                    if (i >= 1 || i < 10) {
                        infoText.setText("IMKANSIZ+++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 10 && i < 20) {
                        infoText.setText("IMKANSIZ++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 20 && i < 30) {
                        infoText.setText("IMKANSIZ");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 30 && i < 40) {
                        infoText.setText("ZOR");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 40 && i < 50) {
                        infoText.setTextSize(30);
                        infoText.setText("NORMAL++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 50 && i < 60) {
                        infoText.setTextSize(30);
                        infoText.setText("NORMAL");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 60 && i < 70) {
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 70 && i < 80){
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 80 && i < 90){
                        infoText.setTextSize(30);
                        infoText.setText("BASİT");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i>=90 && i<=100) {
                        infoText.setTextSize(30);
                        infoText.setText("ANTRENMAN");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                }
                if (lastLevel==6) {
                    if (i >= 1 || i < 10) {
                        infoText.setText("IMKANSIZ+++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 10 && i < 20) {
                        infoText.setText("IMKANSIZ++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 20 && i < 30) {
                        infoText.setText("IMKANSIZ");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 30 && i < 40) {
                        infoText.setTextSize(30);
                        infoText.setText("ZOR");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 40 && i < 50) {
                        infoText.setTextSize(30);
                        infoText.setText("NORMAL++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 50 && i < 60) {
                        infoText.setTextSize(30);
                        infoText.setText("NORMAL");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 60 && i < 70) {
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 70 && i < 80){
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 80 && i < 90){
                        infoText.setTextSize(30);
                        infoText.setText("BASİT");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i>=90 && i<=100) {
                        infoText.setTextSize(30);
                        infoText.setText("ANTRENMAN");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                }
                if (lastLevel==7) {
                    if (i >= 1 || i < 10) {
                        infoText.setText("IMKANSIZ+++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 10 && i < 20) {
                        infoText.setText("IMKANSIZ++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 20 && i < 30) {
                        infoText.setTextSize(30);
                        infoText.setText("IMKANSIZ");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 30 && i < 40) {
                        infoText.setTextSize(30);
                        infoText.setText("ZOR");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 40 && i < 50) {
                        infoText.setTextSize(30);
                        infoText.setText("NORMAL++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 50 && i < 60) {
                        infoText.setTextSize(30);
                        infoText.setText("NORMAL");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 60 && i < 70) {
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 70 && i < 80){
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 80 && i < 90){
                        infoText.setTextSize(30);
                        infoText.setText("BASİT");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i>=90 && i<=100) {
                        infoText.setTextSize(30);
                        infoText.setText("ANTRENMAN");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                }
                if (lastLevel==8) {
                    if (i >= 1 || i < 10) {
                        infoText.setText("IMKANSIZ+++");
                        startButton.setVisibility(View.INVISIBLE);
                    }
                    if (i >= 10 && i < 20) {
                        infoText.setTextSize(30);
                        infoText.setText("IMKANSIZ++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i * 12;
                    }
                    if (i >= 20 && i < 30) {
                        infoText.setTextSize(30);
                        infoText.setText("IMKANSIZ");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i * 12;
                    }
                    if (i >= 30 && i < 40) {
                        infoText.setTextSize(30);
                        infoText.setText("ZOR");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i * 12;
                    }
                    if (i >= 40 && i < 50) {
                        infoText.setTextSize(30);
                        infoText.setText("NORMAL++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i * 12;
                    }
                    if (i >= 50 && i < 60) {
                        infoText.setTextSize(30);
                        infoText.setText("NORMAL");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i * 12;
                    }
                    if (i >= 60 && i < 70) {
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i * 12;
                    }
                    if (i >= 70 && i < 80) {
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i * 12;
                    }
                    if (i >= 80 && i < 90) {
                        infoText.setTextSize(30);
                        infoText.setText("BASİT");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i * 12;
                    }
                    if (i >= 90 && i <= 100) {
                        infoText.setTextSize(30);
                        infoText.setText("ANTRENMAN");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i * 12;
                    }
                }
                if (lastLevel==9) {
                    if (i >= 1 || i < 10) {
                        infoText.setTextSize(30);
                        infoText.setText("IMKANSIZ+++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i * 12;
                    }
                    if (i >= 10 && i < 20) {
                        infoText.setTextSize(30);
                        infoText.setText("IMKANSIZ++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i * 12;
                    }
                    if (i >= 20 && i < 30) {
                        infoText.setTextSize(30);
                        infoText.setText("IMKANSIZ");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 30 && i < 40) {
                        infoText.setTextSize(30);
                        infoText.setText("ZOR");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 40 && i < 50) {
                        infoText.setTextSize(30);
                        infoText.setText("NORMAL++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 50 && i < 60) {
                        infoText.setTextSize(30);
                        infoText.setText("NORMAL");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 60 && i < 70) {
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY++");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 70 && i < 80){
                        infoText.setTextSize(30);
                        infoText.setText("KOLAY");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i >= 80 && i < 90){
                        infoText.setTextSize(30);
                        infoText.setText("BASİT");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                    if (i>=90 && i<=100) {
                        infoText.setTextSize(30);
                        infoText.setText("ANTRENMAN");
                        startButton.setVisibility(View.VISIBLE);
                        Zorluk = i*12 ;
                    }
                }

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    public void firstInfoScreen (){
        if (j >= 1 && j < 10) {
            showLevelText.setText("PATLAT BEYAZI !");
            scoreText.setTextSize(230);
            scoreText.setText("0");
        }
        if (j >= 10 && j < 20) {
            showLevelText.setText("PATLAT BEYAZI !");
            scoreText.setTextSize(230);
            scoreText.setText("0");
        }
        if (j >= 20 && j < 30) {
            showLevelText.setText("PATLAT BEYAZI !");
            scoreText.setTextSize(230);
            scoreText.setText("0");
        }
        if (j >= 30 && j < 40) {
            showLevelText.setText("PATLAT BEYAZI !");
            scoreText.setTextSize(230);
            scoreText.setText("0");
        }
        if (j >= 40 && j < 50) {
            showLevelText.setText("PATLAT BEYAZI !");
            scoreText.setTextSize(230);
            scoreText.setText("0");
        }
        if (j >= 50 && j < 60) {
            showLevelText.setText("PATLAT BEYAZI !");
            scoreText.setTextSize(230);
            scoreText.setText("0");
        }
        if (j >= 60 && j < 70) {
            showLevelText.setText("PATLAT BEYAZI !");
            scoreText.setTextSize(230);
            scoreText.setText("0");
        }
        if (j >= 70 && j < 80) {
            showLevelText.setText("PATLAT BEYAZI !");
            scoreText.setTextSize(230);
            scoreText.setText("0");
        }
        if (j >= 80 && j < 90) {
            showLevelText.setText("PATLAT BEYAZI !");
            scoreText.setTextSize(230);
            scoreText.setText("0");
        }
        if (j >= 90 && j <= 100) {
            showLevelText.setText("PATLAT BEYAZI !");
            scoreText.setTextSize(230);
            scoreText.setText("0");
        }
    }
    public void OyunBaslat(View view) {
        scoreLY.setVisibility(View.VISIBLE);
        startLY.setVisibility(View.INVISIBLE);
        whiteBallonsLY.setVisibility(View.VISIBLE);
        redBallonsLY.setVisibility(View.VISIBLE);
        scoreLY.setVisibility(View.VISIBLE);
        showLevelLY.setVisibility(View.VISIBLE);
        firstInfoScreen();
        beyazBalonGizle();
        kırmızıBalonGizle();
    }
    public void beyazBalonGizle() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : whiteBallonList) {
                    image.setVisibility(View.INVISIBLE);
                }
                if (isEnd == false) {
                    Random random = new Random();
                    int i = random.nextInt(12);
                    whiteBallonList[i].setVisibility(View.VISIBLE);
                    handler.postDelayed(this, Zorluk);
                }
            }
        };
        handler.post(runnable);
    }
    public void kırmızıBalonGizle () {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : redBallonList) {
                    image.setVisibility(View.INVISIBLE);
                }
                if (isEnd == false) {
                    Random random = new Random();
                    int i = random.nextInt(12);
                    redBallonList[i].setVisibility(View.VISIBLE);

                    handler.postDelayed(this, Zorluk);
                }
            }
        };
        handler.post(runnable);
    }
    public void ScorPlus( View view) {
        for (ImageView image : whiteBallonList) {
            image.setVisibility(View.INVISIBLE);
        }
        mp.start();
        reSizeScoreText();
        levelSystem();
    }
    public void levelSystem(){

        if (Zorluk > 0 && Zorluk <= 120) {
            if(lastLevel<9) {
                lastLevel = 9;
                sharedPreferences1.edit().putInt("levelCount", lastLevel).apply();
            }
            if (frst_imkansız_plusplus==true) {
                score = 0;
                frst_imkansız_plusplus=false ;
            }
            score++;
            kalan = (imkansiz_plusplus-score) ;
            showLevelText.setText("Büyük Ödüle " + kalan);
            scoreText.setText(""+score);
        }


        if (Zorluk > 120 && Zorluk <= 240) {
            if(lastLevel<8) {
                lastLevel = 8;
                sharedPreferences1.edit().putInt("levelCount", lastLevel).apply();
            }
            if (frst_imkansız_plus=true) {
                score = 0;
                frst_imkansız_plus=false;
            }
            score++;
            kalan = (imkansiz_plus-score) ;
            if (kalan == 0){
                frst_imkansız_plusplus=true;
                Zorluk= 10 ;
                showLevelText.setText("ÖDÜLE 12");
            }
            else
                showLevelText.setText("ÖDÜLE " + kalan);
            scoreText.setText(""+score);
        }


        if (Zorluk > 240 && Zorluk <= 360) {
            if (lastLevel < 7) {
                lastLevel = 7;
                sharedPreferences1.edit().putInt("levelCount", lastLevel).apply();
            }
            if (frst_imkansız = true) {
                score = 0;
                frst_imkansız = false;
            }
            score++;
            kalan = (imkansiz - score);
            if (kalan == 0) {
                frst_imkansız_plus = true;
                Zorluk = 121;
                showLevelText.setText("Ödüle 11");
            } else {
                showLevelText.setText("İLK REKORA " + kalan);
            }
                scoreText.setText("" + score);

        }


        if(Zorluk>360 && Zorluk <=480) {
            if(lastLevel<6) {
                lastLevel = 6;
                sharedPreferences1.edit().putInt("levelCount", lastLevel).apply();
            }
            if (frst_zor==true) {
                score = 0;
                frst_zor=false;
            }
            score++;
            kalan = (zor-score) ;
            if (kalan == 0){
                frst_imkansız=true;
                Zorluk= 280 ;
                showLevelText.setText("İLK REKORA 11");
            }
            else
                showLevelText.setText("Rekor Seviyelerine "+kalan);
            scoreText.setText(""+score);
        }


        if(Zorluk>480 && Zorluk <=600) {
            if(lastLevel<5) {
                lastLevel = 5;
                sharedPreferences1.edit().putInt("levelCount", lastLevel).apply();
            }
            if(frst_normalplus==true) {
                score = 0;
                frst_normalplus=false;
            }
            score++;
            kalan = (normal_plus-score) ;
            if (kalan == 0){
                frst_zor=true;
                Zorluk= 420;
                showLevelText.setText("Rekor Seviyelerine 40");
            }
            else
                showLevelText.setText("Zor Seviyeye "+kalan);
            scoreText.setText(""+score);
        }


        if(Zorluk>600 && Zorluk <=720) {
            if(lastLevel<4) {
                lastLevel = 4;
                sharedPreferences1.edit().putInt("levelCount", lastLevel).apply();
            }
            if (frst_normal==true) {
                score = 0;
                frst_normal=false;
            }
            score++;
            kalan = (normal-score) ;
            if (kalan == 0){
                frst_normalplus=true;
                Zorluk= 540 ;
                showLevelText.setText("Zor Seviyeye 35");
            }
            else
                showLevelText.setText("Normal++ Seviyeye "+kalan);
            scoreText.setText(""+score);

        }


        if(Zorluk>720 && Zorluk <=840) {
            if(lastLevel<3) {
                lastLevel = 3;
                sharedPreferences1.edit().putInt("levelCount", lastLevel).apply();
            }
            if (frst_kolayplus == true){
                score = 0;
                frst_kolayplus=false;
            }
            score++;
            kalan = (kolay_plus-score) ;
            if (kalan == 0){
                frst_normal=true;
                Zorluk= 660 ;
                showLevelText.setText("Normal++ Seviyeye 30");
            }
            else
                showLevelText.setText("Normal Seviyeye "+kalan);
            scoreText.setText(""+score);
        }


        if(Zorluk>840 && Zorluk <=960) {
            if(lastLevel<2) {
                lastLevel = 2;
                sharedPreferences1.edit().putInt("levelCount", lastLevel).apply();
            }
            if (frst_kolay==true) {
                score = 0;
                frst_kolay=false;
            }
            score++;
            kalan= (kolay-score);
            if (kalan == 0){
                frst_kolayplus=true;
                Zorluk= 780 ;
                showLevelText.setText("Normal Seviyeye 25");
            }
            else
                showLevelText.setText("Kolay++ Seviyeye "+kalan);
            scoreText.setText(""+score);
        }


        if(Zorluk>960 && Zorluk <=1080) {
            if(lastLevel<1) {
                lastLevel = 1;
                sharedPreferences1.edit().putInt("levelCount", lastLevel).apply();
            }
            if (frst_basit == true) {
                score = 0;
                frst_basit=false;
            }
            score++;
            kalan = (basit-score) ;
            if (kalan == 0){
                frst_kolay=true;
                Zorluk= 920 ;
                showLevelText.setText("Kolay++ Seviyeye 20");
            }
            else
                showLevelText.setText("Kolay Seviyeye " + kalan);
            scoreText.setText(""+score);
        }


        if(Zorluk>1080 && Zorluk <=1200) {
            if (frst_antrenman==true) {
                score = 0;
                frst_antrenman=false;
            }
            score++;
            kalan = (antrenman-score) ;
            if (kalan == 0){
                frst_basit=true;
                Zorluk= 1000 ;
                showLevelText.setText("Kolay Seviyeye 15" );
            }
            else
                showLevelText.setText("Basit Seviyeye " + kalan);
            scoreText.setText(""+score);
        }

    }
    public void reSizeScoreText(){
        if (score >= 100 && score < 1000) {
            scoreText.setTextSize(230);
        }
        if (score >= 1000 && score < 10000) {
            scoreText.setTextSize(170);
        }
    }
    public void GameOver (View view){
        gameOverLY.setVisibility(View.VISIBLE);
        closeBalloons();
        startLY.setVisibility(View.INVISIBLE);
        whiteBallonsLY.setVisibility(View.INVISIBLE);
        redBallonsLY.setVisibility(View.INVISIBLE);
        isEnd = true ;
        go.start();
        startEndAnimations();
    }
    public void closeBalloons (){
        redBallon1.setClickable(false);      redBallon2.setClickable(false);
        redBallon3.setClickable(false);      redBallon4.setClickable(false);
        redBallon5.setClickable(false);      redBallon6.setClickable(false);
        redBallon7.setClickable(false);      redBallon8.setClickable(false);
        redBallon9.setClickable(false);      redBallon10.setClickable(false);
        redBallon11.setClickable(false);     redBallon12.setClickable(false);

        whiteBallon1.setClickable(false);   whiteBallon2.setClickable(false);
        whiteBallon3.setClickable(false);   whiteBallon4.setClickable(false);
        whiteBallon5.setClickable(false);   whiteBallon6.setClickable(false);
        whiteBallon7.setClickable(false);   whiteBallon8.setClickable(false);
        whiteBallon9.setClickable(false);   whiteBallon10.setClickable(false);
        whiteBallon11.setClickable(false);   whiteBallon12.setClickable(false);
    }
    public void startEndAnimations (){

        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        scoreText.startAnimation(shake);

        fromside2= AnimationUtils.loadAnimation(this,R.anim.fromside2) ;
        gameOverImage.setAnimation(fromside2);

        fromup = AnimationUtils.loadAnimation(this,R.anim.fromup) ;
        sadFace.setAnimation(fromup);

        fastfrombottom = AnimationUtils.loadAnimation(this,R.anim.fastfrombottom) ;
        againText.setAnimation(fastfrombottom);

    }
    public void again (View view) {
        Intent intent = getIntent() ;
        finish();
        startActivity(intent);

    }
}
