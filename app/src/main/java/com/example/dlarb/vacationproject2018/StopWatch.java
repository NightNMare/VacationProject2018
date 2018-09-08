package com.example.dlarb.vacationproject2018;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StopWatch extends AppCompatActivity {
    TextView mEllapse;
    TextView mSplit;
    Button Start;
    Button mBtnSplit;

    String sSplit;

    final static int IDLE = 0;
    final static int RUNNING = 1;
    final static int PAUSE = 2;
    int mStatus = IDLE;
    long mBaseTime;
    long mPauseTime;
    int mSplitCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        mEllapse = findViewById(R.id.ellapse);
        mSplit = findViewById(R.id.split);
        Start = findViewById(R.id.btnstart);
        mBtnSplit = findViewById(R.id.btnsplit);
    }

    @SuppressLint("HandlerLeak")
    Handler mTimer = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mEllapse.setText(getEllapse());
            mTimer.sendEmptyMessage(0);
        }
    };

    @Override
    protected void onDestroy() {
        mTimer.removeMessages(0);
        super.onDestroy();
    }

    public void mOnClick(View v) {
        switch (v.getId()) {

            case R.id.btnstart:
                switch (mStatus) {
                    case IDLE:
                        mBaseTime = SystemClock.elapsedRealtime();
                        mTimer.sendEmptyMessage(0);
                        Start.setText("중지");
                        mBtnSplit.setEnabled(true);
                        mStatus = RUNNING;
                        break;

                    case RUNNING:
                        mTimer.removeMessages(0);
                        mPauseTime = SystemClock.elapsedRealtime();
                        Start.setText("시작");
                        mBtnSplit.setText("초기화");
                        mStatus = PAUSE;
                        break;

                    case PAUSE:
                        long now = SystemClock.elapsedRealtime();
                        mBaseTime += (now - mPauseTime);
                        mTimer.sendEmptyMessage(0);
                        Start.setText("중지");
                        mBtnSplit.setText("기록");
                        mStatus = RUNNING;
                        break;
                }
                break;

            case R.id.btnsplit:
                switch (mStatus) {
                    case RUNNING:
                        sSplit = mSplit.getText().toString();
                        sSplit += String.format("%d => %s\n", mSplitCount+1, getEllapse());
                        mSplit.setText(sSplit);
                        mSplitCount++;
                        break;
                    case PAUSE:
                        mTimer.removeMessages(0);
                        Start.setText("시작");
                        mBtnSplit.setText("기록");
                        mEllapse.setText("00:00:00");
                        mStatus = IDLE;
                        sSplit = "";
                        mSplit.setText("");
                        mBtnSplit.setEnabled(false);
                        break;
                }
                break;
        }
    }

    String getEllapse() {
        long now = SystemClock.elapsedRealtime();
        long ell = now - mBaseTime;
        String sEll = String.format("%02d:%02d:%02d", ell / 1000 / 60, (ell / 1000) % 60, (ell % 1000) / 10);
        return sEll;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("StopWatch", sSplit);
        setResult(333, intent);
        finish();
    }
}
