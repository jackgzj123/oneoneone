package com.lenovo.smarttraffic.ui.activity.item29activity;

import android.content.Intent;
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
import com.lenovo.smarttraffic.database.dao.ETCdao;

public class ETCjiluActivity extends AppCompatActivity {

    private ListView mListview;
    private TextView mTotal;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private ETCdao etCdao;
    private int i = 0;
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etcjilu);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        cursor = etCdao.selectCarMessage();

        gettotal();

        getAdapter();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ETCjiluActivity.this,Item29Activity.class));
            }
        });

    }

    private void gettotal() {
        while (cursor.moveToNext()){
            String paymoney = cursor.getString(cursor.getColumnIndex("paymoney"));
            Log.e("paymoney", "gettotal: "+paymoney);
            i+= Integer.valueOf(paymoney);
        }
        mTotal.setText(""+i);
    }

    private void initview() {
        etCdao = new ETCdao(this);
        mTotal = findViewById(R.id.item29jilu_total);
        mListview = findViewById(R.id.item29jilu_listview);
        mBack = findViewById(R.id.item29jilu_back);
    }

    private void  getAdapter(){
        adapter = new SimpleCursorAdapter(
                this,R.layout.item8, cursor,
                new String[]{"carId","carname","paymoney","time"},//表头
                new int[]{R.id.item29_item8_carid,R.id.item29_item8_carname,R.id.item29_item8_paymoney,R.id.item29_item8_time},//表头结果所对应的输出对象
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListview.setAdapter(adapter);
    }
}
