package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityOne extends AppCompatActivity {

    private int onCreateCounter = 0;
    private int onRestartCounter = 0;
    private int onStartCounter = 0;
    private int onResumeCounter = 0;

    private TextView onCreateTextView;
    private TextView onRestartTextView;
    private TextView onStartTextView;
    private TextView onResumeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityone);

        onCreateTextView = findViewById(R.id.onCreateTextView);
        onRestartTextView = findViewById(R.id.onRestartTextView);
        onStartTextView = findViewById(R.id.onStartTextView);
        onResumeTextView = findViewById(R.id.onResumeTextView);

        onCreateCounter++;
        displayCounts();

        Button launchActivityTwoButton = findViewById(R.id.launchActivityTwoButton);
        launchActivityTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onRestartCounter++;
        displayCounts();
    }

    @Override
    protected void onStart() {
        super.onStart();
        onStartCounter++;
        displayCounts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onResumeCounter++;
        displayCounts();
    }

    private void displayCounts() {
        onCreateTextView.setText("onCreate() calls : " + onCreateCounter);
        onRestartTextView.setText("onRestart() calls : " + onRestartCounter);
        onStartTextView.setText("onStart() calls : " + onStartCounter);
        onResumeTextView.setText("onResume() calls : " + onResumeCounter);
    }
}