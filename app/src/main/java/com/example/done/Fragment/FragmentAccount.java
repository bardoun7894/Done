package com.example.done.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.done.JoinAsActivity;
import com.example.done.LoginActivity;
import com.example.done.R;
import com.example.done.RegisterActivity;

public class FragmentAccount extends androidx.fragment.app.Fragment implements View.OnClickListener  {
    TextView signِAccountTv,joinAsTv ;
    Intent intent ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account,container,false);
       signِAccountTv =v.findViewById(R.id.signِAccountTvId);
        joinAsTv =v.findViewById(R.id.joinAsId);
        signِAccountTv.setOnClickListener(this);
        joinAsTv.setOnClickListener(this);
        return  v;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.signِAccountTvId:
                intent = new Intent(getContext(),RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.joinAsId:
                intent = new Intent(getContext(), JoinAsActivity.class);
                startActivity(intent);
                break;
        }


    }
}
