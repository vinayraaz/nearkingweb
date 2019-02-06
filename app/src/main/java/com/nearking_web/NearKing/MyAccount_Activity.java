package com.nearking_web.NearKing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nearking_web.Activity.NearKingHome;
import com.nearking_web.Activity.NearKingNavigation;
import com.nearking_web.R;
import com.nearking_web.extra.CommonConstant;

public class MyAccount_Activity extends NearKingNavigation implements View.OnClickListener {
    View custToolbar;
    LinearLayout linearBack;
    TextView titleName,UserEmail,userName,userMobile,userCity,userAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account_activity);
        custToolbar = findViewById(R.id.cust_toolbar);
        linearBack = (LinearLayout) custToolbar.findViewById(R.id.ll_back);
        titleName = (TextView) custToolbar.findViewById(R.id.tvtitle);
        userName = (TextView) findViewById(R.id.username);
        UserEmail = (TextView) findViewById(R.id.useremail);
        userMobile = (TextView) findViewById(R.id.usermobile);
        userCity = (TextView) findViewById(R.id.usercity);
        userAdd = (TextView) findViewById(R.id.useradd);
        titleName.setText("My Account");

        System.out.println("SHARE MYACCOUNT DATA**"+CommonConstant.USERLOGIN+"**"+CommonConstant.USEREMAIL+"**"+CommonConstant.DISPLAYNAME);


        userName.setText(CommonConstant.USERLOGIN);
        UserEmail.setText(CommonConstant.USEREMAIL);
        linearBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
        Intent back = new Intent(MyAccount_Activity.this,NearKingHome.class);
        startActivity(back);
        finish();

    }
}
