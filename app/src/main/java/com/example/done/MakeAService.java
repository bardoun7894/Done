package com.example.done;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.done.models.User;
import com.example.done.models.Services;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    Services s ;
    String usernamePaper = "";
    private ProgressDialog progressDialog;

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
        initData();
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

        progressDialog=new ProgressDialog(this);
        progressDialog.setMax(100);
        progressDialog.setMessage("تحميل ...");
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        progressDialog.setCancelable(false);

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
                     Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                     startActivity(intent);
                   }
               }
               private void saveServiceInfoToDatabase() {
                   User user =Paper.book().read(Prevalent.userClass);
                   UUID idOne = UUID.randomUUID();
               if(usernamePaper!=""){
             s = new Services(idOne.toString(),saveCurrentDate,saveCurrentTime,type_service,desc_service,price_service,time_service,downloadImageUrl,user);
                    }
                   serviceRef.child(idOne.toString()).setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                   Toast.makeText(getApplicationContext(),"تم اضافة الخدمة بنجاح ",Toast.LENGTH_SHORT).show();

                         }
                   });
               }
           }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                   double progress=(100.0 * snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                   progressDialog.incrementProgressBy((int)progress);
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
              Toast.makeText(getApplicationContext(),"لم يتم التحميل",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
               }
           });
    }
    void initData() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    assert user != null;
                    assert firebaseUser != null;
                    if (user.getEmail().equals(firebaseUser.getEmail())) {
                      Paper.book().write(Prevalent.userClass,user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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