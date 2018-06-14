package com.example.mateusz.astroweather2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static String databaseName = "savedCities";
    private static String tableName = "cityList";
    private static String col1 = "ID";
    private static String cityName = "cityName";

    public DatabaseHelper(Context context) {
        super(context, tableName, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)", tableName, cityName);
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addData(String item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cityName,item);

        Log.d(databaseName, "addData: Adding " + item + " to " + tableName);

        db.insert(tableName,null, contentValues);
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + tableName;
        Cursor data = db.rawQuery(query,null);
        return data;
    }
}
