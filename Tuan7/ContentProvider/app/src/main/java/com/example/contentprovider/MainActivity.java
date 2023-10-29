package com.example.contentprovider;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button BtnLoad;
    ListView listview;
    ArrayList<Person> personlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put(MyProvider.name, ((EditText) findViewById(R.id.txtName))
                        .getText().toString());
                getContentResolver().insert(MyProvider.CONTENT_URI, values);
                Toast.makeText(getBaseContext(), "New record inserted", Toast.LENGTH_LONG)
                        .show();
            }
        });

        BtnLoad = (Button) findViewById(R.id.btnLoad);
        listview = (ListView)findViewById(R.id.PersonList);
        BtnLoad.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                int Id;
                String Name;
                Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.myapplication.MyProvider/users"), null, null, null, null);
                if (cursor.moveToFirst()) {
                    while (!cursor.isAfterLast()) {
                        Id = cursor.getInt(cursor.getColumnIndex("id"));
                        Name = cursor.getString(cursor.getColumnIndex("name"));
                        insertList(Id,Name);
                        cursor.moveToNext();
                    }
                }
                showlistview();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (MyProvider.db != null && MyProvider.db.isOpen()) {
            MyProvider.db.close();
        }
        boolean deleted = this.deleteDatabase(MyProvider.DATABASE_NAME);
        if (deleted) {
            Toast.makeText(this, "Database deleted", Toast.LENGTH_LONG).show();
        }
    }

    public void insertList(int id, String name){
        Person p = new Person(id,name);
        personlist.add(p);
    }
    public void showlistview(){
        Adapter myAdapter = new Adapter(MainActivity.this,personlist);
        listview.setAdapter(myAdapter);
    }

}