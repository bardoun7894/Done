package com.example.done.bottomsheets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.done.ChatActivity;
import com.example.done.Prevalent;
import com.example.done.R;
import com.example.done.models.ItemNotification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class BottomMenuChat extends BottomSheetDialogFragment {
    TextView cancel ;
    private String username;

    @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.bottom_menu_chat,container,false);
       Paper.init(v.getContext());
              username = getArguments().getString("username");
            cancel =v.findViewById(R.id.cancel_order_id);
         cancel.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 cancelDemande(v);
                 Intent intent =new Intent(v.getContext(),ChatActivity.class);
                 v.getContext().startActivity(intent);
             }
         });
            return  v ;


    }
    private  void cancelDemande(final View v){
        ItemNotification itemNotification = Paper.book().read(Prevalent.itemNotification);
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference().child("otlob");
        if(itemNotification!=null){
            reference2.child(itemNotification.getClassification().split(" : ")[0]).child(itemNotification.getClassification().split(" : ")[1]).child(username).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(v.getContext(), "تم الغاء الطلب", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
