package com.lenovo.smarttraffic.ui.activity;

import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.bean.okhttp.IOkHttpListener;
import com.lenovo.smarttraffic.bean.okhttp.OkUntil;
import com.lenovo.smarttraffic.database.dao.CarDao;
import com.lenovo.smarttraffic.database.data.Car;
import com.lenovo.smarttraffic.database.data.Data;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item4Activity extends AppCompatActivity {

    private Button mSearchbtn;
    private Button mPaybtn;
    private TextView mYueTv;
    private Spinner mSpinner;
    private String s;
    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private String s1;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private CarDao carDao ;
    private EditText mCongEdt;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item4);

        dialog = new ProgressDialog(this);
        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dialog.setCancelable(false);
        dialog.setMessage("加载中。。。");
        dialog.show();
        initview();

        getyue();

        getchongzhi();

    }

    private void getchongzhi() {
        mPaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //todo 添加数据库数据
                Car car = new Car(s,Integer.valueOf(mCongEdt.getText().toString()));
                Time time = new Time();
                time.setToNow();
                String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
                carDao.addCarMessage(car,time_show_string);
            //todo 更改余额信息
                int yuechong = (int)Double.parseDouble(mYueTv.getText().toString())+Integer.valueOf(mCongEdt.getText().toString());
                mYueTv.setText(""+yuechong+"元");
            }
        });

    }

    private void getyue() {
        if (dialog.isShowing()){
            dialog.dismiss();
        }
        mSearchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String,String> map = new HashMap<>();
                map.put("carId",s);
                OkUntil.getInstance().post_Asyn(UrlClass.CAR_SELECT, map, new IOkHttpListener<Data>() {

                    @Override
                    public void onJson(Data data) {
                        s1 = data.getResult();
                        mYueTv.setText(s1);
                    }

                    @Override
                    public void onErr(String err) {

                    }
                });
//                //todo post请求

//                FormBody.Builder builder = new FormBody.Builder();
//                builder.add("carId",s);
//                FormBody formBody = builder.build();
//                mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody).build()).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(@NotNull Call call, @NotNull final IOException e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//
//                        final String jsonData = response.body().string();
//                        Data data = new Gson().fromJson(jsonData,Data.class);
//                        s1 = data.getResult();
//                        mHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                mYueTv.setText(s1);
//                            }
//                        });
//                    }
//                });

            }
        });
    }


    private void initview() {
        mSearchbtn = findViewById(R.id.item4btn1);
        mPaybtn = findViewById(R.id.item4btn2);
        mYueTv = findViewById(R.id.item4_yue);
        mSpinner = findViewById(R.id.item4_spinner);
        mCongEdt = findViewById(R.id.item4_chongzhi);
        carDao = new CarDao(this);


        final ArrayList<String> arrayList = new ArrayList<String>();//创建数组列表 用来存放以后要显示的内容
        //添加要显示的内容
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arrayList);//创建适配器  this--上下文  android.R.layout.simple_spinner_item--显示的模板   arrayList--显示的内容
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//设置下拉之后的布局的样式 这里采用的是系统的一个布局
        mSpinner.setAdapter(arrayAdapter);//将适配器给下拉框
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//当改变下拉框的时候会触发

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//改变内容的时候
                s=arrayList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {//没有改变的时候

            }
        });


    }
}
