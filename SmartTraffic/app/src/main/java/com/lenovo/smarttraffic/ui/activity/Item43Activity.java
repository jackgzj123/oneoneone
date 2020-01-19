package com.lenovo.smarttraffic.ui.activity;

import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.WindowManager;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.RefreshableView;
import com.lenovo.smarttraffic.ui.Refreshview;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.dao.EnvironmentDao1;
import com.lenovo.smarttraffic.database.data.EnvironmentData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item43Activity extends AppCompatActivity {

    ListView listView;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private EnvironmentDao1 environmentDao1;
    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private ImageView mImg;
    private SwipeRefreshLayout refreshLayout;
    RefreshableView refreshableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item43);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        environmentDao1 = new EnvironmentDao1(this);
//        refreshLayout = findViewById(R.id.refreshable_view);
        refreshableView = findViewById(R.id.refreshable_view);
        listView = (ListView) findViewById(R.id.list_view);
        mImg = findViewById(R.id.lgb2_back);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        getAdapter();

//        给SwipeRefreshLayout组件添加下拉刷新监听事件
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        //刷新结束，隐藏刷新动画
//                        refreshLayout.setRefreshing(false);
//                    }
//                });
//            }
//        });

        refreshableView.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try {

                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            getNet();
                            getNet();
                            getAdapter();
                        }
                    });

                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 0);


    }


    private void  getAdapter(){
        cursor = environmentDao1.selectCarMessage();
        adapter = new SimpleCursorAdapter(
                this,R.layout.item43_adapter_view, cursor,
                new String[]{"co2","pm2","shi","sun","wen","time"},//表头
                new int[]{R.id.item43adapter_co2,R.id.item43adapter_PM2,R.id.item43adapter_shi,R.id.item43adapter_sun,R.id.item43adapter_wen,R.id.item43adapter_time},//表头结果所对应的输出对象
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
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
                final String co2 = environmentData.getCo2();

                final String pm = environmentData.getPm();

                final String shi = environmentData.getHumidity();

                final String sun = environmentData.getLightIntensity();

                final String wen = environmentData.getTemperature();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Time time = new Time();
                        time.setToNow();
                        String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
                        environmentDao1.addCarMessage(wen,shi,co2,pm,time_show_string,sun);
                    }
                });
            }
        });

    }
}
