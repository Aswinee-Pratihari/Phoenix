package com.example.hackathonapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    private static final String dbname="hackathon.db";
    public database(@Nullable Context context ) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q="create table users(_id integer primary key autoincrement,location text,problem text,description text)";
        sqLiteDatabase.execSQL(q);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        onCreate(sqLiteDatabase);

    }


    public boolean insert_data(String location,String problem,String description)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("location",location);
        c.put("problem",problem);
        c.put("description",description);
        long r=db.insert("users",null,c);
        if (r==-1) return false;
        else return true;
    }
}
