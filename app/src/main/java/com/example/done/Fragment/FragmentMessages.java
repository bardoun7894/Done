package com.example.done.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.done.models.ItemChat;
import com.example.done.R;
import com.example.done.RecyclerItemMessages;

import java.util.ArrayList;

public class FragmentMessages extends androidx.fragment.app.Fragment {

RecyclerView recyclerView;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

   View view =inflater.inflate(R.layout.fragment_messages,container,false);
      recyclerView = view.findViewById(R.id.recycler);
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
      recyclerView.setAdapter(new RecyclerItemMessages(initData()));
        return view;
    }

  ArrayList<ItemChat> initData(){
      ArrayList<ItemChat> ar =new ArrayList<>();
       ar.add(new ItemChat(R.drawable.ic_account,"Mohamed ","#4567890156"));
        ar.add(new ItemChat(R.drawable.ic_account,"bilal ","#4567890156"));
        ar.add(new ItemChat(R.drawable.ic_account,"mohsin ","#4567890156"));
        return ar;
    }

}
