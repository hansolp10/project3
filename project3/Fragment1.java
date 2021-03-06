package com.example.project3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Fragment1 extends Fragment {
    FrameLayout fl1;
    ImageButton btn_info,btn_map,btn_vt;
    Adapter adapter;
    ViewPager viewPager;
    private TextView tv_temp;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference docRef = db.collection("sparkProject").document("sparkSensor");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.fragment_1, container, false);

        btn_info = fragment.findViewById(R.id.btn_info);
        btn_map = fragment.findViewById(R.id.btn_map);
        btn_vt = fragment.findViewById(R.id.btn_vt);
        fl1 = fragment.findViewById(R.id.fl1);
        tv_temp = fragment.findViewById(R.id.tv_temp);


        viewPager = (ViewPager) fragment.findViewById(R.id.view);
        adapter = new Adapter(this.getContext());
        viewPager.setAdapter(adapter);



        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                long temp = documentSnapshot.getLong("temp");
                String dust = documentSnapshot.getString("dust");

                tv_temp.setText("?????? ????????? ???????????? ????????? "+ temp + "???\n???????????? ?????? " + dust + "?????????");
            }
        });


        // btn_info ????????? ?????? ?????? ???????????? ????????????
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity01.class);
                startActivity(intent);
            }
        });

        // btn_map ????????? ???????????? ???????????? ????????????
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity02.class);
                startActivity(intent);
            }
        });

        // btn_vt ????????? ???????????? ???????????? ????????????
        btn_vt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity03.class);
                startActivity(intent);


            }
        });

        return fragment;

    }



}