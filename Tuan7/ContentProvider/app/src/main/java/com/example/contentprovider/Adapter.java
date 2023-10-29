package com.example.contentprovider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    Context mContext;
    ArrayList<Person> personlist = new ArrayList<>();
    public Adapter(Context context, ArrayList<Person> person){
        this.mContext = context;
        this.personlist = person;
    }
    @Override
    public int getCount() {
        return personlist.size();
    }

    @Override
    public Object getItem(int i) {
        return personlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.listview_item,viewGroup,false);
        }
        Person tempPerson = (Person) getItem(i);
        TextView txtid = (TextView) view.findViewById(R.id.txtId);
        TextView txtname = (TextView) view.findViewById(R.id.txtName);
        txtid.setText(String.valueOf(tempPerson.getId()));
        txtname.setText(tempPerson.getName());
        return view;
    }
}
