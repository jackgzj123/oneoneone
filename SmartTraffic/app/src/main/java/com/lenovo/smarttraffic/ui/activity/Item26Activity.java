package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.lenovo.smarttraffic.R;

public class Item26Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item26);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView video1 = findViewById(R.id.item26_video_1);
        ImageView video2 = findViewById(R.id.item26_video_2);
        ImageView video3 = findViewById(R.id.item26_video_3);
        ImageView video4 = findViewById(R.id.item26_video_4);
        ImageView video5 = findViewById(R.id.item26_video_5);

        video1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item26Activity.this, VideoActivity.class));
            }
        });
        video2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item26Activity.this, VideoActivity.class));
            }
        });
        video3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item26Activity.this, VideoActivity.class));
            }
        });
        video4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item26Activity.this, VideoActivity.class));
            }
        });
        video5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item26Activity.this, VideoActivity.class));
            }
        });

    }
}
