package com.nearking_web;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.nearking_web.Activity.NearKingHome;
import com.nearking_web.Activity.NearKingNavigation;
import com.nearking_web.Activity.ProductList_Activity;
import com.nearking_web.Activity.SignInSignUpActivity;
import com.nearking_web.extra.CommonConstant;

import io.fabric.sdk.android.Fabric;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    SharedPreferences sharpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Fabric.with(this, new Crashlytics());
        sharpf = getSharedPreferences("NKing_Login",Context.MODE_PRIVATE);
        CommonConstant.LOGIN_STATUS = sharpf.getString("LOGIN_STATUS","0");
        CommonConstant.USERID =sharpf.getString("USER_ID","0");
        CommonConstant.USEREMAIL =sharpf.getString("USER_EMAIL","0");
        CommonConstant.USERLOGIN =sharpf.getString("USER_LOGIN","0");
        CommonConstant.DISPLAYNAME =sharpf.getString("DISPLAY_NAME","0");
        System.out.println("CommonConstant.LOGIN_STATUS***splash**"+CommonConstant.LOGIN_STATUS+"****"+CommonConstant.USEREMAIL);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                               // Start your app main activity
                if (CommonConstant.LOGIN_STATUS.equals("true")){
                    Intent i = new Intent(SplashActivity.this, NearKingHome.class);
                    startActivity(i);
                }else {
                    Intent i = new Intent(SplashActivity.this, SignInSignUpActivity.class);
                    startActivity(i);
                }

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
