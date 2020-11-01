package com.example.recyclers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.done.R;
import com.example.done.models.services;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRecyclerAdapt extends FirebaseRecyclerAdapter<services,FirebaseRecyclerAdapt.servicesViewHolder> {

 String rating ;
 String userN ;
    public FirebaseRecyclerAdapt(@NonNull FirebaseRecyclerOptions<services> options,String rating,String userN) {
        super(options);
        this.rating=rating ;
        this.userN = userN;
    }

    @Override
    protected void onBindViewHolder(@NonNull servicesViewHolder holder, int i, @NonNull services services) {

        Glide.with(holder.imageView.getContext()).load(services.getService_image()).into(holder.imageView);
        userN =services.getService_price();

        holder.rating_numberTv.setText(services.getService_username());

        holder.descTv.setText(services.getService_desc());
        holder.priceTv.setText(services.getService_price());

    }
    @NonNull
    @Override
    public servicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_service,parent, false);
        return new servicesViewHolder(view);
    }

    public static class servicesViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView ;
        public TextView rating_numberTv ,descTv,priceTv ;

        public servicesViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView =itemView.findViewById(R.id.image_service_searchId);
        rating_numberTv =itemView.findViewById(R.id.rating_numberID);
        descTv =itemView.findViewById(R.id.desc_serviceId);
        priceTv =itemView.findViewById(R.id.price_service);
        }
    }
}
