package com.example.project3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Fragment2 extends Fragment {
    public static Fragment2 newInstance(){
        return new Fragment2();
    }

    ImageButton btn_solo, btn_duo, btn_trio;

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
        View fragment = inflater.inflate(R.layout.fragment_2, container, false);

        btn_solo = fragment.findViewById(R.id.btn_solo);
        btn_duo = fragment.findViewById(R.id.btn_duo);
        btn_trio = fragment.findViewById(R.id.btn_trio);

        btn_solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.executeFragment(new SoloFragment());
            }
        });

        btn_duo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.executeFragment(new DuoFragment());
            }
        });

        btn_trio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.executeFragment(new TrioFragment());
            }
        });

        return fragment;
    }

}

