package com.lenovo.smarttraffic.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

public class Item27Activity extends AppCompatActivity {

    private TextView mGuihuaTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item27);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();
    }

    private void initevent() {

        mGuihuaTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Item27Activity.this,SubwayMapActivity.class));
            }
        });

    }

    private void initview() {

        mGuihuaTv = findViewById(R.id.item27_guihua);
    }
}
