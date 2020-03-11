package com.example.travel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class hotel extends AppCompatActivity {
    FirebaseAuth mAuth;
    DatabaseReference mRef;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    String uid;
    ChildEventListener childEventListener;
    private List<hotel_POJO> lt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(hotel.this,MainActivity.class));
        }
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lt=new ArrayList<hotel_POJO>();


//        uid=mAuth.getCurrentUser().getUid();
       mRef= FirebaseDatabase.getInstance().getReference().child("Hotel");
       // loadRecyclerViewData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mRef= FirebaseDatabase.getInstance().getReference().child("Hotel");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //              lt.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String name=ds.child("name").getValue(String.class);
                    String pic=ds.child("pic").getValue(String.class);
                    Log.d("name",name);
                    Log.d("pic",pic);
                    hotel_POJO hp=new hotel_POJO(name,pic);
                    lt.add(hp);
                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        adapter=new Adapter(lt,getApplicationContext());
        recyclerView.setAdapter(adapter);


    }

    private void loadRecyclerViewData() {

    }
}
