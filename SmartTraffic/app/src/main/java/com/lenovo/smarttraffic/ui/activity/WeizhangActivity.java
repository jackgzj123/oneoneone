package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

public class WeizhangActivity extends AppCompatActivity {

    private TextView mCarTv;
    private LinearLayout mXinagLin;
    private ImageView mAddImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weizhang);

        initview();

        initevent();

    }

    private void initevent() {
        mCarTv.setText("鲁"+getIntent().getStringExtra("name"));

        mAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeizhangActivity.this,JiankongActivity.class));
            }
        });

        mXinagLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeizhangActivity.this,JiankongActivity.class));
            }
        });
    }

    private void initview() {
        mAddImg = findViewById(R.id.weizhang_add);
        mXinagLin = findViewById(R.id.weizhang_xiangqing);
        mCarTv = findViewById(R.id.weizhang_carname);

    }
}
