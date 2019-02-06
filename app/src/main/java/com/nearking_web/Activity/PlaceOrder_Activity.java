package com.nearking_web.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nearking_web.NKDatabase.DBAdapter;
import com.nearking_web.R;

public class PlaceOrder_Activity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout checkOut;
    double totalPrice ;
    TextView subTotal,priceTotal;
    EditText deliveryAdd;
    DBAdapter db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_order_layout);
        db = new DBAdapter(this);
        checkOut =(LinearLayout)findViewById(R.id.checkout);
        subTotal =(TextView)findViewById(R.id.subtotal);
        priceTotal =(TextView) findViewById(R.id.totalprice);
        deliveryAdd =(EditText) findViewById(R.id.delivery_add);
        checkOut.setOnClickListener(this);
        Intent mIntent = getIntent();
        totalPrice = mIntent.getDoubleExtra("TotalPrice",0.0);
        subTotal.setText(String.valueOf(totalPrice));
        priceTotal.setText(String.valueOf(totalPrice));

        }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.checkout:
                if (deliveryAdd.getText().toString().isEmpty() || deliveryAdd.getText().toString().equals(null) || deliveryAdd.getText().toString().equals("null")){
                    Toast.makeText(getApplicationContext(),"please enter delivery address",Toast.LENGTH_SHORT).show();
                }else {
                    db.open();
                    db.deleteAllCartProduct();
                    db.close();
                    Toast.makeText(getApplicationContext(), "Order successfull", Toast.LENGTH_SHORT).show();
                    Intent checkoutintent = new Intent(PlaceOrder_Activity.this, NearKingHome.class);
                    startActivity(checkoutintent);
                }
                break;
        }
    }
}
