package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.imageui.ScaleImageView;

public class SubwayMapActivity extends AppCompatActivity {

    private ScaleImageView mImageView;
    private ImageView mBackImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subway_map);

        mImageView = findViewById(R.id.subway_map);
        mBackImg = findViewById(R.id.subway_back);
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubwayMapActivity.this,Item27Activity.class));
            }
        });
    }
}
