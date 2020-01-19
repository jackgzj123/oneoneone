package com.lenovo.smarttraffic.ui.activity.item46activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.adapter.List1Adapter;

import java.util.ArrayList;
import java.util.List;

public class Item46_1Activity extends AppCompatActivity {

    private String s;
    private TextView mTv;
    private ListView mListview;
    private String[] strings = {"巨龙大道","宋家岗","航空总部","天河机场","建设大道双墩","建设大道新合村","解放大道水厂","解放大道太平洋","奥林匹克公园","奥林匹克体育场","后襄和南路","后襄和中路","后襄和北路"};
    private List1Adapter list1Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item46_1);
        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        s = getIntent().getStringExtra("number");

        mTv = findViewById(R.id.item46_1_tv);
        mListview = findViewById(R.id.item46_1_listview);
        mTv.setText(s+"路");

        list1Adapter = new List1Adapter(this,strings);
        mListview.setAdapter(list1Adapter);

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list1Adapter.setSelectedPosition(i);
                list1Adapter.notifyDataSetInvalidated();
            }
        });







    }
}
