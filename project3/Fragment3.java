package com.example.project3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

public class Fragment3 extends Fragment {
    FrameLayout fl3;
    Button btn_start;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_3, container, false);
        fl3 = fragment.findViewById(R.id.fl2);
        btn_start = fragment.findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StepActivity.class);
                startActivity(intent);
            }
        });
        return fragment;


    }
}