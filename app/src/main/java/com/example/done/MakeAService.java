package com.example.done;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.done.models.ItemServices;
import com.example.done.models.User;
import com.example.done.models.services;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import io.paperdb.Paper;

public class MakeAService extends AppCompatActivity implements View.OnClickListener {
    private static final int SELECT_IMAGE = 1;
    public Spinner mTextView, getmTextView;
    ImageView imageOfService ;
    Button uploadImages ,saveButton ;
    EditText typeOfServiceE,descOfServiceE,priceOfServiceE,timeOfServiceE ;
    String type_service,desc_service,price_service ,time_service ,saveCurrentDate,saveCurrentTime ,randomServiceKey ,downloadImageUrl ;
    StorageReference serviceImageStorage  ;
    DatabaseReference serviceRef ;
    List<String> list ;
    private Uri ImageUri;
    services s ;
    String usernamePaper = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_make_a_service);
         Paper.init(this);
        usernamePaper = Paper.book().read(Prevalent.UserNameKey);
        serviceImageStorage = FirebaseStorage.getInstance().getReference().child("ServiceImages");

        imageOfService = findViewById(R.id.imageOfServiceId);
        uploadImages = findViewById(R.id.uploadImagesId);
        saveButton = findViewById(R.id.saveServiceId);
        typeOfServiceE = findViewById(R.id.typeOfServiceEt);
        descOfServiceE = findViewById(R.id.descOfServiceEt);
        priceOfServiceE = findViewById(R.id.priceOfServiceEt);
        timeOfServiceE = findViewById(R.id.timeOfServiceEt);
       Paper.init(this);
        uploadImages.setOnClickListener(this);
        saveButton.setOnClickListener(this);

        mTextView = findViewById(R.id.categorySpinner);
        getmTextView = findViewById(R.id.subcategorySpinner);
        String[] categories = getResources().getStringArray(R.array.categoriesSpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, categories);
        arrayAdapter.notifyDataSetChanged();
        mTextView.setAdapter(arrayAdapter);


  mTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String option = String.valueOf(mTextView.getSelectedItem());  //Don't forget to move this here otherwise it won't be updated.
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
                getmTextView.setAdapter(stringArrayAdapter);

                getmTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        System.out.println(getmTextView.getSelectedItem().toString());
                        System.out.println(mTextView.getSelectedItem().toString());
                        serviceRef =FirebaseDatabase.getInstance().getReference().child(mTextView.getSelectedItem().toString()).child(getmTextView.getSelectedItem().toString());
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.uploadImagesId:
                openGallery();
                break;
                case R.id.saveServiceId:
               validateDataEntry();
                break;

        }
    }

    private void validateDataEntry() {
        type_service =typeOfServiceE.getText().toString();
        desc_service =  descOfServiceE.getText().toString();
        price_service = priceOfServiceE.getText().toString();
        time_service = timeOfServiceE.getText().toString();
        if(TextUtils.isEmpty(type_service)){
            typeOfServiceE.setError("المرجو تعبئة الحقل");
            typeOfServiceE.requestFocus();
            return;
        }
     if(TextUtils.isEmpty(desc_service)){
            descOfServiceE.setError("المرجو تعبئة الحقل");
         descOfServiceE.requestFocus();
            return;
        }
     if(TextUtils.isEmpty(price_service)){
            priceOfServiceE.setError("المرجو تعبئة الحقل");
         priceOfServiceE.requestFocus();
            return;
        }
     if(TextUtils.isEmpty(time_service)){
            timeOfServiceE.setError("المرجو تعبئة الحقل");
         timeOfServiceE.requestFocus();
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
       final StorageReference filePath = serviceImageStorage.child(ImageUri.getLastPathSegment()+randomServiceKey+".jpg");
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
                       downloadImageUrl= String.valueOf(downloadUrl);
                   }
               }
           }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(getApplicationContext(),"تم جلب الصورة بنجاح ",Toast.LENGTH_SHORT).show();
                     saveServiceInfoToDatabase();
                   }
               }
               private void saveServiceInfoToDatabase() {
                   UUID idOne = UUID.randomUUID();

                   if(usernamePaper!=""){
             s = new services(idOne.toString(),saveCurrentDate,saveCurrentTime,type_service,desc_service,price_service,time_service,downloadImageUrl,usernamePaper);

                   }
                   serviceRef.child(idOne.toString()).setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                   Toast.makeText(getApplicationContext(),"تم اضافة الخدمة بنجاح ",Toast.LENGTH_SHORT).show();

                         }
                   });
               }
           });
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
          }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    ImageUri =data.getData();
                    imageOfService.setImageURI(ImageUri);
                }
            } else if (resultCode == Activity.RESULT_CANCELED)  {
                Toast.makeText(getApplicationContext(), "تم الالغاء", Toast.LENGTH_SHORT).show();
            }
        }
    }
}