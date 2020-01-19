package com.lenovo.smarttraffic.ui.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.database.dao.CarMessageDao;
import com.lenovo.smarttraffic.ui.fragment.item3fragment.ChongFragment;
import com.lenovo.smarttraffic.ui.fragment.item3fragment.FazhiFragment;
import com.lenovo.smarttraffic.ui.fragment.item3fragment.MsgFragment;

public class Item3Activity extends AppCompatActivity implements FazhiFragment.SettingListener{


    private TextView mMsgtv;
    private TextView mChongtv;
    private TextView mFazhitv;
    private ChongFragment chongFragment = new ChongFragment();
    private MsgFragment msgFragment = new MsgFragment();
    private FazhiFragment fazhiFragment = new FazhiFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item3);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();


        fazhiFragment.setOnSettingListener(Item3Activity.this);

        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.id_content,msgFragment)
                .add(R.id.id_content,chongFragment)
                .hide(chongFragment)
                .add(R.id.id_content,fazhiFragment)
                .hide(fazhiFragment)
                .commit();
    }

    private void initevent() {

        mMsgtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item3Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(msgFragment)
                        .hide(chongFragment)
                        .hide(fazhiFragment)
                        .commit();
            }
        });

        mChongtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item3Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(chongFragment)
                        .hide(msgFragment)
                        .hide(fazhiFragment)
                        .commit();
            }
        });

        mFazhitv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item3Activity.this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(fazhiFragment)
                        .hide(msgFragment)
                        .hide(msgFragment)
                        .commit();
            }
        });

    }

    private void initview() {
        mMsgtv = findViewById(R.id.item3_msg);
        mChongtv = findViewById(R.id.item3_chong);
        mFazhitv = findViewById(R.id.item3_fazhi);

    }

    @Override
    public void onSetting(String name, String i,int j1) {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(Item3Activity.this, "chat")
                .setAutoCancel(true)
                .setContentTitle("收到警告消息")
                .setContentText("车辆号 "+name+" 余额:"+i+" 阈值:"+j1)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                //在build()方法之前还可以添加其他方法
                .build();
        manager.notify(1, notification);
    }
}
