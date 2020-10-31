package com.example.done.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.done.MainActivity;
import com.example.done.MakeAService;
import com.example.done.Prevalent;
import com.example.done.joinDoc.JoinAsActivity;
import com.example.done.R;
import com.example.done.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class FragmentAccount extends androidx.fragment.app.Fragment implements View.OnClickListener  {
    TextView signِAccountTv,joinAsTv,usernameAccountTv ,makeAServiceTv;
    Intent intent ;
    LinearLayout makeAServiceLayout ;
    String emailPaper    = "";
    String passwordPaper = "";
    String usernamePaper = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.fragment_account , container ,false );
       signِAccountTv =v.findViewById(R.id.signِAccountTvId);
        joinAsTv =    v.findViewById(R.id.joinAsId);
        usernameAccountTv =v.findViewById(R.id.usernameAccountId);
        makeAServiceTv =v.findViewById(R.id.addAservice);
        makeAServiceLayout =v.findViewById(R.id.makeAServiceLayoutId);
        signِAccountTv.setOnClickListener(this);
        makeAServiceTv.setOnClickListener(this);
        joinAsTv.setOnClickListener(this);
        Paper.init(v.getContext());

       emailPaper    = Paper.book().read(Prevalent.UserEmailKey);
        passwordPaper = Paper.book().read(Prevalent.UserPasswordKey);
        usernamePaper = Paper.book().read(Prevalent.UserNameKey);

        if( (emailPaper!="" && passwordPaper != "" )&& (emailPaper!= null && passwordPaper!= null))
        {
            usernameAccountTv.setText(usernamePaper);
            signِAccountTv.setText(R.string.sign_out);
            makeAServiceLayout.setVisibility(View.VISIBLE);

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
                case R.id.addAservice:
                intent = new Intent(getContext(), MakeAService.class);
                startActivity(intent);
                break;
        }


    }
}
