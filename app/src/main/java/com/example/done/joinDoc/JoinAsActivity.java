package com.example.done.joinDoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.done.R;

public class JoinAsActivity extends AppCompatActivity {
 Button continueBtn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_as);
        continueBtn = findViewById(R.id.continueBtnId);
        continueBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(getBaseContext(),BestProfileActivity.class);
            startActivity(intent);
            }

        });
    }
}