package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.JilvDao;

public class JiluActivity extends AppCompatActivity {

    private ImageView mBackImg;
    private ListView mListview;
    private JilvDao jilvDao;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jilu);

        initview();

        initevent();
    }

    private void initevent() {

        jilvDao = new JilvDao(this);

        cursor = jilvDao.selectMessage();

        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JiluActivity.this,Item1Activity.class));
            }
        });
        getAdapter();

    }

    private void initview() {
        mBackImg = findViewById(R.id.jilu_back);
        mListview = findViewById(R.id.jilu_listview);
    }
    private void  getAdapter(){
        adapter = new SimpleCursorAdapter(
                this,R.layout.jilv_view, cursor,
                new String[]{"title","time"},//表头
                new int[]{R.id.jilv_title,R.id.jilv_submit},//表头结果所对应的输出对象
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListview.setAdapter(adapter);
    }
}
