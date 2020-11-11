package com.example.recyclers;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.done.R;
import com.example.done.models.Item_chat_one;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class recyclerChat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ME = 1;
    private static final int VIEW_TYPE_OTHER = 2;
    List<Item_chat_one> mChats;
    Context mContext ;
    String imageProfile ;

    public recyclerChat(List<Item_chat_one> mChats, Context mContext, String imageProfile) {
        this.mChats = mChats;
        this.mContext =mContext;
        this.imageProfile=imageProfile;
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case VIEW_TYPE_ME:
                View viewChatMine = layoutInflater.inflate(R.layout.chat_item_right, parent, false);
                viewHolder = new MyChatViewHolder(viewChatMine);
                break;
            case VIEW_TYPE_OTHER:
                View viewChatOther = layoutInflater.inflate(R.layout.chat_item_left, parent, false);
                viewHolder = new OtherChatViewHolder(viewChatOther);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (TextUtils.equals(mChats.get(position).getSender(),  FirebaseAuth.getInstance().getCurrentUser().getDisplayName())) {
            configureMyChatViewHolder((MyChatViewHolder) holder, position);
        } else {
            configureOtherChatViewHolder((OtherChatViewHolder) holder, position);
        }
    }


    private void configureMyChatViewHolder(final MyChatViewHolder myChatViewHolder, int position) {
        final Item_chat_one chat = mChats.get(position);
        myChatViewHolder.txtChatMessage.setText(chat.getMessage());

    }

    private void configureOtherChatViewHolder(final OtherChatViewHolder otherChatViewHolder, int position) {
        final Item_chat_one chat = mChats.get(position);
        otherChatViewHolder.txtChatMessage.setText(chat.getMessage());
        Glide.with(mContext).load(imageProfile).into(otherChatViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return mChats.size();
    }
    @Override
    public int getItemViewType(int position) {
        if (TextUtils.equals(mChats.get(position).getSender(),FirebaseAuth.getInstance().getCurrentUser().getDisplayName())) {
            return VIEW_TYPE_ME;
        } else {
            return VIEW_TYPE_OTHER;
        }
    }

    private static class MyChatViewHolder extends RecyclerView.ViewHolder {
          TextView txtChatMessage;


        public MyChatViewHolder(View itemView) {
            super(itemView);
            txtChatMessage = (TextView) itemView.findViewById(R.id.showMessage);

        }
    }

    private static class OtherChatViewHolder extends RecyclerView.ViewHolder {
          TextView txtChatMessage;
          ImageView imageView;

        public OtherChatViewHolder(View itemView) {
            super(itemView);

            txtChatMessage = (TextView) itemView.findViewById(R.id.showMessageOther);
            imageView = (ImageView) itemView.findViewById(R.id.accountImage);

        }
    }
}