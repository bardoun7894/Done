package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.done.Fragment.FragmentHome;
import com.example.done.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView ;
    EditText usernameEt,emailEt,passwordEt ;
    Button registerBtn ;
    Intent intent;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textView =findViewById(R.id.youhaveAccountId);
        usernameEt =findViewById(R.id.usernameId);
        emailEt =findViewById(R.id.emailRegisterId);
        passwordEt =findViewById(R.id.passwordRegisterId);
        registerBtn =findViewById(R.id.registerButtonId);
        auth =FirebaseAuth.getInstance();
        textView.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.youhaveAccountId:
                  intent =new Intent(getBaseContext(),LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.registerButtonId:
             register();
                break;

        }
    }


   void  register(){
      final String email =emailEt.getText().toString().trim();
       final String password =passwordEt.getText().toString().trim();
       final String username =usernameEt.getText().toString().trim();

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
        if(TextUtils.isEmpty(username)){
            usernameEt.setError("username is required");
            usernameEt.requestFocus();
            return;


        }
        if(password.length()<6){
            passwordEt.setError("password must be at least 6 characters");
            passwordEt.requestFocus();
            return;

        }
       System.out.println(" befr f");
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    System.out.println(" success f");

                    User user =new User(username,email,password);
                  FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "User has been registered succesfully", Toast.LENGTH_SHORT).show();
                                intent =new Intent(getBaseContext(), ChatActivity.class);
                                startActivity(intent);
                            }  else{
                                Toast.makeText(RegisterActivity.this, "not registed", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "failed", Toast.LENGTH_SHORT).show();

            }
        });

   }


}