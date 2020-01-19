package com.lenovo.smarttraffic.ui.activity.item33activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;

public class Item33_2Activity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView mTv;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item33_2);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        calendarView = findViewById(R.id.item33_2_calenderView);
        mTv = findViewById(R.id.item33_2_tv);
        mBtn = findViewById(R.id.item33_2_btn);
        ImageView mImg = findViewById(R.id.lgb2_back);

        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                String s= mTv.getText().toString();
                mTv.setText(s+i+"-"+(i1+1)+"-"+i2+" ");
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Item33_2Activity.this,Item33_3Activity.class);
                intent.putExtra("wherewhere",getIntent().getStringExtra("wherewhere"));
                intent.putExtra("date",mTv.getText().toString());
                startActivity(intent);
            }
        });
    }
}
