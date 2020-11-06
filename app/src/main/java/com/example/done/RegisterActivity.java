package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.done.Fragment.FragmentHome;
import com.example.done.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView ;
    EditText usernameEt,emailEt,passwordEt ;
    Button registerBtn ;
    Intent intent;
    FirebaseAuth auth;
    RadioGroup radioGroup;
    RadioButton radioButton,rs,rb ;
    String type_of_user ="";
    int selectedId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        textView =findViewById(R.id.youhaveAccountId);
        usernameEt =findViewById(R.id.usernameId);
        emailEt =findViewById(R.id.emailRegisterId);
        passwordEt =findViewById(R.id.passwordRegisterId);
        registerBtn =findViewById(R.id.registerButtonId);

        radioGroup=findViewById(R.id.radio_group_id);
         rs=findViewById(R.id.sell_radio_id);
         rb=findViewById(R.id.buy_radio_id);
          auth = FirebaseAuth.getInstance();
          textView.setOnClickListener(this);
          registerBtn.setOnClickListener(this);

       radioButton  = (RadioButton) findViewById(selectedId);


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


   void  register() {
       if(rs.isChecked()){
           type_of_user =rs.getText().toString();
           Toast.makeText(RegisterActivity.this,rs.getText(), Toast.LENGTH_SHORT).show();
       }
       if(rb.isChecked()){
           type_of_user =rb.getText().toString();
           Toast.makeText(RegisterActivity.this,rb.getText(), Toast.LENGTH_SHORT).show();
       }
       if(type_of_user.equals("")){
          Toast.makeText(RegisterActivity.this,"هل أنت بائع ام مشتري ؟", Toast.LENGTH_SHORT).show();
           return;
       }
       final String email = emailEt.getText().toString().trim();
       final String password = passwordEt.getText().toString().trim();
       final String username = usernameEt.getText().toString().trim();

       if (TextUtils.isEmpty(email)) {
           emailEt.setError("email is required");
           emailEt.requestFocus();
           return;
       }
       if (TextUtils.isEmpty(password)) {
           passwordEt.setError("password is required");
           passwordEt.requestFocus();
           return;

       }
       if (TextUtils.isEmpty(username)) {
           usernameEt.setError("username is required");
           usernameEt.requestFocus();
           return;
       }
       if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
           emailEt.setError("email not match");
           emailEt.requestFocus();
           return;

       }
       if (password.length() < 6) {
           passwordEt.setError("password must be at least 6 characters");
           passwordEt.requestFocus();
           return;
       }

       auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()) {
                   // Sign in is successful
                   FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                   UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                           .setDisplayName(username).build();

                   assert firebaseUser != null;
                   firebaseUser.updateProfile(profileUpdates)
                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                          if (task.isSuccessful()) {
                           Log.d("TAG", "User profile updated.");
                          }
                               }
                           });
                   final DatabaseReference databaseReference;
                   databaseReference = FirebaseDatabase.getInstance().getReference();
                   HashMap<String,Object> hashMap =new HashMap<String,Object>();
                   hashMap.put("username",username);
                   hashMap.put("email",email);
                   hashMap.put("password",password);
                   hashMap.put("rating","0");
                   hashMap.put("type_of_user",type_of_user);
                   databaseReference.child("Users").child(username).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                       public void onComplete(@NonNull Task<Void> task) {
                      Intent intent =new Intent(getApplicationContext(),LoginActivity.class);
                      startActivity(intent);
                     }
                            });
                     }
                   }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });

   }
}