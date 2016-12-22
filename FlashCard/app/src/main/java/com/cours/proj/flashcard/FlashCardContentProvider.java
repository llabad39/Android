package com.cours.proj.flashcard;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class FlashCardContentProvider extends ContentProvider {
    private static final String authority = "com.cours.proj.flashcardcontentprovider";
    private static final int DECK = 1;
    private static final int CARD = 2;
    private static final int ONE_CARD=3;
    private static final int ONE_DECK=4;
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private BaseFlashCard bf ;
    static {
        matcher.addURI(authority, "deck_table", DECK);
        matcher.addURI(authority, "card_table", CARD);
        matcher.addURI(authority, "card/#", ONE_CARD);
        matcher.addURI(authority, "deck/#",ONE_DECK);
    }
    public FlashCardContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = bf.getWritableDatabase();
        int ident =  matcher.match(uri);
        int retour;
        switch (ident) {
            case ONE_CARD:
                long idc = ContentUris.parseId(uri);
                retour =db.delete("card_table","idCard = "+idc,null);
                break;
            case ONE_DECK:
                long idd = ContentUris.parseId(uri);
                SQLiteDatabase dr = bf.getReadableDatabase();
                Cursor test = dr.query("card_table",null,"idDeck = " + idd,null,null,null,null);
                if(test.getCount()==0) {
                    retour = db.delete("deck_table", "idDeck = " + idd, null);

                }
                else
                    retour = -2;
                dr.close();
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        db.close();
        return retour;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = bf.getWritableDatabase();
        int ident =  matcher.match(uri);
        Uri.Builder builder = new Uri.Builder();
        long id;
        switch (ident) {
            case CARD:
                id = db.insert("card_table",null,values);
                builder.appendPath("card_table");
                break;
            case DECK:
                id = db.insert("deck_table",null,values);
                builder.appendPath("deck_table");
                break;
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
        builder.authority(authority);
        builder = ContentUris.appendId(builder, id);
        db.close();
        return builder.build();
    }

    @Override
    public boolean onCreate() {
        bf = BaseFlashCard.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase dr = bf.getReadableDatabase();
        int ident = matcher.match(uri);
        Cursor c;
        switch (ident) {
            case CARD:
                c = dr.query("card_table", projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case DECK:
                c = dr.query("deck_table", projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case ONE_CARD:
                long idc = ContentUris.parseId(uri);
                c = dr.query("card_table",new String[]{"question", "reponse"},"idCard = " + idc,selectionArgs,null,null,sortOrder);
                break;
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
