package com.example.hackathonapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.myViewHolder>{


    Context context;
    ArrayList<User> list;

    public CustomAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.text_row_item,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        User user=list.get(position);
        holder.problem.setText(user.getProblem());
        holder.location.setText(user.getLocation());
        holder.description.setText(user.getDescription());


        holder.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(this,groupchat.class);
//                context.startActivity(intent);


                Intent intent =new Intent(context,groupchat.class);
//        intent.putExtra(MSG,message);
                context.startActivity(intent);


            }

        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{
        TextView problem,location,description;
        Button button3;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            problem=itemView.findViewById(R.id.problem);
            location =itemView.findViewById(R.id.Location);
            description=itemView.findViewById(R.id.description);
            button3=itemView.findViewById(R.id.button3);

        }
    }

}
