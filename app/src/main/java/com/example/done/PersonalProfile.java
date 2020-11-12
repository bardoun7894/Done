package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.done.models.User;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class PersonalProfile extends AppCompatActivity {
EditText numberphoneEt,first_name_et,last_nameEt,email_Et,service_et,certif_et ,language_Et,skills_et,educate_et ;
Button save ;
  ChipGroup pChipGroup;
  TextView addNewPerson;
  private Spinner mLanguage,mLevelLanguage ;
LinearLayout addAPersonLanguageLn;
    private List<String> languageExperience;
    DatabaseReference reference ;
    User user=new User() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        Paper.init(this);
        user=Paper.book().read(Prevalent.userClass);

        reference =FirebaseDatabase.getInstance().getReference().child("Users");
        pChipGroup=findViewById(R.id.pChipGroupPersonId);
        languageExperience=new ArrayList<>();
        addNewPerson=findViewById(R.id.addNewPersonId);
        numberphoneEt=findViewById(R.id.mobile_phonePersonId);
        first_name_et=findViewById(R.id.first_name_person_id);
        last_nameEt=findViewById(R.id.last_person_name_id);
        email_Et=findViewById(R.id.emailPersonId);
        service_et=findViewById(R.id.servicePersonId);
        certif_et=findViewById(R.id.certificatePersonId);
        mLanguage=findViewById(R.id.languagePersonId);
        mLevelLanguage=findViewById(R.id.levelLanguagePersonId);
        skills_et=findViewById(R.id.maharaPersonId);
        educate_et=findViewById(R.id.educationPersonId);
        addAPersonLanguageLn=findViewById(R.id.addAPersonLanguageId);
        if(user!=null){
            changeInfo();

        }
        addNewPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAPersonLanguageLn.setVisibility(View.VISIBLE);
            }
        });



        String[] languages = getResources().getStringArray(R.array.languages);
        ArrayAdapter<String> arrayLanguagesAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item,languages);
        arrayLanguagesAdapter.notifyDataSetChanged();
        mLanguage.setAdapter(arrayLanguagesAdapter);

        String[] Levellanguages = getResources().getStringArray(R.array.languages_level);
        ArrayAdapter<String> arrayLevelLanguagesAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item,Levellanguages);
        arrayLevelLanguagesAdapter.notifyDataSetChanged();
        mLevelLanguage.setAdapter(arrayLevelLanguagesAdapter);
         chooseLanguage();

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
    private void chooseLanguage() {
        mLevelLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!mLevelLanguage.getSelectedItem().toString().equals("غير محدد") &&!mLanguage.getSelectedItem().toString().equals("غير محدد") ){
                    addAPersonLanguageLn.setVisibility(View.GONE);
                    addChip(mLanguage.getSelectedItem().toString()+" "+ mLevelLanguage.getSelectedItem().toString() ,languageExperience);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void getLanguage(){
        String username= FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        reference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()){
//                    List s =dataSnapshot.child("language").
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                     }
                                                 }
        );

    }

    void changeInfo(){

//        String numberPhone =numberphoneEt.getText().toString();
//        numberphoneEt.setText(user.);

//        String first_name =first_name_et.getText().toString();
        first_name_et.setText(user.getFirst_name());
//        String last_name =last_nameEt.getText().toString();
        last_nameEt.setText(user.getLast_name());
        email_Et.setText(user.getEmail());
        certif_et.setText(user.getCertificate());
        for (int i =0;i<user.getLanguageExperience().size();i++){
            addChip(user.getLanguageExperience().get(i),languageExperience);
        }
        educate_et.setText(user.getDegree_science());
//        for (int i =0;i<user.getClassification().size();i++){
//            service_et.setText(user.getClassification().get(0));
//
//        }

//        String email =email_Et.getText().toString();
//        String service_et =email_Et.getText().toString();


    }

}