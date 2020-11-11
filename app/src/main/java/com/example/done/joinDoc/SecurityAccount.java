package com.example.done.joinDoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.done.MainActivity;
import com.example.done.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SecurityAccount extends AppCompatActivity {

Button btnCont ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_account);
        btnCont=findViewById(R.id.continueProbtnID);

        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              verify();
            }
        });


    }


   void verify(){




       FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
       FirebaseUser user =firebaseAuth.getCurrentUser();

       user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
       Toast.makeText(SecurityAccount.this, "تم ارسال رابط التحقق الى بريدك الالكتروني", Toast.LENGTH_SHORT).show();
               Intent intent =new Intent(getBaseContext(), MainActivity.class);
               startActivity(intent);

           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Log.d("",e.getMessage());
           }
       });
    }
}