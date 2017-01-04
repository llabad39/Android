package com.example.belynda.rssreader;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyListFragment extends Fragment {
    //est une reference vers l'activité
    private OnItemSelectedListener listener;
    TextView card;

    @Override
    //oncreeView l'interface graphique du fragment,
    // LayoutInflater : construit le fragment à partir d'un layout,
    // ViewGroup : dans lequel on place le fragment utilisé
    //SaveinstanceStatesi le fragment est reconstruit Bundle contient les
    //informations pour reconstruire l'état de Fragment

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rsslist_overview,
                container, false);

        return view;
    }

    public interface OnItemSelectedListener {
        public void onRssItemSelected(String link);
    }

    @Override
    //cree l'attachement entre l'activity et le fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet MyListFragment.OnItemSelectedListener");
        }
    }


    // May also be triggered from the Activity
    public void updateDetail(String typeBoutonPressé) {
        // create fake data
        // String newTime = String.valueOf(System.currentTimeMillis());
        // Send data to Activity

        //maintenant dans une cette méthode le fragment peut passer "hello" à l'activité
        // car l'activité implement l'interface OnItemSelectedLIstener
        listener.onRssItemSelected(typeBoutonPressé);
    }

}