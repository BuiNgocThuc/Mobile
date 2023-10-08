package com.example.labactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvCreate, tvStart, tvResume, tvRestart;
    private Button btnSAT;
    private int cntCreate = 0, cntStart = 0, cntResume = 0, cntRestart = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.e("MainActivity", "onCreate");
        initViews();
        ++cntCreate;
        tvCreate.setText("onCreate() calls: " + cntCreate);
    }
    public void initViews() {
        tvCreate = findViewById(R.id.tvCreate);
        tvStart = findViewById(R.id.tvStart);
        tvResume = findViewById(R.id.tvResume);
        tvRestart = findViewById(R.id.tvRestart);
        btnSAT = findViewById(R.id.btnSAT);

        btnSAT.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "onStart");
        ++cntStart;
        tvStart.setText("onStart() calls: " + cntStart);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("MainActivity", "onResume");
        ++cntResume;
        tvResume.setText("onResume() calls: " + cntResume);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("MainActivity", "onRestart");
        ++cntRestart;
        tvRestart.setText("onRestart() calls: " + cntRestart);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnSAT) {
            Intent intent = new Intent(MainActivity.this, ActivitySecond.class);
            startActivity(intent);
        }
    }
}