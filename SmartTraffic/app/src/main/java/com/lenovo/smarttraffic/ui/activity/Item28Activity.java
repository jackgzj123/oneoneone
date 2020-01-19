package com.lenovo.smarttraffic.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.adapter.Item28ListAdapter;

public class Item28Activity extends AppCompatActivity {

    private ListView mListview;
    private TextView mGonggaoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item28);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mListview = findViewById(R.id.item28_listview);
        mGonggaoTv = findViewById(R.id.item28_gonggao);

        mGonggaoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1、实例化一个Builder
                AlertDialog.Builder builder =new AlertDialog.Builder(Item28Activity.this);
                //2、设置对话框样式
                builder.setTitle("公告详情");//设置标题
                builder.setMessage("沈海高速胶州、莱西服务器封闭施工通知");//设置文本

                builder.setNegativeButton("确定",null);
                //4、展示
                builder. show();
            }
        });
        mListview.setAdapter(new Item28ListAdapter(this));
    }
}
