package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class InfoActivity03 extends AppCompatActivity {
    Toolbar toolbar1;
    EditText et_name, et_phone, et_email;
    Button btn_submit;

    // 파이어베이스 데이터베이스 연동
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 것.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info03);

        info_toolbar();

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        btn_submit = findViewById(R.id.btn_submit);


        //버튼 누르면 값을 저장
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //에딧 텍스트 값을 문자열로 바꾸어 함수에 넣어준다.
                addVolunteer(et_name.getText().toString(),et_phone.getText().toString(),et_email.getText().toString());



            }
        });

    }// onCreate()끝나는 부분



    //값을 파이어베이스 Realtime database로 넘기는 함수
    public void addVolunteer(String name, String phone, String email) {

        //여기에서 직접 변수를 만들어서 값을 직접 넣는것도 가능

        //volunteer.java에서 선언했던 함수
        volunteer volunteer = new volunteer(name,phone,email);

        //child는 해당 키 위치로 이동하는 함수
        databaseReference.child(name).setValue(volunteer);

        // 제출 완료 페이지로 이동하는 코드
        Intent intent = new Intent(this, InfoActivity03_1.class);
        startActivity(intent);

    }



    private void info_toolbar() {
        toolbar1 = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // 기존 타이틀 제거
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기버튼 생성
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24); // 하얀 화살표
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}