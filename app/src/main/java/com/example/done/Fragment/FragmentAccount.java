package com.example.done.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.done.LoginActivity;
import com.example.done.R;

public class FragmentAccount extends androidx.fragment.app.Fragment {
    Button signِAccountBtn ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account,container,false);
        signِAccountBtn =v.findViewById(R.id.signِAccountBtnId);
        signِAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        Intent intent =new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
            }
            });
        return  v;


    }

}
