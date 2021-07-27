package com.example.project3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btm_nav;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    Fragment5 fragment5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btm_nav = findViewById(R.id.btm_nav);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();


        // 최초 Activity Fragment1 화면 출력
        executeFragment(fragment1);
        // 프래그먼트 매니저.관리할거다.fragment1으로 대체
        // transaction은 커밋이나 롤백으로 완성을 지어줘야 화면대체가 된다.

        btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                // 선택한 메뉴 아이템 클릭시

                int id = item.getItemId();

                if (id == R.id.item_home) {
                    executeFragment(fragment1);
                } else if (id == R.id.item_piano) {
                    executeFragment(fragment2);
                } else if (id == R.id.item_step) {
                    executeFragment(fragment3);
                } else if (id == R.id.item_plant) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = getPackageManager().getLaunchIntentForPackage("com.DefaultCompany.SmartFarm");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }, 3000);
                    executeFragment(fragment4);

                } else if (id == R.id.item_bench) {
                    executeFragment(fragment5);
                }

                // true : 선택한 아이템 클릭시 fragment 전환 후 포커싱 기능!
                // false: 포커싱 비활성화
                return true;
            }
        });

    }

    public void executeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl1, fragment).commit();
    }

    public boolean getPackageList() {
        boolean isExist = false;

        PackageManager pkgMgr = getPackageManager();
        List<ResolveInfo> mApps;
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = pkgMgr.queryIntentActivities(mainIntent, 0);

        try {
            for (int i = 0; i < mApps.size(); i++) {
                if (mApps.get(i).activityInfo.packageName.startsWith("com.DefaultCompany.SmartFarm")) {
                    isExist = true;
                    break;
                }
            }
        } catch (Exception e) {
            isExist = false;
        }
        return isExist;

    }
}