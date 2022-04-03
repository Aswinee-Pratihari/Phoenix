package com.example.hackathonapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private EditText email,password2;
    private Button register2;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.email);
        password2=findViewById(R.id.password2);
        register2=findViewById(R.id.register2);
        auth=FirebaseAuth.getInstance();

        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email2=email.getText().toString();
                String password=password2.getText().toString();
                if(email2.equals("")||password.equals("")){
                    Toast.makeText(Register.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    regis(email2,password);
                }

            }
        });
        Intent intent=getIntent();

    }

    private void regis(String email,String password){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Register.this, "done", Toast.LENGTH_SHORT).show();
                    switching();

                }
                else {
                    Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void switching(){
        Intent intent =new Intent(this,login.class);
//        intent.putExtra(MSG,message);
        startActivity(intent);
    }
}