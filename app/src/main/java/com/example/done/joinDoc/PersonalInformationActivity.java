package com.example.done.joinDoc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.done.Prevalent;
import com.example.done.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import io.paperdb.Paper;

public class PersonalInformationActivity extends AppCompatActivity {
    private static final int SELECT_IMAGE = 1;
    Button continuePersonalInfoBtn;
 public Spinner mLevelLanguage, mLanguage;
    ChipGroup pChipGroup;
    LinearLayout addALanguageLn;
    ImageView pickImageIV ;
    TextView addNewTv;
    private Uri ImageUri;
    EditText first_name_et,last_name_et,desc_experience_et;
    private String saveCurrentDate;
    private String saveCurrentTime;
    private String randomServiceKey;
    private String downloadImageUrl;
    private StorageReference ExprienceImageStorage;
    private String usernamePaper;
    DatabaseReference experienceRef ;
    private String first_name;
    private String last_name;
    private String desc_experience;
    private List<String> languageExperience;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        ExprienceImageStorage = FirebaseStorage.getInstance().getReference().child("UserImage");
        desc_experience_et=findViewById(R.id.desc_experience_id);
        first_name_et =findViewById(R.id.first_name_id);
        last_name_et =findViewById(R.id.last_name_id);
        experienceRef=FirebaseDatabase.getInstance().getReference().child("Users");
        Paper.init(this);
        usernamePaper =Paper.book().read(Prevalent.UserNameKey);
        pickImageIV=findViewById(R.id.pickImageId);
        continuePersonalInfoBtn =findViewById(R.id.continuePersonalInfoBtnId);
        mLevelLanguage=findViewById(R.id.levelLanguageId);
        mLanguage=findViewById(R.id.languageId);
        languageExperience=new ArrayList<>();
        addALanguageLn=findViewById(R.id.addALanguageId);
        pChipGroup=findViewById(R.id.chipGroup);
        addNewTv=findViewById(R.id.addNewId);

        String[] languages = getResources().getStringArray(R.array.languages);
        ArrayAdapter<String> arrayLanguagesAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item,languages);
        arrayLanguagesAdapter.notifyDataSetChanged();
        mLanguage.setAdapter(arrayLanguagesAdapter);
       String[] Levellanguages = getResources().getStringArray(R.array.languages_level);
        ArrayAdapter<String> arrayLevelLanguagesAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item,Levellanguages);
        arrayLevelLanguagesAdapter.notifyDataSetChanged();
        mLevelLanguage.setAdapter(arrayLevelLanguagesAdapter);

        pickImageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          openGallery();
            }
        });

        addNewTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addALanguageLn.setVisibility(View.VISIBLE);
            }
        });

        continuePersonalInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDataEntry();
            }
        });

        chooseLanguage();

    }

    private void chooseLanguage() {
        mLevelLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       if(!mLevelLanguage.getSelectedItem().toString().equals("غير محدد") &&!mLanguage.getSelectedItem().toString().equals("غير محدد") ){
           addALanguageLn.setVisibility(View.GONE);
           addChip(mLanguage.getSelectedItem().toString()+" "+mLevelLanguage.getSelectedItem().toString() ,languageExperience);
       }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
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

    private void validateDataEntry() {
         first_name =first_name_et.getText().toString();
          last_name =  last_name_et.getText().toString();
          desc_experience = desc_experience_et.getText().toString();

        if(TextUtils.isEmpty(desc_experience)){
            desc_experience_et.setError("المرجو تعبئة الحقل");
            desc_experience_et.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(first_name)){
            first_name_et.setError("المرجو تعبئة الحقل");
            first_name_et.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(last_name)){
            last_name_et.setError("المرجو تعبئة الحقل");
            last_name_et.requestFocus();
            return;
        }

        if (ImageUri==null){
            Toast.makeText(getApplicationContext(),"المرجو اضافة الصورة ",Toast.LENGTH_LONG).show();
            return;
        }

        storeServiceInfo();

    }

    private void storeServiceInfo() {
        Calendar calendar =Calendar.getInstance();
        SimpleDateFormat CurrentDate = new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate =CurrentDate.format(calendar.getTime());
        SimpleDateFormat CurrentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime =CurrentTime.format(calendar.getTime());
        randomServiceKey = saveCurrentDate+saveCurrentTime ;
        final StorageReference filePath = ExprienceImageStorage.child(ImageUri.getLastPathSegment()+randomServiceKey+".jpg");
        final UploadTask uploadTask =filePath.putFile(ImageUri) ;
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String error =e.toString();
                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(),"تم اضافة الصورة بنجاح",Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask =uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"cdjl conitnunaition",Toast.LENGTH_SHORT).show();
                        }
                        return filePath.getDownloadUrl();
                    }
                });
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //here
                Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!urlTask.isSuccessful());{
                    Uri downloadUrl = urlTask.getResult();
                     downloadImageUrl = String.valueOf(downloadUrl);
                }
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    System.out.println("helllo sscmcmcm cmc cm");
                    Toast.makeText(getApplicationContext(),"تم جلب الصورة بنجاح ",Toast.LENGTH_SHORT).show();
                    saveServiceInfoToDatabase();
                }
            }

        });
    }
    private void saveServiceInfoToDatabase() {
        Paper.book().write(Prevalent.photoProfile,downloadImageUrl);
        HashMap<String ,Object> hashMap =new HashMap<>();
        hashMap.put("PhotoProfile",downloadImageUrl);
        hashMap.put("desc_experience",desc_experience);
        hashMap.put("first_name",first_name);
        hashMap.put("last_name",last_name);
        hashMap.put("languageExperience",languageExperience);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    ImageUri =data.getData();
                    pickImageIV.setImageURI(ImageUri);
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(getApplicationContext(), "تم الالغاء", Toast.LENGTH_SHORT).show();
            }
        }
    }

}