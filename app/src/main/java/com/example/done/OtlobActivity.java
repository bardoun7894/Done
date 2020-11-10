package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.done.models.ItemNotification;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.paperdb.Paper;

public class OtlobActivity extends AppCompatActivity {
    public Spinner mTextView, getmTextView;
    List<String> list ;
    List<String> listServices ;
    DatabaseReference serviceRef;
    Button saveOtlobServiceBtn;
    EditText desc_otlob_et,otlobTimeet;
    TextView appleTv, credit ;
    String payText ;
    String desc_otlob;
    String otlobTime;
    String username ;
    private String sss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otlob);
        Paper.init(this);
        username =  Paper.book().read(Prevalent.UserNameKey);
        list=new ArrayList<>();
        listServices=new ArrayList<>();
        mTextView = findViewById(R.id.categorySpinnerOtlob);
        getmTextView = findViewById(R.id.subcategorySpinnerOtlob);
        saveOtlobServiceBtn=findViewById(R.id.saveOtlobServiceId);
        desc_otlob_et = findViewById (R.id.desc_otlob_id) ;
        otlobTimeet = findViewById (R.id.otlobTimeId) ;
        appleTv =findViewById(R.id.apple_pay);
        credit=findViewById(R.id.creditID);

        appleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payText ="apple pay";
                appleTv.setTextColor(getResources().getColor(R.color.colorPrimary));
                credit.setTextColor(getResources().getColor(R.color.colorGrey));
            }
        });
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payText ="credit";
                credit.setTextColor(getResources().getColor(R.color.colorPrimary));
                appleTv.setTextColor(getResources().getColor(R.color.colorGrey));
            }
        });

        saveOtlobServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIntoDatabase();
            }
        });

        String[] categories = getResources().getStringArray(R.array.categoriesSpinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, categories);
        arrayAdapter.notifyDataSetChanged();
        mTextView.setAdapter(arrayAdapter);

        mTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String option = String.valueOf(mTextView.getSelectedItem());  //Don't forget to move this here otherwise it won't be updated.
                if (option.contentEquals(getString(R.string.tasmim_and_grahic))) {
                    list = new ArrayList<>();
                    tasmimAndgrahicList(list);
                }
                if (option.contentEquals(getString(R.string.video_animation))) {
                    list = new ArrayList<>();
                    videoAnimationList(list);
                }
                if (option.contentEquals(getString(R.string.translateEditing))) {
                    list = new ArrayList<>();
                    translateList(list);
                }

                if (option.contentEquals(getString(R.string.programmingandtech))) {
                    list = new ArrayList<>();
                    programingList(list);
                }
                final ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, list);
                stringArrayAdapter.notifyDataSetChanged();
                getmTextView.setAdapter(stringArrayAdapter);

                getmTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (!getmTextView.getSelectedItem().toString().equals("غير محدد") && !mTextView.getSelectedItem().toString().equals("غير محدد")) {
               sss=mTextView.getSelectedItem().toString() + " : " + getmTextView.getSelectedItem().toString();

                            System.out.println(listServices);
                            serviceRef = FirebaseDatabase.getInstance().getReference().child("otlob").child(mTextView.getSelectedItem().toString()).child(getmTextView.getSelectedItem().toString()).child(username);
                        }
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

    private void saveIntoDatabase() {
      desc_otlob =desc_otlob_et.getText().toString();
      otlobTime=otlobTimeet.getText().toString();
        if(TextUtils.isEmpty(desc_otlob)){
            desc_otlob_et.setError("المرجو تعبئة الحقل");
            desc_otlob_et.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(otlobTime)){
            otlobTimeet.setError("المرجو تعبئة الحقل");
            otlobTimeet.requestFocus();
            return;
        }
        if(payText==null){
        Toast.makeText(this, "اختر طريقة للدفع", Toast.LENGTH_SHORT).show();
        return;
        }
//        HashMap<String,Object> hashMap =new HashMap<String,Object>();
//        hashMap.put("وصف الطلب",desc_otlob);
//        hashMap.put("اسم المستخدم",username);
//        hashMap.put("وقت التسليم",otlobTime);
//        hashMap.put("وسيلة الدفع",payText);
//        hashMap.put("التصنيف",sss);
        ItemNotification itemNotification =new  ItemNotification(desc_otlob,otlobTime,payText,username,sss);
        serviceRef.setValue(itemNotification).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(OtlobActivity.this, "تم رفع الطلب بنجاح", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
             }
        });

    }

    void tasmimAndgrahicList(List<String> list) {
                list.add("غير محدد");
                list.add("تصميم بروشور");
                list.add("تصميم سيرة ذاتية");
                list.add("تصميم كتاب");
                list.add("تصميم غلاف");
                list.add("تصميم فلاتر");
                list.add("قرطاسيات");
                list.add("تصميم شعار");
                list.add(" تصميم دعوة");
                list.add(" تصميم واجهات الويب و الجوال");
                list.add("أخرى");
            }

            void videoAnimationList(List<String> list) {
                list.add("غير محدد");
                list.add(getString(R.string.i3lanat_video_kasir));
                list.add(getString(R.string.montaj_video));
                list.add(getString(R.string.sira_bayda_motaharika));
                list.add(getString(R.string.rosom_motaharika));
                list.add(getString(R.string.ard_tatbi9at));
                list.add(getString(R.string.logo_motaharika));
                list.add("أخرى");
            }

            void translateList(List<String> list) {
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

            void programingList(List<String> list) {
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

