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

                tv_temp.setText("현재 그린숲 시민공원 기온은 "+ temp + "℃\n미세먼지 상태 " + dust + "입니다");
            }
        });


        // btn_info 클릭시 공원 안내 페이지로 이동하기
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity01.class);
                startActivity(intent);
            }
        });

        // btn_map 클릭시 편의시설 페이지로 이동하기
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InfoActivity02.class);
                startActivity(intent);
            }
        });

        // btn_vt 클릭시 자원봉사 페이지로 이동하기
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