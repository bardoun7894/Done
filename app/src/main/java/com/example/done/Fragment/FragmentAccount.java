package com.example.done.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.done.MainActivity;
import com.example.done.MakeAService;
import com.example.done.Prevalent;
import com.example.done.joinDoc.JoinAsActivity;
import com.example.done.R;
import com.example.done.RegisterActivity;
import com.example.done.joinDoc.ProAccountActivity;
import com.example.done.models.User;
import com.example.recyclers.RecyclerItemMessages;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class FragmentAccount extends androidx.fragment.app.Fragment implements View.OnClickListener  {
    TextView signِAccountTv,joinAsTv,usernameAccountTv ,makeAServiceTv;
    Intent intent ;
    LinearLayout makeAServiceLayout ,joinAsLn;
    ImageView imageAccount ;
    String emailPaper   = "";
    String passwordPaper = "";
    String usernamePaper = "";
    Uri imageUri ;
    String imagePath  ="";
    String type_of_user ="";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate( R.layout.fragment_account , container ,false );
       signِAccountTv =v.findViewById(R.id.signِAccountTvId);
        joinAsTv =    v.findViewById(R.id.joinAsId);
        usernameAccountTv =v.findViewById(R.id.usernameAccountId);
        makeAServiceTv =v.findViewById(R.id.addAservice);
        imageAccount=v.findViewById(R.id.ImageAccountId);
        makeAServiceLayout =v.findViewById(R.id.makeAServiceLayoutId);
       joinAsLn=v.findViewById(R.id.joinAsLnId);
        signِAccountTv.setOnClickListener(this);
        makeAServiceTv.setOnClickListener(this);
        joinAsTv.setOnClickListener(this);
        imageAccount.setOnClickListener(this);
        Paper.init(v.getContext());

        emailPaper    = Paper.book().read( Prevalent.UserEmailKey);
        passwordPaper = Paper.book().read( Prevalent.UserPasswordKey);
        usernamePaper = Paper.book().read( Prevalent.UserNameKey);
        String photoUrl = Paper.book().read( Prevalent.photoProfile);
      String   ls = Paper.book().read(Prevalent.photoProfile);
        String s =Paper.book().read( Prevalent.type_of_user);
       System.out.println(s);

        if(usernamePaper!=null ) {
            type_of_user = Paper.book().read( Prevalent.type_of_user);
            Glide.with(v.getContext()).load(ls).into(imageAccount);
       if(type_of_user!=  "بائع" && photoUrl!=null && photoUrl!=""){
               joinAsLn.setVisibility(View.GONE);
             }


        }

        if( (emailPaper!="" && passwordPaper != "" ) && (emailPaper!= null && passwordPaper!= null) )
        {
            usernameAccountTv.setText(usernamePaper);
            signِAccountTv.setText(R.string.sign_out);
            if(type_of_user.equals("مشتري")){
//           makeAServiceTv.setVisibility(View.GONE);
                makeAServiceLayout.setVisibility(View.GONE);
                System.out.println(type_of_user);
            }else{
                makeAServiceLayout.setVisibility(View.VISIBLE);
            }
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
            case R.id.ImageAccountId:
                intent = new Intent(getContext(), ProAccountActivity.class);
                startActivity(intent);
                break;
                case R.id.addAservice:
                intent = new Intent(getContext(), MakeAService.class);
                startActivity(intent);
                break;
        }


    }
}
