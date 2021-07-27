package com.example.project3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class VoiceActivity extends AppCompatActivity {

    Toolbar toolbar1;

    Context cThis; // context 설정

    // 음성 인식용
    Intent SttIntent;
    SpeechRecognizer mRecognizer;

    // 음성 출력용
    TextToSpeech tts;

    // 화면 처리용
    Button btn_sttStart;
    EditText txtInMsg;
    EditText txtSystem;
    Button btn_move;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        cThis = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);
        info_toolbar();
        // 음성 인식
        SttIntent = new Intent((RecognizerIntent.ACTION_RECOGNIZE_SPEECH));
        SttIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getApplicationContext().getPackageName());
        SttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(cThis);
        mRecognizer.setRecognitionListener(listener);
        // 음성 출력 생성, 리스너 초기화
        tts = new TextToSpeech(cThis, (status) -> {
            if (status != android.speech.tts.TextToSpeech.ERROR) {
                tts.setLanguage(Locale.KOREAN);
            }
        });

        btn_sttStart = findViewById(R.id.btn_sttStart);
        btn_sttStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(cThis, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(VoiceActivity.this, new String[]{Manifest.permission.RECORD_AUDIO}, 1);
                    // 권한 허용하지 않는 경우
                } else {
                    // 권한 허용한 경우
                    try {
                        mRecognizer.startListening(SttIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        // 입력박스 설정
        txtInMsg = findViewById(R.id.txtInMsg);
        txtSystem = findViewById(R.id.txtSystem);
        // musicactivity로 넘어가는 버튼 설정
        btn_move = findViewById(R.id.btn_move);
        // 어플 실행되면 자동으로 1초 뒤에 음성 인식 시작
        new android.os.Handler().postDelayed(
                () -> {
                    txtSystem.setText("어플 실행됨, 자동 실행" + txtSystem.getText());
                    btn_sttStart.performClick();
                }, 1000);
        // stt가 일정 시간 되면 죽기 때문에 일정 시간에 한번씩 계속 실행 처리 -> test를 위한 것, 실제 어플에 절대 사용 금지!
        // live check를 해서 계속 실행되게끔 하기!

    }

    // 툴바 메소드
    private void info_toolbar() {
        toolbar1 = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // 기존 타이틀 제거
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기버튼 생성
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24); // 하얀 화살표
    }
    // 툴바 뒤로가기 메소드
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

    // 음성 인식 위한 메소드
    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {
            txtSystem.setText("ready" + txtSystem.getText());
        }

        @Override
        public void onBeginningOfSpeech() {
            txtSystem.setText("begin" + txtSystem.getText());
        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {
            txtSystem.setText("buffer" + txtSystem.getText());
        }

        @Override
        public void onEndOfSpeech() {
            txtSystem.setText("end" + txtSystem.getText());
        }

        @Override
        public void onError(int error) {
            txtSystem.setText("error" + txtSystem.getText());
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            txtSystem.setText("partial" + txtSystem.getText());
        }

        @Override
        public void onEvent(int eventType, Bundle params) {
            txtSystem.setText("event" + txtSystem.getText());
        }

        @Override
        public void onResults(Bundle results) {
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = results.getStringArrayList(key);
            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);

            txtInMsg.setText(rs[0] + txtInMsg.getText());
            FuncVoiceOrderCheck(rs[0]);

            mRecognizer.startListening(SttIntent);
        }

    };

    // 입력된 음성 메시지 확인 후 동작처리
    private void FuncVoiceOrderCheck(String VoiceMsg) {
//        if(VoiceMsg.length()<1) return;
//        VoiceMsg = VoiceMsg.replace(" ", ""); // 공백 제거

        SharedPreferences pref = getSharedPreferences("emotion", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        Log.d("====", "감정은?" + VoiceMsg.indexOf("우울해"));

        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (VoiceMsg.indexOf("우울해") == 0 || VoiceMsg.indexOf("우울할 때 듣는 노래 틀어 줘") == 0) {
                    Log.d("====", "여기 도착함?");
                    editor.putString("emotion", "1");
                    editor.commit();
                } else if (VoiceMsg.indexOf("비가 오네") == 0 || VoiceMsg.indexOf("비올 때 듣는 노래 틀어 줘") == 0) {
                    editor.putString("emotion", "2");
                    editor.commit();
                } else if (VoiceMsg.indexOf("즐거워") == 0 || VoiceMsg.indexOf("즐거울 때 듣는 노래 틀어 줘") == 0) {
                    editor.putString("emotion", "3");
                    editor.commit();
                } else if (VoiceMsg.indexOf("혼자 있고 싶어") == 0 || VoiceMsg.indexOf("혼자 있고 싶을 때 듣는 노래 틀어 줘") == 0) {
                    editor.putString("emotion", "4");
                    editor.commit();
                } else if (VoiceMsg.indexOf("연애하고 싶어") == 0 || VoiceMsg.indexOf("연애하고 싶을 때 듣는 노래 틀어 줘") == 0) {
                    editor.putString("emotion", "5");
                    editor.commit();
                } else if (VoiceMsg.indexOf("뛰고 싶어") == 0 || VoiceMsg.indexOf("뛰고 싶을 때 듣는 노래 틀어 줘") == 0) {
                    editor.putString("emotion", "6");
                    editor.commit();
                }

                Intent intent = new Intent(VoiceActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
        }
        if (mRecognizer != null) {
            mRecognizer.destroy();
            mRecognizer.cancel();
            mRecognizer = null;
        }
    }
}