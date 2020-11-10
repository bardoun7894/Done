package com.example.done.joinDoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.done.R;

public class BestProfileActivity extends AppCompatActivity {
Button continueBestProfileBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_profile);
        continueBestProfileBtn=findViewById(R.id.continueBestProfileBtnId);
        continueBestProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          Intent intent =new Intent(getBaseContext(),AwayFromActivity.class);
          startActivity(intent);
            }
        });
    }
}