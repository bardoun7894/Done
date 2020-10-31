package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.done.R;
import com.example.done.bottomsheets.BottomSheetFilter;
import com.example.done.bottomsheets.BottomSheetLayout;
import com.example.done.models.ItemServiceSearch;
import com.example.done.models.User;
import com.example.done.models.services;
import com.example.recyclers.FirebaseRecyclerAdapt;
import com.example.recyclers.RecyclerItemSearchServices;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchServicesActivity extends AppCompatActivity {


RecyclerView recyclerView;
ImageView orderByIdIcon ,filterByIdIcon;

    String rating  = "";

    DatabaseReference serviceRef ;
    FirebaseRecyclerAdapt adapter;

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
         System.out.println(serviceRef.child("e02ead25-9520-448e-9d5e-43c1d1072ddb"));
//        User user =new User("dreamer" ,"homedreamer1234@gmail.com","moha789" ,0);
//        int number = 50;
//        String s = "يبدأ من " + String.valueOf(number)   +  " ريال"    ;
//        ar.add(new ItemServiceSearch(R.drawable.tasmim_flater,user.getRating()," هنا وصف بسيط للخدمة المقدمة",""));

        recyclerView = findViewById(R.id.recycler_search_services) ;
        FirebaseRecyclerOptions<services> options
                = new FirebaseRecyclerOptions.Builder<services>()
                .setQuery(serviceRef, services.class)
                .build();

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
        adapter = new FirebaseRecyclerAdapt(options,getApplicationContext()) ;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        System.out.println("finish");
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