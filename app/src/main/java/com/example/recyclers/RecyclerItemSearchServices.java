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
        import com.example.done.ItemClickListener;
        import com.example.done.R;
        import com.example.done.ServiceDetailsActivity;

public class RecyclerItemSearchServices extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView imageView ;
    public  ItemClickListener itemClickListener;
    public TextView rating_numberTv ,descTv,priceTv ;

    public RecyclerItemSearchServices(@NonNull View itemView) {
        super(itemView);
        imageView =itemView.findViewById(R.id.image_service_searchId);
        rating_numberTv =itemView.findViewById(R.id.rating_numberID);
        descTv =itemView.findViewById(R.id.desc_serviceId);
        priceTv =itemView.findViewById(R.id.price_service);
    }


    public  void setItemClick(ItemClickListener listener){
this.itemClickListener =listener;
}
     @Override
     public void onClick(View v) {
itemClickListener.onClick(v,getAdapterPosition());
     }
 }

