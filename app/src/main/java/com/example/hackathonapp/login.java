package com.example.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText email,password;
    Button login;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email =findViewById(R.id.email2);
        password=findViewById(R.id.usrpassword);
        login=findViewById(R.id.login2);
        auth= FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1=email.getText().toString();
                String password1=password.getText().toString();
                if(email1.equals("")||password1.equals("")){
                    Toast.makeText(login.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                }
                else{
                    login3(email1,password1);
                }
            }
        });


        Intent intent=getIntent();
    }

    private void login3(String email,String password){
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(login.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(login.this, "Successful Login", Toast.LENGTH_SHORT).show();
                nextpage();

            }
        });
    }
    public void nextpage(){
        Intent intent =new Intent(this,home.class);
//        intent.putExtra(MSG,message);
        startActivity(intent);
    }
}