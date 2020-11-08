package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.done.Fragment.FragmentAccount;
import com.example.done.Fragment.FragmentHome;
import com.example.done.Fragment.FragmentMessages;
import com.example.done.Fragment.FragmentNotification;
import com.example.done.joinDoc.ProAccountActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btm;
    FloatingActionButton f ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Paper.init(this);
        final String s =Paper.book().read(Prevalent.UserNameKey);
        f=findViewById(R.id.fab);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(!s.isEmpty() && !s.equals(null)){
                  Intent intent =new Intent(getBaseContext(), OtlobActivity.class);
                  startActivity(intent);
              }
            }
        });
        btm = findViewById(R.id.navigationViewId);
        btm.setBackground(null);
        btm.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentHome()).commit();
}
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment =null;
            switch (item.getItemId()){
                case R.id.account:
                    selectedFragment =new FragmentAccount();
                    break;
                    case R.id.homeIcon:
                    selectedFragment =new FragmentHome();
                    break;
                    case R.id.messageId:
                    selectedFragment =new FragmentMessages();
                    break;
                    case R.id.notification:
                    selectedFragment =new FragmentNotification();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}