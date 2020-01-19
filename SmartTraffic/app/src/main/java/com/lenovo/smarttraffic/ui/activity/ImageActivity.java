package com.lenovo.smarttraffic.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.ImgOnTouch;
import com.lenovo.smarttraffic.ui.imageui.ScaleImageView;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView imageView = findViewById(R.id.image_ImageView);

        imageView.setOnTouchListener(new ImgOnTouch(imageView));

//        ScaleImageView mScaleImageView = findViewById(R.id.image_scaleImageView);
//
//        int i = getIntent().getIntExtra("aaa",0);
//        switch (i){
//            case 1:
//                mScaleImageView.setImageResource(R.mipmap.weizhang02);
//                break;
//            case 2:
//                mScaleImageView.setImageResource(R.mipmap.weizhang03);
//                break;
//            case 3:
//                mScaleImageView.setImageResource(R.mipmap.weizhang04);
//                break;
//        }


    }
}
