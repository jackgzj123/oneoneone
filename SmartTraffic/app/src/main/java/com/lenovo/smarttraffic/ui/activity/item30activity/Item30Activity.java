package com.lenovo.smarttraffic.ui.activity.item30activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lenovo.smarttraffic.R;

public class Item30Activity extends AppCompatActivity {

    private ImageView mImg1;
    private ImageView mImg2;
    private ImageView mImg3;
    private ImageView mImg4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item30);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mImg1 = findViewById(R.id.item30_img1);
        mImg2 = findViewById(R.id.item30_img2);
        mImg3 = findViewById(R.id.item30_img3);
        mImg4 = findViewById(R.id.item30_img4);

        mImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item30Activity.this,Item30MsgActivity.class));
            }
        });

        mImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item30Activity.this,Item30MsgActivity.class));
            }
        });

        mImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item30Activity.this,Item30MsgActivity.class));
            }
        });

        mImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item30Activity.this,Item30MsgActivity.class));
            }
        });


    }
}
