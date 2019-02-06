package com.nearking_web;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.nearking_web.extra.WebViewClientImpl;

public class ProfileActivity extends AppCompatActivity {
    private WebView webView = null;
    ProgressDialog barProgressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.webView = (WebView) findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        WebViewClientImpl webViewClient = new WebViewClientImpl(this);
        webView.setWebViewClient(webViewClient);
        final ProgressDialog ringProgressDialog = ProgressDialog.show(ProfileActivity.this, "Please wait ...", "", false);
        ringProgressDialog.setCancelable(true);
        new Thread(new Runnable() {
            @Override

            public void run() {
                try {

                    Thread.sleep(5000);

                } catch (Exception e) {

                }

                ringProgressDialog.dismiss();
            }
        }).start();
        webView.loadUrl("https://nearking.com");
        //webView.loadUrl("https://nearking.co.in");
       // webView.loadUrl("https://nearking.com");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
