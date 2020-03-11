package com.example.travel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText t1,t2;
    Button b1;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=findViewById(R.id.e1);
        t2=findViewById(R.id.e2);
        preferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=preferences.edit();
        auth=FirebaseAuth.getInstance();

    }
    public void sql() {
        String user,pass;
        user = t1.getText().toString().trim();
        pass = t2.getText().toString().trim();
        auth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    MainActivity.this.finish();
                }else{
                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void next(View view) {
        startActivity(new Intent(MainActivity.this,Registration.class));
    }

    public void login(View view) {
        sql();
    }
}
