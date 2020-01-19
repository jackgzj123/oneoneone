package com.lenovo.smarttraffic.ui.activity.item33activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

public class Item33_1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item33_1);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button mBtn = findViewById(R.id.item33_1_btn);
        ImageView mImg = findViewById(R.id.lgb2_back);
        TextView mTv = findViewById(R.id.item33_1_tv);

        mTv.setText(getIntent().getStringExtra("wherewhere"));
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item33_1Activity.this,Item33_2Activity.class);
                intent.putExtra("wherewhere",getIntent().getStringExtra("wherewhere"));
                startActivity(intent);
            }
        });
    }
}
