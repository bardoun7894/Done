package com.example.done.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.done.Prevalent;
import com.example.done.models.ItemChat;
import com.example.done.R;
import com.example.done.models.ItemNotification;
import com.example.done.models.User;
import com.example.recyclers.RecyclerItemMessages;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.paperdb.Paper;

public class FragmentMessages extends androidx.fragment.app.Fragment {
ArrayList<User> userList ;
RecyclerView recyclerView;
ProgressDialog progressDialog ;
String paper ="";
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
   View view =inflater.inflate(R.layout.fragment_messages,container,false);
      recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userList =new ArrayList<>();
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Paper.init(view.getContext());
        paper =Paper.book().read(Prevalent.UserNameKey);
        if(paper!="" && paper!=null){
         initData(userList);
        }


        return view;
    }

  void initData(final ArrayList<User> userList) {
      final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
      final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
      final DatabaseReference referenceOtlob = FirebaseDatabase.getInstance().getReference("demandeFromUser");
          reference.addListenerForSingleValueEvent(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                  userList.clear();
                  for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                      final User user = snapshot.getValue(User.class);
                      assert user != null;
                      assert firebaseUser != null;
         referenceOtlob.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshotOtlob) {
                              for (DataSnapshot snapshot : dataSnapshotOtlob.getChildren()) {
                 ItemNotification itemNotification = snapshot.getValue(ItemNotification.class);
                             String dem =itemNotification.getUsername();
                             String demandeTo = itemNotification.getDemandeTo() ;

          if ((user.getUsername().equals(dem) && firebaseUser.getDisplayName().equals(demandeTo))||
                  user.getUsername().equals(demandeTo) && firebaseUser.getDisplayName().equals(dem)
                    )
                    {
                userList.add(user);
              }

                              }

                              recyclerView.setAdapter(new RecyclerItemMessages(userList));
                          }

                          @Override
                          public void onCancelled(@NonNull DatabaseError databaseError) {

                          }
                      });

                  }
              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }
          });


  }
}
