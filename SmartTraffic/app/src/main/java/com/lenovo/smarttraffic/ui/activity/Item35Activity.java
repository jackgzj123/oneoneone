package com.lenovo.smarttraffic.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.lenovo.smarttraffic.R;

public class Item35Activity extends AppCompatActivity {

    private Button mChongBtn;
    private EditText mEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item35);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mChongBtn = findViewById(R.id.item35_chong);
        mEd = findViewById(R.id.item35_ed);

        mChongBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }
}
