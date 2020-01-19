package com.lenovo.smarttraffic.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Item20Activity extends AppCompatActivity {

    private TextView mTv;
    private EditText mEd;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item20);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();
    }

    private void initevent() {

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBusCarrier2 eventBusCarrier = new EventBusCarrier2();
                eventBusCarrier.setEventType("1");
                eventBusCarrier.setFazhi(mEd.getText().toString());
                EventBus.getDefault().post(eventBusCarrier);
                //mTv.setText("我的1-4号车账户余额告警阀值为"+mEd.getText().toString()+"元");
                Toast.makeText(Item20Activity.this,"保存成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 普通事件的处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(EventBusCarrier2 carrier) {
        String fazhi = carrier.getFazhi();
        mTv.setText("当前1~4号小车账户余额告警阈值为"+fazhi+"元");

        Log.e("Fazhi1", "handleEvent: " );

    }
    private void initview() {
        mTv = findViewById(R.id.item20_text);
        mEd = findViewById(R.id.item20_ed_prise);
        mBtn = findViewById(R.id.item20_shezhi);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
