package com.example.done.joinDoc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.done.R;


public class AccountConnected extends AppCompatActivity {
Button continueAccountConnectBtn;
//List<AuthUI.IdpConfig> provider ;
    private static  final int MY_REQUEST_CODE=7117;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_connected);
        continueAccountConnectBtn =findViewById(R.id.continueAccountConnectBtnId);

        continueAccountConnectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getBaseContext(),SecurityAccount.class);
                startActivity(intent);
            }
        });
//        provider = Arrays.asList(
//                new AuthUI.IdpConfig.FacebookBuilder().build(),
//                new AuthUI.IdpConfig.GoogleBuilder().build(),
//                new AuthUI.IdpConfig.TwitterBuilder().build()
//         );
//          showSignOptions();
    }

//    private void showSignOptions() {
//        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(provider).setTheme(R.style.MyTheme).build(),MY_REQUEST_CODE);
//    }
}