package com.lenovo.smarttraffic.ui.fragment.item39fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.LightData;
import com.lenovo.smarttraffic.ui.activity.MyDialog6;
import com.lenovo.smarttraffic.ui.adapter.ListItem39_fragmentAdapter;

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

/**
 * @Author：gzj
 * @CreateDate: 2019/12/23
 */
public class LuKuangFragment extends Fragment  {

    private List<String> honglist = new ArrayList<>();
    private List<String> huanglist = new ArrayList<>();
    private List<String> lvlist = new ArrayList<>();
    private List<String> roadlist = new ArrayList<>();

    private List<LightData> lightDatalist = new ArrayList<>();

    private OkHttpClient mOkhttp = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    private ListView mListview;
    private ListItem39_fragmentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item39_fragment_lukuang,null);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mListview = view.findViewById(R.id.item39_fragment_lukuangListview);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getdata();
            }
        },0,5000);


        return view;
    }

    private void getdata() {
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

                        roadlist.clear();
                        huanglist.clear();
                        honglist.clear();
                        lvlist.clear();
                        for (int i = 0; i < lightDatalist.size(); i++) {
                            roadlist.add(lightDatalist.get(i).getRoadName());
                            honglist.add(""+lightDatalist.get(i).getRedTime());
                            huanglist.add(""+lightDatalist.get(i).getYellowTime());
                            lvlist.add(""+lightDatalist.get(i).getGreenTime());
                        }
                        adapter = new ListItem39_fragmentAdapter(getActivity(),roadlist,honglist,huanglist,lvlist);
                        adapter.notifyDataSetChanged();
                        mListview.setAdapter(adapter);
                    }
                });

            }
        });
    }

}
