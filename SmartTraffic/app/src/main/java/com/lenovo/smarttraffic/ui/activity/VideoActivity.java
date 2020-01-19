package com.lenovo.smarttraffic.ui.activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;

public class VideoActivity extends AppCompatActivity {

    private Button play;
    private Button pause ;
    private Boolean noPlay=true;//定义播放状态
    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;
    private SurfaceView surfaceView;
    private String TAG = "GZJ";
    private List<String> list = new ArrayList<>();
    private int i = 0;
    private OkHttpClient mOkHttpClient;
    private String VideoUrl = "http://139.9.173.92:8080/Health/appVideo/fetchExeVideos.do?painlevel=1&speed=0.5&angle=0.4&userid=1&position=肩关节\n";
    private Gson gson;
    private Handler mHandler ;
    private int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                ,WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏显示

//        getNet();
        list.add("https://flv2.bn.netease.com/videolib1/1811/26/OqJAZ893T/HD/OqJAZ893T-mobile.mp4");
        list.add("http://111.206.133.26:89/video/100100/00000/100100000000000053.mp4");
        //控制视频的按钮
        play=findViewById(R.id.play);
        pause = findViewById(R.id.pasue);
        surfaceView = findViewById(R.id.surfaceView);
        surfaceHolder= surfaceView.getHolder();//获取surfaceHolder
        mediaPlayer=new MediaPlayer();//创建MediaPlayer对象
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//设置多媒体类型

        j = getIntent().getIntExtra("id",0);

        //播放
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(noPlay){
                    play();
                    noPlay=false;
                    play.setText("play");
                }else{
                    mediaPlayer.start();//继续播放视频
                }
            }
        });
        //暂停
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mediaPlayer.isPlaying()){
                    Toast.makeText(VideoActivity.this, "视频暂停播放", Toast.LENGTH_SHORT).show();
                    mediaPlayer.pause();
                }
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(VideoActivity.this, "视频播放完毕", Toast.LENGTH_SHORT).show();
                //play.setText("播放下一个视频");
                i+=1;
                noPlay = true;
                if (j ==1){
                    finish();
                }

            }
        });
    }
    public void play(){

        mediaPlayer.reset();//重置MediaPlayer
        mediaPlayer.setDisplay(surfaceHolder);//把视频画面输出到SurfaceView中

        try {
            Log.e(TAG, "play:i "+i );
            mediaPlayer.setDataSource(list.get(i));
            //mediaPlayer.setDataSource("http://139.9.173.92:8080/Health/video/上饶.mp4");

            Log.e(TAG, "play: ");
            mediaPlayer.prepare();//预加载

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mediaPlayer == null){
            Log.e(TAG, "is null" );
        }
        mediaPlayer.start();

    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer!=null){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}
