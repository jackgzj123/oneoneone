package com.lenovo.smarttraffic.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.lenovo.smarttraffic.R;

public class Item36Activity extends AppCompatActivity {

    private Button mSelectBtn;
    private Button mMissBtn;
    private ListView mListview;
    private Spinner mSpinner1;
    private Spinner mSpinner2;
    private Spinner mSpinner3;
    private Spinner mSpinner4;
    private Spinner mSpinner5;
    private Spinner mSpinner6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item36);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();


    }

    private void initevent() {
        mSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    private void initview() {
        mSelectBtn = findViewById(R.id.item36_select);
        mMissBtn = findViewById(R.id.item36_miss);
        mListview = findViewById(R.id.item36_listview);
        mSpinner1 = findViewById(R.id.item36_spinner1);
        mSpinner2 = findViewById(R.id.item36_spinner2);
        mSpinner3 = findViewById(R.id.item36_spinner3);
        mSpinner4 = findViewById(R.id.item36_spinner4);
        mSpinner5 = findViewById(R.id.item36_spinner5);
        mSpinner6 = findViewById(R.id.item36_spinner6);

    }
}
