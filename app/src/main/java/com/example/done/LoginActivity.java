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

import com.example.done.Fragment.FragmentAccount;
import com.example.done.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailEt,passwordEt ;
    Button loginBtn ;
    Intent intent;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
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
                String username=  auth.getCurrentUser().getDisplayName();
                Paper.book().write(Prevalent.UserEmailKey,email);
                Paper.book().write(Prevalent.UserPasswordKey,password);
                Paper.book().write(Prevalent.UserNameKey,username);
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

}