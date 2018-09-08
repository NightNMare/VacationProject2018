package com.example.dlarb.vacationproject2018;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PasswdSetting extends AppCompatActivity {

    TextView num1, num2, num3, num4, num5, num6, num7, num8, num9, num0, TV_pass;
    View pass1, pass2, pass3, pass4;
    ImageView remove;
    int count = 0;
    String strnum = null;
    String userspass = null;
    SharedPreferences mprefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwd_setting);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);
        num0 = findViewById(R.id.num0);
        pass1 = findViewById(R.id.pass_1);
        pass2 = findViewById(R.id.pass_2);
        pass3 = findViewById(R.id.pass_3);
        pass4 = findViewById(R.id.pass_4);
        remove = findViewById(R.id.numremove);


        mprefs = getSharedPreferences("userspass", MODE_PRIVATE);
        editor = mprefs.edit();
        userspass = mprefs.getString("userspass", null);


        if (userspass != null) {
            //비번 확인
            //비번 불러오기
            //비번 변경
        } else {
            //비번 생성
        }

        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strnum += "1";
                count++;
                count(count);

                if (count == 4) {
                    //비번 재확인 또는 비번 인증
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    strnum = "";
                    count = 0;
                    count(count);
                }
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strnum += "2";
                count++;
                count(count);
                if (count == 4) {
                    //비번 재확인 또는 비번 인증
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    strnum = "";
                    count = 0;
                    count(count);
                }
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strnum += "3";
                count++;
                count(count);
                if (count == 4) {
                    //비번 재확인 또는 비번 인증
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    strnum = "";
                    count = 0;
                    count(count);
                }
            }
        });
        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strnum += "4";
                count++;
                count(count);
                if (count == 4) {
                    //비번 재확인 또는 비번 인증
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    strnum = "";
                    count = 0;
                    count(count);
                }
            }
        });
        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strnum += "5";
                count++;
                count(count);
                if (count == 4) {
                    //비번 재확인 또는 비번 인증
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    strnum = "";
                    count = 0;
                    count(count);
                }
            }
        });
        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strnum += "6";
                count++;
                count(count);
                if (count == 4) {
                    //비번 재확인 또는 비번 인증
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    strnum = "";
                    count = 0;
                    count(count);
                }
            }
        });
        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strnum += "7";
                count++;
                count(count);
                if (count == 4) {
                    //비번 재확인 또는 비번 인증
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    strnum = "";
                    count = 0;
                    count(count);
                }
            }
        });
        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strnum += "8";
                count++;
                count(count);
                if (count == 4) {
                    //비번 재확인 또는 비번 인증
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    strnum = "";
                    count = 0;
                    count(count);
                }
            }
        });
        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strnum += "9";
                count++;
                count(count);
                if (count == 4) {
                    //비번 재확인 또는 비번 인증
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    strnum = "";
                    count = 0;
                    count(count);
                }
            }
        });
        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strnum += "0";
                count++;
                count(count);
                if (count == 4) {
                    //비번 재확인 또는 비번 인증
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    strnum = "";
                    count = 0;
                    count(count);

                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {

                } else {
                    strnum.substring(0, strnum.length());
                    count--;
                    count(count);
                }
            }
        });
    }

    private void count(int count) {
        switch (count) {
            case 0:
                pass1.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                pass2.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                pass3.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                pass4.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                break;
            case 1:
                pass1.setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));
                pass2.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                break;
            case 2:
                pass2.setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));
                pass3.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                break;
            case 3:
                pass3.setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));
                pass4.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                break;
            case 4:
                pass4.setBackgroundTintList(ColorStateList.valueOf(Color.DKGRAY));
                break;
        }
    }
}
