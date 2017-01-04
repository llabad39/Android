package com.example.belynda.rssreader;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreerCarteActivity extends AppCompatActivity {
    Spinner listeJeux;
    Button ok;
    EditText rep;
    EditText quest;
    RadioGroup diff;
    AccessData ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_carte);
        listeJeux = (Spinner) findViewById(R.id.nomJeux);
        ok = (Button) findViewById(R.id.ok);
        quest=  (EditText) findViewById(R.id.QuestioneEdit);
        rep = (EditText) findViewById(R.id.ReponseEdit);
        diff = (RadioGroup) findViewById(R.id.diff);
        ad = new AccessData(getApplicationContext());
        ArrayList<Deck> listDeck = ad.getAllDeck();
        List list = new ArrayList();
        for(int i=0;i<listDeck.size();i++){
            list.add(listDeck.get(i).getName());
        }
        ArrayAdapter listForSpinner = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
        listForSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listeJeux.setAdapter(listForSpinner);

    }

    public void ajout(View v) {
        String deck_name = listeJeux.getSelectedItem().toString();
        String question = quest.getText().toString();
        String reponse = rep.getText().toString();
        if (!(reponse.isEmpty() || deck_name.isEmpty() || question.isEmpty())) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ad.COLONNE_TYPEDECK, deck_name);
            contentValues.put(ad.COLONNE_QUESTION, question);
            contentValues.put(ad.COLONNE_REPONSE, reponse);
            contentValues.put(ad.COLONNE_MEDIA, " ");
            int difficulté;
            int id = diff.getCheckedRadioButtonId();
            switch (id) {
                case R.id.diff1:
                    difficulté = 1;
                    break;
                case R.id.diff2:
                    difficulté = 2;
                    break;
                case R.id.diff3:
                    difficulté = 3;
                    break;
                default:
                    difficulté = 3;
                    break;
            }
            contentValues.put(ad.COLONNE_DIFF,difficulté);
            contentValues.put(ad.COLONNE_DATE,System.currentTimeMillis());

            if(ad.insert_card(contentValues))
                Toast.makeText(getApplicationContext(), "bien ajouté", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Cette question est déjà présente dans le jeux", Toast.LENGTH_SHORT).show();
            rep.setText("");
            quest.setText("");


        }


    }

}



