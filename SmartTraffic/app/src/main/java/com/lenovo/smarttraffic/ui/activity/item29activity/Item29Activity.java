package com.lenovo.smarttraffic.ui.activity.item29activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

public class Item29Activity extends AppCompatActivity {

    private TextView mPayTv;
    private TextView mMoneyTv;
    private TextView mJiluTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item29);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mPayTv = findViewById(R.id.item29_ETCpay);
        mMoneyTv = findViewById(R.id.item29_ETCmoney);
        mJiluTv = findViewById(R.id.item29_ETCjilu);

        mPayTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item29Activity.this,ETCpayActivity.class));
            }
        });

        mMoneyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item29Activity.this,ETCmoneyActivity.class));
            }
        });

        mJiluTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item29Activity.this,ETCjiluActivity.class));
            }
        });

    }
}
