package com.se.onlinequizsystem;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public interface Persistable {
    void save(SQLiteDatabase dataStore);

    void load(Cursor dataStore);

    String getType();
}
