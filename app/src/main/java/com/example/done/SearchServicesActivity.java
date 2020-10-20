package com.example.done;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.done.models.ItemServiceSearch;
import com.example.done.models.ItemServices;

import java.util.ArrayList;

public class SearchServicesActivity extends AppCompatActivity {

RecyclerView recyclerView;
    ArrayList<ItemServiceSearch> ar =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_services);
        recyclerView=findViewById(R.id.recycler_search_services);
        int number = 50;
        String s = "يبدأ من " + String.valueOf(number)   +  " ريال"    ;
        ar.add(new ItemServiceSearch(R.drawable.tasmim_flater,5.0," هنا وصف بسيط للخدمة المقدمة",s ));
        ar.add(new ItemServiceSearch(R.drawable.tasmim_cv,4.0," هنا وصف بسيط للخدمة المقدمة",s ));
        ar.add(new ItemServiceSearch(R.drawable.tasmim_ghilaf,3.0," هنا وصف بسيط للخدمة المقدمة",s ));
        ar.add(new ItemServiceSearch(R.drawable.tasmim_jawal,2.0," هنا وصف بسيط للخدمة المقدمة",s ));
        ar.add(new ItemServiceSearch(R.drawable.tasmim_kitab,1.0," هنا وصف بسيط للخدمة المقدمة",s ));

        recyclerView =findViewById(R.id.recycler_search_services);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerItemSearchServices(ar,this));
    }
}