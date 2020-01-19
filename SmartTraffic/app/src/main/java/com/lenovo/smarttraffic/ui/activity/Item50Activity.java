package com.lenovo.smarttraffic.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.lenovo.smarttraffic.R;

public class Item50Activity extends AppCompatActivity {

    private LinearLayout mLu1;
    private LinearLayout mAn1;
    private LinearLayout mJiao1;
    private LinearLayout mEr1;
    private LinearLayout mGai1;
    private LinearLayout mWei1;
    private LinearLayout mTui1;
    private LinearLayout mQi1;
    private LinearLayout mKe1;
    private LinearLayout mLu2;
    private LinearLayout mAn2;
    private LinearLayout mJiao2;
    private LinearLayout mEr2;
    private LinearLayout mGai2;
    private LinearLayout mWei2;
    private LinearLayout mTui2;
    private LinearLayout mQi2;
    private LinearLayout mKe2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item50);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();
    }

    private void initevent() {

        mAn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAn1.setVisibility(View.GONE);
                mAn2.setVisibility(View.VISIBLE);
            }
        });
        mLu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLu1.setVisibility(View.GONE);
                mLu2.setVisibility(View.VISIBLE);
            }
        });
        mJiao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mJiao1.setVisibility(View.GONE);
                mJiao2.setVisibility(View.VISIBLE);
            }
        });
        mEr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEr1.setVisibility(View.GONE);
                mEr2.setVisibility(View.VISIBLE);
            }
        });
        mGai1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGai1.setVisibility(View.GONE);
                mGai2.setVisibility(View.VISIBLE);
            }
        });
        mWei1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWei1.setVisibility(View.GONE);
                mWei2.setVisibility(View.VISIBLE);
            }
        });
        mTui1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTui1.setVisibility(View.GONE);
                mTui2.setVisibility(View.VISIBLE);
            }
        });
        mQi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQi1.setVisibility(View.GONE);
                mQi2.setVisibility(View.VISIBLE);
            }
        });
        mKe1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKe1.setVisibility(View.GONE);
                mKe2.setVisibility(View.VISIBLE);
            }
        });



        mAn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAn2.setVisibility(View.GONE);
                mAn1.setVisibility(View.VISIBLE);
            }
        });
        mLu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLu2.setVisibility(View.GONE);
                mLu1.setVisibility(View.VISIBLE);
            }
        });
        mJiao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mJiao2.setVisibility(View.GONE);
                mJiao1.setVisibility(View.VISIBLE);
            }
        });
        mEr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEr2.setVisibility(View.GONE);
                mEr1.setVisibility(View.VISIBLE);
            }
        });
        mGai2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGai2.setVisibility(View.GONE);
                mGai1.setVisibility(View.VISIBLE);
            }
        });
        mWei2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWei2.setVisibility(View.GONE);
                mWei1.setVisibility(View.VISIBLE);
            }
        });
        mTui2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTui2.setVisibility(View.GONE);
                mTui1.setVisibility(View.VISIBLE);
            }
        });
        mQi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQi2.setVisibility(View.GONE);
                mQi1.setVisibility(View.VISIBLE);
            }
        });
        mKe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mKe2.setVisibility(View.GONE);
                mKe1.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initview() {
        mLu1 = findViewById(R.id.item50_lu1);
        mAn1 = findViewById(R.id.item50_an1);
        mJiao1 = findViewById(R.id.item50_jiao1);
        mEr1 = findViewById(R.id.item50_er1);
        mGai1 = findViewById(R.id.item50_gai1);
        mWei1 = findViewById(R.id.item50_wei1);
        mTui1 = findViewById(R.id.item50_tui1);
        mQi1 = findViewById(R.id.item50_qi1);
        mKe1 = findViewById(R.id.item50_ke1);

        mLu2 = findViewById(R.id.item50_lu2);
        mAn2 = findViewById(R.id.item50_an2);
        mJiao2 = findViewById(R.id.item50_jiao2);
        mEr2 = findViewById(R.id.item50_er2);
        mGai2 = findViewById(R.id.item50_gai2);
        mWei2 = findViewById(R.id.item50_wei2);
        mTui2 = findViewById(R.id.item50_tui2);
        mQi2 = findViewById(R.id.item50_qi2);
        mKe2 = findViewById(R.id.item50_ke2);
    }
}
