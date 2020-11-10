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
import com.example.done.models.User;
import com.example.done.models.Services;
import com.example.recyclers.FirebaseRecyclerAdapt;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchServicesActivity extends AppCompatActivity {


RecyclerView recyclerView;
ImageView orderByIdIcon ,filterByIdIcon;

    ArrayList<Services> servicesList ;
    ArrayList<User> userList ;
    DatabaseReference serviceRef ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_services);
        String title_service = getIntent().getStringExtra("TITLE_SERVICE");
        String title_bar =getIntent().getStringExtra("tasmim");
        servicesList =new ArrayList<>();
        userList =new ArrayList<>();
        recyclerView=findViewById(R.id.recycler_search_services);
        orderByIdIcon =findViewById(R.id.orderById);
        filterByIdIcon =findViewById(R.id.filterById);

        System.out.println(title_bar);
        System.out.println(title_service);


        serviceRef = FirebaseDatabase.getInstance().getReference().child(title_bar).child(title_service);
        recyclerView = findViewById(R.id.recycler_search_services) ;

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
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        initData(servicesList);


    }


    void initData(final ArrayList<Services> servicesList) {

        serviceRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                servicesList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                   Services services = snapshot.getValue(Services.class);
                    servicesList.add(services);
                    System.out.println(services.getService_user().getUsername() );
                    System.out.println(servicesList);
                    recyclerView.setAdapter(new FirebaseRecyclerAdapt(servicesList));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}