package com.example.travel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<hotel_POJO> user_students;
    private Context context;

    public Adapter(List<hotel_POJO> listItems, Context context) {
        this.user_students = listItems;
        this.context = context;
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View v= LayoutInflater.from(context).inflate(R.layout.cardview,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        hotel_POJO l=user_students.get(i);
        viewHolder.name.setText(l.getName());
        Picasso.with(context).load(l.getPic()).into(viewHolder.i);
        //Glide.with(context).load(lg.getPic()).into(viewHolder.i);



    }

    @Override
    public int getItemCount() {
        return user_students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
       // private TextView descTextView;
        private ImageView i;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            i=itemView.findViewById(R.id.hotel_img);
        }
    }
}
