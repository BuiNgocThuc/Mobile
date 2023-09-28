package com.example.th3_bai2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText hoten, cmnd;
    Button gui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gui = findViewById(R.id.btGui);
        gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HienthiThongtin();
            }
        });
    }
    RadioGroup trinhdo, sothich;
    public void HienthiThongtin() {
        ThongTinCaNhan tt;
        hoten = findViewById(R.id.edtHoTen);
        cmnd = findViewById(R.id.edtCMND);
        String _hoten = hoten.getText().toString();
        String _cmnd = cmnd.getText().toString();
        String _sothich = "";
        String _trinhdo = "";

        trinhdo = findViewById(R.id.grTrinhDo);
        int selectedTrinhDoId = trinhdo.getCheckedRadioButtonId();

        if (selectedTrinhDoId == R.id.rdTrcap) {
            _trinhdo = "Trung cấp";
        } else if (selectedTrinhDoId == R.id.rdCaodang) {
            _trinhdo = "Cao đẳng";
        } else if (selectedTrinhDoId == R.id.rdDaihoc) {
            _trinhdo = "Đại học";
        }

        sothich = findViewById(R.id.grSothich);
        int selectedSoThichId = sothich.getCheckedRadioButtonId();

        if (selectedSoThichId == R.id.rdDocbao) {
            _sothich = "Đọc báo";
        } else if (selectedSoThichId == R.id.rdDocsach) {
            _sothich = "Đọc sách";
        } else if (selectedSoThichId == R.id.rdDoccode) {
            _sothich = "Đọc coding";
        }

        tt = new ThongTinCaNhan(_hoten, _cmnd, _sothich, _trinhdo);
        Toast t = Toast.makeText(this, tt.toString(), Toast.LENGTH_LONG);
        t.show();
    }

}