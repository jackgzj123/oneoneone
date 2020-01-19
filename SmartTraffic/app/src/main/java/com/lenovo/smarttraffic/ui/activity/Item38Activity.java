package com.lenovo.smarttraffic.ui.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.fragment.item38fragment.ChongFragment;
import com.lenovo.smarttraffic.ui.fragment.item38fragment.YuanchengFragment;
import com.lenovo.smarttraffic.ui.fragment.item38fragment.YueFragment;

public class Item38Activity extends AppCompatActivity {

    private TextView mYue;
    private TextView mYuancheng;
    private TextView mChong;

    private YueFragment yueFragment = new YueFragment();
    private YuanchengFragment yuanchengFragment = new YuanchengFragment();
    private ChongFragment chongFragment = new ChongFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item38);
        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initview();

        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.id_content,yueFragment)
                .add(R.id.id_content,chongFragment)
                .hide(chongFragment)
                .add(R.id.id_content,yuanchengFragment)
                .hide(yuanchengFragment)
                .commit();
        mYue.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        initevent();

    }

    private void initevent() {
        mYue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item38Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(yueFragment)
                        .hide(chongFragment)
                        .hide(yuanchengFragment)
                        .commit();

                mYue.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mYuancheng.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                mChong.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }
        });

        mYuancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item38Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(yueFragment)
                        .hide(chongFragment)
                        .show(yuanchengFragment)
                        .commit();

                mYuancheng.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mYue.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                mChong.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }
        });
        mChong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item38Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(yueFragment)
                        .show(chongFragment)
                        .hide(yuanchengFragment)
                        .commit();

                mChong.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                mYuancheng.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                mYue.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            }
        });
    }

    private void initview() {
        mYue = findViewById(R.id.item38_yue);
        mYuancheng = findViewById(R.id.item38_yuancheng);
        mChong = findViewById(R.id.item38_chong);

    }
}
