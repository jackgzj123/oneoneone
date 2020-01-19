package com.lenovo.smarttraffic.ui.fragment.item39fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.MainActivity;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.EnvironmentData;
import com.lenovo.smarttraffic.ui.activity.Item8Activity;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author：gzj
 * @CreateDate: 2019/12/23
 */
public class HuanjingFragment extends Fragment {

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private TextView mPmTv;
    private TextView mSunTv;
    private TextView mSunZhi;
    private View mLeft;
    private View mRight;
    private TextView mPmtv;
    private TextView mSuntv;
    private VideoView mVideo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item39_fragment_huanjing,null);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mPmTv = view.findViewById(R.id.item39_fragment_huanjing_pm2);
        mSunTv = view.findViewById(R.id.item39_fragment_huanjing_sun);
        mSunZhi = view.findViewById(R.id.item39_fragment_huanjing_sunzhi);
        mLeft = view.findViewById(R.id.item39_fragment_huanjing_left);
        mRight = view.findViewById(R.id.item39_fragment_huanjing_right);
        mPmtv = view.findViewById(R.id.item39_fragment_huanjing_pmtext);
        mSuntv = view.findViewById(R.id.item39_fragment_huanjing_suntext);
        mVideo = view.findViewById(R.id.item39_fragment_huanjing_videoview);

        LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) mLeft.getLayoutParams();
        linearParams.weight=50;  //这里的50即为50dp，height的值为像素，这里就是将50dp对应的像素值传递给height
        mLeft.setLayoutParams(linearParams);




        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getNet();
            }
        },0,3000);


        return view;
    }

    private void getvideo() {
        Uri uri = Uri.parse("https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4");
        mVideo.setVideoURI(uri);
        mVideo.start();


        mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                mp.start();// 播放
               // Toast.makeText(getActivity(), "开始播放！", Toast.LENGTH_LONG).show();
            }
        });

        mVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
               // Toast.makeText(getActivity(), "播放完毕", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getNet() {

        //todo get请求
        mOkhttp.newCall(new Request.Builder().url(UrlClass.ENVIRONMENT_SELECT).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                gson = new Gson();
                EnvironmentData environmentData = gson.fromJson(jsonData, EnvironmentData.class);

                final String pm = environmentData.getPm();

                final String sun = environmentData.getLightIntensity();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        mPmTv.setText("PM2.5当前值："+pm);
                        mSunTv.setText("光照强度当前值："+sun);
                        mSunZhi.setText(sun
                        );

                        if (Integer.valueOf(pm)>200){
                            NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notification = new NotificationCompat.Builder(getActivity(), "chat")
                                    .setAutoCancel(true)
                                    .setContentTitle("收到警告消息")
                                    .setContentText("PM2.5大于200，不适合出行")
                                    .setWhen(System.currentTimeMillis())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                    //在build()方法之前还可以添加其他方法
                                    .build();
                            manager.notify(7, notification);

                            mVideo.setVisibility(View.VISIBLE);
                            getvideo();

                            mPmtv.setText("友情提示：不适合出行");
                        }else {
                            mPmtv.setText("友情提示：适合出行");
                            mVideo.setVisibility(View.INVISIBLE); //设置隐藏
                        }

                        if (Integer.valueOf(sun)>3190){
                            NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notification = new NotificationCompat.Builder(getActivity(), "chat")
                                    .setAutoCancel(true)
                                    .setContentTitle("收到警告消息")
                                    .setContentText("光照较强，出行请戴墨镜")
                                    .setWhen(System.currentTimeMillis())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                    //在build()方法之前还可以添加其他方法
                                    .build();
                            manager.notify(8, notification);

                            mSuntv.setText("友情提示：不适合出行");

                        }else if (Integer.valueOf(sun)<1100){
                            NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                            Notification notification = new NotificationCompat.Builder(getActivity(), "chat")
                                    .setAutoCancel(true)
                                    .setContentTitle("收到警告消息")
                                    .setContentText("光照较弱，出行请开灯")
                                    .setWhen(System.currentTimeMillis())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                    //在build()方法之前还可以添加其他方法
                                    .build();
                            manager.notify(8, notification);

                            mSuntv.setText("友情提示：不适合出行");
                        }else  mSuntv.setText("友情提示：适合出行");



                    }
                });
            }
        });

    }
}
