package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.done.Fragment.FragmentNotification;
import com.example.done.bottomsheets.BottomSheetFilter;
import com.example.done.models.ItemNotification;
import com.example.done.models.Services;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class NotificationDetail extends AppCompatActivity {
Button acceptBtn,refuseBtn ;
    ItemNotification itemNotification ;
    private String photoOfDemandeTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);
        acceptBtn=findViewById(R.id.acceptButton);
        refuseBtn=findViewById(R.id.refuseBtn);
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            itemNotification  = (ItemNotification) extras.getSerializable("itemNotification");

        }

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accept();
                Toast.makeText(NotificationDetail.this, "تم قبول الطلب", Toast.LENGTH_SHORT).show();
            }
        });
        refuseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemNotification.getDemandeTo() != "") {
                    cancelDemande(v);
                   }

            }
        });

    }

    private void accept() {
        Intent intent =new Intent(getApplicationContext(),ChatActivity.class);
        intent.putExtra("username",itemNotification.getUsername());
//                    intent.putExtra("photoS",photoOfDemandeTo);
        intent.putExtra("acceptOrder" ,"تم قبول الطلب");
        startActivity(intent);
    }

    private  void cancelDemande(final View v){
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("demandeFromUser");
        if(itemNotification!=null){
            reference2.child(itemNotification.getUsername()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
             Toast.makeText(v.getContext(), "تم رفض الطلب", Toast.LENGTH_SHORT).show();
    getSupportFragmentManager().beginTransaction().add(android.R.id.content, new FragmentNotification()).commit();

          }
            });
        }

    }


}