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

import com.bumptech.glide.Glide;
import com.example.done.R;
import com.example.done.ServiceDetailsActivity;
import com.example.done.models.Services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FirebaseRecyclerAdapt extends RecyclerView.Adapter<FirebaseRecyclerAdapt.ViewHolder> {
    List<Services> servicesList ;

public FirebaseRecyclerAdapt(List<Services> servicesList){
    this.servicesList =  servicesList;
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_service,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirebaseRecyclerAdapt.ViewHolder holder, final int position) {

        Glide.with(holder.imageView.getContext()).load(servicesList.get(position).getService_image()).into(holder.imageView);
        holder.rating_numberTv.setText(servicesList.get(position).getService_user().getRating());
        holder.descTv.setText(servicesList.get(position).getService_desc());
        String price ="يبدأ من  "+servicesList.get(position).getService_price() + " ريال "  ;
        holder.priceTv.setText(price);
      holder.services_ln.setOnClickListener(new View.OnClickListener() {
             @Override
              public void onClick(View v) {
              Intent intent =new Intent(v.getContext(), ServiceDetailsActivity.class);
              intent.putExtra("service",servicesList.get(position));
              v.getContext().startActivity(intent);
                        }
});
    }
    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView ;
        public TextView rating_numberTv ,descTv,priceTv ;
        LinearLayout services_ln;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.image_service_searchId);
            services_ln =itemView.findViewById(R.id.services_ln);
            rating_numberTv =itemView.findViewById(R.id.rating_numberID);
            descTv =itemView.findViewById(R.id.desc_serviceId);
            priceTv =itemView.findViewById(R.id.price_service);

        }
    }
}
