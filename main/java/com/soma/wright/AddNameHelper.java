package com.soma.wright;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AddNameHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="Wrights";
    private static final int DB_VERSION=1;

    AddNameHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE WRIGHT_LIST (_id INTEGER PRIMARY KEY AUTOINCREMENT, CONTACT TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
/*
    public void InsertName(SQLiteDatabase db,String contact){
        ContentValues nameValues=new ContentValues();
        nameValues.put("CONTACT",contact);
        db.insert("WRIGHT_LIST",null,nameValues);
    }

 */
}
