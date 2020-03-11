package com.example.travel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class Expenses extends AppCompatActivity {
    ListView listView;
    static ArrayList<String> notes=new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        listView=findViewById(R.id.list);
        SharedPreferences sharedPreferences=getApplicationContext()
                .getSharedPreferences("com.example.menu", Context.MODE_PRIVATE);
        HashSet<String> set= (HashSet<String>) sharedPreferences.getStringSet("notes",null);
        if(set==null)
            notes.add("Example note");
        else
            notes=new ArrayList(set);

        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Expenses.this,EmptyNote.class);
                intent.putExtra("noteId",position);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(Expenses.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you Sure!!")
                        .setMessage("Do you want to delete this note")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(position);
                                arrayAdapter.notifyDataSetChanged();
                                SharedPreferences sharedPreferences=getApplicationContext()
                                        .getSharedPreferences("com.example.menu", Context.MODE_PRIVATE);
                                HashSet<String> set=new HashSet<>(Expenses.notes);
                                sharedPreferences.edit().putStringSet("notes",set).apply();
                            }
                        })
                        .setNegativeButton("No",null).show();



                return true;
            }
        });
    }

    public void previous(View view) {
        startActivity(new Intent(Expenses.this,HomeActivity.class));
    }

    public void add(View view) {
        startActivity(new Intent(Expenses.this,EmptyNote.class));
    }
}
