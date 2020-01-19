package com.lenovo.smarttraffic.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.dao.ChangeDao;
import com.lenovo.smarttraffic.database.data.Data;
import com.lenovo.smarttraffic.database.data.LightData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//设置自定义对话框样式
public class MyDialog3 extends Dialog {

    private String s1;
    private SettingListener mSListener = null;
    private final EditText mHonged;
    private final EditText mHuanged;
    private final EditText mLved;
    private final Button mSaveBtn;
    private final String i;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private ChangeDao changeDao;

    private List<Integer> list = new ArrayList<>();

    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private String hong;
    private String huang;
    private String lv;


    public MyDialog3(Context context, int themeResId ,final String i,final List<Integer> list) {
        super(context, themeResId);
        this.i=i;
        this.list=list;

        //为对话框设置布局
        setContentView(R.layout.diydialog3);

        changeDao = new ChangeDao(context);
        mHonged = findViewById(R.id.diylog3_hong);
        mHuanged = findViewById(R.id.diylog3_huang);
        mLved = findViewById(R.id.diylog3_lv);
        mSaveBtn = findViewById(R.id.diylog3_savebtn);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hong = mHonged.getText().toString();
                huang = mHuanged.getText().toString();
                lv = mLved.getText().toString();

                    if (i==null){

                        Log.e("list", "onClick: "+list.size() );
                        for (int j = 0; j < list.size(); j++) {

                            FormBody.Builder builder = new FormBody.Builder();
                            builder.add("roadName", ""+list.get(j));
                            builder.add("redTime",hong);
                            builder.add("greenTime",lv);
                            builder.add("yellowTime",huang);
                            FormBody formBody = builder.build();
                            final int finalJ = j;
                            mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.LIGHT_SELECT).post(formBody).build()).enqueue(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                }
                                @Override
                                public void onResponse(Call call, Response response) throws IOException {

                                   if ( finalJ == (list.size()-1)){
                                       if (mSListener != null) {
                                           mSListener.onSetting(1);

                                       }
                                   }


                                }
                            });
                        }
                        //todo
                    }else {
                        //todo
                        FormBody.Builder builder = new FormBody.Builder();
                        builder.add("roadName", i);
                        builder.add("redTime",hong);
                        builder.add("greenTime",lv);
                        builder.add("yellowTime",huang);
                        FormBody formBody = builder.build();
                        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.LIGHT_SELECT).post(formBody).build()).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                            }
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                if (mSListener != null) {
                                    mSListener.onSetting(1);

                                }
                            }
                        });


                    }
                dismiss();

                }

        });


        findViewById(R.id.diylog3_quitbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();//让对话框消失
            }
        });


    }

    public interface SettingListener {
        public void onSetting(int i);}

    public void setOnSettingListener(SettingListener listener) {
        mSListener = listener;
    }

}
