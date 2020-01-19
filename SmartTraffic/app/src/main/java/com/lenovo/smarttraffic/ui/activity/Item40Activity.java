package com.lenovo.smarttraffic.ui.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lenovo.smarttraffic.R;

public class Item40Activity extends AppCompatActivity {

    private WebView mWebview;
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item40);

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        mWebview = findViewById(R.id.webview);
        mProgress = findViewById(R.id.progressbar1);
        mProgress.setMax(100);
    WebSettings settings = mWebview.getSettings();
    settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    // 使得mWebview支持js
   settings.setJavaScriptEnabled(true);
    mWebview.requestFocus();
    mWebview.loadUrl("file:///android_asset/www/newfile.html");
    // 覆盖mWebview默认使用第三方系统或者默认浏览器打开网页的行为，使网页使用mWebview打开
    mWebview.setWebViewClient(new WebViewClient() {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    // 当遇到https请求的时候进行的处理
    public void onReceivedSslError(WebView view,
    SslErrorHandler handler, SslError error) {
        handler.proceed();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
    // TODO Auto-generated method stub
        super.onPageFinished(view, url);
        mProgress.setProgress(100);
        mProgress.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
    // TODO Auto-generated method stub
        super.onPageStarted(view, url, favicon);
        mProgress.setVisibility(ProgressBar.VISIBLE);
        mProgress.setProgress(0);
    }

    });

    mWebview.setWebChromeClient(new WebChromeClient() {

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress == 100) {
        mProgress.setVisibility(ProgressBar.INVISIBLE);
        } else {
        Log.e("MyTag", "加载进度" + newProgress);
        }
        mProgress.setProgress(newProgress);
        super.onProgressChanged(view, newProgress);
    }

    });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
    if (mWebview.canGoBack()) {
    mWebview.goBack();
    return true;
    }
    }
    return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}

