package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_play, btn_pause, btn_stop;

    private Handler mHandler;
    Socket socket;
    private String ip = "192.168.43.184"; // 서버의 IP 주소
    private int port = 8097; // PORT번호를 꼭 맞추어 주어야한다.
    String emotion = null;
    String emotion1 = null;

    Toolbar toolbar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        mHandler = new Handler();

        info_toolbar();

        SharedPreferences pref = getSharedPreferences("emotion", MODE_PRIVATE);
        emotion = pref.getString("emotion", "0");

        Log.d("====", "재생 전 감정" + emotion);


        btn_play = findViewById(R.id.btn_play);
        btn_pause = findViewById(R.id.btn_pause);
        btn_stop = findViewById(R.id.btn_stop);

        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                //우울해
                if (emotion.equals("1")) {
                    // 라즈베리파이의 ip주소로 8091포트에 텍스트를 보냅니다.
                    ConnectThread th = new ConnectThread();
                    th.start();
                    Log.d("mmCheck", "start");
                }
                //비가 오네
                else if (emotion.equals("2")) {
                    // 라즈베리파이의 ip주소로 8091포트에 텍스트를 보냅니다.
                    ConnectThread th = new ConnectThread();
                    th.start();
                    Log.d("mmCheck", "start");
                }
                //즐거워
                else if (emotion.equals("3")) {
                    // 라즈베리파이의 ip주소로 8091포트에 텍스트를 보냅니다.
                    ConnectThread th = new ConnectThread();
                    th.start();
                    Log.d("mmCheck", "start");
                }
                //혼자 있고 싶어
                else if (emotion.equals("4")) {
                    // 라즈베리파이의 ip주소로 8091포트에 텍스트를 보냅니다.
                    ConnectThread th = new ConnectThread();
                    th.start();
                    Log.d("mmCheck", "start");
                }
                //연애하고 싶어
                else if (emotion.equals("5")) {
                    // 라즈베리파이의 ip주소로 8091포트에 텍스트를 보냅니다.
                    ConnectThread th = new ConnectThread();
                    th.start();
                    Log.d("mmCheck", "start");
                }
                //뛰고 싶어
                else if (emotion.equals("6")) {
                    // 라즈베리파이의 ip주소로 8091포트에 텍스트를 보냅니다.
                    ConnectThread th = new ConnectThread();
                    th.start();
                    Log.d("mmCheck", "start");
                } else if (emotion.equals("8")) {
                    emotion = emotion1;
                } else if (emotion.equals("9")) {
                    emotion = emotion1;
                }
                break;

            case R.id.btn_pause:
                emotion1 = emotion;
                emotion = "8";
                ConnectThread th = new ConnectThread();
                th.start();

                Log.d("mmCheck", "pause");
                //Toast.makeText(this, "일시정지합니다.", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btn_stop:
                emotion1 = emotion;
                emotion = "9";
                th = new ConnectThread();
                th.start();
                //Toast.makeText(this, "멈춥니다.", Toast.LENGTH_SHORT).show();
                Log.d("mmCheck", "stop");

                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            socket.close();//종료시 소켓도 닫아주어야한다.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ConnectThread extends Thread {//소켓통신을 위한 스레드

        public void run() {
            try {
                //소켓 생성
                InetAddress serverAddr = InetAddress.getByName(ip);
                socket = new Socket(serverAddr, port);
                //입력 메시지
                String sndMsg = emotion;
                Log.d("=============", sndMsg);
                //데이터 전송
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                out.println(sndMsg);
                //데이터 수신
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String read = input.readLine();

                socket.close();//사용이 끝난뒤 꼭 닫아주어야한다.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 툴바 메소드
    private void info_toolbar() {
        toolbar1 = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // 기존 타이틀 제거
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기버튼 생성
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24); // 하얀 화살표
    }

    // 툴바 뒤로가기 버튼 메소드
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}