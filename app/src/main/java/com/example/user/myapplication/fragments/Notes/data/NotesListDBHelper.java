package com.example.user.myapplication.fragments.Notes.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 27.12.2017.
 */

public class NotesListDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "notes.db";

    public NotesListDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_NOTES_TABLE = "CREATE TABLE " + NotesListContract.NotesEntry.TABLE_NAME + " (" +
                NotesListContract.NotesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NotesListContract.NotesEntry.COLUMN_DATE + " INTEGER, " +
                NotesListContract.NotesEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +


                "UNIQUE (" + NotesListContract.NotesEntry.COLUMN_DATE + ", " + NotesListContract.NotesEntry.COLUMN_DESCRIPTION + ") ON " +
                "CONFLICT IGNORE" +
                " );";
        db.execSQL(SQL_CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NotesListContract.NotesEntry.TABLE_NAME);
        onCreate(db);
    }
}
