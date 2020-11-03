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

public class FragmentMessages extends androidx.fragment.app.Fragment {
ArrayList<User> userList ;
RecyclerView recyclerView;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
   View view =inflater.inflate(R.layout.fragment_messages,container,false);
      recyclerView = view.findViewById(R.id.recycler);
        userList =new ArrayList<>();
      recyclerView.setHasFixedSize(true);
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initData(userList);
        return view;
    }

  void initData(final ArrayList<User> userList) {
      final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
      DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
      reference.addListenerForSingleValueEvent(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              userList.clear();
              for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                  User user = snapshot.getValue(User.class);
                  assert user != null;
                  assert firebaseUser != null;
                  System.out.println(user.getEmail());

                 if (!user.getEmail().equals(firebaseUser.getEmail())) {
                      userList.add(user);
                   }
                  recyclerView.setAdapter(new RecyclerItemMessages(userList));
              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });
//
//
//      ArrayList<ItemChat> ar =new ArrayList<>();
//
//       ar.add(new ItemChat(R.drawable.ic_account,"Mohamed ","#4567890156"));
//        ar.add(new ItemChat(R.drawable.ic_account,"bilal ","#4567890156"));
//        ar.add(new ItemChat(R.drawable.ic_account,"mohsin ","#4567890156"));
//        return ar;

  }
}
