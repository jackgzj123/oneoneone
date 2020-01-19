package com.lenovo.smarttraffic.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.dao.ChangeDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//设置自定义对话框样式
public class MyDialog6 extends Dialog {


    private SettingListener mSListener = null;
    private final EditText mHonged;
    private final EditText mHuanged;
    private final EditText mLved;
    private final Button mSaveBtn;
    private final String i;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private String hong;
    private String huang;
    private String lv;


    public MyDialog6(Context context, int themeResId , final String i) {
        super(context, themeResId);
        this.i=i;

        //为对话框设置布局
        setContentView(R.layout.diydialog6);

        mHonged = findViewById(R.id.diylog3_hong);
        mHuanged = findViewById(R.id.diylog3_huang);
        mLved = findViewById(R.id.diylog3_lv);
        mSaveBtn = findViewById(R.id.diylog3_savebtn);

        mHonged.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //禁止输入51-99之间的整数  通过for循环
                for(int i = 31;i<=99;i++) {
                    if (mHonged.getText().toString().equals(String.valueOf(i))) {
                        mHonged.setText("");
                    }
                }
            }
        });

        mHuanged.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //禁止输入51-99之间的整数  通过for循环
                for(int i = 31;i<=99;i++) {
                    if (mHuanged.getText().toString().equals(String.valueOf(i))) {
                        mHuanged.setText("");
                    }
                }
            }
        });

        mLved.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //禁止输入51-99之间的整数  通过for循环
                for(int i = 31;i<=99;i++) {
                    if (mLved.getText().toString().equals(String.valueOf(i))) {
                        mLved.setText("");
                    }
                }
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hong = mHonged.getText().toString();
                huang = mHuanged.getText().toString();
                lv = mLved.getText().toString();

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
                                    mSListener.onSetting(i,hong,huang,lv);

                                }
                            }
                        });



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
        public void onSetting(String i,String hong,String huang,String lv);}

    public void setOnSettingListener(SettingListener listener) {
        mSListener = listener;
    }

}
