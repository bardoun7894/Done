package com.example.done.bottomsheets;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.done.Fragment.FragmentAccount;
import com.example.done.Fragment.FragmentHome;
import com.example.done.Fragment.FragmentMessages;
import com.example.done.Fragment.FragmentNotification;
import com.example.done.FragmentToActivity;
import com.example.done.R;
import com.example.done.SearchServicesActivity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.zip.Inflater;

public class BottomSheetLayout extends BottomSheetDialogFragment  implements View.OnClickListener {
LinearLayout lowPriceOrder,highPriceOrder ;
    private FragmentToActivity mCallback;

    String comm ;
    private FragmentManager manager;


    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        try {
            mCallback = (FragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FragmentToActivity");
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.bottomsheetlayout,container,false);

    lowPriceOrder =v.findViewById(R.id.lowPriceOrderId);
     highPriceOrder =v.findViewById(R.id.highPriceOrderId);
     lowPriceOrder.setOnClickListener(this);
     highPriceOrder.setOnClickListener(this);
       return  v ;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lowPriceOrderId:
            comm ="lowPriceOrder";
            sendData(comm);
                break;
                case R.id.highPriceOrderId:
            comm ="highPriceOrder";
          sendData(comm);
            break;

        }

    }

    private void sendData(String comm)
    {
        mCallback.communicate(comm);
     requireActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();

    }
}
