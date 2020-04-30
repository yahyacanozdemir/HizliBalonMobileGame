package com.example.hizliBalon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class iletisim extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3 ;
    Button gonder ;
    TextView bilgi ;
    TextView uyari ;

    String ad_Soyad ;
    String kategori ;
    String oneri ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iletisim);


        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText3);
        editText3 = (EditText) findViewById(R.id.editText5);
        gonder = findViewById(R.id.button) ;
        bilgi = findViewById(R.id.textView);
        uyari = (TextView)findViewById(R.id.textView2);

    }
    public void gonder (View view) {

        if (editText1.getText().toString().trim().equals("")) {
            editText1.setError("Bu Alan Boş Bırakılamaz!");
        }
        else if (editText2.getText().toString().trim().equals("")) {
            editText2.setError("Bu Alan Boş Bırakılamaz!");
        }

        else if (editText3.getText().toString().trim().equals("")) {
            editText3.setError("Bu Alan Boş Bırakılamaz!");
        }


        else {
            ad_Soyad = editText1.getText().toString().toUpperCase();
            kategori = editText3.getText().toString().toUpperCase();
            oneri = editText2.getText().toString();

            editText1.setText("");
            editText2.setText("");
            editText3.setText("");
            calistir(view);

            bilgi.setText("Yahya Can Özdemir'e iletilecek. Desteğiniz için teşekkür ederiz!");

            new CountDownTimer(30000, 100) {
                @Override
                public void onTick(long l) {
                }
                @Override
                public void onFinish() {
                    bilgi.setText("BİZİMLE BAĞLANTI KURUN  VEYA FASTBAS'İ GELİŞTİRMEMİZE YARDIMCI OLUN");
                }
            }.start();

        }
    }

    public void calistir(View v) {
        Intent intent = null, chooser = null;

        if(v.getId()==R.id.button){
            intent = new Intent( Intent.ACTION_SEND );
            intent.setData( Uri.parse( "mailto:" ) );
            String[] to = {"yahyacanozdemir@gmail.com"};
            intent.putExtra( Intent.EXTRA_EMAIL,to);
            intent.putExtra( Intent.EXTRA_SUBJECT, " Merhaba İsmim "+ad_Soyad+"" );
            intent.putExtra( Intent.EXTRA_TEXT, ""+"'' "+kategori+" ''"+" konusunda  "+" '' "+oneri+" '' gibi düşüncelere sahibim.  Dikkate Aldığınız İçin Teşekkür Ederim.");
            intent.setType( "messages/rfc822" );
            chooser=Intent.createChooser( intent, "Yahya Can Özdemir'e Gönder" );
            startActivity( chooser );
        }


    }
}
