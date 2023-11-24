package com.example.demofilemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    Button btnExit, btnSelect, btnBack;
    RecyclerView rcFiles;

    TextView tvNoFiles, tvPath;
    public static final int REQUEST_CODE = 1;

    static Stack<String> pathStack = new Stack<>();

    public static final String MUSIC_LAST_PLAYED = "LAST_PLAYED";
    public static final String MUSIC_FILE = "STORED_MUSIC";
    public static boolean SHOW_MINI_PLAYER = false;
    public static String PATH_TO_FRAG = null;
    FileAdapter files;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        permission();
        solveEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(MUSIC_LAST_PLAYED, MODE_PRIVATE);

        String value = preferences.getString(MUSIC_FILE, null);
        if(value != null) {
            SHOW_MINI_PLAYER = true;
            PATH_TO_FRAG = value;
        } else {
            SHOW_MINI_PLAYER = false;
            PATH_TO_FRAG = null;
        }
    }

    private void permission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        } else {
            queryFilesAndFolders();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                queryFilesAndFolders();
            } else {
                finish();
            }
        }

    }

    private void solveEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNoFiles.setVisibility(View.INVISIBLE);
                if (!pathStack.isEmpty()) {
                    pathStack.pop();
                    String previousPath = pathStack.isEmpty() ? null : pathStack.peek();
                    if (previousPath != null) {
                        File previousFolder = new File(previousPath);
                        File[] filesAndFolders = previousFolder.listFiles();
                        files.setData(filesAndFolders);
                        changePath(previousPath);
                    } else {
                        queryFilesAndFolders();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Đang Ở Thư Mục Gốc", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File[] tempFiles = files.getData();
                ArrayList<File> musicFiles = getMusicFiles(tempFiles);
                if(musicFiles.size() == 0) {
                    Toast.makeText(MainActivity.this, "Không có file nhạc", Toast.LENGTH_SHORT).show();
                } else {
                    String[] songs = new String[musicFiles.size()];
                    for(int i = 0; i < musicFiles.size(); ++i){
                        songs[i] = musicFiles.get(i).getName();
                    }
                    startActivity(new Intent(MainActivity.this, PlayerActivity.class)
                            .putExtra("songsList", musicFiles));
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private ArrayList<File> getMusicFiles(File[] files){
        ArrayList<File> musicFiles = new ArrayList<>();
        for(File currentFile : files) {
            if(currentFile.getName().toLowerCase().endsWith(".mp3")) {
                musicFiles.add(currentFile);
            }
        }
        return musicFiles;
    }

    private void queryFilesAndFolders() {
        String path = Environment.getExternalStorageDirectory().getPath();
        File root = new File(path);
        File[] filesAndFolders = root.listFiles();
        metaData(filesAndFolders);
        changePath(path);
    }

    public void changePath(String path) {
        tvPath.setText(path);
    }

    private void metaData(File[] filesAndFolders) {
        LinearLayoutManager layout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcFiles.setLayoutManager(layout);
        files = new FileAdapter(getApplicationContext());
        files.setItemClickListener(this); // Đặt ItemClickListener vào Adapter
        files.setData(filesAndFolders);
        rcFiles.setAdapter(files);
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnSelect = findViewById(R.id.btnSelect);
        btnExit = findViewById(R.id.btnExit);

        rcFiles = findViewById(R.id.rcFiles);

        tvPath = findViewById(R.id.tvPath);
        tvNoFiles = findViewById(R.id.tvNoFile);
    }

    @Override
    public void onItemClickGetPath(String path) {
        changePath(path);
        pathStack.push(path);
        if(files.getData().length == 0) {
            tvNoFiles.setVisibility(View.VISIBLE);
        } else {
            tvNoFiles.setVisibility(View.INVISIBLE);
        }
    }
}