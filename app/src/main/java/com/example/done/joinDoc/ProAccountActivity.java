package com.example.done.joinDoc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.done.Prevalent;
import com.example.done.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.paperdb.Paper;

public class ProAccountActivity extends AppCompatActivity {
Button continueProbtn;
    public Spinner mihna, khidma,khibra;
    private ArrayList<String> list;
      List<String> tagList ;
    private ChipGroup pChipGroup;
    EditText skillsEt,balad_koliyaEt,koliya_nameEt ,degree_scienceEt,degree_yearEt,certificateEt,CertifiedEt,Certified_yearEt;
      String skills;
      String balad_koliya;
      String koliya_name;
      String degree_science;
      String degree_year;
      String certificate;
      String Certified;
      String Certified_year;
      DatabaseReference experienceRef;
    private String usernamePaper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_account);
         khibra=findViewById(R.id.khibraId);
        mihna =findViewById(R.id.mihnaId);
        khidma =findViewById(R.id.khidmaId);
      pChipGroup =findViewById(R.id.chipGroupPro);
       Paper.init(this);
      experienceRef=FirebaseDatabase.getInstance().getReference().child("Users");
       skillsEt=findViewById(R.id.skillsId);
       balad_koliyaEt=findViewById(R.id.balad_koliyaId);
        koliya_nameEt=findViewById(R.id.koliya_nameId);
        degree_scienceEt=findViewById(R.id.degree_scienceId);
        degree_yearEt=findViewById(R.id.degree_yearId);
        certificateEt=findViewById(R.id.certificateId);
        CertifiedEt=findViewById(R.id.CertifiedId);
        Certified_yearEt=findViewById(R.id.Certified_yearId);

      usernamePaper=Paper.book().read(Prevalent.UserNameKey);

      list=new ArrayList<>();
      tagList=new ArrayList<>();
        String[] categories = getResources().getStringArray(R.array.categoriesSpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, categories);
        arrayAdapter.notifyDataSetChanged();
        mihna.setAdapter(arrayAdapter);

        mihna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String option = String.valueOf(mihna.getSelectedItem());  //Don't forget to move this here otherwise it won't be updated.
                if (option.contentEquals(getString(R.string.tasmim_and_grahic))){
                    list = new ArrayList<>();
                    tasmimAndgrahicList(list);
                }
                if (option.contentEquals( getString(R.string.video_animation))) {
                    list = new ArrayList<>();
                    videoAnimationList(list);
                }
                if (option.contentEquals( getString(R.string.translateEditing))) {
                    list = new ArrayList<>();
                    translateList(list);
                }

                if (option.contentEquals( getString(R.string.programmingandtech))) {
                    list = new ArrayList<>();
                    programingList(list);
                }
                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, list);
                stringArrayAdapter.notifyDataSetChanged();
                khidma.setAdapter(stringArrayAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        khidma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(khidma.getSelectedItem().toString());
                System.out.println(mihna.getSelectedItem().toString());
                addChip(khidma.getSelectedItem().toString()+" "+mihna.getSelectedItem().toString(),tagList);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

               }
               });

        String[] experience = getResources().getStringArray(R.array.experience);
        ArrayAdapter<String> arrayAdapterExp = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, experience);
        arrayAdapter.notifyDataSetChanged();
        khibra.setAdapter(arrayAdapterExp);

        continueProbtn =findViewById(R.id.continueProbtnID);
        continueProbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDataEntry();
                Intent intent =new Intent(getBaseContext(),AccountConnected.class);
                startActivity(intent);
            }
        });
    }
    private void validateDataEntry() {
          skills  =  skillsEt.getText().toString();
          balad_koliya =  balad_koliyaEt.getText().toString();
          koliya_name =  koliya_nameEt.getText().toString();
          degree_science =  degree_scienceEt.getText().toString();
          degree_year =  degree_yearEt.getText().toString();
          certificate =  certificateEt.getText().toString();
          Certified =  CertifiedEt.getText().toString();
          Certified_year =  Certified_yearEt.getText().toString();
        if(TextUtils.isEmpty(skills)){
            skillsEt.setError("المرجو تعبئة الحقل");
            skillsEt.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(balad_koliya)){
            balad_koliyaEt.setError("المرجو تعبئة الحقل");
            balad_koliyaEt.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(koliya_name)){
            koliya_nameEt.setError("المرجو تعبئة الحقل");
            koliya_nameEt.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(degree_science)){
            degree_scienceEt.setError("المرجو تعبئة الحقل");
            degree_scienceEt.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(degree_year)){
            degree_yearEt.setError("المرجو تعبئة الحقل");
            degree_yearEt.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(certificate)){
            certificateEt.setError("المرجو تعبئة الحقل");
            certificateEt.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(Certified)){
            CertifiedEt.setError("المرجو تعبئة الحقل");
            CertifiedEt.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(Certified_year)){
            Certified_yearEt.setError("المرجو تعبئة الحقل");
            Certified_yearEt.requestFocus();
            return;
        }

        storeServiceInfo();

    }

    private void storeServiceInfo() {

        HashMap<String ,Object> hashMap =new HashMap<>();
        hashMap.put("skills",skills);
        hashMap.put("balad_koliya",balad_koliya);
        hashMap.put("koliya_name",koliya_name);
        hashMap.put("degree_science",degree_science);
        hashMap.put("degree_year",degree_year);
        hashMap.put("certificate",certificate);
        hashMap.put("Certified",Certified);
        hashMap.put("Certified_year",Certified_year);
        if(usernamePaper!=""){
            experienceRef.child(usernamePaper).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(),"تم اضافة المعلومات بنجاح ",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getBaseContext(),ProAccountActivity.class);
                startActivity(intent);
            }
        });
        }
    }

    private void addChip(String s, final List<String> tagList) {
        tagList.clear();
        tagList.add(s);
        for (int index = 0; index < tagList.size(); index++) {
            final String tagName = tagList.get(index);
            final Chip chip = new Chip(this);
            int paddingDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics()
            );
            chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
            chip.setText(tagName);
            chip.setTextColor(getResources().getColor(R.color.colorPrimary));
            chip.setCloseIconResource(R.drawable.ic_close_24);
            chip.setCloseIconEnabled(true);
            //Added click listener on close icon to remove tag from ChipGroup
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tagList.remove(tagName);
                    pChipGroup.removeView(chip);
                }
            });
            pChipGroup.addView(chip);
        }
    }


    void tasmimAndgrahicList(List<String> list){
        list .add("تصميم بروشور");
        list.add("تصميم سيرة ذاتية");
        list.add("تصميم كتاب");
        list.add( "تصميم غلاف");
        list.add("تصميم فلاتر" );
        list.add("قرطاسيات");
        list.add( "تصميم شعار");
        list.add(" تصميم دعوة");
        list.add(" تصميم واجهات الويب و الجوال");
        list.add("أخرى");
    }
    void videoAnimationList(List<String> list){
        list .add(getString(R.string.i3lanat_video_kasir));
        list.add(getString(R.string.montaj_video));
        list.add(getString(R.string.sira_bayda_motaharika));
        list.add( getString(R.string.rosom_motaharika));
        list.add( getString(R.string.ard_tatbi9at));
        list.add( getString(R.string.logo_motaharika));
        list.add("أخرى");
    }

    void translateList(List<String> list){
        list.add(getString(R.string.dirasat_lhala));
        list.add(getString(R.string.kitabat_sira));
        list.add(getString(R.string.ma9alat_post));
        list.add(getString(R.string.tajribat_mostakhdim));
        list.add(getString(R.string.sinario));
        list.add(getString(R.string.tarjama));
        list.add(getString(R.string.wasf_montaj));
        list.add(getString(R.string.mohtawa_ibda3i));
        list.add(getString(R.string.mohtawa_tawasol));
        list.add("أخرى");
    }
    void programingList(List<String> list){
        list.add(getString(R.string.database));
        list.add(getString(R.string.programing_web));
        list.add(getString(R.string.application_web));
        list.add(getString(R.string.ui_ux_test));
        list.add(getString(R.string.security_database));
        list.add(getString(R.string.data_analyses));
        list.add("أخرى");
    }
}