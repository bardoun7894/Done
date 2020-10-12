package com.example.done;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerItem extends RecyclerView.Adapter<RecyclerItem.ViewHolder> {
    List<ItemChat> itemChatList ;
    public RecyclerItem(List<ItemChat> itemChatList) {
        this.itemChatList =itemChatList;
    }

    @NonNull
    @Override
    public RecyclerItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false );
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerItem.ViewHolder holder, int position) {
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
