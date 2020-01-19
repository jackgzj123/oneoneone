package com.lenovo.smarttraffic.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.imageui.ScaleImageView;

public class ImgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        ScaleImageView mScaleImageView = findViewById(R.id.image_scaleImageView);
        Button mBack = findViewById(R.id.Img_back);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImgActivity.this.finish();
            }
        });

        int i = getIntent().getIntExtra("aaa",0);
        switch (i){
            case 1:
                mScaleImageView.setImageResource(R.mipmap.weizhang02);
                break;
            case 2:
                mScaleImageView.setImageResource(R.mipmap.weizhang03);
                break;
            case 3:
                mScaleImageView.setImageResource(R.mipmap.weizhang04);
                break;
        }
    }
}
