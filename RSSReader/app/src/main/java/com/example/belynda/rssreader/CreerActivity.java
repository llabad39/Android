package com.example.belynda.rssreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreerActivity extends AppCompatActivity {
Button creerjeux, creercarte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer);
        creercarte = (Button) findViewById(R.id.creerCarte);
        creerjeux = (Button) findViewById(R.id.creerJeux);


    }
    public void choix(View v) {
        if (v == creerjeux) {
            Intent intent = new Intent(getApplicationContext(),
                    CreeJeuxActivity.class);
             //intent.putExtra(DetailActivity.EXTRA_URL, link);
            startActivity(intent);
        }else
        if(v == creercarte)
        {
            Intent intent2 = new Intent(getApplicationContext(),
                    CreerCarteActivity.class);
            //intent.putExtra(DetailActivity.EXTRA_URL, link);
            startActivity(intent2);
        }
    }
}
