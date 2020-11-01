package com.example.recyclers;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.done.R;
import com.example.done.SearchServicesActivity;
import com.example.done.models.ItemServices;

import java.util.List;

public class RecyclerItem extends RecyclerView.Adapter<RecyclerItem.ViewHolder> {
Context mcontext ;
    List<ItemServices> itemServicesList ;
   public String title ;
    public RecyclerItem(List<ItemServices> itemServicesList, Context mcontext,String title) {
        this.mcontext =mcontext;
        this.title = title;
        this.itemServicesList = itemServicesList;
    }
    @NonNull
    @Override
    public RecyclerItem.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_services,parent,false );
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Picasso.get().load(itemServicesList.get(position).getImageUrl()).into(holder.imageView);
        Glide.with(mcontext).load(itemServicesList.get(position).getImageUrl()).into(holder.imageView);
        holder.nameTextView.setText(itemServicesList.get(position).getName());
        holder.title =title;
    }

    @Override
    public int getItemCount() {
        return itemServicesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView ;
        TextView nameTextView ;
        String title ;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_graphic_service);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(v.getContext(), SearchServicesActivity.class);
                    intent.putExtra("TITLE_SERVICE", nameTextView.getText());
                    intent.putExtra("tasmim",title);
                      v.getContext().startActivity(intent);

                }
            });
           nameTextView = itemView.findViewById(R.id.text_graphic_service);
        }
    }
}
