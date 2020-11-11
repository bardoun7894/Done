package com.example.done.home4Buttons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.done.R;
import com.example.done.bottomsheets.BottomMenuChat;
import com.example.recyclers.RecyclerItem;
import com.example.done.SearchServicesActivity;
import com.example.done.models.ItemServices;

import java.io.ByteArrayOutputStream;
import java.text.BreakIterator;
import java.util.ArrayList;

public class GraphicActivity extends AppCompatActivity  implements View.OnClickListener {

RecyclerView recyclerView;
Button btnOtherGraphic;
ImageView backImg,searchGraphicIv,searchIconGraphicIv;
   ArrayList<ItemServices> ar =new ArrayList<>();
   ItemServices tasmim_prochor,tasmim_sira_datiya,tasmim_kitab, tasmim_ghilaf ,tasmim_flater,kartasiyat,tasmim_chi3ar,tasmim_da3wa,tasmim_web_mobile;

    private LinearLayout homeGraphicBarLn,searchAppBarGraphicLn;
    private EditText searchBoxGraphicEt;
    private ArrayList<String> l =new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic) ;
        tasmim_sira_datiya = new ItemServices(getString(R.string.tasmim_sira_datiya),R.drawable.tasmim_cv);
        tasmim_prochor = new ItemServices(getString(R.string.tasmim_prochor),R.drawable.tasmim_prochor);
        tasmim_kitab = new ItemServices(getString(R.string.tasmim_kitab),R.drawable.tasmim_kitab);
        tasmim_ghilaf = new ItemServices(getString(R.string.tasmim_ghilaf),R.drawable.tasmim_ghilaf);
        tasmim_flater = new ItemServices(getString(R.string.tasmim_flater),R.drawable.tasmim_flater);
        kartasiyat = new ItemServices(getString(R.string.kartasiyat),R.drawable.kartasiyat);
        tasmim_chi3ar =new ItemServices(getString(R.string.tasmim_chi3ar),R.drawable.icon_design);
        tasmim_web_mobile =new ItemServices(getString(R.string.tasmim_web_mobile),R.drawable.tasmim_jawal);
        tasmim_da3wa =new ItemServices(getString(R.string.tasmim_da3wa),R.drawable.invite_card);

    l.add(getString(R.string.tasmim_prochor));
    l.add(getString(R.string.tasmim_sira_datiya));
    l.add(getString(R.string.tasmim_kitab));
    l.add(getString(R.string.tasmim_ghilaf));
    l.add(getString(R.string.tasmim_flater));
    l.add(getString(R.string.kartasiyat));
    l.add(getString(R.string.tasmim_chi3ar));
    l.add(getString(R.string.tasmim_da3wa));
    l.add(getString(R.string.tasmim_web_mobile));

        addAllServices();

        btnOtherGraphic=findViewById(R.id.otherGraphicId);
        searchGraphicIv=findViewById(R.id.searchGraphicId);
        searchIconGraphicIv=findViewById(R.id.searchIconGraphicId);
        homeGraphicBarLn=findViewById(R.id.homeGraphicBarID);
        searchAppBarGraphicLn=findViewById(R.id.searchAppBarGraphicId);
        searchBoxGraphicEt=findViewById(R.id.searchBoxGraphicId);

        backImg=findViewById(R.id.backGraphicId);
        btnOtherGraphic.setOnClickListener(this);
        searchGraphicIv.setOnClickListener(this);
        searchIconGraphicIv.setOnClickListener(this);
        backImg.setOnClickListener(this);

        recyclerView =findViewById(R.id.recycler_graphic);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerItem(ar,this,getString(R.string.tasmim_and_grahic)));
    }
    private void addAllServices() {
        ar.add(tasmim_prochor);
        ar.add(tasmim_sira_datiya);
        ar.add(tasmim_kitab);
        ar.add(tasmim_ghilaf);
        ar.add(tasmim_flater);
        ar.add(kartasiyat);
        ar.add(tasmim_chi3ar);
        ar.add(tasmim_da3wa);
        ar.add(tasmim_web_mobile);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backGraphicId :
                finish();
                break;
            case R.id.searchIconGraphicId :
                makeSearch();
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(this,3));
                recyclerView.setAdapter(new RecyclerItem(ar,this,getString(R.string.tasmim_and_grahic)));
                break;
            case R.id.searchGraphicId :
                hideAppBar(View.GONE);
                showSearchBar(View.VISIBLE);
                break;
            case R.id.otherGraphicId :
                Intent intent =new Intent(getBaseContext(), SearchServicesActivity.class);
                intent.putExtra( "tasmim",getString(R.string.tasmim_and_grahic));
                intent.putExtra( "TITLE_SERVICE","أخرى");
                startActivity(intent);
                break;
        }
    }

    private void makeSearch() {
        String data =  searchBoxGraphicEt.getText().toString();

        switch (data){
            case"تصميم بروشور":
                ar.clear();
                ar.add(tasmim_prochor) ;
                break;
            case "":
                addAllServices();
                break;
            case "تصميم سيرة ذاتية":
                ar.clear();
                ar.add(tasmim_sira_datiya) ;
                break;
            case "تصميم كتاب":
                ar.clear();
                ar.add(tasmim_kitab) ;
                break;
            case "تصميم غلاف":
                ar.clear();
                ar.add(tasmim_ghilaf) ;
                break;
            case "تصميم فلاتر":
                ar.clear();
                ar.add(tasmim_flater) ;
                break;

            case "قرطاسيات":
                ar.clear();
                ar.add(kartasiyat) ;
                break;
            case "تصميم شعار":
                ar.clear();
                ar.add(tasmim_chi3ar) ;
                break;
            case "تصميم دعوة":
                ar.clear();
                ar.add(tasmim_da3wa) ;
                break;
            case "تصميم واجهات الويب و الجوال":
                ar.clear();
                ar.add(tasmim_web_mobile) ;
                break;
            default:
                addAllServices();
                break;
        }
        hideAppBar(View.VISIBLE);
        showSearchBar(View.GONE);

    }

    private void showSearchBar(int visible) {
        searchAppBarGraphicLn.setVisibility(visible);
    }
    private void hideAppBar(int gone) {
        homeGraphicBarLn.setVisibility(gone);
    }
}
