package com.example.th3_bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText hoten, chieucao, cannang, chisobmi, chandoan;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hoten = (EditText)findViewById(R.id.editTextTen);
        chieucao = (EditText)findViewById(R.id.editTextChieucao);
        cannang = (EditText)findViewById(R.id.editTextCannang);
        chisobmi = (EditText)findViewById(R.id.editTextBMI);
        chandoan = (EditText)findViewById(R.id.editTextChandoan);
        bt = (Button)findViewById(R.id.btnTinh);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinh();
            }
        });
    }

    public void tinh()
    {
        double h = Double.parseDouble(chieucao.getText().toString());
        double w = Double.parseDouble(cannang.getText().toString());
        Bmiclass obj = new Bmiclass(h,w);
        double bmi = obj.getBMI();
        String kq = obj.getChanDoan();
        chisobmi.setText(String.valueOf(bmi));
        chandoan.setText(kq);
    }
}