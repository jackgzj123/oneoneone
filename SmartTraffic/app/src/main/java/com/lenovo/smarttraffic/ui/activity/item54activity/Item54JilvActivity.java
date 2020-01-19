package com.lenovo.smarttraffic.ui.activity.item54activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.ETC1dao;

public class Item54JilvActivity extends AppCompatActivity {

    private ETC1dao etc1dao;
    private ListView mListview;
    private TextView mToatl;
    private ImageView mBack;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item54_jilv);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etc1dao = new ETC1dao(this);
        mBack = findViewById(R.id.item29jilu_back);
        mListview = findViewById(R.id.item54_jilv_listview);
        mToatl = findViewById(R.id.item54_jilv_totalmoney);
        cursor = etc1dao.selectCarMessage();

        initevent();


        gettotal();

        getAdapter();



    }

    private void initevent() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void gettotal() {
        while (cursor.moveToNext()){
            String paymoney = cursor.getString(cursor.getColumnIndex("paymoney"));
            Log.e("paymoney", "gettotal: "+paymoney);
            i+= Integer.valueOf(paymoney);
        }
        mToatl.setText(""+i);
    }

    private void  getAdapter(){
        adapter = new SimpleCursorAdapter(
                this,R.layout.item9, cursor,
                new String[]{"timedate","timeday","carname","paymoney","time"},//表头
                new int[]{R.id.date,R.id.day,R.id.carname,R.id.money,R.id.time},//表头结果所对应的输出对象
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListview.setAdapter(adapter);
    }


}
