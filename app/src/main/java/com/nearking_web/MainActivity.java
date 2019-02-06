package com.nearking_web;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.crashlytics.android.Crashlytics;
import com.nearking_web.extra.WebAppInterface;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    ProgressDialog barProgressDialog;
    WebView mywebview;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fabric.with(this, new Crashlytics());
        mywebview = (WebView) findViewById(R.id.webView);
        //mywebview.loadUrl("https://www.ocabbie.com/demo/admin/index.html");  https://nearking.co.in/
        mywebview.loadUrl("https://nearking.com");
       // mywebview.loadUrl("http://nearking.co.in");

   /*    webView = new WebView(this);
      webView.getSettings().setJavaScriptEnabled(true);
      webView.setWebViewClient(new WebViewClient());
      webView.loadUrl("https://codeitbro.com/");
*/
        final ProgressDialog ringProgressDialog = ProgressDialog.show(MainActivity.this, "Please wait ...", "", false);
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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView!= null){
            webView.destroy();
        }
    }
}
