package com.lenovo.smarttraffic.ui.fragment.item34fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lenovo.smarttraffic.R;
import com.lenovo.smarttraffic.bean.UrlClass;
import com.lenovo.smarttraffic.database.data.ETCData;
import com.lenovo.smarttraffic.ui.adapter.JunAdapter;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author：gzj
 * @CreateDate: 2019/11/6
 */
public class JunshiFragment extends Fragment {

    private OkHttpClient mOkHttpUtil = new OkHttpClient();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Gson gson;
    List<String> strings = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.junshifragment,null);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ListView listView = view.findViewById(R.id.llistview);
        strings.add("山东舰服役这天 美在建新航母也动了");
        strings.add("老挝坦克部队鸟枪换炮 换装T-72坦克");
        strings.add("双航母时代！首艘国产航母定名山东舰");
        strings.add("首艘国产航母入役 盘点全球现役航母");
        strings.add("8年连增!日本基本敲定明年预算案 防卫费再创新高");
        strings.add("军情锐评：无人机“蜂群战”或重塑未来战争");
        strings.add("西媒:冷战后签署的《开放天空条约》面临失效风险");
        listView.setAdapter(new JunAdapter(getActivity(),strings));
        return view;
    }

    private void getyue() {

        //todo get请求

        mOkHttpUtil.newCall(new Request.Builder().url("https://3g.163.com/touch/reconstruct/article/list/BA8E6OEOwangning/1-10.html").build()).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull final IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                final String jsonData = response.body().string();
                gson = new Gson();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });

    }
}
