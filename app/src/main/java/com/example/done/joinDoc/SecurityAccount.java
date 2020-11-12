package com.example.done.joinDoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.done.MainActivity;
import com.example.done.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SecurityAccount extends AppCompatActivity {

Button btnCont ;
EditText emailVerify,mobilePhone;
DatabaseReference reference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_account);
        reference= FirebaseDatabase.getInstance().getReference().child("Users");
        btnCont=findViewById(R.id.continueProbtnID);
        emailVerify=findViewById(R.id.emailVerifyId);
        mobilePhone=findViewById(R.id.phoneVerifyId);
        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              verify();
            }
        });

    }


   void verify(){
        String email =emailVerify.getText().toString();
       if (TextUtils.isEmpty(email)) {
           emailVerify.setError("المرجو ملأ الحقول");
           emailVerify.requestFocus();
           return;
       }
       final String mobile_phone =mobilePhone.getText().toString();
       if (TextUtils.isEmpty(mobile_phone))
           {
           mobilePhone.setError("المرجو ملأ الحقول");
           mobilePhone.requestFocus();
           return;
           }
       FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();
      final FirebaseUser user = firebaseAuth.getCurrentUser();

       user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {
       Toast.makeText(SecurityAccount.this, "تم ارسال رابط التحقق الى بريدك الالكتروني", Toast.LENGTH_SHORT).show();
               HashMap<String,Object> hashMap=new HashMap();
               hashMap.put("mobile_phone",mobile_phone);
         reference.child(user.getDisplayName()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task){
                       Intent intent =new Intent(getBaseContext(), MainActivity.class);
                       startActivity(intent);
                   }
               });
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Log.d("",e.getMessage());
           }
       });





    }
}