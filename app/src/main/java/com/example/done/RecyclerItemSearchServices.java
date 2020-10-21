package com.example.done;
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
        import com.example.done.models.ItemServiceSearch;
        import java.util.List;

public class RecyclerItemSearchServices extends RecyclerView.Adapter<RecyclerItemSearchServices.ViewHolder> {
    Context mcontext ;
    List<ItemServiceSearch> itemServicesSearchList ;
    public RecyclerItemSearchServices(List<ItemServiceSearch> itemServicesSearchList,Context mcontext) {
        this.mcontext =mcontext;
        this.itemServicesSearchList = itemServicesSearchList;
    }
    @NonNull
    @Override
    public RecyclerItemSearchServices.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_service,parent,false );
        ViewHolder viewHolder =new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mcontext).load(itemServicesSearchList.get(position).getImageService()).into(holder.imageView);
        String rating  =String.valueOf(itemServicesSearchList.get(position).getRating_number());
        holder.rating_numberTv.setText(rating);
        holder.descTv.setText(itemServicesSearchList.get(position).getDescription_text());
        holder.priceTv.setText(itemServicesSearchList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return itemServicesSearchList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView ;
        TextView rating_numberTv ,descTv,priceTv ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_service_searchId);
            rating_numberTv = itemView.findViewById(R.id.rating_numberID);
            descTv = itemView.findViewById(R.id.desc_serviceId);
            priceTv = itemView.findViewById(R.id.price_service);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
        public void onClick(View v) {
                  Intent intent =new Intent(v.getContext(),ServiceDetailsActivity.class);
                   v.getContext().startActivity(intent);
                }
            });
        }


    }
}
