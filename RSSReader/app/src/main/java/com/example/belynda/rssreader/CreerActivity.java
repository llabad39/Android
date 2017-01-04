package com.example.belynda.rssreader;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CreerActivity extends AppCompatActivity {
    Button creerjeux, creercarte,téléchargez,inserer;
    EditText url;
    long idCharge;
    DownloadManager dm;
    AccessData ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer);
        creercarte = (Button) findViewById(R.id.creerCarte);
        creerjeux = (Button) findViewById(R.id.creerJeux);
        téléchargez = (Button) findViewById(R.id.téléchargezJeux);
        inserer = (Button) findViewById(R.id.InsererJeux);
        inserer.setEnabled(false);
        url = (EditText) findViewById(R.id.url);
        dm=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        ad = new AccessData(getApplicationContext());


    }
    public void choix(View v) {
        if (v == creerjeux) {
            Intent intent = new Intent(getApplicationContext(),
                    CreeJeuxActivity.class);
            startActivity(intent);
        }else
        if(v == creercarte)
        {
            Intent intent2 = new Intent(getApplicationContext(),
                    CreerCarteActivity.class);
            startActivity(intent2);
        }
    }


    public void loadData(View v){
        String u = url.getText().toString();
        if(u.isEmpty()) {
            Uri uri = Uri.parse(u);
            try {
                DownloadManager.Request req = new DownloadManager.Request(Uri.parse("https://drive.google.com/uc?export=download&id=0B7S5eFlnyU4IUGxqdHNCc21CamM"));
                idCharge = dm.enqueue(req);
                Toast.makeText(getApplicationContext(),"Téléchargement réussie",Toast.LENGTH_SHORT);
                inserer.setEnabled(true);
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(),"Le téléchargement a échoué,verifiez l'url",Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(getApplicationContext(),"Remplissez l'url",Toast.LENGTH_SHORT).show();
        }


    }
    public void insertInDataBase(View v) throws FileNotFoundException {
        DownloadManager.Query question = new DownloadManager.Query();
        question.setFilterById(idCharge).setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
        Cursor cur= dm.query(question);
        if(cur.getCount()!=0) {
            cur.moveToFirst();
            String s = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));

            try {
                BufferedReader f = new BufferedReader(new FileReader(s));
                String deck;
                String card;
                deck = f.readLine();
                ContentValues cv = ad.getLineTableDeck(deck);
                if(ad.insert_deck(cv)) {
                    while ((card = f.readLine()) != null) {
                        cv = ad.getLineTableCard(card, deck);
                        ad.insert_card(cv);

                    }
                    Toast.makeText(getApplicationContext(),"Jeu bien inseré",Toast.LENGTH_SHORT);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Nom du jeu déjà existant,supprimez le avant de téléchargez",Toast.LENGTH_SHORT);
                }
                f.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }
        else
            Toast.makeText(getApplicationContext(),"L'insertion a échoué,verifiez l'url",Toast.LENGTH_SHORT).show();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // When the user center presses, let them pick a contact.
            Intent intent = new Intent(getApplicationContext(),
                    RssfeedActivity.class);
            startActivity(intent);
        }
        return false;
    }
}
