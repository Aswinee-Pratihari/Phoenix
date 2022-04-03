package com.example.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class profile extends AppCompatActivity {
    TextView problem,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_profile);
        problem=findViewById(R.id.problem);
        description=findViewById(R.id.description);

    }
}




//android:ellipsize="end"
//
//        android:maxLines="2"