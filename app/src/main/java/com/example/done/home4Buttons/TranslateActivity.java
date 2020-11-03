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

public class TranslateActivity extends AppCompatActivity implements View.OnClickListener  {
    Button btnOtherTranslate ;
    ImageView backTranslateIv;
    RecyclerView recyclerView;
    ArrayList<ItemServices> ar =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        btnOtherTranslate =findViewById(R.id.otherTranslateId);
        backTranslateIv =findViewById(R.id.backTranslateId);
        ar.add(new ItemServices(getString(R.string.dirasat_lhala),R.drawable.tasmim_prochor));
        ar.add(new ItemServices(getString(R.string.kitabat_sira),R.drawable.tasmim_cv));
        ar.add(new ItemServices(getString(R.string.ma9alat_post),R.drawable.kartasiyat));
        ar.add(new ItemServices(getString(R.string.ui_ux_test),R.drawable.tasmim_kitab));
        ar.add(new ItemServices(getString(R.string.sinario),R.drawable.tasmim_ghilaf));
        ar.add(new ItemServices(getString(R.string.translateEditing),R.drawable.tasmim_ghilaf));
        ar.add(new ItemServices(getString(R.string.wasf_montaj),R.drawable.kartasiyat));
        ar.add(new ItemServices(getString(R.string.mohtawa_ibda3i),R.drawable.kartasiyat));
        ar.add(new ItemServices(getString(R.string.mohtawa_tawasol),R.drawable.kartasiyat));

        recyclerView =findViewById(R.id.recycler_translate);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerItem(ar,this,getString(R.string.translateEditing)));

        btnOtherTranslate.setOnClickListener(this);
        backTranslateIv.setOnClickListener(this);

    }
    @Override
    protected void onStart() {
        super.onStart();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backTranslateId :
                finish();
                break;
            case R.id.otherProgrammingId :
                Intent intent =new Intent(getBaseContext(), SearchServicesActivity.class);
                intent.putExtra( "tasmim",getString(R.string.translateEditing));
                intent.putExtra( "TITLE_SERVICE","أخرى");
                startActivity(intent);
                break;
        }
    }
}