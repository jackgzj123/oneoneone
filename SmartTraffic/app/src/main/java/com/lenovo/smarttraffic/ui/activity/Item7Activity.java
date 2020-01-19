package com.lenovo.smarttraffic.ui.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.ImageFragment;
import com.lenovo.smarttraffic.ui.fragment.VideoFragment;

public class Item7Activity extends AppCompatActivity {

    private TextView mVideoTv;
    private TextView mImgTv;

    private VideoFragment videoFragment = new VideoFragment();
    private ImageFragment imageFragment = new ImageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item7);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();

        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.id_content,videoFragment)
                .add(R.id.id_content,imageFragment)
                .hide(imageFragment)
                .commit();
    }

    private void initevent() {

        mImgTv.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                mVideoTv.setBackgroundResource(R.drawable.tvstyle);
                mImgTv.setBackgroundResource(R.drawable.tvstyle1);

                Item7Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(imageFragment)
                        .hide(videoFragment)
                        .commit();
            }
        });

        mVideoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVideoTv.setBackgroundResource(R.drawable.tvstyle1);
                mImgTv.setBackgroundResource(R.drawable.tvstyle);

                Item7Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(imageFragment)
                        .show(videoFragment)
                        .commit();
            }
        });
    }

    private void initview() {
        mVideoTv = findViewById(R.id.item7_video);
        mImgTv = findViewById(R.id.item7_img);
    }
}
