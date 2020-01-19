package com.lenovo.smarttraffic.ui.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.Collectiondao;

public class CollectionActivity extends AppCompatActivity {

    private ListView mListview;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private Collectiondao collectiondao;
    private ImageView mImg;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        collectiondao = new Collectiondao(this);
        linearLayout = findViewById(R.id.collection_tv);
        mListview = findViewById(R.id.collection_lidstview);
        mImg = findViewById(R.id.lgb2_back);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getAdapter();

    }

    private void  getAdapter(){
        cursor = collectiondao.selectCarMessage();
        if (!cursor.moveToFirst()){

        }else {
            linearLayout.setVisibility(View.GONE);
            adapter = new SimpleCursorAdapter(
                    this,R.layout.colleciton_view, cursor,
                    new String[]{"username","name","phonenumber","time"},//表头
                    new int[]{R.id.collection_view_username,R.id.collection_view_name,R.id.collection_view_phonenumber,R.id.collection_view_time},//表头结果所对应的输出对象
                    CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
            mListview.setAdapter(adapter);
        }

    }

}
