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
public class TiyuFragment extends Fragment {

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
        strings.add("西甲-贝尔首发进球被取消 国家德比巴萨0-0平皇马");
        strings.add("世俱杯-凯塔破门菲米补时绝杀！利物浦2-1进决赛");
        strings.add("意甲-迪巴拉抽射C罗逆天头球 尤文2-1桑普暂居第一");
        strings.add("武磊:即使球队降级也不会回国 否则所有努力白费");
        strings.add("东亚杯-吉翔张稀哲破门 国足2-0中国香港获季军");
        strings.add("欧洲大师赛丁俊晖5-1晋级正赛 特鲁姆普爆冷出局");
        strings.add("刘国梁亲承：将公开渠道 谁都可报名争取进国家队");
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
