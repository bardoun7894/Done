package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.done.bottomsheets.BottomSheetFilter;
import com.example.done.bottomsheets.BottomSheetLayout;
import com.example.done.models.services;
import com.example.recyclers.FirebaseRecyclerAdapt;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchServicesActivity extends AppCompatActivity {


RecyclerView recyclerView;
ImageView orderByIdIcon ,filterByIdIcon;

      String rating ;

    DatabaseReference serviceRef ,userRef ;
    FirebaseRecyclerAdapt adapter;
      String userN ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_services);
        String title_service = getIntent().getStringExtra("TITLE_SERVICE");
        String title_bar =getIntent().getStringExtra("tasmim");

        recyclerView=findViewById(R.id.recycler_search_services);
        orderByIdIcon =findViewById(R.id.orderById);
        filterByIdIcon =findViewById(R.id.filterById);

        System.out.println(title_bar);
        System.out.println(title_service);



        serviceRef = FirebaseDatabase.getInstance().getReference().child(title_bar).child(title_service);
        recyclerView = findViewById(R.id.recycler_search_services) ;

        FirebaseRecyclerOptions<services> options = new FirebaseRecyclerOptions.Builder<services>()
                .setQuery(serviceRef, services.class).build();

        orderByIdIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          BottomSheetLayout bsl =new BottomSheetLayout();
          bsl.show(getSupportFragmentManager(),"bottomsheetLayout");
                      }
        });

        filterByIdIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             BottomSheetFilter bslF =new BottomSheetFilter();
             bslF.show(getSupportFragmentManager(),"bottomsheetFilter");
              }
        });
        adapter = new FirebaseRecyclerAdapt(options,"2",userN) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        System.out.println("DD"+userN);
        userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userN).child("rating");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                  System.out.println(dataSnapshot.getValue());
                  System.out.println("dllsdklsjkldjskj");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}