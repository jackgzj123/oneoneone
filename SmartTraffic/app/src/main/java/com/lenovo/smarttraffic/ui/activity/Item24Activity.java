package com.lenovo.smarttraffic.ui.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.BusData;
import com.lenovo.smarttraffic.database.data.Chapter;
import com.lenovo.smarttraffic.database.data.ChapterItem;
import com.lenovo.smarttraffic.ui.adapter.ExpandlistAdapter;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item24Activity extends AppCompatActivity {

    private ExpandableListView mExpandablelistview;
    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private List<BusData.PlatformsBean> platformsBeanList;
    private List<Chapter> chapterList = new ArrayList<>();
    private int j1;
    private int j2;
    private int j3;
    private int j4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item24);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        initview();

        initevent();
    }

    private void initevent() {

        Timer timer = new Timer();
        //参数1：TimerTask 参数2：定时时间，单位毫秒
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                chapterList.clear();
                getNet();
            }

        },0,3000);
        mExpandablelistview.setAdapter(new ExpandlistAdapter(chapterList,Item24Activity.this));
    }

    private void initview() {

        mExpandablelistview = findViewById(R.id.item24_expandlistview);
    }

    private void getNet() {

        //todo get请求
        mOkhttp.newCall(new Request.Builder().url(UrlClass.BUS_SELECT).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                gson = new Gson();
                BusData busData = gson.fromJson(jsonData, BusData.class);
                String busName = busData.getBusName();
                String beginTime = busData.getBeginTime();
                String endTime = busData.getEndTime();
                platformsBeanList = busData.getPlatforms();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        j1 = platformsBeanList.get(0).getChildBeans().get(0).getDistance();
                        j2 = platformsBeanList.get(0).getChildBeans().get(1).getDistance();
                        j3 = platformsBeanList.get(1).getChildBeans().get(0).getDistance();
                        j4 = platformsBeanList.get(1).getChildBeans().get(1).getDistance();
                        if (j1 < j2 && j3 < j4){
                            setData1();
                        }else if (j1 < j2 && j3 > j4){
                            setData2();
                        }else if (j1 > j2 && j3 < j4){
                            setData3();
                        }else {
                            setData4();
                        }
                    }
                });
            }
        });
    }

    private void setData1() {

        Chapter chapter = new Chapter("一号站台");
        ChapterItem chapterItem = new ChapterItem("一号公交车  "+j1+"米");
        ChapterItem chapterItem1 = new ChapterItem("二号公交车  "+j2+"米");
        chapter.addChild(chapterItem);
        chapter.addChild(chapterItem1);
        chapterList.add(chapter);

        Chapter chapter1 = new Chapter("二号站台");
        ChapterItem chapterItem2 = new ChapterItem("一号公交车  "+j3+"米");
        ChapterItem chapterItem3 = new ChapterItem("二号公交车  "+j4+"米");
        chapter1.addChild(chapterItem2);
        chapter1.addChild(chapterItem3);
        chapterList.add(chapter1);

        mExpandablelistview.setAdapter(new ExpandlistAdapter(chapterList,Item24Activity.this));


    }
    private void setData2() {

        Chapter chapter = new Chapter("一号站台");
        ChapterItem chapterItem = new ChapterItem("一号公交车  "+j1+"米");
        ChapterItem chapterItem1 = new ChapterItem("二号公交车  "+j2+"米");
        chapter.addChild(chapterItem);
        chapter.addChild(chapterItem1);
        chapterList.add(chapter);

        Chapter chapter1 = new Chapter("二号站台");
        ChapterItem chapterItem2 = new ChapterItem("一号公交车  "+j3+"米");
        ChapterItem chapterItem3 = new ChapterItem("二号公交车  "+j4+"米");
        chapter1.addChild(chapterItem3);
        chapter1.addChild(chapterItem2);
        chapterList.add(chapter1);

        mExpandablelistview.setAdapter(new ExpandlistAdapter(chapterList,Item24Activity.this));


    }
    private void setData3() {

        Chapter chapter = new Chapter("一号站台");
        ChapterItem chapterItem = new ChapterItem("一号公交车  "+j1+"米");
        ChapterItem chapterItem1 = new ChapterItem("二号公交车  "+j2+"米");
        chapter.addChild(chapterItem1);
        chapter.addChild(chapterItem);
        chapterList.add(chapter);

        Chapter chapter1 = new Chapter("二号站台");
        ChapterItem chapterItem2 = new ChapterItem("一号公交车  "+j3+"米");
        ChapterItem chapterItem3 = new ChapterItem("二号公交车  "+j4+"米");
        chapter1.addChild(chapterItem2);
        chapter1.addChild(chapterItem3);
        chapterList.add(chapter1);

        mExpandablelistview.setAdapter(new ExpandlistAdapter(chapterList,Item24Activity.this));


    }
    private void setData4() {


        Chapter chapter = new Chapter("一号站台");
        ChapterItem chapterItem = new ChapterItem("一号公交车  "+j1+"米");
        ChapterItem chapterItem1 = new ChapterItem("二号公交车  "+j2+"米");
        chapter.addChild(chapterItem1);
        chapter.addChild(chapterItem);
        chapterList.add(chapter);

        Chapter chapter1 = new Chapter("二号站台");
        ChapterItem chapterItem2 = new ChapterItem("一号公交车  "+j3+"米");
        ChapterItem chapterItem3 = new ChapterItem("二号公交车  "+j4+"米");
        chapter1.addChild(chapterItem3);
        chapter1.addChild(chapterItem2);
        chapterList.add(chapter1);

        mExpandablelistview.setAdapter(new ExpandlistAdapter(chapterList,Item24Activity.this));

    }
}
