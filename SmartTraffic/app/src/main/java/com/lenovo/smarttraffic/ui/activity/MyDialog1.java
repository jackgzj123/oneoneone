package com.lenovo.smarttraffic.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.ui.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

//设置自定义对话框样式
public class MyDialog1 extends Dialog {


    private List<Integer> carnumberlist = new ArrayList<>();
    private String car_name;
    private String s1;
    private SettingListener mSListener = null;
    private final ListView listView;
    private int number;

    public MyDialog1(Context context, int themeResId, List<Integer> carnumberlist,int number ) {
        super(context, themeResId);
        this.carnumberlist = carnumberlist;
        this.number=number;

        //为对话框设置布局
        setContentView(R.layout.diydialog1);

        listView = findViewById(R.id.diylog1_listview);

        listView.setAdapter(new ListAdapter(getContext(),carnumberlist));




        findViewById(R.id.diylog1_quitbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();//让对话框消失
            }
        });
        TextView total = findViewById(R.id.diyloge1_total);
        total.setText(""+number);

    }

    public interface SettingListener {
        public void onSetting(String name, int i);}

    public void setOnSettingListener(SettingListener listener) {
        mSListener = listener;
    }
}
