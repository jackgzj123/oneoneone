package com.lenovo.smarttraffic.ui.fragment.item45fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.BusData;
import com.lenovo.smarttraffic.database.data.EnvironmentData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author：gzj
 * @CreateDate: 2019/12/21
 */
public class TwoFragment extends Fragment {

    private TextView mCar1Distance;
    private TextView mCar2Distance;
    private TextView mCo2;
    private TextView mPm2;
    private TextView mShi;
    private TextView mWen;
    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private List<BusData.PlatformsBean> platformsBeanList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.item45activity_twofragment,null);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mCar1Distance = view.findViewById(R.id.item45activity_twofragment_car1);
        mCar2Distance = view.findViewById(R.id.item45activity_twofragment_car2);
        mCo2 = view.findViewById(R.id.item45activity_twofragment_CO2);
        mPm2 = view.findViewById(R.id.item45activity_twofragment_pm2);
        mShi = view.findViewById(R.id.item45activity_twofragment_shi);
        mWen = view.findViewById(R.id.item45activity_twofragment_wen);
        ImageView mImg = view.findViewById(R.id.lgb2_back);
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getCarNet();
                getEnvirNet();
            }
        },0,3000);

        return view;
    }
    private void getCarNet() {

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
                platformsBeanList = busData.getPlatforms();
                final int car1 = platformsBeanList.get(0).getChildBeans().get(0).getDistance();
                final int car2 = platformsBeanList.get(0).getChildBeans().get(1).getDistance();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        mCar1Distance.setText(car1+"m");
                        mCar2Distance.setText(car2+"m");

                    }
                });
            }
        });
    }

    private void getEnvirNet() {

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

                final String wen = environmentData.getTemperature();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mWen.setText(wen+"℃");
                        mCo2.setText(co2+"PPM");
                        mPm2.setText(pm+"μg/m3");
                        mShi.setText(shi+"RH");
                    }
                });
            }
        });

    }
}
