package com.example.belynda.rssreader;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SupprimerActivity extends AppCompatActivity {
    EditText jeuSupp;
    Button ok ;
    AccessData ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer);
        jeuSupp =(EditText) findViewById(R.id.nomJeu);
        ok = (Button) findViewById(R.id.ok);
        ad = new AccessData(getApplicationContext());
    }


    public  void onClick(View v){
        String name = jeuSupp.getText().toString();
        if(name.isEmpty())
            Toast.makeText(getApplicationContext(),"Remplissez le nom du jeu",Toast.LENGTH_SHORT).show();
        else{
            ad.delete_deck(name);
            Toast.makeText(getApplicationContext(),"Supression réussi",Toast.LENGTH_SHORT).show();
        }
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
