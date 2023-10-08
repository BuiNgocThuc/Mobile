package com.example.testlistviewadvance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    int image[] = {R.drawable.java, R.drawable.python, R.drawable.frontend, R.drawable.fullstack, R.drawable.react, R.drawable.kotlin};
    String name[] = {"Khóa học Java", "Khóa học Python", "Khóa học Front-end", "Khóa học Full-Stack", "Khóa học React", "Khóa học Kotlin"};
    String URL[] = {"https://docs.oracle.com/en/java/", "https://www.python.org/doc/", "https://roadmap.sh/frontend", "https://roadmap.sh/full-stack", "https://react.dev/learn", "https://kotlinlang.org/docs/home.html"};
    ArrayList<Course> myCourses;
    myCourseAdapter myAdapter;
    ListView lvCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    void initViews() {
        lvCourse = findViewById(R.id.lvCourse);
        myCourses = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            myCourses.add(new Course(image[i], name[i]));
        }
        myAdapter = new myCourseAdapter(MainActivity.this, R.layout.layout_courses, myCourses);
        lvCourse.setAdapter(myAdapter);
        lvCourse.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(MainActivity.this, myCourses.get(i).getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL[i]));
        startActivity(intent);
    }
}