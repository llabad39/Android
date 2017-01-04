package com.example.belynda.rssreader;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DetailFragment extends Fragment {
    private OnFragmentInteractionListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_rssitem_detail,
                container, false);

        final Button supprimer = (Button) view.findViewById(R.id.supprimer);
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail("0");}
        });

        final Button difficile = (Button) view.findViewById(R.id.difficile);
        difficile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail("3");}
        });

        final Button bien = (Button) view.findViewById(R.id.bien);
        bien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail("2");}
        });

        final Button parfait = (Button) view.findViewById(R.id.parfait);
        parfait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail("1");}
        });

        return view;
    }
    public interface OnFragmentInteractionListener{
        public void onFragmentInteraction(String link);
    }
    //cree l'attachement entre l'activity et le fragment
    public void onAttach(Context activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener ){
            listener = (OnFragmentInteractionListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet DetailFragment.OnItemSelectedListener");
        }
    }

    public void setText(String item) {
        TextView view = (TextView) getView().findViewById(R.id.detailsText);
        view.setText(item);
    }
    // May also be triggered from the Activity
    public void updateDetail(String typeBoutonPressé) {
        // create fake data
        // String newTime = String.valueOf(System.currentTimeMillis());
        // Send data to Activity

        //maintenant dans une cette méthode le fragment peut passer "hello" à l'activité
        // car l'activité implement l'interface OnItemSelectedLIstener
        listener.onFragmentInteraction(typeBoutonPressé);
    }
}
