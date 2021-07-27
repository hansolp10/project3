package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class InfoActivity03_1 extends AppCompatActivity {
    Toolbar toolbar1;
    Button btn_gomain;
    Fragment1 fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info03_1);

        info_toolbar();

        fragment1 = new Fragment1();
        btn_gomain = findViewById(R.id.btn_gomain);

        btn_gomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InfoActivity03_1.this, MainActivity.class);
                startActivity(intent);

            }
        });

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

    private void executeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl1, fragment).commit();
    }

}