package com.lenovo.smarttraffic.ui.fragment.item3fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.Data;
import com.lenovo.smarttraffic.ui.activity.EventBusCarrier;
import com.lenovo.smarttraffic.ui.activity.EventBusCarrier2;
import com.lenovo.smarttraffic.ui.activity.Item20Activity;
import com.lenovo.smarttraffic.ui.activity.Item3Activity;
import com.lenovo.smarttraffic.ui.activity.Item8Activity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author：gzj
 * @CreateDate: 2019/11/6
 */
public class FazhiFragment extends Fragment {

    private TextView mTvFa;
    private EditText ed;
    private Button button;

    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private SettingListener mSListener = null;

    private int j = 50;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gerenzhongxin3,null);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mTvFa = view.findViewById(R.id.tv_fazhi);
        ed = view.findViewById(R.id.ed_prise);
        button = view.findViewById(R.id.shezhi);

        Timer timer = new Timer();
        //参数1：TimerTask 参数2：定时时间，单位毫秒
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                for (int i = 1; i <= 4; i++) {
                    getNet(String.valueOf(i));
                }

            }

        },0,3000);


      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              j = Integer.valueOf(ed.getText().toString());
              mTvFa.setText("当前1~4号小车账户余额告警阈值为"+ed.getText().toString()+"元");
              EventBusCarrier2 eventBusCarrier = new EventBusCarrier2();
              eventBusCarrier.setEventType("1");
              eventBusCarrier.setFazhi(ed.getText().toString());
              EventBus.getDefault().post(eventBusCarrier);
              //mTv.setText("我的1-4号车账户余额告警阀值为"+mEd.getText().toString()+"元");
              Toast.makeText(getActivity(),"保存成功",Toast.LENGTH_SHORT).show();
          }
      });
        return view;

    }

    // 普通事件的处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(EventBusCarrier2 carrier) {
        String fazhi = carrier.getFazhi();
        j = Integer.valueOf(fazhi);
        mTvFa.setText("当前1~4号小车账户余额告警阈值为"+fazhi+"元");

        Log.e("Fazhi", "handleEvent: " );

    }
    private void getNet(final String value) {
        //todo post请求
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("carId",value);
        FormBody formBody = builder.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                Data data = new Gson().fromJson(jsonData,Data.class);
                final String s1 = data.getResult();
                final float a = Float.valueOf(s1);
                Log.e("s1", "onResponse: "+s1 +"a:"+a);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                       //todo
                        if (a<j){
                            //todo 发送状态栏
                            final int j2 = j;
                            if (mSListener != null){
                                //mSListener.onSetting(s1,value,j2);
                                NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                                Notification notification = new NotificationCompat.Builder(getActivity(), "chat")
                                        .setAutoCancel(true)
                                        .setContentTitle("收到警告消息")
                                        .setContentText("车辆号 "+s1+" 余额:"+value+" 阈值:"+j2)
                                        .setWhen(System.currentTimeMillis())
                                        .setSmallIcon(R.mipmap.ic_launcher)
                                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                        //在build()方法之前还可以添加其他方法
                                        .build();
                                manager.notify(1, notification);
                            }
                        }
                    }
                });
            }
        });
    }

    public interface SettingListener {
        public void onSetting(String name,String i,int j1);}

    public void setOnSettingListener(SettingListener listener) {
        mSListener = listener;
    }
    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

