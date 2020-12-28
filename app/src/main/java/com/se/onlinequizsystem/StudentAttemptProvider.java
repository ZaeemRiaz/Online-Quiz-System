package com.se.onlinequizsystem;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.se.onlinequizsystem.QuizDbHelper;

public class StudentAttemptProvider extends ContentProvider {
    private static final String TAG = "=== StudentAttemptProvider ===";
    private static final String PROVIDER_NAME = "com.se.onlinequizsystem.studentattemptprovider";
    private static final String URL = "content://" + PROVIDER_NAME + "/studentattempt";
    static final Uri CONTENT_URI = Uri.parse(URL);
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI("com.se.onlinequizsystem.studentattemptprovider", "studentattempt", 1);
    }

    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        QuizDbHelper dbHelper = new QuizDbHelper(getContext());
        db = dbHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, "query");
        if (matcher.match(uri) == 1) {
            return db.query("studentAttempt", projection, selection, selectionArgs, null, null, sortOrder);
        }
        Log.d(TAG, "No match");
        String[] cols = {"_ID"};
        return new MatrixCursor(cols);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "insert");
        long rowId = 0;
        if (matcher.match(uri) == 1) {
            rowId = db.insertWithOnConflict("studentAttempt", "", values, SQLiteDatabase.CONFLICT_IGNORE);

            if (rowId > 0) {
                Uri uriRet = ContentUris.withAppendedId(uri, rowId);
                getContext().getContentResolver().notifyChange(uriRet, null);
                return uriRet;
            }
            throw new SQLiteException("Failed to add a record into " + uri);
        }
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete");
        int count = 0;
        if (matcher.match(uri) == 1) {
            count = db.delete("studentAttempt", selection, selectionArgs);
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update");
        int count = 0;
        if (matcher.match(uri) == 1) {
            count = db.update("studentAttempt", values, selection, selectionArgs);
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
        throw new IllegalArgumentException("Unknown URI " + uri);
    }
}
