package com.lenovo.smarttraffic.ui.activity.item49activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.lenovo.smarttraffic.R;

public class Item49Activity extends AppCompatActivity {

    private EditText mEd;
    private Button mBtn;
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item49);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mEd = findViewById(R.id.item49_ed);
        mBtn = findViewById(R.id.item49_btn);
        mImg = findViewById(R.id.lgb2_back);

        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item49Activity.this,Item49_1Activity.class);
                startActivity(intent);
            }
        });
    }
}
