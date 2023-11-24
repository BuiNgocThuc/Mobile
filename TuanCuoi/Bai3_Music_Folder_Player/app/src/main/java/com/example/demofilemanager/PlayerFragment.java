package com.example.demofilemanager;

import static com.example.demofilemanager.MainActivity.PATH_TO_FRAG;
import static com.example.demofilemanager.MainActivity.SHOW_MINI_PLAYER;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;

public class PlayerFragment extends Fragment {

    ImageView img_bottom, btnNext_bottom, btnPrev_bottom;
    TextView tvName_bottom;
    FloatingActionButton btnPlay_Pause_bottom;
    View view;
    static MediaPlayer mediaPlayer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_player, container, false);
        img_bottom = view.findViewById(R.id.img_bottom);
        btnNext_bottom = view.findViewById(R.id.skip_next_bottom);
        btnPrev_bottom = view.findViewById(R.id.skip_previous_bottom);
        btnPlay_Pause_bottom = view.findViewById(R.id.play_pause_bottom);
        tvName_bottom = view.findViewById(R.id.tvName_bottom);

        btnPlay_Pause_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play_pause();
            }
        });

        btnPrev_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevSong();
            }
        });

        btnNext_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextSong();
            }
        });
        return view;
    }

    private void nextSong() {
    }

    private void prevSong() {

    }

    private void play_pause() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (SHOW_MINI_PLAYER) {
            if (PATH_TO_FRAG != null) {
                byte[] art = getAlbumArt(PATH_TO_FRAG);
                if (art != null) {
                    Glide.with(getContext()).load(art).into(img_bottom);
                } else {
                    Glide.with(getContext())
                            .load(R.drawable.template_img)
                            .into(img_bottom);
                }
                File temp = new File(PATH_TO_FRAG);
                int lastDot = temp.getName().lastIndexOf(".");
                tvName_bottom.setText(temp.getName());
            }
        }
    }

    private byte[] getAlbumArt(String uri) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(uri);
        byte[] art = mmr.getEmbeddedPicture();
        try {
            mmr.release();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return art;
    }
}