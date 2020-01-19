package com.lenovo.smarttraffic.ui.activity.item46activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.EnvironmentDao1;
import com.lenovo.smarttraffic.database.dao.SearchDao;

public class Item46Activity extends AppCompatActivity {

    private EditText mEd;
    private Button mBtn;
    private TextView mMap;
    private TextView mClear;
    private ListView mListView;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private SearchDao searchDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item46);
        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        getAdapter();

        initevent();
    }

    private void initevent() {
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchDao.deleteCarMessage();
                getAdapter();
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = mEd.getText().toString();
                searchDao.addCarMessage(s);

                Intent intent = new Intent(Item46Activity.this,Item46_1Activity.class);
                intent.putExtra("number",s);
                startActivity(intent);
            }
        });

        mMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo

            }
        });
    }

    private void initview() {

        searchDao = new SearchDao(this);
        mEd = findViewById(R.id.item46_ed);
        mBtn = findViewById(R.id.item46_searchbtn);
        mMap = findViewById(R.id.item46_map);
        mClear = findViewById(R.id.item46_clear);
        mListView = findViewById(R.id.item46_listview);


    }

    private void  getAdapter(){
        cursor = searchDao.selectCarMessage();
        adapter = new SimpleCursorAdapter(
                this,R.layout.item46_adapter_view, cursor,
                new String[]{"text"},//表头
                new int[]{R.id.item46_adapter_view_tv},//表头结果所对应的输出对象
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        mListView.setAdapter(adapter);
    }
}
