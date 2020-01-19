package com.lenovo.smarttraffic.ui.activity.item54activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.bean.okhttp.IOkHttpListener;
import com.lenovo.smarttraffic.bean.okhttp.OkUntil;
import com.lenovo.smarttraffic.database.dao.ETC1dao;
import com.lenovo.smarttraffic.database.data.ETCData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

import okhttp3.OkHttpClient;

public class Item54PayActivity extends AppCompatActivity {

    private TextView m10tV;
    private TextView m50tV;
    private TextView m100tV;
    private Button mBtn;
    private EditText mEd;
    private int i1 = 0;
    private int i2 = 0;
    private int i3 = 0;
    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private ImageView mBackImg;
    private ETC1dao etCdao;
    private Spinner mSpinner;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item54_pay);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initview();

        initevent();
    }

    private void initevent() {

        final ArrayList<String> arrayList = new ArrayList<String>();//创建数组列表 用来存放以后要显示的内容
        //添加要显示的内容
        arrayList.add("辽A10001");
        arrayList.add("辽A10002");
        arrayList.add("辽A10003");
        arrayList.add("辽A10004");
        arrayList.add("辽A10005");

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


        m10tV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i1 == 0){
                    m10tV.setBackgroundResource(R.drawable.edtt6);
                    i1 =1;
                }else {
                    m10tV.setBackgroundResource(R.drawable.edtt5);
                    i1 = 0;
                }
            }
        });

        m50tV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i2 == 0){
                    m50tV.setBackgroundResource(R.drawable.edtt6);
                    i2 =1;
                }else {
                    m50tV.setBackgroundResource(R.drawable.edtt5);
                    i2 = 0;
                }
            }
        });

        m100tV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i3 == 0){
                    m100tV.setBackgroundResource(R.drawable.edtt6);
                    i3 =1;
                }else {
                    m100tV.setBackgroundResource(R.drawable.edtt5);
                    i3 = 0;
                }
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (i1 == 0 && i2 == 0&& i3 == 0){
                    setnet(mEd.getText().toString(),s);
                }else if (i1 == 1){
                    setnet("10",s);
                }else if (i2 == 1){
                    setnet("50",s);
                }else {
                    setnet("100",s);
                }
            }
        });

        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setnet(final String carmoney,final String carnumber) {

        HashMap<String,String> map = new HashMap<>();
        map.put("carMoney",carmoney);
        map.put("carNumber",carnumber);
        OkUntil.getInstance().post_Asyn(UrlClass.ETC_SELECT, map, new IOkHttpListener<ETCData>() {
            @Override
            public void onJson(ETCData etcData) {
                setdata(carmoney,carnumber);
                Toast.makeText(Item54PayActivity.this,"充值成功",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onErr(String err) {

            }
        });
        //todo post请求
//        FormBody.Builder builder = new FormBody.Builder();
//        builder.add("carMoney",carmoney);
//        builder.add("carNumber",carnumber);
//        FormBody formBody = builder.build();
//        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.ETC_SELECT).post(formBody).build()).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull final IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//
//
//                setdata(carmoney,carnumber);
//                Toast.makeText(Item54PayActivity.this,"充值成功",Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    private void setdata(String carmoney,String carnumber) {
        Time time = new Time();
        time.setToNow();
        final String time_show_string = "" + time.year + "." + (time.month+1) + "." + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
        final String time_date = "" + time.year + "." + (time.month+1) + "." + time.monthDay;
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if("1".equals(mWay)){
            mWay ="天";
        }else if("2".equals(mWay)){
            mWay ="一";
        }else if("3".equals(mWay)){
            mWay ="二";
        }else if("4".equals(mWay)){
            mWay ="三";
        }else if("5".equals(mWay)){
            mWay ="四";
        }else if("6".equals(mWay)){
            mWay ="五";
        }else if("7".equals(mWay)){
            mWay ="六";
        }
        if (carnumber.equals("辽A10001")){
            //etcData.setCarId(1);
            etCdao.addCarMessage(time_date,"辽A10001",carmoney,time_show_string,mWay);
        }else if (carnumber.equals("辽A10002")){
            //etcData.setCarId(2);
            etCdao.addCarMessage(time_date,"辽A10002",carmoney,time_show_string,mWay);
        }else if (carnumber.equals("辽A10003")){
            //etcData.setCarId(3);
            etCdao.addCarMessage(time_date,"辽A10003",carmoney,time_show_string,mWay);
        }else if (carnumber.equals("辽A10004")){
            //etcData.setCarId(4);
            etCdao.addCarMessage(time_date,"辽A10004",carmoney,time_show_string,mWay);
        }else {
            //etcData.setCarId(5);
            etCdao.addCarMessage(time_date,"辽A10005",carmoney,time_show_string,mWay);
        }


    }

    private void initview() {

        etCdao = new ETC1dao(this);
        m10tV = findViewById(R.id.item54_pay_10yuan);
        m50tV = findViewById(R.id.item54_pay_50yuan);
        m100tV = findViewById(R.id.item54_pay_100yuan);
        mEd = findViewById(R.id.item54_pay_edt);
        mBtn = findViewById(R.id.item54_pay_btn);
        mBackImg = findViewById(R.id.lgb2_back);
        mSpinner = findViewById(R.id.item54_pay_spinner);

    }
}
