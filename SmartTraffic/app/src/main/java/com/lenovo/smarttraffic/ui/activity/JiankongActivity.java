package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lenovo.smarttraffic.R;

public class JiankongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiankong);

        ImageView mImag1 = findViewById(R.id.jiankong_wei1);
        ImageView mImag2 = findViewById(R.id.jiankong_wei2);
        ImageView mImag3 = findViewById(R.id.jiankong_wei3);
        ImageView mImag4 = findViewById(R.id.jiankong_wei4);
        Button mBack = findViewById(R.id.jiankong_back);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JiankongActivity.this.finish();
            }
        });

        mImag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(JiankongActivity.this, ImgActivity.class));
            }
        });

        mImag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JiankongActivity.this, ImgActivity.class);
                intent.putExtra("aaa",1);
                startActivity(intent);
            }
        });

        mImag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JiankongActivity.this, ImgActivity.class);
                intent.putExtra("aaa",2);
                startActivity(intent);
            }
        });

        mImag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JiankongActivity.this, ImgActivity.class);
                intent.putExtra("aaa",3);
                startActivity(intent);
            }
        });
    }
}
