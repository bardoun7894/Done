package com.example.done.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.done.Prevalent;
import com.example.done.R;
import com.example.done.models.ItemNotification;
import com.example.done.models.User;
import com.example.recyclers.RecyclerItemMessages;
import com.example.recyclers.recyclerNotification;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.paperdb.Paper;

public class FragmentNotification extends androidx.fragment.app.Fragment {
RecyclerView recyclerView;
ArrayList<ItemNotification> notificationsList;
    List<String> classification ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View v =inflater.inflate(R.layout.fragment_notification,container,false);
    recyclerView=v.findViewById(R.id.recycler_notification);
       classification=new ArrayList<>();

        Paper.init(v.getContext());
        String username = Paper.book().read(Prevalent.UserNameKey);
        classification =Paper.book().read(Prevalent.classification);
        System.out.println(username+"dddl"+classification);
        if(  username!=null   && classification!=null ){
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            notificationsList =new ArrayList<>();
            System.out.println( classification + "ميؤميمؤميمميميمي" );
            initData(notificationsList);
                 }
    return v;
    }

    void initData(final ArrayList<ItemNotification> notificationsList)
    {

        for (int i =0 ;i<classification.size(); i++){
            System.out.println(classification.get(i).split(" : ")[0]);
            final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference serviceRef = FirebaseDatabase.getInstance().getReference().child("otlob").child(classification.get(i).split(" : ")[0]).child(classification.get(i).split(" : ")[1]);
           System.out.println(classification.get(i).split(" : ")[0] +" ");
            serviceRef.addListenerForSingleValueEvent(new ValueEventListener(){
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ItemNotification itemNotification = snapshot.getValue(ItemNotification.class);
                        assert itemNotification != null;
                        assert firebaseUser != null;
        if (!itemNotification.getUsername().equals(firebaseUser.getDisplayName()) ) {
                        notificationsList.add(itemNotification);
                      }
        if ( itemNotification.getDemandeTo().equals(firebaseUser.getDisplayName()) ) {
                 notificationsList.add(itemNotification);
                 System.out.println(itemNotification.getUsername()+"user");
              }
                        recyclerView.setAdapter(new recyclerNotification(notificationsList));

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }


    }

}
