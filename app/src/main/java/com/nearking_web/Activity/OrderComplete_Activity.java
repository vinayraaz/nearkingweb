package com.nearking_web.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nearking_web.R;

public class OrderComplete_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordercomplete_layout);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent orderIntent = new Intent(OrderComplete_Activity.this,NearKingHome.class);
        startActivity(orderIntent);
        finish();
    }
}
