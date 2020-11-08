package com.example.recyclers;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.done.ChatActivity;
import com.example.done.R;
import com.example.done.models.ItemNotification;
import com.example.done.models.User;

import java.util.ArrayList;
import java.util.List;


public class recyclerNotification extends RecyclerView.Adapter<recyclerNotification.ViewHolder> {
    List<ItemNotification> itemNotificationList;
    public recyclerNotification(ArrayList<ItemNotification> itemNotificationList) {
        this.itemNotificationList =itemNotificationList;
    }
    @NonNull
    @Override
    public recyclerNotification.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false );
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //           holder.imageView.setImageResource(itemChatList.get(position).getImageUrl());
        holder.userTextView.setText(itemNotificationList.get(position).getUsername());
        holder.desc_otlob_tv.setText(itemNotificationList.get(position).getDescription());
        holder.class_item_otlob_tv.setText(itemNotificationList.get(position).getClassification().split(" : ")[1]);
    }
    @Override
    public int getItemCount() {
        return itemNotificationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView userTextView  ,desc_otlob_tv,class_item_otlob_tv;

        LinearLayout ln ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            });

            userTextView = itemView.findViewById(R.id.userNotificationItemID);
            desc_otlob_tv = itemView.findViewById(R.id.desc_otlob_item_id);
            class_item_otlob_tv = itemView.findViewById(R.id.class_item_otlob);

        }
    }
}
