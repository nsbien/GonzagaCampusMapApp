package com.example.gonzagamapapp;

/**
 * Class that creates the database for a users class
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClassesOpenHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "classesDatabase";
    static final int DATABASE_VERSION = 1;

    static final String TABLE_CLASSES = "tableClasses";
    static final String ID = "_id";
    static final String TITLE = "title";
    static final String BUILDING = "building";
    static final String ROOM_NUMBER = "roomNumber";

    public ClassesOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreate = "CREATE TABLE " + TABLE_CLASSES + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITLE + " TEXT, " +
                BUILDING + " TEXT, " +
                ROOM_NUMBER + " TEXT)";

        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getSelectAllClassesCursor(){

        String sqlSelect = "SELECT * FROM " + TABLE_CLASSES;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlSelect, null);

        return cursor;
    }

    public void insertContact(Classes classes){
        String sqlInsert = "INSERT INTO " + TABLE_CLASSES + " VALUES(null, '" +
                classes.getTitle() + "', '" + classes.getBuilding() +
                "', '" + classes.getRoomNumber() +"')";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteAllClasses(){
        String sqlDelete = "DELETE FROM " + TABLE_CLASSES;

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete);
        db.close();
    }
}

