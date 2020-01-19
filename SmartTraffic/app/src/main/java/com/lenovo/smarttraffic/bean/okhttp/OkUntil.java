package com.lenovo.smarttraffic.bean.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Author：gzj
 * @CreateDate: 2019/12/21
 */
public class OkUntil {
    private static OkUntil okUntil;
    private OkHttpClient client;
    private Handler handler;
    private Gson gson;
    private FormBody formBody;

    private OkUntil(){
        client=new OkHttpClient();
        gson=new Gson();
        handler=new Handler(Looper.getMainLooper());
    }

    public static OkUntil getInstance(){
        if(okUntil==null){
            synchronized (OkUntil.class){
                if (okUntil==null){
                    okUntil=new OkUntil();
                }
            }
        }
        return okUntil;
    }

    public void post_Asyn(String url, Map<String,String> data, IOkHttpListener listener){
        FormBody formBody = null;
        if(data==null){
            Request request=new Request.Builder().url(url).build();
            deliverResult(request,listener);
        }else {
            for(Map.Entry<String,String> d:data.entrySet()){
                    FormBody.Builder builder = new FormBody.Builder();
                    builder.add(d.getKey(),d.getValue());
                    formBody = builder.build();

            }
            Request request=new Request.Builder().url(url).post(formBody).build();
            deliverResult(request,listener);
        }

    }

    private void deliverResult(Request request, final IOkHttpListener listener) {
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onErr(e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String data = response.body().string();
                        if(listener.type ==String.class){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onJson(data);
                                }
                            });
                        }else {
                            final Object o = gson.fromJson(data, listener.type);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onJson(o);
                                }
                            });
                        }
                    }
                });
    }
}
