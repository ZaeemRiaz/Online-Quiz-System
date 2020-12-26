package com.se.onlinequizsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class QuizDbHelper extends SQLiteOpenHelper {
        private static final String TAG = "=== QuizDbHelper ===";

        // Table and column names
        static final String transaction = "Transactions";
        static final String id = "Id";
        static final String date = "Date";
        static final String amount = "Amount";
        static final String description = "Description";

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "QuizDB.db";

        public QuizDbHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE " + transaction + "(" +
                    id + " INTEGER, " +
                    date + " TEXT, " +
                    amount + " REAL, " +
                    description + " TEXT," +
                    "PRIMARY KEY (" + id + "))";
            Log.i(TAG, "onCreate: sql:" + sql);
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + transaction);
            onCreate(db);
        }
    }
