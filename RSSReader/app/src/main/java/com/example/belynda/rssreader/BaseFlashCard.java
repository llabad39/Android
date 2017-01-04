package com.example.belynda.rssreader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by lucaslabadens on 22/12/2016.
 */
public class BaseFlashCard extends SQLiteOpenHelper{

    public final static int Version = 3;
    public final static String DB_NAME="base_flashcard";
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

    public final static String CREATE_DECK = "create table " + TABLE_DECK + "("+COLONNE_IDDECK+" integer AUTO_INCREMENT PRIMARY KEY, "+
            COLONNE_TYPEDECK+ " string UNIQUE);";

    public final static String CREATE_CARD = "create table " + TABLE_CARD + "(" + COLONNE_IDCARD + " integer primary key, " + COLONNE_TYPEDECK + " integer references " + TABLE_DECK + ", "
            + COLONNE_QUESTION + " string, " + COLONNE_REPONSE + " string, " + COLONNE_DATE + " integer ,"+ COLONNE_DIFF + " integer ,"
            + COLONNE_MEDIA + " string ," +  "CONSTRAINT u_question UNIQUE (" + COLONNE_TYPEDECK + "," +COLONNE_QUESTION+"));";

    private static BaseFlashCard instance;
    protected BaseFlashCard(Context context) {
        super(context, DB_NAME, null, Version);
    }

    public static BaseFlashCard getInstance(Context context){
        if(instance==null)
            instance = new BaseFlashCard(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("++++++", "onCreate: "+ CREATE_DECK);
        Log.d("++++++", "onCreate: "+ CREATE_CARD);

        db.execSQL(CREATE_DECK);
        db.execSQL(CREATE_CARD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("drop table if exists " + TABLE_CARD);
            db.execSQL("drop table if exists " + TABLE_DECK);
            onCreate(db);
        }
    }
}
