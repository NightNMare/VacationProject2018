package com.example.dlarb.vacationproject2018;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPrefs;
    private TextView TV_StopWatch;
    private TextView MyLocation;
    private String name;
    private String Reg_Address;
    private String Reg_StopWatch;
    SharedPreferences sPrefs;
    SharedPreferences aPrefs;
    SharedPreferences.Editor sEditor;
    SharedPreferences.Editor aEditor;

    DrawerLayout drawerLayout;
    ImageView setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView StopWatch = findViewById(R.id.TV_stopwatch);
        TextView TV_GoogleMap = findViewById(R.id.TV_Map);
        MyLocation = findViewById(R.id.googlemap_mylocation);
        TV_StopWatch = findViewById(R.id.main_stopwatch_record);
        TextView Drawer_GPS = findViewById(R.id.drawer_gps);
        TextView Drawer_SW = findViewById(R.id.drawer_stopwatch);
        TextView Profile_name = findViewById(R.id.profile_name);
        drawerLayout = findViewById(R.id.drawerlayout);
        setting = findViewById(R.id.setting);

        sPrefs = getSharedPreferences("StopWatchData",MODE_PRIVATE);
        aPrefs = getSharedPreferences("AddressData",MODE_PRIVATE);
        sEditor = sPrefs.edit();
        aEditor = aPrefs.edit();

        MyLocation.setText(aPrefs.getString("AddressData","저장된 주소가 없습니다."));
        TV_StopWatch.setText(sPrefs.getString("StopWatchData","저장된 기록이 없습니다."));

        SharedPreferences nPrefs = getSharedPreferences("UserName", MODE_PRIVATE);
        name = nPrefs.getString("UserName", "사용자");
        Profile_name.setText(name);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passintent = new Intent(MainActivity.this, PasswdSetting.class);
                startActivityForResult(passintent,335);
            }
        });

        Drawer_GPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMap = new Intent(MainActivity.this, GoogleMap.class);
                startActivityForResult(intentMap, 334);
                drawerLayout.closeDrawers();
            }
        });

        Drawer_SW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSW = new Intent(MainActivity.this, StopWatch.class);
                startActivityForResult(intentSW, 333);
                drawerLayout.closeDrawers();
            }
        });

        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPrefs = getSharedPreferences("Login", MODE_PRIVATE);
                SharedPreferences.Editor mEditor = mPrefs.edit();
                mEditor.putString("Login", "");
                mEditor.apply();
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(MainActivity.this, Splash.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {
            case 333:
                TV_StopWatch.setText(data.getStringExtra("StopWatch"));
                sEditor.putString("StopWatchData",data.getStringExtra("StopWatch"));
                sEditor.apply();
                break;
            case 334:
                MyLocation.setText(data.getStringExtra("Address"));
                aEditor.putString("AddressData",data.getStringExtra("Address"));
                aEditor.apply();
                break;
            case 335:
                break;
        }
    }
}
