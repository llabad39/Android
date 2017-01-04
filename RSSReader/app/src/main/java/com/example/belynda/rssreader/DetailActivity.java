package com.example.belynda.rssreader;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity implements DetailFragment.OnFragmentInteractionListener{

    public static final String EXTRA_URL = "url";
    Card currentCard;
    ListCard listCard;
    TextView details;
    AccessData ad ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Need to check if Activity has been switched to landscape mode
        // If yes, finished and go back to the start Activity
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_detail);
        details = (TextView) findViewById(R.id.detailsText);
        if(savedInstanceState == null) {
            Bundle b = getIntent().getBundleExtra("bundle");
            currentCard = b.getParcelable("card");
            listCard = b.getParcelable("listCard");
        }

        else{
            listCard = savedInstanceState.getParcelable("listCard");
            currentCard = savedInstanceState.getParcelable("card");
        }
        details.setText(currentCard.getAnswer());
        ad = new AccessData(getApplicationContext());

    }
    public void onFragmentInteraction(String link) {

        int choice  = Integer.parseInt(link);
        if(choice==0){
            ad.delete_card(currentCard.getId());
        }
        else {
            ContentValues cv = new ContentValues();
            cv.put(ad.COLONNE_DATE,System.currentTimeMillis());
            cv.put(ad.COLONNE_DIFF,choice);
            ad.update(currentCard.getId(),cv);
        }
        if(listCard.isEmpty()){
            Intent intent = new Intent(getApplicationContext(),
                    JeuExistantActivity.class);
            Toast.makeText(getApplicationContext(),"vous avez répondu à toute les questions", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(getApplicationContext(),
                    Jouer.class);
            Bundle b = new Bundle();
            b.putParcelable("card", currentCard);
            b.putParcelable("listCard", listCard);
            intent.putExtra("bundle", b);
            startActivity(intent);
        }
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
