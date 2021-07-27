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

public class TrioFragment extends Fragment {
    ImageButton btn_trio_1,btn_trio_2,btn_trio_3;

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
        View fragment = inflater.inflate(R.layout.fragment_trio, container, false);
        btn_trio_1 = fragment.findViewById(R.id.btn_trio_1);
        btn_trio_2 = fragment.findViewById(R.id.btn_trio_2);
        btn_trio_3 = fragment.findViewById(R.id.btn_trio_3);

        SharedPreferences pref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        btn_trio_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("image", "4");
                editor.commit();
                activity.executeFragment(new SheetFragment());
            }
        });
        btn_trio_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("image", "5");
                editor.commit();
                activity.executeFragment(new SheetFragment());
            }
        });
        btn_trio_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("image", "6");
                editor.commit();
                activity.executeFragment(new SheetFragment());
            }
        });

        return fragment;
    }
}