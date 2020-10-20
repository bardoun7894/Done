package com.example.done;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.done.models.ItemServices;

import java.util.List;

public class RecyclerItemGraphic extends RecyclerView.Adapter<RecyclerItemGraphic.ViewHolder> {
Context mcontext ;
    List<ItemServices> itemServicesList ;
    public RecyclerItemGraphic(List<ItemServices> itemServicesList,Context mcontext) {
this.mcontext =mcontext;
        this.itemServicesList = itemServicesList;
    }
    @NonNull
    @Override
    public RecyclerItemGraphic.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_services,parent,false );
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Picasso.get().load(itemServicesList.get(position).getImageUrl()).into(holder.imageView);
        Glide.with(mcontext).load(itemServicesList.get(position).getImageUrl()).into(holder.imageView);
        holder.nameTextView.setText(itemServicesList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return itemServicesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView nameTextView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_graphic_service);
           nameTextView = itemView.findViewById(R.id.text_graphic_service);
        }
    }
}
