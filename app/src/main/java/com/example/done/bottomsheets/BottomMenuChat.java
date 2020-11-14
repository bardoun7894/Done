package com.example.done.bottomsheets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.done.CancelOrder;
import com.example.done.ChatActivity;
import com.example.done.FragmentToActivity;
import com.example.done.Prevalent;
import com.example.done.R;
import com.example.done.models.ItemNotification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import io.paperdb.Paper;

public class BottomMenuChat extends BottomSheetDialogFragment {
    LinearLayout cancel ;
    private String username;
    private CancelOrder mCallback;


    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        try {
            mCallback = (CancelOrder) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }
    @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.bottom_menu_chat,container,false);
             Paper.init(v.getContext());
              username = getArguments().getString("username");
              cancel =v.findViewById(R.id.cancelOrder_id);
         cancel.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 cancelDemande();

             }
         });
            return  v ;
    }

    private  void cancelDemande() {
        mCallback.cancel(username);
    }


}