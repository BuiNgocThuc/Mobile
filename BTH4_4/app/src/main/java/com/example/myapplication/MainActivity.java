package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btnScan = findViewById(R.id.btnQRScan);
        btnScan.setOnClickListener(view -> {
            IntentIntegrator intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.setOrientationLocked(false);
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            intentIntegrator.initiateScan();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this,"Huỷ QRScan",Toast.LENGTH_SHORT).show();
            } else {
                String scannedData = result.getContents();
                if (scannedData.startsWith("http://") || scannedData.startsWith("https://")) {
                    openWebAndImage(scannedData);
                } else if (scannedData.startsWith("image:")) {
                    openWebAndImage(scannedData);
                }else if(scannedData.startsWith("MATMSG:")){
                    openEmailApp2(scannedData);
                }else if (scannedData.startsWith("MAILTO:")) {
                    openEmailApp1(scannedData);
                } else if (scannedData.startsWith("SMSTO:")) {
                    openSmsApp(scannedData);
                }
                else {
                    Toast.makeText(this,"Không thể scan mã QR",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void openWebAndImage(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    // open email without subject or context
    private void openEmailApp1(String input) {
        String email = null;
        int emailStart = input.indexOf("MAILTO:")+7;
        email=input.substring(emailStart);
        if (email != null) {
            String uri = "mailto:" + Uri.encode(email);
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(uri));
            startActivity(emailIntent);
        }
    }

    // open email have title and context
    private void openEmailApp2(String input){
        String email = null;
        String subject = null;
        String body = null;

        int emailStart = input.indexOf("TO:")+3;
        int emailEnd = input.indexOf(";",emailStart);
        if(emailStart>=0 && emailEnd>=0){
            email=input.substring(emailStart,emailEnd);
        }

        int subStart = input.indexOf("SUB:")+4;
        int subEnd = input.indexOf(";",subStart);
        if(subStart>=0 && subEnd>=0){
            subject=input.substring(subStart,subEnd);
        }

        int bodyStart = input.indexOf("BODY:")+5;
        int bodyEnd = input.indexOf(";",bodyStart);
        if(bodyStart>=0 && bodyEnd>=0){
            body=input.substring(bodyStart,bodyEnd);
        }

        if (email != null) {
            String uri = "mailto:" + Uri.encode(email);
            if (subject != null) {
                uri += "?subject=" + Uri.encode(subject);
            }
            if (body != null) {
                uri += "&body=" + Uri.encode(body);
            }
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(uri));
            startActivity(emailIntent);
        }
    }


    private void openSmsApp(String phoneNum) {
        //Toast.makeText(this,phoneNum,Toast.LENGTH_SHORT).show();
        String phoneNumFix = phoneNum.replaceAll("[^0-9]", "");
        Uri uri = Uri.parse("smsto:"+ phoneNumFix);
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO,uri);
        startActivity(smsIntent);
    }
}