package com.example.belynda.rssreader;


import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Jouer extends Activity implements DetailFragment.OnFragmentInteractionListener,
        MyListFragment.OnItemSelectedListener  {
    ListCard listCard;
    Card currentCard;
    TextView tv;
    Button supr;
    Button dif;
    Button parf;
    Button bien;
    AccessData ad;
    DetailFragment fragment;
    MyListFragment fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed);
        Log.d("my id","id");
        AccessData ad = new AccessData(getApplicationContext());
        if(savedInstanceState==null) {
                Bundle extras = getIntent().getBundleExtra("bundle");
                listCard = extras.getParcelable("listCard");
                currentCard = listCard.remove(0);

        }
        else{
            listCard = savedInstanceState.getParcelable("listCard");
            currentCard = savedInstanceState.getParcelable("card");
        }
        tv = (TextView) findViewById(R.id.carte);
        fragment2 = (MyListFragment) getFragmentManager()
                .findFragmentById(R.id.listFragment);
        if(fragment2 != null)
        tv.setText(currentCard.getQuestion());
        supr = (Button) findViewById(R.id.supprimer);
        dif = (Button) findViewById(R.id.difficile);
        parf = (Button) findViewById(R.id.parfait);
        bien = (Button) findViewById(R.id.bien);
        fragment = (DetailFragment) getFragmentManager()
                .findFragmentById(R.id.detailFragment);

    }

    @Override
    public void onRssItemSelected(String link) {
        //Ajout du fragment de manière statique

        if (fragment != null && fragment.isInLayout()) {
            //**********************  cas ou onn est en paysage ********************/////
            fragment.setText(currentCard.getAnswer());

        } else {

            Intent intent = new Intent(getApplicationContext(),
                    DetailActivity.class);
            Bundle b = new Bundle();
            b.putParcelable("card",currentCard);
            b.putParcelable("listCard",listCard);
            intent.putExtra("bundle",b);
            intent.putExtra(DetailActivity.EXTRA_URL, link);


            startActivity(intent);
        }
    }

    @Override
    public void onFragmentInteraction(String link) {
        if(fragment2 != null && fragment2.isInLayout()) {
            int choice = Integer.parseInt(link);
            if (choice == 0)
                ad.delete_card(currentCard.getId());
            else {
                ContentValues cv = new ContentValues();
                cv.put(ad.COLONNE_DATE, System.currentTimeMillis());
                cv.put(ad.COLONNE_DIFF, choice);
                ad = new AccessData(getApplicationContext());
                try {
                    ad.update(currentCard.getId(), cv);
                } catch (Exception e) {
                    Log.d("update after click", e.getMessage());
                }
            }

            if (listCard.isEmpty()) {
                Toast.makeText(getApplicationContext(), "vous avez répondu à toute les questions", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),
                        JeuExistantActivity.class);
                startActivity(intent);
            } else {
                currentCard = listCard.remove(0);
                if (fragment2 != null && fragment2.isInLayout()) {
                    tv.setText(currentCard.getQuestion());
                    fragment.setText("repondez à la question");
                } else {


                    Intent intent = new Intent(getApplicationContext(),
                            Jouer.class);
                    Bundle b = new Bundle();
                    b.putParcelable("card", currentCard);
                    b.putParcelable("listCard", listCard);
                    intent.putExtra("bundle", b);
                    intent.putExtra(DetailActivity.EXTRA_URL, link);
                    startActivity(intent);
                }
            }
        }

    }

    public void retournerCarte(View v) {
        onRssItemSelected(currentCard.getAnswer());

    }
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("card",currentCard);
        outState.putParcelable("listCard",listCard);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // When the user center presses, let them pick a contact.
            Intent intent = new Intent(getApplicationContext(),
                    JeuExistantActivity.class);
            startActivity(intent);
        }
        return false;
    }
}