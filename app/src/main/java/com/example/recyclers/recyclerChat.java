package com.example.recyclers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.done.ChatActivity;
import com.example.done.R;
import com.example.done.models.Item_chat_one;
import com.example.done.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class recyclerChat extends RecyclerView.Adapter<recyclerChat.ViewHolder> {
    List<Item_chat_one> itemChatList ;
    public String imageUrl;
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    FirebaseUser fUser ;
    private Context mContext ;

    public recyclerChat(Context mContext,List<Item_chat_one> itemChatList,String imageUrl) {
        this.itemChatList =itemChatList;
        this.imageUrl=imageUrl;
        this.mContext=mContext;
    }
    @NonNull
    @Override
    public recyclerChat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == MSG_TYPE_RIGHT){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right,parent,false );
            ViewHolder viewHolder =new ViewHolder(view);
            return viewHolder;
        }else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left,parent,false );
            ViewHolder viewHolder =new ViewHolder(view);
            return viewHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull recyclerChat.ViewHolder holder, int position) {
//           holder.imageView.setImageResource();
        Glide.with(mContext).load(imageUrl).into(holder.profile_image);
        holder.show_message_tv.setText(itemChatList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return itemChatList.size();
    }

    @Override
    public int getItemViewType(int position) {

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        if(itemChatList.get(position).getSender().equals(fUser.getUid())){
            return MSG_TYPE_RIGHT ;
        }else{
            return MSG_TYPE_LEFT ;
        }



    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profile_image ;
        TextView show_message_tv  ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.accountImage);
            show_message_tv = itemView.findViewById(R.id.showMessage);

        }
    }
}
