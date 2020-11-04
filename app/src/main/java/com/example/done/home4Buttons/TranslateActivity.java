package com.example.done.home4Buttons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.done.R;
import com.example.recyclers.RecyclerItem;
import com.example.done.SearchServicesActivity;
import com.example.done.models.ItemServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class TranslateActivity extends AppCompatActivity implements View.OnClickListener  {
    Button btnOtherTranslate ;
    ImageView backTranslateIv,searchTranslateIv,searchIconTranlsateIv;
    RecyclerView recyclerView;
    private LinearLayout homeTranslateBarLn,searchAppBarTranslateLn;
    EditText searchBoxTranslateEt;
    ItemServices dirasat_lhala, kitabat_sira,ma9alat_post,tajribat_mostakhdim,sinario,tarjama,wasf_montaj,mohtawa_ibda3i,mohtawa_tawasol ;

    ArrayList<ItemServices> ar =new ArrayList<>();

    private ArrayList<String> l=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        dirasat_lhala =new ItemServices(getString(R.string.dirasat_lhala),R.drawable.tasmim_prochor);
        kitabat_sira =new ItemServices(getString(R.string.kitabat_sira),R.drawable.tasmim_cv);
        ma9alat_post =new ItemServices(getString(R.string.ma9alat_post),R.drawable.kartasiyat);
        tajribat_mostakhdim =new ItemServices(getString(R.string.tajribat_mostakhdim),R.drawable.tasmim_kitab);
        sinario =new ItemServices(getString(R.string.sinario),R.drawable.tasmim_ghilaf);
        tarjama =new ItemServices(getString(R.string.tarjama),R.drawable.tasmim_ghilaf);
        wasf_montaj =new ItemServices(getString(R.string.wasf_montaj),R.drawable.kartasiyat);
        mohtawa_ibda3i =new ItemServices(getString(R.string.mohtawa_ibda3i),R.drawable.kartasiyat);
        mohtawa_tawasol =new ItemServices(getString(R.string.mohtawa_tawasol),R.drawable.kartasiyat);

        l.add(getString(R.string.dirasat_lhala));
        l.add(getString(R.string.kitabat_sira));
        l.add(getString(R.string.ma9alat_post));
        l.add(getString(R.string.tajribat_mostakhdim));
        l.add(getString(R.string.sinario));
        l.add(getString(R.string.tarjama));
        l.add(getString(R.string.wasf_montaj));
        l.add(getString(R.string.mohtawa_ibda3i));
        l.add(getString(R.string.mohtawa_tawasol));

        btnOtherTranslate =findViewById(R.id.otherTranslateId);
        backTranslateIv =findViewById(R.id.backTranslateId);

        searchAppBarTranslateLn=findViewById(R.id.searchAppBartranslateId);
        homeTranslateBarLn =findViewById(R.id.homeTranslateBarID);

        searchTranslateIv=findViewById(R.id.searchTranslateId);
        searchIconTranlsateIv=findViewById(R.id.searchIconTranslateId);
        searchBoxTranslateEt =findViewById(R.id.searchBoxTranslateId);
         addAllServices();

        recyclerView =findViewById(R.id.recycler_translate);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerItem(ar,this,getString(R.string.translateEditing)));

        searchTranslateIv.setOnClickListener(this);
        searchIconTranlsateIv.setOnClickListener(this);
        btnOtherTranslate.setOnClickListener(this);
        backTranslateIv.setOnClickListener(this);
    }

    private void addAllServices() {
        ar.add(dirasat_lhala);
        ar.add(kitabat_sira);
        ar.add(ma9alat_post);
        ar.add(tajribat_mostakhdim);
        ar.add(sinario);
        ar.add(tarjama);
        ar.add(wasf_montaj);
        ar.add(mohtawa_ibda3i);
        ar.add(mohtawa_tawasol);
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
            case R.id.searchIconTranslateId :
                makeSearch();
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(this,3));
                recyclerView.setAdapter(new RecyclerItem(ar,this,getString(R.string.tasmim_and_grahic)));
                break;
            case R.id.searchTranslateId :
                hideAppBar(View.GONE);
                showSearchBar(View.VISIBLE);
                break;
            case R.id.otherTranslateId :
                Intent intent =new Intent(getBaseContext(), SearchServicesActivity.class);
                intent.putExtra( "tasmim",getString(R.string.translateEditing));
                intent.putExtra( "TITLE_SERVICE","أخرى");
                startActivity(intent);
                break;
        }
    }

    private void showSearchBar(int visible) {
        searchAppBarTranslateLn.setVisibility(visible);
    }

    private void hideAppBar(int gone) {
        homeTranslateBarLn.setVisibility(gone);
    }

    private void makeSearch() {
        String data =  searchBoxTranslateEt.getText().toString();
        for(int i=0;i<l.size();i++){
            if(data.contains(l.get(i).subSequence(0,4))){
                data =l.get(i);
            }
        }
        switch (data){
         case "دراسة الحالة":
                ar.clear();
                ar.add(dirasat_lhala) ;
                break;
         case "كتابة السيرة الذاتية":
                ar.clear();
                ar.add(kitabat_sira) ;
                break;
          case "مقالات و منشورات":
                ar.clear();
                ar.add(ma9alat_post) ;
                break;
         case "كتابة تجربة المستخدم":
                ar.clear();
                ar.add(tajribat_mostakhdim) ;
                break;
         case "كتابة السيناريو":
                ar.clear();
                ar.add(sinario) ;
                break;

         case "ترجمة":
                ar.clear();
                ar.add(tarjama) ;
                break;

         case "وصف منتج":
                ar.clear();
                ar.add(wasf_montaj) ;
                break;

         case "كتابة محتوى ابداعي":
                ar.clear();
                ar.add(mohtawa_ibda3i) ;
                break;

         case "كتابة محتوى لوسائل التواصل الاجتماعي":
                ar.clear();
                ar.add(mohtawa_tawasol) ;
                break;

            case "":
                addAllServices();
                break;
            default:
                addAllServices();
                break;
        }
        hideAppBar(View.VISIBLE);
        showSearchBar(View.GONE);
    }
}