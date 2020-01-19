package com.lenovo.smarttraffic.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//设置自定义对话框样式
public class MyDialog7 extends Dialog {


    private SettingListener mSListener = null;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private String hong;
    private String huang;
    private String lv;
    private final TextView mSaveBtn;
    private final DatePicker datePicker;
    private int i;


    public MyDialog7(Context context, int themeResId ,int i ) {
        super(context, themeResId);
        this.i = i;

        //为对话框设置布局
        setContentView(R.layout.diydialog7);

        mSaveBtn = findViewById(R.id.dialog7_sure);
        datePicker = findViewById(R.id.dialog7_datepicker);


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int dayOfMonth = datePicker.getDayOfMonth();
                //todo
                if (mSListener != null) {
                    mSListener.onSetting(i,year,month,dayOfMonth);

                }

                dismiss();

                }

        });


        findViewById(R.id.dialog7_quit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();//让对话框消失
            }
        });


    }

    public interface SettingListener {
        public void onSetting(int i,int year,int month,int day);}

    public void setOnSettingListener(SettingListener listener) {
        mSListener = listener;
    }

}
