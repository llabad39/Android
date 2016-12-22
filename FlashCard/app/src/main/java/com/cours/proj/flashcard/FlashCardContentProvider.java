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
    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private BaseFlashCard bf ;
    static {
        matcher.addURI(authority, "deck_table", DECK);
        matcher.addURI(authority, "card_table", CARD);
        matcher.addURI(authority, "card/#", ONE_CARD);
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
            default:
                throw new UnsupportedOperationException("Not yet implemented");
        }
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
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
