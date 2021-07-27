package com.example.project3;

import android.content.Context;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class SoloFragment extends Fragment {
    ImageButton btn_solo_1, btn_solo_2, btn_solo_3;


    MainActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_solo, container, false);
        btn_solo_1 = fragment.findViewById(R.id.btn_solo_1);
        btn_solo_2 = fragment.findViewById(R.id.btn_solo_2);
        btn_solo_3 = fragment.findViewById(R.id.btn_solo_3);

        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        btn_solo_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("image", "0");
                editor.commit();
                activity.executeFragment(new SheetFragment());
            }
        });

        btn_solo_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("image", "1");
                editor.commit();
                activity.executeFragment(new SheetFragment());
            }
        });
        btn_solo_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("image", "2");
                editor.commit();
                activity.executeFragment(new SheetFragment());
            }
        });


        return fragment;
    }
}