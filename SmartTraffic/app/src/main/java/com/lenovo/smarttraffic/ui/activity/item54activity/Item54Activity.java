package com.lenovo.smarttraffic.ui.activity.item54activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

public class Item54Activity extends AppCompatActivity {

    private TextView mPay;
    private TextView mMoney;
    private TextView mJilv;
    private ImageView mLaba;
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item54);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        mPay = findViewById(R.id.item54_ETCpay);
        mMoney = findViewById(R.id.item54_ETCmoney);
        mJilv = findViewById(R.id.item54_ETCjilu);
        mLaba = findViewById(R.id.item54_laba);
        mBack = findViewById(R.id.lgb2_back);

        AnimationDrawable animaition = (AnimationDrawable)mLaba.getBackground();
        animaition.setOneShot(false);   //设置是否只播放一次，和上面xml配置效果一致
        animaition.start();

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finish();
            }
        });

        mPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item54Activity.this,Item54PayActivity.class));
            }
        });

        mMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item54Activity.this,Item54YueActivity.class));
            }
        });

        mJilv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item54Activity.this,Item54JilvActivity.class));
            }
        });

    }
}
