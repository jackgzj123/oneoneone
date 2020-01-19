package com.lenovo.smarttraffic.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.dao.Car1Dao;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//设置自定义对话框样式
public class MyDialog5 extends Dialog {

    private final EditText mEd;
    private String s;
    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private Car1Dao car1Dao;
    public MyDialog5(Context context, int themeResId ,final String s) {
        super(context, themeResId);
        car1Dao = new Car1Dao(context);
        this.s =s ;

        //为对话框设置布局
        setContentView(R.layout.diydialog5);

        mEd = findViewById(R.id.dialog5_ed);


        mEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                //禁止输入51-99之间的整数  通过for循环
                for(int i = 51;i<=99;i++) {
                    if (mEd.getText().toString().equals(String.valueOf(i))) {
                        mEd.setText("");
                    }
                }
            }
        });

        findViewById(R.id.diylog5_quitbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();//让对话框消失
            }
        });

        findViewById(R.id.diylog5_chongbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String s1= mEd.getText().toString();
                getchong(s1,s);
                Time time = new Time();
                time.setToNow();
                String time_show_string = "" + time.year + "-" + (time.month+1) + "-" + time.monthDay + " " + time.hour + ":" + time.minute + ":" + time.second;
                car1Dao.addCarMessage(s,s1,time_show_string);
                Toast.makeText(getContext(),s+"号小车充值"+mEd.getText().toString()+"元成功",Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });
    }

    private void getchong(final String carmoney,final String carnumber) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("money",carmoney);
        builder.add("carId",carnumber);
        FormBody formBody = builder.build();
        mOkHttpUtil.newCall(new Request.Builder().url(UrlClass.CAR_SELECT).post(formBody).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

            }
        });
    }


}
