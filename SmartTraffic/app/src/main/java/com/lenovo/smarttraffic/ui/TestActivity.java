package com.lenovo.smarttraffic.ui;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.TestData;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient = new OkHttpClient();
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView textView = findViewById(R.id.test_tv);
        okHttpClient.newCall(new Request.Builder().url("http://localhost:3000/users").build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("jjj", "11");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String s = response.body().toString();
                Gson gson = new Gson();
                TestData testData = gson.fromJson(s, TestData.class);
                Log.e("jjj", "111111111");
            }
        });
    }
}
