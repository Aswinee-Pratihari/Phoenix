package com.example.hackathonapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class problems extends AppCompatActivity {
    EditText et1,et2,et3;
    Button button,upload,save;
    ImageView pic;
    private final int IMG_REQUEST_ID=1;
    private Uri u;
    FirebaseStorage storage;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems);
        et1=findViewById(R.id.location);
        et2=findViewById(R.id.problem);
        et3=findViewById(R.id.description);
        button=findViewById(R.id.done);
        upload=findViewById(R.id.upload);
        pic=findViewById(R.id.pic);
        save=findViewById(R.id.save);
        save.setEnabled(false);

        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        Intent intent=getIntent();


//        image
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(i,"select picture"),IMG_REQUEST_ID);
                startActivityForResult(Intent.createChooser(i,"select picture"),IMG_REQUEST_ID);
            }
        });



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog p=new ProgressDialog(problems.this);
                p.setTitle("Uploading..");
                p.show();

                if (u!=null){
                    StorageReference reference=storageReference.child("picture/"+ UUID.randomUUID().toString());
                    reference.putFile(u).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            p.dismiss();
                            Toast.makeText(problems.this, "saved", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(problems.this, "failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                upload.setEnabled(true);
                save.setEnabled(false);
            }
        });




        database g=new database(this);
//        SQLiteDatabase db = g.getReadableDatabase();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location=et1.getText().toString();
                String problem=et2.getText().toString();
                String description=et3.getText().toString();
                if(location.equals("")||problem.equals("")||description.equals("")){
                    Toast.makeText(problems.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean i=g.insert_data(location,problem,description);
                    if (i==true){
                        Toast.makeText(problems.this, "Successfully filled", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(problems.this, "Not Successful", Toast.LENGTH_SHORT).show();
                    }
                }

                HashMap<String,Object> m=new HashMap<String,Object>();
                m.put("location",et1.getText().toString());
                m.put("problem",et2.getText().toString());
                m.put("description",et3.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("user").push().setValue(m);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMG_REQUEST_ID && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            u=data.getData();
            try{
                Bitmap bit = MediaStore.Images.Media.getBitmap(getContentResolver(),u);
                pic.setImageBitmap(bit);
                upload.setEnabled(false);
                save.setEnabled(true);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}