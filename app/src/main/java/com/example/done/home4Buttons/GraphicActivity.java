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

public class GraphicActivity extends AppCompatActivity {
private FirebaseDatabase  firebasedatabase  =  FirebaseDatabase.getInstance();
private DatabaseReference databaseReference =  firebasedatabase.getReference();
private DatabaseReference first = databaseReference.child("Graphic").child("picture");
RecyclerView recyclerView;
Button btnOtherGraphic;
    ArrayList<ItemServices> ar =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);
        ar.add(new ItemServices("تصميم بروشور",R.drawable.tasmim_prochor));
        ar.add(new ItemServices("تصميم سيرة ذاتية",R.drawable.tasmim_cv));
        ar.add(new ItemServices("تصميم كتاب",R.drawable.tasmim_kitab));
        ar.add(new ItemServices("تصميم غلاف",R.drawable.tasmim_ghilaf));
        ar.add(new ItemServices("تصميم فلاتر",R.drawable.tasmim_flater));
        ar.add(new ItemServices(" قرطاسيات",R.drawable.kartasiyat));
        ar.add(new ItemServices(" تصميم شعار",R.drawable.icon_design));
        ar.add(new ItemServices(" تصميم دعوة",R.drawable.invite_card));
        ar.add(new ItemServices(" تصميم واجهات الويب و الجوال",R.drawable.tasmim_jawal));
        btnOtherGraphic=findViewById(R.id.otherGraphicId);

        btnOtherGraphic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(), SearchServicesActivity.class);
                startActivity(intent);
            }
        });

        recyclerView =findViewById(R.id.recycler_graphic);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerItem(ar,this));
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