package com.example.done.home4Buttons;

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
import com.example.done.models.ItemServices;

import java.util.ArrayList;

public class VideoAnimationActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnOtherVideoAnimation ;
    ImageView backVideoIv, searchVideoIv,searchIconVideoIv;
    RecyclerView recyclerView;
    LinearLayout homeMessageBarLn,searchAppBarLn;
    EditText searchBoxVideoEt;
    ArrayList<ItemServices> ar =new ArrayList<>();
    ItemServices i3lanat;
    ItemServices montag;
    private ItemServices sira_bayda_motaharika;
    private ItemServices rosom_motaharika;
    private ItemServices ard_tatbi9at;
    private ItemServices chi3ar_motahirik;
    ArrayList<String> l =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_animation);
        l.add("اعلانات فيديو قصيرة");
        l.add("مونتاج الفيديو");
        l.add("السيرة البيضاء المتحركة");
        l.add("رسوم متحركة");
        l.add("عرض التطبيقات");
        l.add("شعار متحرك");
        chi3ar_motahirik =new ItemServices(getString(R.string.logo_motaharika),R.drawable.logo_c);
        ard_tatbi9at = new ItemServices( getString(R.string.ard_tatbi9at),R.drawable.application_showing);
        rosom_motaharika = new ItemServices(getString(R.string.rosom_motaharika),R.drawable.animation_);
        i3lanat =new ItemServices(getString(R.string.i3lanat_video_kasir),R.drawable.ads_);
        montag =new ItemServices(getString(R.string.montaj_video),R.drawable.video_mon);
        sira_bayda_motaharika =    new ItemServices(getString(R.string.sira_bayda_motaharika),R.drawable.white_b);

       recyclerView=findViewById(R.id.recycler_video);
        btnOtherVideoAnimation =findViewById(R.id.otherVideoAnimationId);
        backVideoIv =findViewById(R.id.backVideoId);
        homeMessageBarLn =findViewById(R.id.homeMessageBarID);
        searchAppBarLn =findViewById(R.id.searchAppBarId);
        searchBoxVideoEt =findViewById(R.id.searchBoxVideoId);
        searchIconVideoIv =findViewById(R.id.searchIconVideoId);
        searchVideoIv =findViewById(R.id.searchVideoId);
        addAllServices();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerItem(ar,this,getString(R.string.video_animation)));

        btnOtherVideoAnimation.setOnClickListener(this);
        backVideoIv.setOnClickListener(this);
        searchVideoIv.setOnClickListener(this);
        searchIconVideoIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backVideoId :
                finish();
                break;
          case R.id.searchIconVideoId :
              makeSearch();
              recyclerView.setHasFixedSize(true);
              recyclerView.setLayoutManager(new GridLayoutManager(this,3));
              recyclerView.setAdapter(new RecyclerItem(ar,this,getString(R.string.video_animation)));
              break;
             case R.id.searchGraphicId :
               hideAppBar(View.GONE);
               showSearchBar(View.VISIBLE);
                break;
            case R.id.otherProgrammingId :
                Intent intent =new Intent(getBaseContext(), VideoAnimationActivity.class);
                intent.putExtra( "tasmim",getString(R.string.video_animation));
                intent.putExtra( "TITLE_SERVICE","أخرى");
                startActivity(intent);
                break;
        }
    }

    private void makeSearch() {
      String data =  searchBoxVideoEt.getText().toString();
      for(int i=0;i<l.size();i++){
          if(data.contains(l.get(i).subSequence(0,4))){
              data =l.get(i);
            }
      }
    switch (data){
      case "اعلانات فيديو قصيرة":
         ar.clear();
         ar.add(i3lanat) ;
      break;
      case "":
          addAllServices();
          break;
      case "مونتاج الفيديو":
        ar.clear();
        ar.add(montag) ;
          break;
      case "السيرة البيضاء المتحركة":
        ar.clear();
        ar.add(sira_bayda_motaharika) ;
      break;
      case "رسوم متحركة":
          ar.clear();
        ar.add(rosom_motaharika) ;
      break;
      case "عرض التطبيقات":
        ar.clear();
        ar.add(ard_tatbi9at) ;
      break;

      case "شعار متحرك":
        ar.clear();
        ar.add(chi3ar_motahirik) ;
      break;
        default:
        addAllServices();
            break;
    }
        hideAppBar(View.VISIBLE);
        showSearchBar(View.GONE);

    }
   private void addAllServices(){
        ar.clear();
        ar.add(i3lanat);
        ar.add(montag);
        ar.add(sira_bayda_motaharika);
        ar.add(rosom_motaharika);
        ar.add(ard_tatbi9at);
        ar.add(chi3ar_motahirik);
    }
    private void showSearchBar(int i) {
        searchAppBarLn.setVisibility(i);
    }

    private void hideAppBar(int i) {
        homeMessageBarLn.setVisibility(i);
    }
}