package com.lenovo.smarttraffic.ui.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.BusData;
import com.lenovo.smarttraffic.database.data.EnvironmentData;

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

public class Item12Activity extends AppCompatActivity {

    private TextView mBusname;
    private TextView mBeginTime;
    private TextView mEndTime;
    private TextView mPeopleNum;
    private Button mBtn;
    private TextView mCar11Num;
    private TextView mCar11Su;
    private TextView mCar11Distance;
    private TextView mCar12Num;
    private TextView mCar12Su;
    private TextView mCar12Distance;
    private TextView mCar21Num;
    private TextView mCar21Su;
    private TextView mCar21Distance;
    private TextView mCar22Num;
    private TextView mCar22Su;
    private TextView mCar22Distance;

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private List<BusData.PlatformsBean> platformsBeanList;

    private TextView mCar11Name;
    private TextView mCar12Name;
    private TextView mCar21Name;
    private TextView mCar22Name;

    private List<Integer> carnumberlist = new ArrayList<>();
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item12);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();

        Timer timer = new Timer();
        //参数1：TimerTask 参数2：定时时间，单位毫秒
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                getNet();

            }

        },0,3000);
    }

    private void initevent() {

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog1 dialog = new MyDialog1(Item12Activity.this,R.style.mydialog,carnumberlist,number);
                WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
                attributes.width=1000;
                attributes.height = 800;
                dialog.show();
            }
        });


    }

    private void initview() {
        mBusname = findViewById(R.id.item12_busname);
        mBeginTime = findViewById(R.id.item12_begintime);
        mEndTime = findViewById(R.id.item12_endtime);
        mPeopleNum = findViewById(R.id.item12_peoplenumber);
        mBtn = findViewById(R.id.item12_xbtn);

        mCar11Num = findViewById(R.id.item12_car11_number);
        mCar11Su = findViewById(R.id.item12_car11_sudu);
        mCar11Distance = findViewById(R.id.item12_car11_distance);

        mCar12Num = findViewById(R.id.item12_car12_number);
        mCar12Su = findViewById(R.id.item12_car12_sudu);
        mCar12Distance = findViewById(R.id.item12_car12_distance);

        mCar21Num = findViewById(R.id.item12_car21_number);
        mCar21Su = findViewById(R.id.item12_car21_sudu);
        mCar21Distance = findViewById(R.id.item12_car21_distance);

        mCar22Num = findViewById(R.id.item12_car22_number);
        mCar22Su = findViewById(R.id.item12_car22_sudu);
        mCar22Distance = findViewById(R.id.item12_car22_distance);

        mCar11Name = findViewById(R.id.item12_car11name);
        mCar12Name = findViewById(R.id.item12_car12name);
        mCar21Name = findViewById(R.id.item12_car21name);
        mCar22Name = findViewById(R.id.item12_car22name);
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
                number = busData.getNumber();
                String busName = busData.getBusName();
                String beginTime = busData.getBeginTime();
                String endTime = busData.getEndTime();
                platformsBeanList = busData.getPlatforms();

                carnumberlist.clear();
                for (int i = 0; i < 15; i++) {
                    carnumberlist.add(platformsBeanList.get(0).getChildBeans().get(i).getNumber());
                }


                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mBeginTime.setText(beginTime);
                        mEndTime.setText(endTime);
                        mBusname.setText(busName);
                        mPeopleNum.setText(""+ number);
                        int j1=platformsBeanList.get(0).getChildBeans().get(0).getDistance();
                        int j2=platformsBeanList.get(0).getChildBeans().get(1).getDistance();
                        int j3=platformsBeanList.get(1).getChildBeans().get(0).getDistance();
                        int j4=platformsBeanList.get(1).getChildBeans().get(1).getDistance();
                        if (j1<j2&&j3<j4){
                            setData1();
                        }else if (j1<j2&&j3>j4){
                            setData2();
                        }else if (j1>j2&&j3<j4){
                            setData3();
                        }else {
                            setData4();
                        }
                    }
                });
            }
        });
    }

    private void setData4() {
        mCar11Name.setText("2号");
        mCar12Num.setText("("+platformsBeanList.get(0).getChildBeans().get(0).getNumber()+")人");
        mCar12Distance.setText("距离站台"+platformsBeanList.get(0).getChildBeans().get(0).getDistance()+"米");
        int i =platformsBeanList.get(0).getChildBeans().get(0).getDistance()/333;
        mCar12Su.setText(""+i+"分钟到达");

        mCar12Name.setText("1号");
        mCar11Num.setText("("+platformsBeanList.get(0).getChildBeans().get(1).getNumber()+")人");
        mCar11Distance.setText("距离站台"+platformsBeanList.get(0).getChildBeans().get(1).getDistance()+"米");
        int i2 =platformsBeanList.get(0).getChildBeans().get(1).getDistance()/333;
        mCar11Su.setText(""+i2+"分钟到达");

        mCar22Name.setText("1号");
        mCar22Num.setText("("+platformsBeanList.get(1).getChildBeans().get(0).getNumber()+")人");
        mCar22Distance.setText("距离站台"+platformsBeanList.get(1).getChildBeans().get(0).getDistance()+"米");
        int i1 =platformsBeanList.get(1).getChildBeans().get(0).getDistance()/333;
        mCar22Su.setText(""+i1+"分钟到达");

        mCar21Name.setText("2号");
        mCar21Num.setText("("+platformsBeanList.get(1).getChildBeans().get(1).getNumber()+")人");
        mCar21Distance.setText("距离站台"+platformsBeanList.get(1).getChildBeans().get(1).getDistance()+"米");
        int i3 =platformsBeanList.get(1).getChildBeans().get(1).getDistance()/333;
        mCar21Su.setText(""+i3+"分钟到达");
    }

    private void setData3() {

        mCar11Name.setText("2号");
        mCar12Num.setText("("+platformsBeanList.get(0).getChildBeans().get(0).getNumber()+")人");
        mCar12Distance.setText("距离站台"+platformsBeanList.get(0).getChildBeans().get(0).getDistance()+"米");
        int i =platformsBeanList.get(0).getChildBeans().get(0).getDistance()/333;
        mCar12Su.setText(""+i+"分钟到达");

        mCar12Name.setText("1号");
        mCar11Num.setText("("+platformsBeanList.get(0).getChildBeans().get(1).getNumber()+")人");
        mCar11Distance.setText("距离站台"+platformsBeanList.get(0).getChildBeans().get(1).getDistance()+"米");
        int i2 =platformsBeanList.get(0).getChildBeans().get(1).getDistance()/333;
        mCar11Su.setText(""+i2+"分钟到达");


        mCar21Num.setText("("+platformsBeanList.get(1).getChildBeans().get(0).getNumber()+")人");
        mCar21Distance.setText("距离站台"+platformsBeanList.get(1).getChildBeans().get(0).getDistance()+"米");
        int i1 =platformsBeanList.get(1).getChildBeans().get(0).getDistance()/333;
        mCar21Su.setText(""+i1+"分钟到达");

        mCar22Num.setText("("+platformsBeanList.get(1).getChildBeans().get(1).getNumber()+")人");
        mCar22Distance.setText("距离站台"+platformsBeanList.get(1).getChildBeans().get(1).getDistance()+"米");
        int i3 =platformsBeanList.get(1).getChildBeans().get(1).getDistance()/333;
        mCar22Su.setText(""+i3+"分钟到达");
    }

    private void setData2() {
        mCar11Num.setText("("+platformsBeanList.get(0).getChildBeans().get(0).getNumber()+")人");
        mCar11Distance.setText("距离站台"+platformsBeanList.get(0).getChildBeans().get(0).getDistance()+"米");
        int i =platformsBeanList.get(0).getChildBeans().get(0).getDistance()/333;
        mCar11Su.setText(""+i+"分钟到达");

        mCar12Num.setText("("+platformsBeanList.get(0).getChildBeans().get(1).getNumber()+")人");
        mCar12Distance.setText("距离站台"+platformsBeanList.get(0).getChildBeans().get(1).getDistance()+"米");
        int i2 =platformsBeanList.get(0).getChildBeans().get(1).getDistance()/333;
        mCar12Su.setText(""+i2+"分钟到达");

        mCar22Name.setText("1号");
        mCar22Num.setText("("+platformsBeanList.get(1).getChildBeans().get(0).getNumber()+")人");
        mCar22Distance.setText("距离站台"+platformsBeanList.get(1).getChildBeans().get(0).getDistance()+"米");
        int i1 =platformsBeanList.get(1).getChildBeans().get(0).getDistance()/333;
        mCar22Su.setText(""+i1+"分钟到达");

        mCar21Name.setText("2号");
        mCar21Num.setText("("+platformsBeanList.get(1).getChildBeans().get(1).getNumber()+")人");
        mCar21Distance.setText("距离站台"+platformsBeanList.get(1).getChildBeans().get(1).getDistance()+"米");
        int i3 =platformsBeanList.get(1).getChildBeans().get(1).getDistance()/333;
        mCar21Su.setText(""+i3+"分钟到达");
    }

    private void setData1(){
        mCar11Num.setText("("+platformsBeanList.get(0).getChildBeans().get(0).getNumber()+")人");
        mCar11Distance.setText("距离站台"+platformsBeanList.get(0).getChildBeans().get(0).getDistance()+"米");
        int i =platformsBeanList.get(0).getChildBeans().get(0).getDistance()/333;
        mCar11Su.setText(""+i+"分钟到达");

        mCar12Num.setText("("+platformsBeanList.get(0).getChildBeans().get(1).getNumber()+")人");
        mCar12Distance.setText("距离站台"+platformsBeanList.get(0).getChildBeans().get(1).getDistance()+"米");
        int i2 =platformsBeanList.get(0).getChildBeans().get(1).getDistance()/333;
        mCar12Su.setText(""+i2+"分钟到达");

        mCar21Num.setText("("+platformsBeanList.get(1).getChildBeans().get(0).getNumber()+")人");
        mCar21Distance.setText("距离站台"+platformsBeanList.get(1).getChildBeans().get(0).getDistance()+"米");
        int i1 =platformsBeanList.get(1).getChildBeans().get(0).getDistance()/333;
        mCar21Su.setText(""+i1+"分钟到达");

        mCar22Num.setText("("+platformsBeanList.get(1).getChildBeans().get(1).getNumber()+")人");
        mCar22Distance.setText("距离站台"+platformsBeanList.get(1).getChildBeans().get(1).getDistance()+"米");
        int i3 =platformsBeanList.get(1).getChildBeans().get(1).getDistance()/333;
        mCar22Su.setText(""+i3+"分钟到达");
    }
}
