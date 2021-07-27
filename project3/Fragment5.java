package com.example.project3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Fragment5 extends Fragment {
    private Context context;

    Button btn_voice;
    Fragment5 fragment5;
    Button btn_bench1, btn_bench2, btn_bench3, btn_bench4, btn_bench5;
    TextView tv_available;
    ImageView iv_On1, iv_On2, iv_On3, iv_On4, iv_On5, iv_Off1, iv_Off2, iv_Off3, iv_Off4, iv_Off5;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference docRef = db.collection("sparkProject").document("sparkPress");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_5, container, false);
        context = container.getContext();

        fragment5 = new Fragment5();
        btn_bench1 = fragment.findViewById(R.id.btn_bench1);
        btn_bench2 = fragment.findViewById(R.id.btn_bench2);
        btn_bench3 = fragment.findViewById(R.id.btn_bench3);
        btn_bench4 = fragment.findViewById(R.id.btn_bench4);
        btn_bench5 = fragment.findViewById(R.id.btn_bench5);
        iv_On1 = fragment.findViewById(R.id.iv_On1);
        iv_On2 = fragment.findViewById(R.id.iv_On2);
        iv_On3 = fragment.findViewById(R.id.iv_On3);
        iv_On4 = fragment.findViewById(R.id.iv_On4);
        iv_On5 = fragment.findViewById(R.id.iv_On5);
        iv_Off1 = fragment.findViewById(R.id.iv_Off1);
        iv_Off2 = fragment.findViewById(R.id.iv_Off2);
        iv_Off3 = fragment.findViewById(R.id.iv_Off3);
        iv_Off4 = fragment.findViewById(R.id.iv_Off4);
        iv_Off5 = fragment.findViewById(R.id.iv_Off5);

        tv_available = fragment.findViewById(R.id.tv_available);

        // 벤치1 클릭 시 Firebase firestore에서 데이터 수신
        btn_bench1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.e("success", "DocumentSnapshot data: " + document.getString("press1"));
                            } else {
                                Log.e("fail", "No such document");
                            }
                        } else {
                            Log.e("fail perfectly", "get failed with ", task.getException());
                        }
                    }
                });
                // 데이터값 받아서 역치 50이상일 경우 벤치이용가능유무 판별
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String press = documentSnapshot.getString("press1");
                            if (Integer.parseInt(press) < 50) {
                                Log.e("please", "value" + press);
                                Toast.makeText(context, "벤치이용가능!", Toast.LENGTH_SHORT).show();
                                tv_available.setText("벤치이용가능!");
                                iv_On1.setVisibility(View.VISIBLE);
                                btn_voice.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(context, "벤치이용불가능!", Toast.LENGTH_SHORT).show();
                                tv_available.setText("벤치이용불가능!");
                                iv_On1.setVisibility(View.INVISIBLE);
                                iv_Off1.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
            }
        });
        // 화장실 오른쪽
        btn_bench2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.e("success", "DocumentSnapshot data: " + document.getString("press2"));
                            } else {
                                Log.e("fail", "No such document");
                            }
                        } else {
                            Log.e("fail perfectly", "get failed with ", task.getException());
                        }
                    }
                });
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String press = documentSnapshot.getString("press2");
                            if (Integer.parseInt(press) < 50) {
                                Log.e("please", "value" + press);
                                Toast.makeText(context, "벤치이용가능!", Toast.LENGTH_SHORT).show();
                                tv_available.setText("벤치이용가능!");
                                iv_On2.setVisibility(View.VISIBLE);
                                btn_voice.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(context, "벤치이용불가능!", Toast.LENGTH_SHORT).show();
                                tv_available.setText("벤치이용불가능!");
                                iv_On2.setVisibility(View.INVISIBLE);
                                iv_Off2.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
            }
        });
        // 분수 앞 벤치
        btn_bench3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.e("success", "DocumentSnapshot data: " + document.getString("press3"));
                            } else {
                                Log.e("fail", "No such document");
                            }
                        } else {
                            Log.e("fail perfectly", "get failed with ", task.getException());
                        }
                    }
                });
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String press = documentSnapshot.getString("press3");
                            if (Integer.parseInt(press) < 50) {
                                Log.e("please", "value" + press);
                                Toast.makeText(context, "벤치이용가능!", Toast.LENGTH_SHORT).show();
                                tv_available.setText("벤치이용가능!");
                                iv_On3.setVisibility(View.VISIBLE);
                                btn_voice.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(context, "벤치이용불가능!", Toast.LENGTH_SHORT).show();
                                tv_available.setText("벤치이용불가능!");
                                iv_On3.setVisibility(View.INVISIBLE);
                                iv_Off3.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
            }
        });
        btn_bench4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.e("success", "DocumentSnapshot data: " + document.getString("press4"));
                            } else {
                                Log.e("fail", "No such document");
                            }
                        } else {
                            Log.e("fail perfectly", "get failed with ", task.getException());
                        }
                    }
                });
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String press = documentSnapshot.getString("press4");
                            if (Integer.parseInt(press) < 50) {
                                Log.e("please", "value" + press);
                                Toast.makeText(context, "벤치이용가능!", Toast.LENGTH_SHORT).show();
                                tv_available.setText("벤치이용가능!");
                                iv_On4.setVisibility(View.VISIBLE);
                                btn_voice.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(context, "벤치이용불가능!", Toast.LENGTH_SHORT).show();
                                tv_available.setText("벤치이용불가능!");
                                iv_On4.setVisibility(View.INVISIBLE);
                                iv_Off4.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
            }
        });
        btn_bench5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.e("success", "DocumentSnapshot data: " + document.getString("press5"));
                            } else {
                                Log.e("fail", "No such document");
                            }
                        } else {
                            Log.e("fail perfectly", "get failed with ", task.getException());
                        }
                    }
                });
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String press = documentSnapshot.getString("press5");
                            if (Integer.parseInt(press) < 50) {
                                Log.e("please", "value" + press);
                                Toast.makeText(context, "벤치이용가능!", Toast.LENGTH_SHORT).show();
                                tv_available.setText("벤치이용가능!");
                                iv_On5.setVisibility(View.VISIBLE);
                                btn_voice.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(context, "벤치이용불가능!", Toast.LENGTH_SHORT).show();
                                tv_available.setText("벤치이용불가능!");
                                iv_On5.setVisibility(View.INVISIBLE);
                                iv_Off5.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
            }
        });
        btn_voice = fragment.findViewById(R.id.btn_voice);
        btn_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VoiceActivity.class);
                startActivity(intent);
            }
        });
        return fragment;
    }

}