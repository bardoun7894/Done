package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.done.models.ItemNotification;
import com.example.done.models.Services;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class NotificationDetail extends AppCompatActivity {
Button acceptBtn,refuseBtn ;
    ItemNotification itemNotification ;
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

        refuseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemNotification.getDemandeTo() != "") {
                    cancelDemande(v);
                }

            }
        });

    }

    private  void cancelDemande(final View v){
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("otlob");
        if(itemNotification!=null){
            reference2.child(itemNotification.getClassification().split(" : ")[0]).child(itemNotification.getClassification().split(" : ")[1]).child(itemNotification.getUsername()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(v.getContext(), "تم رفض الطلب", Toast.LENGTH_SHORT).show();
                          }
            });
        }

    }


}