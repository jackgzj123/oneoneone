package com.lenovo.smarttraffic.ui.activity;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.CarMessageDao;

public class ChongActivity extends AppCompatActivity {

    private SimpleCursorAdapter adapter;
    private Cursor cursor;
    private ListView mList;
    private CarMessageDao carMessageDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chong);
        mList = findViewById(R.id.item3_list);
        carMessageDao = new CarMessageDao(this);
        cursor = carMessageDao.selectCarMessage(null);
        getAdapter();
        mList.setAdapter(adapter);
    }
    private void  getAdapter(){
        adapter = new SimpleCursorAdapter(
                this,R.layout.item4, cursor,
                new String[]{"_id","carNumber","chong","chongyue","peoplename","time"},//表头
                new int[]{R.id.xuhao_item,R.id.name_item,R.id.chong_item,R.id.yue_item,R.id.people_item,R.id.time_item},//表头结果所对应的输出对象
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }
}
