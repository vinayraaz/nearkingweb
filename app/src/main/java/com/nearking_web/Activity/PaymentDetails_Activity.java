package com.nearking_web.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nearking_web.R;

public class PaymentDetails_Activity extends AppCompatActivity {
    View custToolbar;
    LinearLayout linearBack;
    TextView titleName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);
        custToolbar = findViewById(R.id.cust_toolbar);
        linearBack = (LinearLayout) custToolbar.findViewById(R.id.ll_back);
        titleName = (TextView) custToolbar.findViewById(R.id.tvtitle);
        titleName.setText("Payment");
    }
}
