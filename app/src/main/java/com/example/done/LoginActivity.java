package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.done.Fragment.FragmentAccount;
import com.example.done.joinDoc.JoinAsActivity;
import com.example.done.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailEt,passwordEt ;
    Button loginBtn ;
    FirebaseAuth auth;
    String type_of_user;
    DatabaseReference reference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        auth =FirebaseAuth.getInstance();
        emailEt=findViewById(R.id.emailLoginId);
        passwordEt=findViewById(R.id.passwordLoginId);
        loginBtn=findViewById(R.id.loginBtnId);
        loginBtn.setOnClickListener(this);
        Paper.init(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.loginBtnId :
              login();
                break;
         }
    }

    void login(){

        final String email =emailEt.getText().toString().trim();
        final String password =passwordEt.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            emailEt.setError("email is required");
            emailEt.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            passwordEt.setError("password is required");
            passwordEt.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEt.setError("email not match");
            emailEt.requestFocus();
            return;

        }
        if(password.length()<6){
            passwordEt.setError("password must be at least 6 characters");
            passwordEt.requestFocus();
            return;
        }
         AllowAccessToAccount(email, password);
       }

    private void AllowAccessToAccount(final String email, final String password) {

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                final String username = auth.getCurrentUser().getDisplayName();
                Paper.book().write(Prevalent.UserEmailKey,email);
                Paper.book().write(Prevalent.UserPasswordKey,password);
                Paper.book().write(Prevalent.UserNameKey,username);
                reference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                     if(dataSnapshot.exists()){
                            type_of_user = (String) dataSnapshot.child("type_of_user").getValue() ;
                            String numberphone = (String) dataSnapshot.child("mobile_phone").getValue() ;
                         List<String> classification  = (List<String>) dataSnapshot.child("classification").getValue();

                         Paper.book().write(Prevalent.type_of_user,type_of_user);
                        if(classification!=null ) {
                            Paper.book().write(Prevalent.classification,classification);
                          }
                    if(type_of_user.equals("بائع" ) && numberphone == null ) {
                        Intent intent =new Intent(getApplicationContext(),JoinAsActivity.class);
                        startActivity(intent);
                              } else {
                        Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                             }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}