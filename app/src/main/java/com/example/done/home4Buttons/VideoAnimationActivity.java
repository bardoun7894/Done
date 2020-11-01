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
import com.example.done.SearchServicesActivity;
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
        ar.add(new ItemServices(getString(R.string.i3lanat_video_kasir),R.drawable.tasmim_prochor));
        ar.add(new ItemServices(getString(R.string.montaj_video),R.drawable.tasmim_cv));
        ar.add(new ItemServices(getString(R.string.sira_bayda_motaharika),R.drawable.tasmim_kitab));
        ar.add(new ItemServices(getString(R.string.rosom_motaharika),R.drawable.tasmim_ghilaf));
        ar.add(new ItemServices( getString(R.string.ard_tatbi9at),R.drawable.tasmim_flater));
        ar.add(new ItemServices(getString(R.string.logo_motaharika),R.drawable.kartasiyat));

        recyclerView =findViewById(R.id.recycler_video);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerItem(ar,this,getString(R.string.video_animation)));

        btnOtherVideoAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(), SearchServicesActivity.class);
                intent.putExtra( "tasmim",getString(R.string.video_animation));
                intent.putExtra( "TITLE_SERVICE","أخرى");
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