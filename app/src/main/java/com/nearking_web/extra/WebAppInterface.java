package com.nearking_web.extra;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;



public class WebAppInterface {
    Context mContext;


    public WebAppInterface(Context c) {
        mContext = c;
    }


    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }

}
/*public class WebAppInterface {
    public WebAppInterface(MainActivity mainActivity) {

    }
}*/
