package com.example.belynda.rssreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class SupprimerActivity extends AppCompatActivity {
    RadioButton supprimerCarte, supprimerJeu;
    Button ok ;
    int test = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer);
        supprimerCarte =(RadioButton) findViewById(R.id.suppCarte);
        supprimerJeu =(RadioButton) findViewById(R.id.supprimerJeu);
        ok = (Button) findViewById(R.id.ok);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.suppCarte:
                if (checked)
                    test = 1;
                    break;
            case R.id.supprimerJeu:
                if (checked)
                    test = 2 ;
                    break;
        }
    }
    public  void onClock(View v){
        if (v == ok){
            if(test == 1){

            }else
                if(test == 2){

                }
        }
    }
}
