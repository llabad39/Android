package com.example.belynda.rssreader;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lucaslabadens on 24/12/2016.
 */

public class AccessData {
    ContentResolver cr;
    private static final String authority= "com.cours.proj.flashcardcontentprovider";

    public final static  String TABLE_DECK = "deck_table";
    public final static  String TABLE_CARD = "card_table";
    public final static String COLONNE_IDDECK= "idDeck";
    public final static String COLONNE_IDCARD="idCard";
    public final static String COLONNE_TYPEDECK = "typeDeck";
    public final static String COLONNE_MAX = "max";
    public final static String COLONNE_QUESTION="question";
    public final static String COLONNE_REPONSE = "reponse";
    public final static String COLONNE_DATE = "date";
    public final static String COLONNE_DIFF = "diff";
    public final static String COLONNE_MEDIA = "media";
    public final static String ONE_CARD = "one_card";
    public final static String ONE_DECK = "one_deck";

    public AccessData(Context context){
        cr = context.getContentResolver();
    }

    public void insert_deck(ContentValues cv){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(TABLE_DECK);
        Uri uri = builder.build();
        try {
            cr.insert(uri, cv);
            Log.d("instert_deck","sucess");
        }
        catch (Exception e){
            Log.d("insert_deck",e.getMessage());
        }
    }

    public void insert_card(ContentValues cv){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(TABLE_CARD);
        Uri uri = builder.build();
        try {
            cr.insert(uri, cv);
            Log.d("instert_card","sucess");
        }
        catch (Exception e){
            Log.d("insert_card",e.getMessage());

        }
    }
    public void delete_card(int id){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(ONE_CARD).appendPath(Integer.toString(id));
        Uri uri = builder.build();
        try{
            cr.delete(uri,null,null);
            Log.d("delete_card "+id+" :","sucess");
        }
        catch (Exception e){
            Log.d("delete_card "+id+" :",e.getMessage());

        }
    }
    public void delete_deck(int id){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(ONE_DECK).appendPath(Integer.toString(id));
        Uri uri = builder.build();
        try{
            cr.delete(uri,null,null);
            Log.d("delete_deck "+id+" :","sucess");
        }
        catch (Exception e){
            Log.d("delete_deck "+id+" :",e.getMessage());

        }
    }
    public ArrayList<Card> getTheHardes(int id, int nbr){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(TABLE_CARD);
        Uri uri = builder.build();
        Cursor c = cr.query(uri,new String[]{COLONNE_IDCARD,COLONNE_QUESTION,COLONNE_REPONSE},null,null,COLONNE_DIFF + " DESC");
        if(c.moveToFirst()){

            int acc = 0;
            ArrayList<Card> list = new ArrayList<Card>();
            do {
                acc++;
                list.add(new Card(c.getString(c.getColumnIndex(COLONNE_QUESTION)), c.getString(c.getColumnIndex(COLONNE_REPONSE)), c.getInt(c.getColumnIndex(COLONNE_IDCARD))));
            } while (c.moveToNext() && acc < nbr);
            return list;
        }
        else
            throw new UnsupportedOperationException("No card in this deck");
    }

    public CursorLoader getTheOldest(int id,int nbr){
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void update(int id,ContentValues cv){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(ONE_CARD).appendPath(Integer.toString(id));
        Uri uri = builder.build();
        try{
            cr.update(uri,cv,null,null);
            Log.d("update_card "+id+" :","sucess");
        }
        catch (Exception e){
            Log.d("update_card "+id+" :",e.getMessage());

        }
    }
}
