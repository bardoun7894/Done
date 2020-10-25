package com.example.done;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonalInformationActivity extends AppCompatActivity {
Button continuePersonalInfoBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal_information);
        continuePersonalInfoBtn =findViewById(R.id.continuePersonalInfoBtnId);

        continuePersonalInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),ProAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}