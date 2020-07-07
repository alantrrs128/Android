package com.example.myfirstapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;

// Los adaptadores que existen son:
//BaseAdapter
//ArrayAdapter
//SimpleAdapter

public class AdapterStudent extends ArrayAdapter {

    private Activity context;
    ArrayList<Student> students;
    private int resource;

    public AdapterStudent(@NonNull Activity context, int resource, ArrayList<Student> students) {
        super(context, resource, students);
        this.context = context;
        this.resource = resource;
        this.students = students;

    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        if(context != null) {
            if(context != null) {
                view = context.getLayoutInflater().inflate(resource, null);
                holder.txtView = (TextView)view.findViewById(R.id.textItem);
                view.setTag(holder);
            }
        } else {
            holder = (ViewHolder)view.getTag();
        }
        Student student = students.get(i);
        holder.txtView.setText(student.getName());
        if(!student.isPass())
            holder.txtView.setBackgroundColor(Color.RED);

        return view;
    }

    static class ViewHolder {
        private TextView txtView;
    }
}
