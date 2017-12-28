package com.example.user.myapplication.fragments.Notes.data;

import android.net.Uri;
import android.provider.BaseColumns;


/**
 * Created by User on 27.12.2017.
 */

public class NotesListContract {
    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.
    public static final String CONTENT_AUTHORITY = "com.example.user.myapplication.fragments.Notes";
    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final String QUERY_EQUAL              = "=?";
    public static final String PATH_NOTES = "notes";


    public static final class NotesEntry implements BaseColumns{
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DATE = "date";

        public static final String WHERE_TODO_ID                   = _ID + QUERY_EQUAL;

        public static final Uri    CONTENT_URI       = BASE_CONTENT_URI.buildUpon().appendPath(PATH_NOTES).build();
        public static final String CONTENT_TYPE      = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_NOTES;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_NOTES;

    }
}
