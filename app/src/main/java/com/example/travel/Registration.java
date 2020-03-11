package com.example.travel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    EditText t1,t2, t3, t4,t6;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    FirebaseAuth mFirebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        t1 = findViewById(R.id.edit1);
        t2 = findViewById(R.id.edit2);
        t3 = findViewById(R.id.edit3);
        //t4 = findViewById(R.id.edit4);
        //t5 = findViewById(R.id.edit5);
        t6 = findViewById(R.id.edit6);
        mFirebase=FirebaseAuth.getInstance();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();
    }
    public void register(View view) {
        final String pass, email, phone, password,name;
        name=t1.getText().toString();
        email=t2.getText().toString().trim();
        password=t3.getText().toString().trim();
        //pass=t4.getText().toString().trim();
        phone=t6.getText().toString().trim();
        if(name.isEmpty()){
            t1.setError("Please enter your name");
            t1.requestFocus();
        }
        if(email.isEmpty()){
            t2.setError("Please enter your email");
            t2.requestFocus();
        }
        if(password.isEmpty()){
            t3.setError("Enter password");
            t3.requestFocus();
        }
        if(phone.isEmpty()){
            t6.setError("Enter Phone.No");
            t6.requestFocus();
        }
        if(!(phone.length()==10)){
            t6.setError("10 digits requried");
            t6.requestFocus();
        }

        mFirebase.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    user u=new user(name,email,phone);
                    FirebaseDatabase.getInstance().getReference("Travel")
                            .child(FirebaseAuth.getInstance().getUid())
                            .setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Registration.this, "Registration success", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Registration.this,HomeActivity.class);

                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("email",name);
                                startActivity(intent);
                                Registration.this.finish();
                            }
                            else{
                                Toast.makeText(Registration.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Registration.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
