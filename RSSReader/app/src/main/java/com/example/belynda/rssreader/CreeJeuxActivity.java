package com.example.belynda.rssreader;

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
    EditText NomJeux;
    Button ok ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cree_jeux);

        NomJeux= (EditText) findViewById(R.id.nomJeux);
        ok = (Button) findViewById(R.id.ok);
       // NomJeux.setText(savedInstanceState.getString("nom"));
       // savedInstanceState.putString("nomjeux", NomJeux.getText().toString());
    }
   /* public void onClick(View v,Bundle save) {
        String chaine = NomJeux.getText().toString();

        //Pour voir la valeur que j'ai récupérée
       final int essai = Log.v("Essai", chaine);

        Context context = getApplicationContext();
        CharSequence text = "Votre jeux a été créé avec succès ";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }*/
}
