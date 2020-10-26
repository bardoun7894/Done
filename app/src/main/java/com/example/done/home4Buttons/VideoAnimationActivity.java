package com.example.done.home4Buttons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.done.R;
import com.example.recyclers.RecyclerItem;
import com.example.done.bottomsheets.SearchServicesActivity;
import com.example.done.models.ItemServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VideoAnimationActivity extends AppCompatActivity {
    Button btnOtherVideoAnimation ;
    private FirebaseDatabase  firebasedatabase  =  FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference =  firebasedatabase.getReference();
    private DatabaseReference first = databaseReference.child("Graphic").child("picture");
    RecyclerView recyclerView;
    ArrayList<ItemServices> ar =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_animation);
btnOtherVideoAnimation =findViewById(R.id.otherVideoAnimationId);
        ar.add(new ItemServices(" اعلانات فيديو قصيرة",R.drawable.tasmim_prochor));
        ar.add(new ItemServices("مونتاج الفيديو",R.drawable.tasmim_cv));
        ar.add(new ItemServices("السيرة البيضاء المتحركة",R.drawable.tasmim_kitab));
        ar.add(new ItemServices(" رسوم متحركة ",R.drawable.tasmim_ghilaf));
        ar.add(new ItemServices("عرض التطبيقات ",R.drawable.tasmim_flater));
        ar.add(new ItemServices(" شعار متحرك",R.drawable.kartasiyat));
//        ar.add(new ItemServices(" تصميم شعار",R.drawable.icon_design));
//        ar.add(new ItemServices(" تصميم دعوة",R.drawable.invite_card));
//        ar.add(new ItemServices(" تصميم واجهات الويب و الجوال",R.drawable.tasmim_jawal));
//        ar.add(new ItemServices(" تصميم شعار",R.drawable.icon_design));
//        ar.add(new ItemServices(" تصميم دعوة",R.drawable.invite_card));
//        ar.add(new ItemServices(" تصميم واجهات الويب و الجوال",R.drawable.tasmim_jawal));
        recyclerView =findViewById(R.id.recycler_video);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerItem(ar,this));

        btnOtherVideoAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(), SearchServicesActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        first.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//            while( snapshot.getChildren().iterator().hasNext()){
////                   String name  =snapshot.getKey();
////                   String imageUrl = (String) snapshot.getValue();
////                   ar.add(new ItemServices(name,imageUrl));
//                         }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}