package com.example.demofilemanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.nio.file.Files;
import java.util.Stack;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.FileViewHolder> {

    Context context;
    File[] filesAndFolders;
    private ItemClickListener itemClickListener;

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.file_item, parent, false);
        return new FileViewHolder(view);
    }

    public FileAdapter(Context context) {
        this.context = context;
    }

    public void setData(File[] filesAndFolders) {
        this.filesAndFolders = filesAndFolders;
        notifyDataSetChanged();
    }

    public File[] getData() {
        return this.filesAndFolders;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, @SuppressLint("RecyclerView") int position) {
        File selectedFile = filesAndFolders[position];
        holder.tvTitleFile.setText(selectedFile.getName());

        if (selectedFile.isDirectory()) {
            holder.imgFile.setImageResource(R.drawable.folder);
        } else {
            holder.imgFile.setImageResource(R.drawable.file);
            if(selectedFile.getName().toLowerCase().endsWith(".mp3")) {
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                mmr.setDataSource(selectedFile.getPath());
                byte[] art = mmr.getEmbeddedPicture();
                if(art != null) {
                    Glide.with(context)
                            .asBitmap()
                            .load(art)
                            .into(holder.imgFile);
                }else {
                    Glide.with(context)
                            .asBitmap()
                            .load(R.drawable.template_img)
                            .into(holder.imgFile);
                }
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedFile.isDirectory()) {
                    String path = selectedFile.getAbsolutePath();
                    File root = new File(path);
                    File[] newFilesAndFolders = root.listFiles();
                    setData(newFilesAndFolders);
                    if (itemClickListener != null) {
                        itemClickListener.onItemClickGetPath(path);
                    }

                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return filesAndFolders.length;
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitleFile;
        ImageView imgFile;

        RelativeLayout layout;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitleFile = itemView.findViewById(R.id.titleFile);
            imgFile = itemView.findViewById(R.id.imgFile);
            layout = itemView.findViewById(R.id.layout_item);
        }
    }
}
