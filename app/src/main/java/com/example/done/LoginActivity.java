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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
              public void onComplete(@NonNull Task<AuthResult> task){
                     if(task.isSuccessful()){
                         Paper.book().write(Prevalent.UserEmailKey,email);
                         Paper.book().write(Prevalent.UserPasswordKey,password);
                         Toast.makeText(LoginActivity.this,"تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
                         intent =new Intent(getBaseContext(), MainActivity.class);
                         startActivity(intent);
                        }else{
                         Toast.makeText(LoginActivity.this, "وقع مشكل اثناء تسجيل الدخول", Toast.LENGTH_SHORT).show();
                      }
                 }
             });
    }

}