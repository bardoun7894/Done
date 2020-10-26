package com.example.done;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.done.bottomsheets.BottomMenuChat;

public class ChatActivity extends AppCompatActivity {
ImageView img ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        img =findViewById(R.id.menu_chatId);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomMenuChat bslF =new BottomMenuChat();
                bslF.show(getSupportFragmentManager(),"BottomMenuChat");

            }
        });

    }
}