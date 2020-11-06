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
import java.text.BreakIterator;
import java.util.ArrayList;

public class ProgramingActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnOtherProgramming ;
    ImageView backProgrammingIv,searchProgrammingIv,searchIconProgrammingIv;
    RecyclerView recyclerView;
    ArrayList<ItemServices> ar =new ArrayList<>();


    ItemServices database,programing_web,application_web,ui_ux_test,security_database,data_analyses;
    private LinearLayout homeProgrammingBarLn,searchAppBarProgrammingLn;

    private EditText searchBoxprogrammingEt;
    private ArrayList<String> l =new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programing);



        database= new ItemServices(getString(R.string.database),R.drawable.tasmim_prochor);
        programing_web= new ItemServices(getString(R.string.programing_web),R.drawable.tasmim_cv);
        application_web= new ItemServices(getString(R.string.application_web),R.drawable.tasmim_kitab);
        ui_ux_test= new ItemServices(getString(R.string.ui_ux_test),R.drawable.tasmim_ghilaf);
        security_database= new ItemServices(getString(R.string.security_database),R.drawable.tasmim_flater);
        data_analyses= new ItemServices(getString(R.string.data_analyses),R.drawable.kartasiyat);


        l.add(getString(R.string.database));
        l.add(getString(R.string.programing_web));
        l.add(getString(R.string.application_web));
        l.add(getString(R.string.ui_ux_test));
        l.add(getString(R.string.security_database));
        l.add(getString(R.string.data_analyses));

        btnOtherProgramming =findViewById(R.id.otherProgrammingId);
        backProgrammingIv =findViewById(R.id.backProgrammingId);
        searchBoxprogrammingEt=findViewById(R.id.searchBoxProgrammingId);
        
        searchIconProgrammingIv =findViewById(R.id.searchIconProgrammingId);
        searchProgrammingIv =findViewById(R.id.searchProgramminId);
        
        //Linear Layout
        homeProgrammingBarLn =findViewById(R.id.homeProgrammingBarID);
        searchAppBarProgrammingLn =findViewById(R.id.searchAppBarProgrammingId);

        addAllServices();
        

        recyclerView =findViewById(R.id.recycler_programming);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setAdapter(new RecyclerItem(ar,this,getString(R.string.programmingandtech)));

        searchProgrammingIv.setOnClickListener(this);
        searchIconProgrammingIv.setOnClickListener(this);
        btnOtherProgramming.setOnClickListener(this);
        backProgrammingIv.setOnClickListener(this);

    }

    private void addAllServices() {
        ar.add(database);
        ar.add(programing_web);
        ar.add(application_web);
        ar.add(ui_ux_test);
        ar.add(security_database);
        ar.add(data_analyses);
    }
 

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backProgrammingId :
                finish();
                break;
            case R.id.searchIconProgrammingId :
                makeSearch();
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(this,3));
                recyclerView.setAdapter(new RecyclerItem(ar,this,getString(R.string.tasmim_and_grahic)));
                break;
            case R.id.searchProgramminId :
                hideAppBar(View.GONE);
                showSearchBar(View.VISIBLE);
                break;
            case R.id.otherProgrammingId :
                Intent intent =new Intent(getBaseContext(), SearchServicesActivity.class);
                intent.putExtra( "tasmim",getString(R.string.programmingandtech));
                intent.putExtra( "TITLE_SERVICE","أخرى");
                startActivity(intent);
                break;
        }
    }
    private void showSearchBar(int visible) {
        searchAppBarProgrammingLn.setVisibility(visible);
    }

    private void hideAppBar(int gone) {
        homeProgrammingBarLn.setVisibility(gone);
    }
    private void makeSearch() {
        String data =  searchBoxprogrammingEt.getText().toString();
        for(int i=0;i<l.size();i++){
            if(data.contains(l.get(i).subSequence(0,4))){
                data =l.get(i);
            }
        }
        switch (data){
            case"قواعد البيانات":
                ar.clear();
                ar.add(database) ;
                break;
            case "":
                addAllServices();
                break;
            case "برمجة الويب":
                ar.clear();
                ar.add(programing_web) ;
                break;
            case "تطبيقات الويب":
                ar.clear();
                ar.add(application_web) ;
                break;
            case "اختبار تجربة المستخدم":
                ar.clear();
                ar.add(ui_ux_test) ;
                break;
            case "الامن السيبراني و حماية البيانات":
                ar.clear();
                ar.add(security_database) ;
                break;
                case "تحليل البيانات و التقارير":
                ar.clear();
                ar.add(data_analyses) ;
                break;
            default:
                addAllServices();
                break;
        }
        hideAppBar(View.VISIBLE);
        showSearchBar(View.GONE);

    }
}