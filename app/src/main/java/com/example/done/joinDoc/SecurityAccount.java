package com.example.done.joinDoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.done.MainActivity;
import com.example.done.R;

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
                Intent intent =new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}