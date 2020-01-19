package com.lenovo.smarttraffic.ui.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.item39fragment.HuanjingFragment;
import com.lenovo.smarttraffic.ui.fragment.item39fragment.LuKuangFragment;

public class Item39Activity extends AppCompatActivity {

    private TextView mLukuang;
    private TextView mHuan;
    private HuanjingFragment huanjingFragment = new HuanjingFragment();
    private LuKuangFragment luKuangFragment = new LuKuangFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item39);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        this.getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.id_content,huanjingFragment)
            .hide(huanjingFragment)
            .add(R.id.id_content,luKuangFragment)
            .show(luKuangFragment)
            .commit();

        mLukuang.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        initevent();



    }

    private void initevent() {
        mHuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item39Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(luKuangFragment)
                        .show(huanjingFragment)
                        .commit();

                mHuan.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mLukuang.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }
        });

        mLukuang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item39Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(luKuangFragment)
                        .hide(huanjingFragment)
                        .commit();

                mLukuang.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mHuan.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }
        });

    }

    private void initview() {
        mLukuang = findViewById(R.id.item39_lukuang);
        mHuan = findViewById(R.id.item39_huanjing);
    }
}
