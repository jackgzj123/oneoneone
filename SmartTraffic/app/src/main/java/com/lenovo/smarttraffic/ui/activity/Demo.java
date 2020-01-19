package com.lenovo.smarttraffic.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import com.lenovo.smarttraffic.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Demo extends BaseActivity {
    @BindView(R.id.lgb2_back)
    ImageView lgb2Back;
    @BindView(R.id.lgb2_webView)
    WebView lgb2WebView;

    @Override
    protected int getLayout() {
        return R.layout.item2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        ;
    }
}
