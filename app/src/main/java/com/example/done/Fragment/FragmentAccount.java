package com.example.done.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.done.MainActivity;
import com.example.done.Prevalent;
import com.example.done.joinDoc.JoinAsActivity;
import com.example.done.R;
import com.example.done.RegisterActivity;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class FragmentAccount extends androidx.fragment.app.Fragment implements View.OnClickListener  {
    TextView signِAccountTv,joinAsTv,usernameAccountTv ;
    Intent intent ;
    String emailPaper    = "";
    String passwordPaper ="";
    FirebaseDatabase firebaseDatabase;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.fragment_account , container ,false );
       signِAccountTv =v.findViewById(R.id.signِAccountTvId);
        joinAsTv =    v.findViewById(R.id.joinAsId);
        usernameAccountTv =v.findViewById(R.id.usernameAccountId);
        signِAccountTv.setOnClickListener(this);
        joinAsTv.setOnClickListener(this);
        Paper.init(v.getContext());

       emailPaper    = Paper.book().read(Prevalent.UserEmailKey);
        passwordPaper = Paper.book().read(Prevalent.UserPasswordKey);

        if( (emailPaper!="" && passwordPaper != "" )&& (emailPaper!= null && passwordPaper!= null))
        {
            System.out.println(emailPaper);

            usernameAccountTv.setText(emailPaper.split("@")[0]);
            signِAccountTv.setText(R.string.sign_out);

            System.out.println(emailPaper);
        }
        return  v;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.signِAccountTvId:
                if(signِAccountTv.getText().toString().equals(getString(R.string.sign_out))){
                    Paper.book().destroy();
                    intent = new Intent(getContext(),MainActivity.class);
                    startActivity(intent);
                }else{
                    intent = new Intent(getContext(),RegisterActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.joinAsId:
                intent = new Intent(getContext(), JoinAsActivity.class);
                startActivity(intent);
                break;
        }


    }
}
