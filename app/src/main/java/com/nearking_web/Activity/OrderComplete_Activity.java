package com.nearking_web.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.nearking_web.NKDatabase.DBAdapter;
import com.nearking_web.R;
import com.nearking_web.extra.CommonConstant;

public class OrderComplete_Activity extends AppCompatActivity implements View.OnClickListener {
    DBAdapter db;
    TextView  OrderId, Amount;
    String orderId, totalPrice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordercomplete_layout);
        TextView Submit = (TextView)findViewById(R.id.submit);
        OrderId = (TextView) findViewById(R.id.orderId);
        Amount = (TextView) findViewById(R.id.amount);
        Submit.setOnClickListener(this);
        db = new DBAdapter(this);
        db.open();
        db.deleteAllCartProduct();
        db.close();

        Bundle b = getIntent().getExtras();
        if (b != null) {

            orderId = b.getString("OrderID");
            totalPrice = b.getString("TotalPrice");
        }
        CommonConstant.ORDERID=orderId;
        CommonConstant.TOTALPRICE= totalPrice;
        OrderId.setText(orderId);
        Amount.setText(totalPrice);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent orderIntent = new Intent(OrderComplete_Activity.this,NearKingHome.class);
       // orderIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(orderIntent);
        finish();
    }

    @Override
    public void onClick(View v) {
        Intent orderIntent = new Intent(OrderComplete_Activity.this,NearKingHome.class);
       // orderIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(orderIntent);
        finish();
    }
}
