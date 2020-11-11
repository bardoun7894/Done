package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.done.bottomsheets.BottomMenuChat;
import com.example.done.models.Item_chat_one;
import com.example.done.models.User;
import com.example.recyclers.recyclerChat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
ImageView img ,backchatI,photosPerson,send_chat ;
TextView textUsername;
EditText messageET ;
DatabaseReference databaseReference ;
String username;
FirebaseUser fuser;


    List<Item_chat_one> chatList ;
    RecyclerView recyclerView;
    DatabaseReference reference;
    private List<User> userList;
    private String photos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        username =getIntent().getStringExtra("username");

        photos =getIntent().getStringExtra("photoS");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
          recyclerView=findViewById(R.id.recycler_chat_one);
          recyclerView.setHasFixedSize(true);
          recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        reference = FirebaseDatabase.getInstance().getReference().child("Chats");
        userList =new ArrayList<>();
        chatList =new ArrayList<>();
        databaseReference =FirebaseDatabase.getInstance().getReference("Users").child(username);
        img =findViewById(R.id.menu_chatId);
        photosPerson =findViewById(R.id.photoPerson);
        messageET =findViewById(R.id.messageET);
        send_chat =findViewById(R.id.send_chatId);
        backchatI=findViewById(R.id.backChatId);
        textUsername =findViewById(R.id.usernameChat);
        textUsername.setText(username);
        Glide.with(this.getBaseContext()).load(photos).into(photosPerson);
        img.setOnClickListener(this);
        backchatI.setOnClickListener(this);
        send_chat.setOnClickListener(this);
       fuser= FirebaseAuth.getInstance().getCurrentUser();


readMessages(fuser.getDisplayName(), username,photos);

     }
    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.backChatId :
            finish();
             break;
         case R.id.send_chatId :
             String msg =messageET.getText().toString();
             if(!msg.equals("")){
                 sendMessage(fuser.getDisplayName(), username,msg);
                 messageET.setText("");
                readMessages(fuser.getDisplayName(), username,photos);
             }else{
                  Toast.makeText(this, "لا يمكنك ان ترسل رسالة فارغة", Toast.LENGTH_SHORT).show();
                 }
             break;
       case R.id.menu_chatId :
           BottomMenuChat bslF =new BottomMenuChat();
           bslF.show(getSupportFragmentManager(),"BottomMenuChat");

           break;
     }
    }

    private void readMessages(final String recieverUser, final String me, final String imageUrl){
      chatList = new ArrayList<>();
      reference.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           chatList.clear();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Item_chat_one chat_one = snapshot.getValue(Item_chat_one.class);
          System.out.println(chat_one.getMessage());
            assert chat_one != null;
//            chatList.add(chat_one);

        if(chat_one.getReciever().equals(recieverUser) && chat_one.getSender().equals(me)  ||  chat_one.getReciever().equals(me) && chat_one.getSender().equals(recieverUser) ){

             System.out.println("mohamed");

             chatList.add(chat_one);

                }

            recyclerView.setAdapter(new recyclerChat(chatList,ChatActivity.this,imageUrl));
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});

    }
    private  void sendMessage(String sender,String reciever,String message){

        HashMap<String,Object> hashMap =new HashMap<>();
           hashMap.put("sender",sender);
           hashMap.put("reciever",reciever);
           hashMap.put("message",message);
           reference.push().setValue(hashMap);
    }
}