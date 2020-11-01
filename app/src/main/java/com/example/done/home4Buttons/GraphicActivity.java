package com.example.done.home4Buttons;

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

import java.util.ArrayList;

public class GraphicActivity extends AppCompatActivity {

RecyclerView recyclerView;
Button btnOtherGraphic;

    ArrayList<ItemServices> ar =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic) ;
        ar.add(new ItemServices(getString(R.string.tasmim_prochor),R.drawable.tasmim_prochor));
        ar.add(new ItemServices(getString(R.string.tasmim_sira_datiya),R.drawable.tasmim_cv));
        ar.add(new ItemServices(getString(R.string.tasmim_kitab),R.drawable.tasmim_kitab));
        ar.add(new ItemServices(getString(R.string.tasmim_ghilaf),R.drawable.tasmim_ghilaf));
        ar.add(new ItemServices(getString(R.string.tasmim_flater),R.drawable.tasmim_flater));
        ar.add(new ItemServices(getString(R.string.kartasiyat),R.drawable.kartasiyat));
        ar.add(new ItemServices(getString(R.string.tasmim_chi3ar),R.drawable.icon_design));
        ar.add(new ItemServices(getString(R.string.tasmim_da3wa),R.drawable.invite_card));
        ar.add(new ItemServices(getString(R.string.tasmim_web_mobile),R.drawable.tasmim_jawal));
        btnOtherGraphic=findViewById(R.id.otherGraphicId);

        btnOtherGraphic.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(), SearchServicesActivity.class);
                intent.putExtra( "tasmim",getString(R.string.tasmim_and_grahic));
                intent.putExtra( "TITLE_SERVICE","أخرى");
                startActivity(intent);
            }
        });
        recyclerView =findViewById(R.id.recycler_graphic);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerItem(ar,this,getString(R.string.tasmim_and_grahic)));
    }



}