package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import io.paperdb.Paper;

public class PersonalProfile extends AppCompatActivity {
EditText numberphoneEt,first_name_et,last_nameEt,email_Et,service_et,certif_et ,language_Et,skills_et,educate_et ;
Button save ;
  ChipGroup pChipGroup,chipGroupServicePersonPro;
  TextView addNewPerson,addNewPersonServId;
  private Spinner mLanguage,mLevelLanguage,mihna, khidma,khibra;;
LinearLayout addAPersonLanguageLn,addAServiceLnId;
    private List<String> languageExperience;
    DatabaseReference reference ;
    private String first_name ;
    private String last_name  ;
    private String email;
    private  List<String> listMihna;
    private String classification;
    private List<String> certList;
    private List<String> languageList;
    private String skill;
    private String degree_science;
    private String mobile_phone;
    private List<String> languages;
    private List<String> services;

    private String certif;

    ArrayList<String> listLanguageWithoutDuplicates;
    ArrayList<String> listlanguage2;

    private     ArrayList<String>  listClass2;
    private ArrayList<String> listclass2WithoutDuplicates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        Paper.init(this);
        certList=new ArrayList<>();
        languageList=new ArrayList<>();
        listlanguage2=new ArrayList<>();
        listClass2=new ArrayList<>();

        reference =FirebaseDatabase.getInstance().getReference().child("Users");
        pChipGroup=findViewById(R.id.pChipGroupPersonId);
        save=findViewById(R.id.savePersonalInfoBtnId);
        chipGroupServicePersonPro=findViewById(R.id.chipGroupServicePersonPro);
        languageExperience=new ArrayList<>();
        addNewPerson=findViewById(R.id.addNewPersonId);
        addNewPersonServId=findViewById(R.id.addNewPersonServId);
        numberphoneEt=findViewById(R.id.mobile_phonePersonId);
        first_name_et=findViewById(R.id.first_name_person_id);
        last_nameEt=findViewById(R.id.last_person_name_id);
        email_Et=findViewById(R.id.emailPersonId);
//        service_et=findViewById(R.id.servicePersonId);
        certif_et=findViewById(R.id.certificatePersonId);
        mLanguage=findViewById(R.id.languagePersonId);
        mihna=findViewById(R.id.mihnaPersonId);
        khidma=findViewById(R.id.khidmaPersonId);
        mLevelLanguage=findViewById(R.id.levelLanguagePersonId);
        skills_et=findViewById(R.id.maharaPersonId);
        educate_et=findViewById(R.id.educationPersonId);
        addAPersonLanguageLn=findViewById(R.id.addAPersonLanguageId);
        addAServiceLnId=findViewById(R.id.addAServiceLnId);

        addNewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAPersonLanguageLn.setVisibility(View.VISIBLE);
            }
        });
        chooseService() ;


        String[] languages = getResources().getStringArray(R.array.languages);
        ArrayAdapter<String> arrayLanguagesAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item,languages);
        arrayLanguagesAdapter.notifyDataSetChanged();
        mLanguage.setAdapter(arrayLanguagesAdapter);

        String[] Levellanguages = getResources().getStringArray(R.array.languages_level);
        ArrayAdapter<String> arrayLevelLanguagesAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item,Levellanguages);
        arrayLevelLanguagesAdapter.notifyDataSetChanged();
        mLevelLanguage.setAdapter(arrayLevelLanguagesAdapter);
         chooseLanguage();
        changeInfo();
    }
    private void addClassChip(final ChipGroup pChipGroup, String s, final List<String> tagList) {
        tagList.clear();
        tagList.add(s);
        listClass2.add(s);
        listclass2WithoutDuplicates = new ArrayList<>(
                new HashSet<>(listClass2));
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
    private void addLanguageChip(final ChipGroup pChipGroup, String s, final List<String> tagList) {
        tagList.clear();
        tagList.add(s);
        listlanguage2.add(s);
        listLanguageWithoutDuplicates = new ArrayList<>(  new HashSet<>(listlanguage2));
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
    private void  chooseService(){
        String[] categories = getResources().getStringArray(R.array.categoriesSpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, categories);
        arrayAdapter.notifyDataSetChanged() ;
        mihna.setAdapter(arrayAdapter) ;

        mihna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String option = String.valueOf(mihna.getSelectedItem());  //Don't forget to move this here otherwise it won't be updated.
                if (option.contentEquals(getString(R.string.tasmim_and_grahic))){
                    listMihna = new ArrayList<>();
                    tasmimAndgrahicList(listMihna);
                }
                if (option.contentEquals( getString(R.string.video_animation))) {
                    listMihna = new ArrayList<>();
                    videoAnimationList(listMihna);
                }
                if (option.contentEquals( getString(R.string.translateEditing))) {
                    listMihna = new ArrayList<>();
                    translateList(listMihna);
                }

                if (option.contentEquals( getString(R.string.programmingandtech))) {
                    listMihna = new ArrayList<>();
                    programingList(listMihna);
                }
                ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, listMihna);
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
                if(!khidma.getSelectedItem().toString().equals("غير محدد") && !mihna.getSelectedItem().toString().equals("غير محدد") ) {
                    classification = mihna.getSelectedItem().toString() + " : " + khidma.getSelectedItem().toString();
                    addClassChip(chipGroupServicePersonPro,classification, certList);
                    addAServiceLnId.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addNewPersonServId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAServiceLnId.setVisibility(View.VISIBLE);
            }
        });





    }
    private void chooseLanguage() {
        mLevelLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!mLevelLanguage.getSelectedItem().toString().equals("غير محدد") &&!mLanguage.getSelectedItem().toString().equals("غير محدد") ){
                    addAPersonLanguageLn.setVisibility(View.GONE);
                    addLanguageChip(pChipGroup,mLanguage.getSelectedItem().toString()+" "+ mLevelLanguage.getSelectedItem().toString() ,languageExperience);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
//    private void getLanguage(){
//        String username= FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
//        reference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            if (dataSnapshot.exists()){
////                    List s =dataSnapshot.child("language").
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                                                     }
//                                                 }
//        );
//
//    }

    void changeInfo(){
        final String username= FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        reference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  if (dataSnapshot.exists()){
                    String typ =  Paper.book().read(Prevalent.type_of_user);
                      if(typ!="مشتري"){
                       languages =   (List<String>) dataSnapshot.child("languageExperience").getValue();
                       services =    (List<String>) dataSnapshot.child("classification").getValue();
                          first_name = dataSnapshot.child("first_name").getValue().toString();
                          last_name = dataSnapshot.child("last_name").getValue().toString();
                          skill = dataSnapshot.child("skills").getValue().toString();
                          mobile_phone =dataSnapshot.child("mobile_phone").getValue().toString();
                          degree_science =dataSnapshot.child("degree_science").getValue().toString();
                          certif=dataSnapshot.child("certificate").getValue().toString();
                               }
                     email = dataSnapshot.child("email").getValue().toString();
                  if(first_name!=null){
                      first_name_et.setText(first_name);
                  }else{
                      first_name_et.setText("");
                  }
                  if(certif!=null){
                      certif_et.setText(certif);
                  }else{
                      certif_et.setText("");
                  }
                 if(degree_science!=null){
                          educate_et.setText(degree_science);
                  }else{
                          educate_et.setText("");
                         }
                 if(skill!=null){
                          skills_et.setText(skill);
                     }else{
                          skills_et.setText("");
                        }
                 if(last_name!=null){
                     last_nameEt.setText(last_name);
                     }else{
                     last_nameEt.setText("");
                        }
                 if(email!=null){
                     email_Et.setText(email);
                     }else{
                     email_Et.setText("");
                        }
                 if(mobile_phone!=null){
                     numberphoneEt.setText(mobile_phone);
                     }else{
                     numberphoneEt.setText("");
                        }
                 if(languages!=null){
                     for (int i =0;i<languages.size();i++){
                         addLanguageChip(pChipGroup,languages.get(i),languageExperience);
                     }
                 }
                 if(services!=null){
                     for (int i =0;i<services.size();i++){
                         addClassChip(chipGroupServicePersonPro,services.get(i),certList);
                     }
                 }





                          }
                            }
                         @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {


                    }
                             }
        );
  save.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          HashMap<String,Object> hashmap2=new HashMap<>();
          hashmap2.put("first_name",first_name_et.getText().toString());
          hashmap2.put("last_name",last_nameEt.getText().toString());
          hashmap2.put("skills",skills_et.getText().toString());
          hashmap2.put("email",email_Et.getText().toString());
          hashmap2.put("mobile_phone",numberphoneEt.getText().toString());
          hashmap2.put("degree_science",educate_et.getText().toString());
          hashmap2.put("languageExperience",listLanguageWithoutDuplicates);
          hashmap2.put("classification",listclass2WithoutDuplicates);

          reference.child(username).updateChildren(hashmap2).addOnCompleteListener(new OnCompleteListener<Void>() {
              @Override
              public void onComplete(@NonNull Task<Void> task) {
                  Toast.makeText(PersonalProfile.this, "تم حفظ المعلومات الشخصية", Toast.LENGTH_SHORT).show();
                  Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                  startActivity(intent);
              }
          });

      }
  });
    }
    void tasmimAndgrahicList(List<String> list){
        list .add("غير محدد");
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
        list .add("غير محدد");
        list .add(getString(R.string.i3lanat_video_kasir));
        list.add(getString(R.string.montaj_video));
        list.add(getString(R.string.sira_bayda_motaharika));
        list.add( getString(R.string.rosom_motaharika));
        list.add( getString(R.string.ard_tatbi9at));
        list.add( getString(R.string.logo_motaharika));
        list.add("أخرى");
    }

    void translateList(List<String> list){
        list.add("غير محدد");
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
        list.add("غير محدد");
        list.add(getString(R.string.database));
        list.add(getString(R.string.programing_web));
        list.add(getString(R.string.application_web));
        list.add(getString(R.string.ui_ux_test));
        list.add(getString(R.string.security_database));
        list.add(getString(R.string.data_analyses));
        list.add("أخرى");
    }

}