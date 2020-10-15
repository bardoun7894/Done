package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GraphicActivity extends AppCompatActivity {
private FirebaseDatabase firebasedatabase =FirebaseDatabase.getInstance();
private DatabaseReference databaseReference =firebasedatabase.getReference();
private DatabaseReference first =databaseReference.child("Graphic").child("picture");
RecyclerView recyclerView;
    ArrayList<ItemServices> ar =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);
        ar.add(new ItemServices("names","https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg"));
        ar.add(new ItemServices("name","https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg"));
        ar.add(new ItemServices("names","https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg"));
        ar.add(new ItemServices("name","https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg"));
        ar.add(new ItemServices("names","https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg"));
        ar.add(new ItemServices("name","https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg"));
        ar.add(new ItemServices("names","https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg"));
        ar.add(new ItemServices("name","https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg"));
        ar.add(new ItemServices("name","https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg"));
        ar.add(new ItemServices("names","https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg"));
        ar.add(new ItemServices("name","https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg"));
        ar.add(new ItemServices("name","https://cdn.jpegmini.com/user/images/slider_puffin_before_mobile.jpg"));
        System.out.println("peooekk;ck"+ar);
        recyclerView =findViewById(R.id.recycler_graphic);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerItemGraphic(ar));
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