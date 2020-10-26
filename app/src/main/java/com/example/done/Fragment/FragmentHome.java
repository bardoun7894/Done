package com.example.done.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.done.home4Buttons.GraphicActivity;
import com.example.done.home4Buttons.ProgramingActivity;
import com.example.done.R;
import com.example.done.home4Buttons.TranslateActivity;
import com.example.done.home4Buttons.VideoAnimationActivity;

public class FragmentHome extends androidx.fragment.app.Fragment implements  View.OnClickListener {

    ImageView graphicImage ,videoEditing,translate,computerScience;

    Intent intent ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        graphicImage=v.findViewById(R.id.graphicId);
        videoEditing=v.findViewById(R.id.videoEditingId);
        translate=v.findViewById(R.id.translateId);
        computerScience=v.findViewById(R.id.programmingId);
         graphicImage.setOnClickListener(this);
         videoEditing.setOnClickListener(this);
         translate.setOnClickListener(this);
         computerScience.setOnClickListener(this);



        return  v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.videoEditingId:
              intent =new Intent(v.getContext(), VideoAnimationActivity.class);
                v.getContext().startActivity(intent);
                break;
            case R.id.graphicId:
             intent =new Intent(v.getContext(), GraphicActivity.class);
                v.getContext().startActivity(intent);
                break;
           case R.id.translateId:
             intent =new Intent(v.getContext(), TranslateActivity.class);
                v.getContext().startActivity(intent);
                break;
                case R.id.programmingId:
             intent =new Intent(v.getContext(), ProgramingActivity.class);
                v.getContext().startActivity(intent);
                break;
        }


    }
    }

