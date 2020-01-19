package com.lenovo.smarttraffic.ui.activity.item29activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.ETCData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ETCmoneyActivity extends AppCompatActivity {

    private Button mBtn;
    private ImageView mBack;
    private TextView mYuee;
    private EditText mCarId;
    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private List<ETCData> carDatalist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etcmoney);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();
    }

    private void initevent() {


        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ETCmoneyActivity.this,Item29Activity.class));
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getyue();
            }
        });


    }

    private void initview() {
        mCarId = findViewById(R.id.item29money_ed);
        mYuee = findViewById(R.id.item29money_yue);
        mBack = findViewById(R.id.item29money_back);
        mBtn = findViewById(R.id.item29money_btn);
    }

    private void getyue() {

                //todo get请求

                mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.ETC_SELECT).build()).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull final IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                        final String jsonData = response.body().string();
                        gson = new Gson();
                        carDatalist = gson.fromJson(jsonData, new TypeToken<List<ETCData>>() {}.getType());
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                int i = Integer.parseInt(mCarId.getText().toString())-1;
                                mYuee.setText(carDatalist.get(i).getCarMoney());
                            }
                        });
                    }
                });

    }
}
