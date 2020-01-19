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
import com.lenovo.smarttraffic.R;
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
public class YuleFragment extends Fragment {

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
        strings.add("再破离婚传闻？黄晓明Baby晒同款照罕见秀恩爱");
        strings.add("刘恺威父亲拒谈杨幂绯闻 透露小糯米近况言辞温馨");
        strings.add("郑爽张恒公司1个月前停运 员工称没欠薪补偿未发");
        strings.add("王力宏回应与娃哈哈解约：从小一块长大 会继续喝");
        strings.add("郭碧婷透露生子计划：生三个蛮好 男孩女孩都喜欢");
        strings.add("杨千嬅自信老公不会出轨 丁子高揭男人出轨原因");
        strings.add("汤唯纯白look身材高挑气质佳 举香槟畅饮");
        listView.setAdapter(new JunAdapter(getActivity(),strings));
        return view;
    }

    private void getyue() {

        //todo get请求

        mOkHttpUtil.newCall(new Request.Builder().url("https://3g.163.com/touch/reconstruct/article/list/BA10TA81wangning/1-10.html").build()).enqueue(new Callback() {
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
