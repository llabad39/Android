package com.example.belynda.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class RssfeedActivity extends Activity {
    Button creer, jouer, supprimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        creer = (Button) findViewById(R.id.creerJeu);
        jouer = (Button) findViewById(R.id.jouer);
        supprimer = (Button) findViewById(R.id.supprimerMenu);// qui se trouve dans le menu
        AccessData ad = new AccessData(getApplicationContext());
        ad.initDeck();
        ad.initCard();
    }

    public void onClick(View v) {
        if (v == jouer) {
            Intent intent = new Intent(getApplicationContext(),
                    JeuExistantActivity.class);
            // intent.putExtra(DetailActivity.EXTRA_URL, link);
            startActivity(intent);
        }else
            if(v == creer)
            {
                Intent intent2 = new Intent(getApplicationContext(),
                        CreerActivity.class);
                //intent.putExtra(DetailActivity.EXTRA_URL, link);
                startActivity(intent2);
            }else
                if(v == supprimer)
                {
                    Intent intent3 = new Intent(getApplicationContext(),
                            SupprimerActivity.class);
                   // intent.putExtra(DetailActivity.EXTRA_URL, link);
                    startActivity(intent3);
                }
    }
}

