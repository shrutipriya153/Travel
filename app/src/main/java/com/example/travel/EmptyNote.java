package com.example.travel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class EmptyNote extends AppCompatActivity {
    EditText editText;
    int nodeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_note);
        editText=findViewById(R.id.txt1);
        Intent intent=getIntent();
        nodeId= intent.getIntExtra("noteId",-1);
        if(nodeId!=-1){
            editText.setText(Expenses.notes.get(nodeId));
        }
        else{
            Expenses.notes.add("");
            nodeId=Expenses.notes.size()-1;
            Expenses.arrayAdapter.notifyDataSetChanged();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Expenses.notes.set(nodeId, String.valueOf(s));
                Expenses.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences=getApplicationContext()
                        .getSharedPreferences("com.example.menu", Context.MODE_PRIVATE);
                HashSet<String> set=new HashSet<>(Expenses.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
