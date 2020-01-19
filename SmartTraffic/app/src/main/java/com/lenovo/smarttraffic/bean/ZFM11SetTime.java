package com.lenovo.smarttraffic.bean;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.database.data.Data;


import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZFM11SetTime {
    private static final String url = "http://192.168.43.238:8088/transportservice/action/SetTrafficLightConfig.do";
    private static List<String> roadNames = new ArrayList<>();
    private static String reds, greens, yellows;
    private static IZfm1IFOK iZfm1IFOKs;
    private static OkHttpClient mOkHttpUtil = new OkHttpClient();
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void setTime(List<String> roadName, String red, String green, String yellow, IZfm1IFOK iZfm1IFOK) {
        roadNames = roadName;
        reds = red;
        yellows = yellow;
        greens = green;
        iZfm1IFOKs = iZfm1IFOK;

        Log.e("===>", roadNames.toString());
        Log.e("===>", yellows);

        setTime(1);

    }

    private static void setTime(final int i) {


        //todo post请求
        FormBody.Builder builder = new FormBody.Builder();

        builder.add("roadName", roadNames.get(i-1));
        builder.add("redTime", greens);
        builder.add("yellowTime", yellows);
        builder.add("greenTime", reds);
        FormBody formBody = builder.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.LIGHT_SELECT).post(formBody).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
                iZfm1IFOKs.failure();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (i < roadNames.size()) {
                            int temp = i;
                            temp++;
                            setTime(temp);

                        } else {
                            iZfm1IFOKs.success();
                        }
                    }
                });
            }
        });




    }

    public interface IZfm1IFOK {
        void success();

        void failure();
    }
}
