package com.example.listsongs;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private Context context;
    private ArrayList<Song> album;

    private boolean played = false;


    public SongAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Song> album) {
        this.album = album;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song_layout, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = album.get(position);
        if (song == null) {
            return;
        }

        holder.imgSong.setImageResource(song.getSrcImg());
        holder.tvNameAuthor.setText(song.getAuthor());
        holder.tvNameSong.setText(song.getNameSong());
        holder.setUrl(song.getUrl());
    }

    @Override
    public int getItemCount() {
        if (album != null) {
            return album.size();
        }
        return 0;
    }

    public class SongViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgSong, btnPlay, btnStop, btnPause;
        private TextView tvNameSong, tvNameAuthor;
        private int url;

        private Animation ani;

        private MediaPlayer player;
        public int getUrl() {
            return url;
        }

        public void setUrl(int url) {
            this.url = url;
        }


        private List<View> pauseButtons = new ArrayList<>();

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSong = itemView.findViewById(R.id.img_song);
            tvNameSong = itemView.findViewById(R.id.name_song);
            tvNameAuthor = itemView.findViewById(R.id.name_author);
            btnPlay = (ImageView) itemView.findViewById(R.id.btnPlay);
            btnStop = (ImageView) itemView.findViewById(R.id.btnStop);
            btnPause = (ImageView) itemView.findViewById(R.id.btnPause);
            btnPlay.setOnClickListener(this);
            btnStop.setOnClickListener(this);
            btnPause.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.btnPlay) {
                Play();
                Log.i("MainActivity", "Play: " + played);
            } else if (id == R.id.btnPause) {
                Pause();
                Log.i("MainActivity", "Pause: " + played);
            } else if (id == R.id.btnStop) {
                Stop();
                Log.i("MainActivity", "Stop: " + played);
            }
        }

        public void Play() {
            if (played == true) {
                Stop();
            }
            player = MediaPlayer.create(getContext(), getUrl());
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Stop();
                }
            });
            player.start();
            rotateAnimation();
            played = true;
            btnPause.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.INVISIBLE);

        }

        public void Pause() {
            if (player != null) {
                player.pause();
                played = false;
                btnPause.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.VISIBLE);
                imgSong.clearAnimation();
            }
        }

        public void Stop() {
            if (player != null) {
                player.release();
                player = null;
                played = false;
                btnPause.setVisibility(View.INVISIBLE);
                btnPlay.setVisibility(View.VISIBLE);
                imgSong.clearAnimation();
            }
        }

        public void rotateAnimation() {
            ani = AnimationUtils.loadAnimation(context, R.anim.rotate);
            imgSong.setAnimation(ani);
        }
    }
}
