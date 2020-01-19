package com.lenovo.smarttraffic.ui.activity.item53activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.activity.item30activity.Item30MsgActivity;

public class Item53Activity extends AppCompatActivity {

    private ImageView mImg1;
    private ImageView mImg2;
    private ImageView mImg3;
    private ImageView mImg4;
    private TextView mPay1;
    private TextView mPay2;
    private TextView mPay3;
    private TextView mPay4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item53);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        intiview();

        initevent();
    }

    private void initevent() {
        mImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item53Activity.this, Item30MsgActivity.class));
            }
        });
        mImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item53Activity.this, Item30MsgActivity.class));
            }
        });
        mImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item53Activity.this, Item30MsgActivity.class));
            }
        });
        mImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item53Activity.this, Item30MsgActivity.class));
            }
        });

        mPay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item53Activity.this, Item53_1Activity.class));
            }
        });
        mPay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item53Activity.this, Item53_1Activity.class));
            }
        });
        mPay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item53Activity.this, Item53_1Activity.class));
            }
        });
        mPay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item53Activity.this, Item53_1Activity.class));
            }
        });

    }

    private void intiview() {
        mImg1 = findViewById(R.id.item53_img1);
        mImg2 = findViewById(R.id.item53_img2);
        mImg3 = findViewById(R.id.item53_img3);
        mImg4 = findViewById(R.id.item53_img4);
        mPay1 = findViewById(R.id.item53_pay1);
        mPay2 = findViewById(R.id.item53_pay2);
        mPay3 = findViewById(R.id.item53_pay3);
        mPay4 = findViewById(R.id.item53_pay4);

    }
}
