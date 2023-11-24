package com.example.modernartui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

public class TableLayoutFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.modern_table_layout, container, false);

        SeekBar colorSeekBar = ((MainActivity) requireActivity()).getColorSeekBar();

        View rectangle1 = (View) view.findViewById(R.id.Rectangle1);
        View rectangle2 = (View) view.findViewById(R.id.Rectangle2);
        View rectangle3 = (View) view.findViewById(R.id.Rectangle3);
        View rectangle4 = (View) view.findViewById(R.id.Rectangle4);
        View rectangle5 = (View) view.findViewById(R.id.Rectangle5);

        colorSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateRectanglesAlpha(progress, rectangle1, rectangle2, rectangle3, rectangle4, rectangle5);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Không cần xử lý ở đây
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Không cần xử lý ở đây
            }
        });

        return view;
    }
    private void updateRectanglesAlpha(int progress, View... rectangles) {
        String[][] colorTables = {
                {"#FFFF00", "#FFD700", "#FFC125", "#FFB90F"},
                {"#F0FFF0", "#ADFF2F", "#7FFF00", "#32CD32", "#00FF00"},
                {"#B0E0E6", "#ADD8E6", "#87CEEB", "#1E90FF", "#0000FF"},
                {"#FFA07A", "#FF6347", "#FF4500", "#FF0000", "#DC143C"},
                {"#0D301D", "#17472F", "#1E5E42", "#277753", "#2F8A66"}
        };

        if(progress > 10) {
            int colorIndex = (int) (progress / 100.0 * (colorTables[0].length - 1));

            for (int i = 0; i < rectangles.length; i++) {
                int color = Color.parseColor(colorTables[i][colorIndex]);

                int newColor = Color.rgb(Color.red(color), Color.green(color), Color.blue(color));

                rectangles[i].setBackgroundColor(newColor);
            }
        } else {
            String[] colorDeflt = {
                    "#FFFF00",
                    "#0D301D",
                    "#00FF00",
                    "#0000FF",
                    "#FF0000"
            };
            for (int i = 0; i < rectangles.length; i++) {
                int color = Color.parseColor(colorDeflt[i]);

                rectangles[i].setBackgroundColor(color);
            }
        }
    }
}