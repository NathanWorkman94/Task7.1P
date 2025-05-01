package com.example.lostfoundapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lostfoundapp.util.Util;
import com.example.lostfoundapp.model.Advert;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, Util.DATABASE_NAME, factory, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create table with advert properties
        String CREATE_ADVERT_TABLE = "CREATE TABLE " +
                Util.TABLE_NAME + "(" +
                Util.ADVERT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Util.ADVERT_NAME + " TEXT, " +
                Util.ADVERT_PHONE + " TEXT, " +
                Util.ADVERT_DATE + " DATE, " +
                Util.ADVERT_LOCATION + " TEXT, " +
                Util.ADVERT_DESCRIPTION + " TEXT, " +
                Util.ADVERT_TYPE + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_ADVERT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(db);
    }

    public long insertAdvert (Advert advert)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        // Create key value map for column names and values
        ContentValues values = new ContentValues();

        // Insert data into table with advert properties
        values.put(Util.ADVERT_NAME, advert.getName());
        values.put(Util.ADVERT_PHONE, advert.getPhone());
        values.put(Util.ADVERT_DATE, advert.getDate());
        values.put(Util.ADVERT_LOCATION, advert.getLocation());
        values.put(Util.ADVERT_DESCRIPTION, advert.getDescription());
        values.put(Util.ADVERT_TYPE, advert.getType());

        long newRowId = db.insert(Util.TABLE_NAME, null, values);

        // Close to prevent memory leaks
        db.close();

        return newRowId;
    }

    // Returns a list of all adverts in the database
    public List<Advert> getAllAdverts()
    {
        // Create empty list
        List<Advert> advertList = new ArrayList<>();

        // Open readable database and query all adverts
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Util.TABLE_NAME, null);

        // Loop through the results of the query
        if (cursor.moveToFirst())
        {
            do {
                // Create advert object
                Advert advert = new Advert();

                // Set advert properties
                advert.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Util.ADVERT_ID)));
                advert.setName(cursor.getString(cursor.getColumnIndexOrThrow(Util.ADVERT_NAME)));
                advert.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(Util.ADVERT_PHONE)));
                advert.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Util.ADVERT_DATE)));
                advert.setLocation(cursor.getString(cursor.getColumnIndexOrThrow(Util.ADVERT_LOCATION)));
                advert.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(Util.ADVERT_DESCRIPTION)));
                advert.setType(cursor.getString(cursor.getColumnIndexOrThrow(Util.ADVERT_TYPE)));

                // Add advert to list
                advertList.add(advert);
                }

                // move to next row
                while (cursor.moveToNext());
        }

        // Close the cursor and db
        cursor.close();
        db.close();

        return advertList;
    }

    public void deleteAdvertById(int id)
    {
        // Open writable database
        SQLiteDatabase db = this.getWritableDatabase();

        // Delete advert from database
        db.delete(Util.TABLE_NAME, Util.ADVERT_ID + " = ?", new String[]{String.valueOf(id)});

        // Close db
        db.close();
    }
}
