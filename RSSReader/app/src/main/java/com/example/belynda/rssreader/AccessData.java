package com.example.belynda.rssreader;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by lucaslabadens on 24/12/2016.
 */

public class AccessData {
    ContentResolver cr;
    DownloadManager downloadManager;
    private static final String authority= "flashcardcontentprovider";

    public final static  String TABLE_DECK = "deck_table";
    public final static  String TABLE_CARD = "card_table";
    public final static String COLONNE_IDDECK= "idDeck";
    public final static String COLONNE_IDCARD="idCard";
    public final static String COLONNE_TYPEDECK = "typeDeck";
    public final static String COLONNE_QUESTION="question";
    public final static String COLONNE_REPONSE = "reponse";
    public final static String COLONNE_DATE = "date";
    public final static String COLONNE_DIFF = "diff";
    public final static String COLONNE_MEDIA = "media";
    public final static String ONE_CARD = "card";
    public final static String ONE_DECK = "deck";


    public AccessData(Context context){
        cr = context.getContentResolver();
        downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);

    }

    public boolean insert_deck(ContentValues cv){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(TABLE_DECK);
        Uri uri = builder.build();
        try {
            Uri ur = cr.insert(uri, cv);
            if(ur.getLastPathSegment().equals("-1")) {
                Log.d("insert_deck", "fail");
                return false;
            }
            else{

                Log.d("insert_deck", "sucess"+ur);
                return true;
            }
        }
        catch (Exception e){
            Log.d("insert_deck",e.getMessage());
            return false;
        }
    }

    public boolean insert_card(ContentValues cv){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(TABLE_CARD);
        Uri uri = builder.build();
        try {
            Uri ur  = cr.insert(uri, cv);
            if(ur.getLastPathSegment().equals("-1")) {
                Log.d("insert_card", "fail");
                return false;
            }
            else{

                Log.d("insert_card", "sucess"+ur);
                return true;
            }

        }
        catch (Exception e){
            Log.d("insert_card","fail"+e.getMessage());
            return false;
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
    public void delete_deck(String name){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(ONE_DECK).appendPath(name);
        Uri uri = builder.build();
        try{
            cr.delete(uri,null,null);
            Log.d("delete_deck "+name+" :","sucess");
        }
        catch (Exception e){
            Log.d("delete_deck "+name+" :",e.getMessage());

        }
    }

    public ArrayList<Deck> getAllDeck(){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(TABLE_DECK);
        Uri uri = builder.build();
        Cursor c = cr.query(uri,null,null,null,null);
        if(c.moveToFirst()){
            ArrayList<Deck> list = new ArrayList<Deck>();
            do{
                list.add(new Deck(c.getString(c.getColumnIndex(COLONNE_TYPEDECK)),c.getInt(c.getColumnIndex(COLONNE_IDDECK))));
            }while(c.moveToNext());
            return list;
        }
        return new ArrayList<Deck>();
    }

    /*public ArrayList<Card> getAllCardFromDeck(String name){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(TABLE_CARD);
        Uri uri = builder.build();

    }*/
    public ListCard getTheHardestOldest(String id, int nbr){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(TABLE_CARD);
        Uri uri = builder.build();
        Cursor c = cr.query(uri,new String[]{COLONNE_IDCARD,COLONNE_QUESTION,COLONNE_REPONSE},COLONNE_TYPEDECK+"=?",new String[]{id},COLONNE_DATE+ " ,"+COLONNE_DIFF + " DESC");
        if(c.moveToFirst()){

            int acc = 0;
            ListCard list = new ListCard();
            do {
                acc++;
                list.add(new Card(c.getString(c.getColumnIndex(COLONNE_QUESTION)), c.getString(c.getColumnIndex(COLONNE_REPONSE)), c.getInt(c.getColumnIndex(COLONNE_IDCARD))));
            } while (c.moveToNext() && acc < nbr);
            Log.d("nb card"," "+list.size());
            return list;
        }
        else
            throw new UnsupportedOperationException("No card in this deck");
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

    public void initDeck(){
        ContentValues cv = new ContentValues();
        cv.put(COLONNE_TYPEDECK,"informatique");
        insert_deck(cv);
    }

    public void initCard(){
        ContentValues cv = new ContentValues();
        cv.put(COLONNE_IDDECK,1);
        cv.put(COLONNE_QUESTION,"est ce que sa marche");
        cv.put(COLONNE_REPONSE,"oui");
        cv.put(COLONNE_DATE,System.currentTimeMillis());
        insert_card(cv);
    }

    public ContentValues getLineTableDeck(String data){
        ContentValues  c = new ContentValues();
        c.put(COLONNE_TYPEDECK,data);
        return c;
    }



    public ContentValues getLineTableCard(String data,String deck){
        String tab[] = data.split(";");
        ContentValues  c = new ContentValues();
        c.put(COLONNE_TYPEDECK,deck);
        c.put(COLONNE_QUESTION,tab[0]);
        c.put(COLONNE_REPONSE,tab[1]);
        c.put(COLONNE_DATE,System.currentTimeMillis());
        c.put(COLONNE_DIFF,Integer.parseInt(tab[2]));
        c.put(COLONNE_MEDIA,tab[3]);
        return c;
    }
}
