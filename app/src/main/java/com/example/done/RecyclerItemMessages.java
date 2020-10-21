package com.example.done;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.done.models.ItemChat;

import java.util.List;

public class RecyclerItemMessages extends RecyclerView.Adapter<RecyclerItemMessages.ViewHolder> {
    List<ItemChat> itemChatList ;
    public RecyclerItemMessages(List<ItemChat> itemChatList) {
        this.itemChatList =itemChatList;
    }

    @NonNull
    @Override
    public RecyclerItemMessages.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false );
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerItemMessages.ViewHolder holder, int position) {
           holder.imageView.setImageResource(itemChatList.get(position).getmImageResource());
           holder.userTextView.setText(itemChatList.get(position).getmUser());
           holder.hashTextView.setText(itemChatList.get(position).getmHach());
    }

    @Override
    public int getItemCount() {
        return itemChatList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView ;
        TextView userTextView,hashTextView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageItem);
            userTextView = itemView.findViewById(R.id.userItemID);
            hashTextView = itemView.findViewById(R.id.hashID);

        }
    }
}