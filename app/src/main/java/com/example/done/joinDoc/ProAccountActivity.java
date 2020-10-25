package com.example.done.joinDoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.done.R;

public class ProAccountActivity extends AppCompatActivity {
Button continueProbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_account);
        continueProbtn =findViewById(R.id.continueProbtnID);
        continueProbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(),AccountConnected.class);
                startActivity(intent);
            }
        });
    }
}