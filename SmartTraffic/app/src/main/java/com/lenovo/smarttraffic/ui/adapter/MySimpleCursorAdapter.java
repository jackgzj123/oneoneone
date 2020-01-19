package com.lenovo.smarttraffic.ui.adapter;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.activity.Item11Activity;
import com.lenovo.smarttraffic.ui.activity.Item13Activity;
import com.lenovo.smarttraffic.ui.activity.MyDialog3;

import java.util.ArrayList;
import java.util.List;

public class MySimpleCursorAdapter extends SimpleCursorAdapter implements MyDialog3.SettingListener{

    private Cursor c ;

    private String roadname;
    private Button button;
    private Button btn;
    private Context context;

    private List<Integer> list = new ArrayList<>();

    public MySimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to,Button btn) {
        super(context, layout, c, from, to);
        this.context = context;
        this.c=c;
        this.btn=btn;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        ImageView imageView = view.findViewById(R.id.select_item);
        button = view.findViewById(R.id.sz_item);

        list.clear();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDialog3 myDialog3 = new MyDialog3(view.getContext(),R.style.mydialog,Integer.toString(position+1),null);
                myDialog3.show();

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.mipmap.b);
                list.add(position+1);

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("list1", "onClick: "+list.size());
                MyDialog3 myDialog3 = new MyDialog3(view.getContext(),R.style.mydialog,null,list);

                myDialog3.show();

            }
        });
        return view;
    }

    @Override
    public void onSetting(int i) {

    }
}
