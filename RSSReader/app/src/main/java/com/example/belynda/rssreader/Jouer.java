package com.example.belynda.rssreader;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class Jouer extends Activity implements
        MyListFragment.OnItemSelectedListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rssfeed);
    }

    @Override
    public void onRssItemSelected(String link) {
        //Ajout du fragment de manière statique
        DetailFragment fragment = (DetailFragment) getFragmentManager()
                .findFragmentById(R.id.detailFragment);

        if (fragment != null && fragment.isInLayout()) {
            //**********************  cas ou onn est en paysage ********************/////
            fragment.setText(link);
        } else {
            Intent intent = new Intent(getApplicationContext(),
                    DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_URL, link);
            startActivity(intent);
        }
    }
}