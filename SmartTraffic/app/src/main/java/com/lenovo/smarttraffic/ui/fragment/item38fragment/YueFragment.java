package com.lenovo.smarttraffic.ui.fragment.item38fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.dao.Car1Dao;
import com.lenovo.smarttraffic.database.data.Data;
import com.lenovo.smarttraffic.ui.activity.Item11aActivity;
import com.lenovo.smarttraffic.ui.activity.MyDialog5;

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
 * @CreateDate: 2019/12/21
 */
public class YueFragment extends Fragment {

    private TextView mCar1Zhuangt;
    private TextView mCar1Yue;
    private TextView mCar2Zhuangt;
    private TextView mCar2Yue;
    private TextView mCar3Zhuangt;
    private TextView mCar3Yue;
    private TextView mCar4Zhuangt;
    private TextView mCar4Yue;
    private LinearLayout mCar1Background;
    private LinearLayout mCar2Background;
    private LinearLayout mCar3Background;
    private LinearLayout mCar4Background;

    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item38_yuefragment,null);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mCar1Zhuangt = view.findViewById(R.id.item38_yuefragment_car1zhuangtai);
        mCar1Yue = view.findViewById(R.id.item38_yuefragment_car1yue);
        mCar2Zhuangt = view.findViewById(R.id.item38_yuefragment_car2zhuangtai);
        mCar2Yue = view.findViewById(R.id.item38_yuefragment_car2yue);
        mCar3Zhuangt = view.findViewById(R.id.item38_yuefragment_car3zhuangtai);
        mCar3Yue = view.findViewById(R.id.item38_yuefragment_car3yue);
        mCar4Zhuangt = view.findViewById(R.id.item38_yuefragment_car4zhuangtai);
        mCar4Yue = view.findViewById(R.id.item38_yuefragment_car4yue);
        mCar1Background = view.findViewById(R.id.item38_yuefragment_lin1);
        mCar2Background = view.findViewById(R.id.item38_yuefragment_lin2);
        mCar3Background = view.findViewById(R.id.item38_yuefragment_lin3);
        mCar4Background = view.findViewById(R.id.item38_yuefragment_lin4);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getcar1();
                getcar2();
                getcar3();
                getcar4();
            }
        },0,5000);


        chongcar();


        return view;
    }

    private void chongcar() {

        mCar1Yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog5 dialog = new MyDialog5(getActivity(),R.style.mydialog,"1");
                dialog.show();
            }
        });

        mCar2Yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog5 dialog = new MyDialog5(getActivity(),R.style.mydialog,"2");
                dialog.show();
            }
        });

        mCar3Yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog5 dialog = new MyDialog5(getActivity(),R.style.mydialog,"3");
                dialog.show();
            }
        });

        mCar4Yue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog5 dialog = new MyDialog5(getActivity(),R.style.mydialog,"4");
                dialog.show();
            }
        });

    }


    private void getcar1() {
        FormBody.Builder builder2 = new FormBody.Builder();
        builder2.add("carId","1");
        FormBody formBody2 = builder2.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody2).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                Data data = new Gson().fromJson(jsonData,Data.class);
                final String s1 = data.getResult();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCar1Yue.setText(s1);
                        if (Float.parseFloat(s1)>100.0){
                            mCar1Zhuangt.setText("警告");
                            mCar1Background.setBackgroundColor(Color.rgb(255,0,0));
                        }else {
                            mCar1Zhuangt.setText("正常");
                            mCar1Background.setBackgroundColor(Color.rgb(0,255,0));
                        }
                    }
                });

            }
        });
    }

    private void getcar2() {
        FormBody.Builder builder2 = new FormBody.Builder();
        builder2.add("carId","2");
        FormBody formBody2 = builder2.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody2).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                Data data = new Gson().fromJson(jsonData,Data.class);
                final String s1 = data.getResult();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCar2Yue.setText(s1);
                        if (Float.parseFloat(s1)>100.0){
                            mCar2Zhuangt.setText("警告");
                            mCar2Background.setBackgroundColor(Color.rgb(255,0,0));
                        }else {
                            mCar2Zhuangt.setText("正常");
                            mCar2Background.setBackgroundColor(Color.rgb(0,255,0));
                        }
                    }
                });
            }
        });
    }

    private void getcar3() {
        FormBody.Builder builder2 = new FormBody.Builder();
        builder2.add("carId","3");
        FormBody formBody2 = builder2.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody2).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                Data data = new Gson().fromJson(jsonData,Data.class);
                final String s1 = data.getResult();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCar3Yue.setText(s1);
                        if (Float.parseFloat(s1)>100.0){
                            mCar3Zhuangt.setText("警告");
                            mCar3Background.setBackgroundColor(Color.rgb(255,0,0));
                        }else {
                            mCar3Zhuangt.setText("正常");
                            mCar3Background.setBackgroundColor(Color.rgb(0,255,0));
                        }
                    }
                });
            }
        });
    }

    private void getcar4() {
        FormBody.Builder builder2 = new FormBody.Builder();
        builder2.add("carId","4");
        FormBody formBody2 = builder2.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody2).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                Data data = new Gson().fromJson(jsonData,Data.class);
                final String s1 = data.getResult();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mCar4Yue.setText(s1);
                        if (Float.parseFloat(s1)>100.0){
                            mCar4Zhuangt.setText("警告");
                            mCar4Background.setBackgroundColor(Color.rgb(255,0,0));
                        }else {
                            mCar4Zhuangt.setText("正常");
                            mCar4Background.setBackgroundColor(Color.rgb(0,255,0));
                        }
                    }
                });
            }
        });
    }


}
