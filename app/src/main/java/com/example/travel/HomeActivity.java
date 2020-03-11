package com.example.travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void hotel(View view) {
        startActivity(new Intent(HomeActivity.this,hotel.class));
    }
    public void place(View view) {
    }
    public void expense(View view) {
        startActivity(new Intent(HomeActivity.this,Expenses.class));
    }
    public void signout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(HomeActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
