package com.example.done;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.done.bottomsheets.BottomMenuChat;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
ImageView img ,backchatI    ;
TextView textUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String s =getIntent().getStringExtra("username");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        img =findViewById(R.id.menu_chatId);
        backchatI=findViewById(R.id.backChatId);
        textUsername =findViewById(R.id.usernameChat);
        textUsername.setText(s);
        img.setOnClickListener(this);
        backchatI.setOnClickListener(this);
                 }
    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.backChatId :
            finish();
             break;
       case R.id.menu_chatId :
           BottomMenuChat bslF =new BottomMenuChat();
           bslF.show(getSupportFragmentManager(),"BottomMenuChat");

           break;
     }
    }
}