package com.example.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//    public  static final String MSG="com.example.hackathonapp.MSG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent=getIntent();
//        database g=new database(this);
//        SQLiteDatabase db = g.getReadableDatabase();


    }


    public void register(View View){
        Intent intent =new Intent(this,Register.class);
//        intent.putExtra(MSG,message);
        startActivity(intent);
    }

    public void login(View View){
        Intent intent =new Intent(this,login.class);
//        intent.putExtra(MSG,message);
        startActivity(intent);
    }
}