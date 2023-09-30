package com.example.callcontact;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCall, btnView, btnClear;
    private static final int CONTACT_PICKER_REQUEST = 1;

    Uri contactInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        // Call contact
        btnCall = (Button)findViewById(R.id.btnCall);
        btnCall.setOnClickListener(this);

        //view contact
        btnView = (Button) findViewById(R.id.btnView);
        btnView.setOnClickListener(this);

        //button clear contact selected
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.btnCall) {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//            startActivityForResult(intent, CONTACT_PICKER_REQUEST);
            contactPickerLauncher.launch(intent);
        } else if(id == R.id.btnView) {
            Intent viewContactIntent = new Intent(Intent.ACTION_VIEW, contactInfo);
            startActivity(viewContactIntent);
        } else if(id == R.id.btnClear) {
            contactInfo = null;
            btnView.setEnabled(false);
            btnClear.setEnabled(false);
            Toast.makeText(MainActivity.this, "Clear contact successfully!!", Toast.LENGTH_SHORT).show();
        }
    }

    private ActivityResultLauncher<Intent> contactPickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    contactInfo = result.getData().getData();
                    Log.d("ContactPicker", "Selected contact URI: " + contactInfo.toString());
                    Toast.makeText(MainActivity.this, "Select contact successfully!!", Toast.LENGTH_SHORT).show();
                    if(contactInfo != null) {
                        btnView.setEnabled(true);
                        btnClear.setEnabled(true);
                    }
                }
            });

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK && requestCode == CONTACT_PICKER_REQUEST) { // Activity.RESULT_OK
//            contactInfo = data.getData();
//            Toast.makeText(MainActivity.this, "Select contact successfully!!", Toast.LENGTH_SHORT).show();
//            if(contactInfo != null) {
//                btnView.setEnabled(true);
//                btnClear.setEnabled(true);
//            }
//        }
//    }
}