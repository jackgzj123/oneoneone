package com.lenovo.smarttraffic.ui.activity.item54activity;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.bean.okhttp.IOkHttpListener;
import com.lenovo.smarttraffic.bean.okhttp.OkUntil;
import com.lenovo.smarttraffic.database.data.CarData;
import com.lenovo.smarttraffic.database.data.ETCData;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class Item54YueActivity extends AppCompatActivity {

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private List<CarData> carDatalist = new ArrayList<>();
    private TextView mCarname1;
    private TextView mCaryue1;
    private TextView mCarname2;
    private TextView mCaryue2;
    private TextView mCarname3;
    private TextView mCaryue3;
    private TextView mCarname4;
    private TextView mCaryue4;
    private TextView mCarname5;
    private TextView mCaryue5;
    private ImageView mBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item54_yue);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        initview();
        initevent();
    }

    private void initview() {


        mCarname1 = findViewById(R.id.item54_yue_car1name);
        mCaryue1 = findViewById(R.id.item54_yue_car1yue);


        mCarname2 = findViewById(R.id.item54_yue_car2name);
        mCaryue2 = findViewById(R.id.item54_yue_car2yue);


        mCarname3 = findViewById(R.id.item54_yue_car3name);
        mCaryue3 = findViewById(R.id.item54_yue_car3yue);


        mCarname4 = findViewById(R.id.item54_yue_car4name);
        mCaryue4 = findViewById(R.id.item54_yue_car4yue);


        mCarname5 = findViewById(R.id.item54_yue_car5name);
        mCaryue5 = findViewById(R.id.item54_yue_car5yue);

        mBack = findViewById(R.id.lgb2_back);
    }

    private void initevent() {

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        OkUntil.getInstance().post_Asyn(UrlClass.ETC_SELECT, null, new IOkHttpListener<ETCData>() {

            @Override
            public void onJson(ETCData etcData) {
                mCarname1.setText(carDatalist.get(0).getCarNumber());
                mCarname2.setText(carDatalist.get(1).getCarNumber());
                mCarname3.setText(carDatalist.get(2).getCarNumber());
                mCarname4.setText(carDatalist.get(3).getCarNumber());
                mCarname5.setText(carDatalist.get(4).getCarNumber());

                mCaryue1.setText(carDatalist.get(0).getCarMoney()+"元");
                mCaryue2.setText(carDatalist.get(1).getCarMoney()+"元");
                mCaryue3.setText(carDatalist.get(2).getCarMoney()+"元");
                mCaryue4.setText(carDatalist.get(3).getCarMoney()+"元");
                mCaryue5.setText(carDatalist.get(4).getCarMoney()+"元");
            }

            @Override
            public void onErr(String err) {

            }
        });
        //todo get请求
//        mOkhttp.newCall(new Request.Builder().url(UrlClass.ETC_SELECT).build()).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//
//                final String jsonData = response.body().string();
//                gson = new Gson();
//                carDatalist = gson.fromJson(jsonData, new TypeToken<List<CarData>>() {
//                }.getType());
//
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        mCarname1.setText(carDatalist.get(0).getCarNumber());
//                        mCarname2.setText(carDatalist.get(1).getCarNumber());
//                        mCarname3.setText(carDatalist.get(2).getCarNumber());
//                        mCarname4.setText(carDatalist.get(3).getCarNumber());
//                        mCarname5.setText(carDatalist.get(4).getCarNumber());
//
//                        mCaryue1.setText(carDatalist.get(0).getCarMoney()+"元");
//                        mCaryue2.setText(carDatalist.get(1).getCarMoney()+"元");
//                        mCaryue3.setText(carDatalist.get(2).getCarMoney()+"元");
//                        mCaryue4.setText(carDatalist.get(3).getCarMoney()+"元");
//                        mCaryue5.setText(carDatalist.get(4).getCarMoney()+"元");
//
//                    }
//                });
//            }
//        });
    }

}
