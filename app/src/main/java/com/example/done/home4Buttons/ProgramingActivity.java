package com.example.done.home4Buttons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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

public class ProgramingActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOtherProgramming ;
    ImageView backProgrammingIv;
    private FirebaseDatabase firebasedatabase  =  FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference =  firebasedatabase.getReference();
    private DatabaseReference first = databaseReference.child("Graphic").child("picture");
    RecyclerView recyclerView;
    ArrayList<ItemServices> ar =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programing);
        btnOtherProgramming =findViewById(R.id.otherProgrammingId);
        backProgrammingIv =findViewById(R.id.backProgrammingId);
        ar.add(new ItemServices(getString(R.string.database),R.drawable.tasmim_prochor));
        ar.add(new ItemServices(getString(R.string.programing_web),R.drawable.tasmim_cv));
        ar.add(new ItemServices(getString(R.string.application_web),R.drawable.tasmim_kitab));
        ar.add(new ItemServices(getString(R.string.ui_ux_test),R.drawable.tasmim_ghilaf));
        ar.add(new ItemServices(getString(R.string.security_database),R.drawable.tasmim_flater));
        ar.add(new ItemServices(getString(R.string.data_analyses),R.drawable.kartasiyat));

        recyclerView =findViewById(R.id.recycler_programming);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerItem(ar,this,getString(R.string.programmingandtech)));

        btnOtherProgramming.setOnClickListener(this);
        backProgrammingIv.setOnClickListener(this);

    }
    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backProgrammingId :
                finish();
                break;
            case R.id.otherProgrammingId :
                Intent intent =new Intent(getBaseContext(), SearchServicesActivity.class);
                intent.putExtra( "tasmim",getString(R.string.programmingandtech));
                intent.putExtra( "TITLE_SERVICE","أخرى");
                startActivity(intent);
                break;
        }
    }
}