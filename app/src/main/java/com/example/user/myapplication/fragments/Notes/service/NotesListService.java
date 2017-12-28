package com.example.user.myapplication.fragments.Notes.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.user.myapplication.fragments.Notes.data.NotesListContract;

/**
 * Created by User on 27.12.2017.
 */

public class NotesListService extends IntentService {
    public static final String EXTRA_NOTE_DESCRIPTION = "EXTRA_NOTE_DESCRIPTION";
    private final String LOG_TAG = NotesListService.class.getSimpleName();

    public NotesListService() {
        super("NotesListService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String notedescription = intent.getStringExtra(EXTRA_NOTE_DESCRIPTION);

        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesListContract.NotesEntry.COLUMN_DESCRIPTION, notedescription);

        getContentResolver().insert(NotesListContract.NotesEntry.CONTENT_URI, contentValues);

    }
}
