package com.example.done;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.done.Fragment.FragmentAccount;
import com.example.done.Fragment.FragmentHome;
import com.example.done.Fragment.FragmentMessages;
import com.example.done.Fragment.FragmentNotification;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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