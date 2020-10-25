package com.example.done.joinDoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.done.R;

public class AccountConnected extends AppCompatActivity {
Button continueAccountConnectBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_connected);
        continueAccountConnectBtn =findViewById(R.id.continueAccountConnectBtnId);
        continueAccountConnectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(),SecurityAccount.class);
                startActivity(intent);
            }
        });
    }
}