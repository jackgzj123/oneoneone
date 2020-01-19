package com.lenovo.smarttraffic.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.item17Fragment.Fragment1;
import com.lenovo.smarttraffic.ui.fragment.item19fragment.AnalysisFragment;
import com.lenovo.smarttraffic.ui.fragment.item19fragment.SelectFragment;

public class Item19Activity extends AppCompatActivity {

    private TextView mSeclect;
    private TextView mAnalysis;
    private SelectFragment selectFragment = new SelectFragment();
    private AnalysisFragment analysisFragment = new AnalysisFragment();
    private Fragment1 fragment1 = new Fragment1();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item19);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.id_content,selectFragment)
                .add(R.id.id_content,analysisFragment)
                .hide(analysisFragment)
//                .add(R.id.id_content,fragment1)
//                .hide(fragment1)
                .commit();

        mSeclect.setBackgroundResource(R.drawable.edtt6);

        initevent();

    }

    private void initevent() {

        mAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item19Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(analysisFragment)
                        .hide(selectFragment)
                        .commit();
                mAnalysis.setBackgroundResource(R.drawable.edtt6);
                mSeclect.setBackgroundResource(R.drawable.edtt5);
            }
        });

        mSeclect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item19Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(selectFragment)
                        .hide(analysisFragment)
                        .commit();
                mSeclect.setBackgroundResource(R.drawable.edtt6);
                mAnalysis.setBackgroundResource(R.drawable.edtt5);
            }
        });

    }

    private void initview() {
        mSeclect = findViewById(R.id.item19_select);
        mAnalysis = findViewById(R.id.item19_analysis);
    }
}
