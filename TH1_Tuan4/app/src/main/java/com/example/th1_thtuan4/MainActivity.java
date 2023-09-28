package com.example.th1_thtuan4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button callContactsButton;
    private Button viewContactButton;
    private Uri selectedContactUri;

    private ActivityResultLauncher<Intent> contactPickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    selectedContactUri = result.getData().getData();
                    Log.d("ContactPicker", "Selected contact URI: " + selectedContactUri.toString());
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callContactsButton = findViewById(R.id.callContactsButton);
        viewContactButton = findViewById(R.id.viewContactButton);

        callContactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactPicker();
            }
        });

        viewContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedContactUri != null) {
                    openContact(selectedContactUri);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (selectedContactUri != null) {
            viewContactButton.setEnabled(true);
        }
    }

    private void openContactPicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        contactPickerLauncher.launch(intent);
    }
    private void openContact(Uri contactUri) {
        Intent viewContactIntent = new Intent(Intent.ACTION_VIEW, selectedContactUri);
        startActivity(viewContactIntent);
    }
}