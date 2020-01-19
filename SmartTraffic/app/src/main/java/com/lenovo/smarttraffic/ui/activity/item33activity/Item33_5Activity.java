package com.lenovo.smarttraffic.ui.activity.item33activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.item33fragment.Daifragment;
import com.lenovo.smarttraffic.ui.fragment.item33fragment.Yifragment;

public class Item33_5Activity extends AppCompatActivity {

    private TextView mDai;
    private TextView mYi;
    private Daifragment daifragment ;
    private Yifragment yifragment = new Yifragment();
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item33_5);

        daifragment =Daifragment.newInstance(getIntent().getStringExtra("tag"),getIntent().getStringExtra("date"));
        initview();

        intievent();
    }

    private void intievent() {

        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.id_content,daifragment)
                .add(R.id.id_content,yifragment)
                .hide(yifragment)
                .commit();

        mDai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDai.setBackgroundResource(R.drawable.edtt15);
                mYi.setBackgroundResource(R.drawable.edtt9);
                Item33_5Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(daifragment)
                        .hide(yifragment)
                        .commit();
            }
        });

        mYi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYi.setBackgroundResource(R.drawable.edtt15);
                mDai.setBackgroundResource(R.drawable.edtt9);
                Item33_5Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(yifragment)
                        .hide(daifragment)
                        .commit();
            }
        });
    }

    private void initview() {

        mDai = findViewById(R.id.item33_5_dai);
        mYi = findViewById(R.id.item33_5_yi);
        mImg = findViewById(R.id.lgb2_back);

    }
}
