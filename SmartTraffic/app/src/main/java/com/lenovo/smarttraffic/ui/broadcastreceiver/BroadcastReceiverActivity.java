package com.lenovo.smarttraffic.ui.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.ui.activity.Item8Activity;
import com.lenovo.smarttraffic.ui.activity.Item9Activity;

public class BroadcastReceiverActivity extends BroadcastReceiver {

    private int bWen;
    private int bShi;
    private int bSun;
    private int bCo2;
    private int bPm2;
    private int bDao;
    private int j;

    public BroadcastReceiverActivity(int bWen, int bShi, int bSun, int bCo2, int bPm2, int bDao,int j) {
        this.bWen = bWen;
        this.bShi = bShi;
        this.bSun = bSun;
        this.bCo2 = bCo2;
        this.bPm2 = bPm2;
        this.bDao = bDao;
        this.j = j;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e("GZJJJ", "onReceive: "+ action );

        //判断是否是自定义的广播
        if (TextUtils.equals(action, Item8Activity.AAAAA)){
            int j1 = intent.getIntExtra(Item9Activity.J,0);
            j=j1;
            Log.e("GZJjjj", "11j  "+j );
            if (j == 1){
                int  s = Integer.parseInt(intent.getStringExtra(Item9Activity.CO2));
                Log.e("GZJJJJJ", "s    "+s );
                bCo2 =s;
                int  s1 = Integer.parseInt(intent.getStringExtra(Item9Activity.PM_2));
                bPm2 =s1;
                int  s2 = Integer.parseInt(intent.getStringExtra(Item9Activity.SHI));
                bPm2 =s2;
                int  s3 = Integer.parseInt(intent.getStringExtra(Item9Activity.SUN));
                bPm2 =s3;
                int  s4 = Integer.parseInt(intent.getStringExtra(Item9Activity.WEN));
                bPm2 =s4;
                int  s5 = Integer.parseInt(intent.getStringExtra(Item9Activity.DAO));
                bPm2 =s5;
            }


        }
    }
}
