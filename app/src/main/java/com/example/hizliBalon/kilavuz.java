package com.example.hizliBalon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class kilavuz extends AppCompatActivity {

    Button geriButton,hemenOynaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kilavuz);
        geriButton=(Button) findViewById(R.id.geriButton);
        hemenOynaButton=(Button) findViewById(R.id.hemenOynaButton);
    }
    public void OyunAcici(View view) {
        Intent GameScreen = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(GameScreen);
    }

    public void GeriDondur2 (View view) {
        Intent back = new Intent(getApplicationContext(),startScreen.class);
        startActivity(back);

    }
}
