package com.lenovo.smarttraffic.ui.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.bean.ZFM11Dialog;
import com.lenovo.smarttraffic.bean.ZFM11Entity;
import com.lenovo.smarttraffic.database.data.LightData;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Item13aActivity extends AppCompatActivity {

    private Spinner mSpinner;
    private Button mBtn;
    private Button mPlszBtn;
    private ListView mListview;

    private List<ZFM11Entity> zfm1EntityList;
    private List<ZFM11Entity> zfm1EntityListTemp = new ArrayList<>();

    private ZFM1Adapter adapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private List<LightData> lightDatalist = new ArrayList<>();
    private OkHttpClient mOkhttp = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item13);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initview();

        initEvents();

    }

    private void initview() {
        mSpinner = findViewById(R.id.item13_spinner);
        mBtn = findViewById(R.id.item13_btn);
        mPlszBtn = findViewById(R.id.item13_plsz);
        mListview = findViewById(R.id.item13_listview);

        String[] spData = {
                "路口升序",
                "路口降序",
                "红灯升序",
                "红灯降序",
                "绿灯升序",
                "绿灯降序",
                "黄灯升序",
                "黄灯降序",
        };

        mSpinner.setAdapter(new ArrayAdapter<>(Item13aActivity.this,R.layout.support_simple_spinner_dropdown_item,spData));

        zfm1EntityList = new ArrayList<>();


        adapter = new ZFM1Adapter();
        mListview.setAdapter(adapter);
    }

    private void initEvents() {
        zfm1EntityListTemp.clear();
        getData(1);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seekSort();
            }
        });
        mPlszBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAll();
            }
        });
    }

    private void setAll() {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < mListview.getChildCount(); i++) {
            View view = mListview.getChildAt(i);
            CheckBox checkBox = view.findViewById(R.id.zfm_1_lv_checkBox);
            if (checkBox.isChecked()) {
                TextView tvRoad = view.findViewById(R.id.zfm_1_lv_roadName);
                stringList.add(tvRoad.getText().toString().trim());
            }
        }

        if (stringList.size() == 0) {
            Toast.makeText(this, "请选择要设置的路口", Toast.LENGTH_SHORT).show();
            return;
        }

        ZFM11Dialog dialog = new ZFM11Dialog(Item13aActivity.this, stringList);
        dialog.show();
        dialog.setSetOk(new ZFM11Dialog.setOk() {
            @Override
            public void success() {
                zfm1EntityListTemp.clear();
                getData(1);

            }

            @Override
            public void failure() {

            }
        });

    }

    private void seekSort() {

        Collections.sort(zfm1EntityList, new Comparator<ZFM11Entity>() {
            @Override
            public int compare(ZFM11Entity zfm1Entity, ZFM11Entity t1) {
                int ans = 0;
                switch (String.valueOf(mSpinner.getSelectedItem())) {
                    case "路口升序":
                        ans = Integer.valueOf(zfm1Entity.getRoadName()) - Integer.valueOf(t1.getRoadName());
                        break;
                    case "路口降序":
                        ans = -(Integer.valueOf(zfm1Entity.getRoadName()) - Integer.valueOf(t1.getRoadName()));
                        break;
                    case "红灯升序":
                        ans = zfm1Entity.getRed() - t1.getRed();
                        break;
                    case "红灯降序":
                        ans = -(zfm1Entity.getRed() - t1.getRed());
                        break;
                    case "绿灯升序":
                        ans = zfm1Entity.getGreen() - t1.getGreen();
                        break;
                    case "绿灯降序":
                        ans = -(zfm1Entity.getGreen() - t1.getGreen());
                        break;
                    case "黄灯升序":
                        ans = zfm1Entity.getYellow() - t1.getYellow();
                        break;
                    case "黄灯降序":
                        ans = -(zfm1Entity.getYellow() - t1.getYellow());
                        break;
                }
                return ans;
            }
        });

        adapter.notifyDataSetChanged();
    }
    private void getData(final int i) {
        mOkhttp.newCall(new Request.Builder().url(UrlClass.LIGHT_SELECT).build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                gson = new Gson();
                lightDatalist = gson.fromJson(jsonData, new TypeToken<List<LightData>>() {}.getType());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                        zfm1EntityListTemp.add(new ZFM11Entity(i + "", lightDatalist.get(i-1).getRedTime(), lightDatalist.get(i-1).getGreenTime(), lightDatalist.get(i-1).getYellowTime()));
                        int temp = i;
                        if (temp < 5) {
                            temp++;
                            getData(temp);
                        } else {
                            zfm1EntityList.clear();
                            zfm1EntityList.addAll(zfm1EntityListTemp);
                            adapter.notifyDataSetChanged();

                        }
                    }
                });
            }
        });

    }

    class ZFM1Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return zfm1EntityList.size();
        }

        @Override
        public Object getItem(int i) {
            return zfm1EntityList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = LayoutInflater.from(Item13aActivity.this).inflate(R.layout.zfm_11_lv, null);

            final TextView tvRoad = view.findViewById(R.id.zfm_1_lv_roadName);
            TextView tvRed = view.findViewById(R.id.zfm_1_lv_red);
            TextView tvYellow = view.findViewById(R.id.zfm_1_lv_yellow);
            TextView tvGreen = view.findViewById(R.id.zfm_1_lv_green);
            CheckBox checkBox = view.findViewById(R.id.zfm_1_lv_checkBox);
            Button btn = view.findViewById(R.id.zfm_1_lv_btn_set);

            tvRoad.setText(zfm1EntityList.get(i).getRoadName() + "");
            tvRed.setText(zfm1EntityList.get(i).getRed() + "");
            tvYellow.setText(zfm1EntityList.get(i).getYellow() + "");
            tvGreen.setText(zfm1EntityList.get(i).getGreen() + "");

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    List<String> list = new ArrayList<>();
                    list.add(tvRoad.getText().toString().trim());
                    ZFM11Dialog dialog = new ZFM11Dialog(Item13aActivity.this, list);
                    dialog.show();
                    dialog.setSetOk(new ZFM11Dialog.setOk() {
                        @Override
                        public void success() {
                            zfm1EntityListTemp.clear();
                            getData(1);
                            Toast.makeText(Item13aActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void failure() {
                            Toast.makeText(Item13aActivity.this, "设置失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

            return view;
        }
    }
}
