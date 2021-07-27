package com.example.project3;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class StepActivity<repeat> extends AppCompatActivity implements SensorEventListener {
    Toolbar toolbar1;
    // 센서 연결을 위한 변수
    SensorManager sensorManager;
    Sensor stepCountSensor;
    TextView tvStepCount;
    Button btn_end;
    SensorEvent event;
    Fragment3 fragment3;
    // 현재 걸음 수
    private int step = 0;
    // 리스너 등록 후 걸음 수
    int count = 0;
    private View view;
    Boolean isOk = true;
    // TYPE_STEP_COUNTER 사용 이유: 앱이 종료 시 기존의 값을 가지고 있다가 증가한 값을 리턴!
    // TYPE_STEP_DETECTOR : 리턴 값이 무조건 1이 되어 앱 종료 시 0으로 초기화
    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_STEP_DETECTOR:
                tvStepCount.setText((step++) + "걸음");
                break;

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        info_toolbar();
        btn_end = findViewById(R.id.btn_end);
        tvStepCount = findViewById(R.id.tvStepCount);
        tvStepCount.setText("0");
        fragment3 = new Fragment3();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        if (stepCountSensor == null) {
            Toast.makeText(this, "걸음 센서가 탐지되지 못했습니다!", Toast.LENGTH_SHORT).show();
        }
        // Ctrl + Alt + M : 메소드 추출
        btn_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOk){
                    start();
                    isOk = !isOk;
                }else{
                    end();
                    isOk = !isOk;
                }
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

    private void start() {
        btn_end.setText("다시 시작");
        findViewById(R.id.tvStepCount).setBackground(getResources().getDrawable(R.drawable.oval_2));
        tvStepCount.setText("그린숲 시민공원과 함께한\n" + (step) + "걸음\n만큼 건강해졌어요!");
    }
    private void end() {
        btn_end.setText("끝내기");
        step = 0;
        findViewById(R.id.tvStepCount).setBackground(getResources().getDrawable(R.drawable.oval));
        tvStepCount.setText((step) + "걸음");
    }
    @Override
    protected void onResume() {
        super.onResume();
        // 센서 속도 결정
        sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
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
