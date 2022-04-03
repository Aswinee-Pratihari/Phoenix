package com.example.hackathonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class home extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    CustomAdapter CustomAdapter;
    ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView=findViewById(R.id.recyclerView);
//        CustomAdapter c=FirebaseDatabase.getInstance().getReference().child("vendor1");
        database= FirebaseDatabase.getInstance().getReference("user");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        CustomAdapter =new CustomAdapter(this,list);
        recyclerView.setAdapter(CustomAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User user=dataSnapshot.getValue(User.class);
                    list.add(user);
                }

                CustomAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Intent intent=getIntent();
    }
    public void problems(View View){
        Intent intent =new Intent(this,problems.class);
//        intent.putExtra(MSG,message);
        startActivity(intent);
    }
}