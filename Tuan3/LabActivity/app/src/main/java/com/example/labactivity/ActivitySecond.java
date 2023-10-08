package com.example.labactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivitySecond extends AppCompatActivity implements View.OnClickListener {

    private TextView tvCreate2, tvStart2, tvResume2, tvRestart2;
    private Button btnFinish;
    private int cntCreate2 = 0, cntStart2 = 0, cntResume2 = 0, cntRestart2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.e("MainActivity", "onCreate");
        initViews();
        ++cntCreate2;
        tvCreate2.setText("onCreate() calls: " + cntCreate2);
    }
    public void initViews() {
        tvCreate2 = findViewById(R.id.tvCreate);
        tvStart2 = findViewById(R.id.tvStart);
        tvResume2 = findViewById(R.id.tvResume);
        tvRestart2 = findViewById(R.id.tvRestart);
        btnFinish = findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "onStart");
        ++cntStart2;
        tvStart2.setText("onStart() calls: " + cntStart2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity", "onResume");
        ++cntResume2;
        tvResume2.setText("onResume() calls: " + cntResume2);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MainActivity", "onRestart");
        ++cntRestart2;
        tvRestart2.setText("onRestart() calls: " + cntRestart2);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnFinish) {
            finish();
        }
    }
}