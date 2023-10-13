package com.example.listsongs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int[] ImgSong = {R.drawable.attention, R.drawable.astrounaunt_in_the_ocean, R.drawable.ben_trai, R.drawable.chet_trong_em, R.drawable.chuyen_rang, R.drawable.co_tho_may, R.drawable.em_khong_hieu, R.drawable.forget_me_now, R.drawable.giu_lay_lam_gi, R.drawable.let_me_down_slowly, R.drawable.neu_em_con_doi_anh, R.drawable.ngoi_nhin_em_khoc, R.drawable.see_you_again, R.drawable.thanh, R.drawable.the_thoi, R.drawable.the_way_i_am, R.drawable.tinh_yeu_cham_tre, R.drawable.tinh_yeu_xanh_la, R.drawable.tiny_love, R.drawable.toi_va_em};
    private int[] Url = {R.raw.attention, R.raw.astronaut_in_the_ocean_masked_wolf, R.raw.ben_trai, R.raw.chet_trong_em, R.raw.chuyen_rang, R.raw.co_tho_may, R.raw.em_khong_hieu, R.raw.forget_me_now, R.raw.giu_lay_lam_gi, R.raw.let_me_down_slowly, R.raw.neu_em_con_doi_anh, R.raw.ngoi_nhin_em_khoc, R.raw.see_you_again, R.raw.thanh, R.raw.the_thoi, R.raw.the_way_i_am, R.raw.tinh_yeu_cham_tre, R.raw.tinh_yeu_xanh_la, R.raw.tiny_love, R.raw.toi_va_em};
    private String[] nameSong = {"Attention", "Astronaunt In The Ocean", "Bên Trái", "Chết Trong Em", "Chuyện Rằng", "Cô Thợ May", "Em Không Hiểu", "Forget Me Now", "Giữ Lấy Làm Gì", "Let Me Down Slowly", "Nếu Em Còn Đợi Anh", "Ngồi Nhìn Em Khóc", "See You Again", "Thanh", "Thế Thôi", "The Way I Am", "Tình Yêu Chậm Trễ", "Tình Yêu Xanh Lá", "Tiny Love", "Tôi Và Em"};
    private String[] nameAuthor = {"Charlie Puth", "Masked Wolf", "Kiên", "Thịnh Suy", "Thịnh Suy", "Tọi", "Changg", "Trí Dũng", "Monstar","Alec Benjamin", "Buitruonglinh", "Sáo", "Wiz Khalifa", "Thịnh Suy", "Thịnh Suy", "Charlie Puth", "Monstar", "Thịnh Suy", "Thịnh Suy", "Pink Frog"};
    private RecyclerView rcvAlbum;
    private SongAdapter album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    public void initViews() {
        rcvAlbum = findViewById(R.id.rcvAlbum);
        album = new SongAdapter(this);

        LinearLayoutManager layout = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvAlbum.setLayoutManager(layout);

        album.setData(getListSong());
        rcvAlbum.setAdapter(album);
    }

    public ArrayList<Song> getListSong() {
        ArrayList<Song> listSong = new ArrayList<>();
        for (int i = 0; i < nameSong.length; i++) {
            listSong.add(new Song(nameSong[i], ImgSong[i], Url[i], nameAuthor[i]));
        }
        return listSong;
    }
}