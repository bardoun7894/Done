package com.example.done.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.done.GraphicActivity;
import com.example.done.R;

public class FragmentHome extends androidx.fragment.app.Fragment {

    ImageView graphicImage ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        graphicImage=v.findViewById(R.id.graphicId);
        ((View) graphicImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getContext(), GraphicActivity.class);
                startActivity(intent);
            }
        });
        return  v;
    }
}
