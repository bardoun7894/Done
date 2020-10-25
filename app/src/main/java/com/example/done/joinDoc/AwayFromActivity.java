package com.example.done.joinDoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.done.R;

public class AwayFromActivity extends AppCompatActivity {
Button continueAwayBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_away_from);
        continueAwayBtn =findViewById(R.id.continueAwayBtnId);
        continueAwayBtn.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(), PersonalInformationActivity.class);
             startActivity(intent);
            }
        });
    }
}
