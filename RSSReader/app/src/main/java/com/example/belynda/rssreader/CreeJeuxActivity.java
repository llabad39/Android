package com.example.belynda.rssreader;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreeJeuxActivity extends AppCompatActivity {
    EditText nomJeux;
    Button ok ;
    AccessData ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cree_jeux);

        ad = new AccessData(getApplicationContext());
        nomJeux= (EditText) findViewById(R.id.nomJeux);
        ok = (Button) findViewById(R.id.ok);
       // NomJeux.setText(savedInstanceState.getString("nom"));
       // savedInstanceState.putString("nomjeux", NomJeux.getText().toString());
    }

    public void onClick(View v) {
        String chaine = nomJeux.getText().toString();
        if(!chaine.isEmpty()){
            ContentValues cv = new ContentValues();
            cv.put(ad.COLONNE_TYPEDECK,chaine);
            try {
                ad.insert_deck(cv);
                Toast toast = Toast.makeText(getApplicationContext(),"Insertion réussi",Toast.LENGTH_SHORT);
                toast.show();
                nomJeux.setText("");
            }
            catch(Exception e){
                Log.d("ajout deck","ne peut ajouter votre deck");
                Toast toast = Toast.makeText(getApplicationContext(),"Insertion echoué",Toast.LENGTH_SHORT);
                toast.show();
            }
        }

    }
}
