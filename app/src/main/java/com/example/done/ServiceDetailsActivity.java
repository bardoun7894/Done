package com.example.done;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.done.models.Services;

import java.util.List;

import javax.security.auth.callback.Callback;

public class ServiceDetailsActivity extends AppCompatActivity {
ImageView account_image_detail ;
TextView username_detail,desc_detail,rating_details;
LinearLayout image_of_service ;
    Services services ;
    Button btnOtlob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_service_details);
        account_image_detail=findViewById(R.id.account_image_detailId);
        username_detail=findViewById(R.id.username_detailId);
        image_of_service=findViewById(R.id.image_of_service);
        desc_detail=findViewById(R.id.desc_detail);
        rating_details=findViewById(R.id.rating_details);
        btnOtlob=findViewById(R.id.btnOtlobId);

        Bundle extras = getIntent().getExtras();


        if (extras != null)
        {
            services  = (Services) extras.getSerializable("service");
            getData();
        }

        btnOtlob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(),OtlobActivity.class);
                intent.putExtra("demandeTo",services.getService_user().getUsername());
                startActivity(intent);
            }
        });
    }

   void getData(){

        String image_profile =services.getService_user().getPhotoProfile();
        String userName = services.getService_user().getFirst_name() +" "+ services.getService_user().getLast_name() ;
        String description_detail = services.getService_desc();
        String rating_detail = services.getService_user().getRating();
        String image_of_serv = services.getService_image();

       Glide.with(getApplicationContext()).load(image_profile).into(account_image_detail);

       Glide.with(getApplicationContext()).load(image_of_serv).into(new SimpleTarget<Drawable>() {
           @Override
           public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                   image_of_service.setBackground(resource);
               }
           }
       });

       username_detail.setText(userName);
       desc_detail.setText(description_detail);
       rating_details.setText(rating_detail);

    }

}