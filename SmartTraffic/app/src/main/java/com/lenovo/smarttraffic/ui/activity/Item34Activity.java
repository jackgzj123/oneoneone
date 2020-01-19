package com.lenovo.smarttraffic.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.item34fragment.JunshiFragment;
import com.lenovo.smarttraffic.ui.fragment.item34fragment.TiyuFragment;
import com.lenovo.smarttraffic.ui.fragment.item34fragment.YuleFragment;

import java.util.List;

public class Item34Activity extends AppCompatActivity {

    private TextView mJunshi;
    private TextView mTiyu;
    private TextView mYule;
    private JunshiFragment junshiFragment = new JunshiFragment();
    private YuleFragment yuleFragment = new YuleFragment();
    private TiyuFragment tiyuFragment = new TiyuFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item34);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.id_content,junshiFragment)
                .add(R.id.id_content,tiyuFragment)
                .hide(tiyuFragment)
                .add(R.id.id_content,yuleFragment)
                .hide(yuleFragment)
                .commit();

        initevent();
    }

    private void initevent() {
        mJunshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item34Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(junshiFragment)
                        .hide(tiyuFragment)
                        .hide(yuleFragment)
                        .commit();
            }
        });

        mTiyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item34Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(junshiFragment)
                        .show(tiyuFragment)
                        .hide(yuleFragment)
                        .commit();
            }
        });

        mYule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item34Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(junshiFragment)
                        .hide(tiyuFragment)
                        .show(yuleFragment)
                        .commit();
            }
        });
    }

    private void initview() {
        mJunshi = findViewById(R.id.item34_junshi);
        mTiyu = findViewById(R.id.item34_tiyu);
        mYule = findViewById(R.id.item34_yule);

    }
}
