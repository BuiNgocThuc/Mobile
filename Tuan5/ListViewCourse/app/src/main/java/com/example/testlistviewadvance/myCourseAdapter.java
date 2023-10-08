package com.example.testlistviewadvance;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class myCourseAdapter extends ArrayAdapter<Course> {

    Activity context;
    int idLayout;
    ArrayList<Course> myCourse;

    public myCourseAdapter(Activity context, int idLayout, ArrayList<Course> myCourse) {
        super(context, idLayout, myCourse);
        this.context = context;
        this.idLayout = idLayout;
        this.myCourse = myCourse;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myFlater = context.getLayoutInflater();
        convertView = myFlater.inflate(idLayout, null);
        Course courseName = myCourse.get(position);
        ImageView courseImg = convertView.findViewById(R.id.imgCourse);
        courseImg.setImageResource(courseName.getImage());
        TextView txtCourse = convertView.findViewById(R.id.tvCourse);
        txtCourse.setText(courseName.getName());

        return convertView;
    }
}
