package com.nearking_web.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nearking_web.R;
import com.nearking_web.ccAvenue.WebViewActivity;
import com.nearking_web.ccAvenue.utility.AvenuesParams;
import com.nearking_web.ccAvenue.utility.ServiceUtility;
import com.nearking_web.extra.CommonConstant;

public class PaymentDetails_Activity extends AppCompatActivity implements View.OnClickListener {
    View custToolbar;
    LinearLayout linearBack, Pay;
    TextView titleName, OrderId, Amount, accessCode, currency;
    String orderId, totalPrice;
    private EditText merchantId, rsaKeyUrl, redirectUrl, cancelUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);
        custToolbar = findViewById(R.id.cust_toolbar);
        linearBack = (LinearLayout) custToolbar.findViewById(R.id.ll_back);
        titleName = (TextView) custToolbar.findViewById(R.id.tvtitle);
        titleName.setText("CCAvenue Payment");
        OrderId = (TextView) findViewById(R.id.orderId);
        Amount = (TextView) findViewById(R.id.amount);
        Pay = (LinearLayout) findViewById(R.id.pay);
        accessCode = (TextView) findViewById(R.id.accessCode);
        merchantId = (EditText) findViewById(R.id.merchantId);
        currency = (TextView) findViewById(R.id.currency);
        rsaKeyUrl = (EditText) findViewById(R.id.rsaUrl);
        redirectUrl = (EditText) findViewById(R.id.redirectUrl);
        cancelUrl = (EditText) findViewById(R.id.cancelUrl);



        Pay.setOnClickListener(this);
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
    public void onClick(View v) {

        String vAccessCode = ServiceUtility.chkNull(accessCode.getText()).toString().trim();
        String vMerchantId = ServiceUtility.chkNull(merchantId.getText()).toString().trim();
        String vCurrency = ServiceUtility.chkNull(currency.getText()).toString().trim();
        String vAmount = ServiceUtility.chkNull(Amount.getText()).toString().trim();
        if(!vAccessCode.equals("") && !vMerchantId.equals("") && !vCurrency.equals("") && !vAmount.equals("")){
            Intent intent = new Intent(this,WebViewActivity.class);
            intent.putExtra(AvenuesParams.ACCESS_CODE, ServiceUtility.chkNull(accessCode.getText()).toString().trim());
            intent.putExtra(AvenuesParams.MERCHANT_ID, ServiceUtility.chkNull(merchantId.getText()).toString().trim());
            intent.putExtra(AvenuesParams.ORDER_ID, ServiceUtility.chkNull(OrderId.getText()).toString().trim());
            intent.putExtra(AvenuesParams.CURRENCY, ServiceUtility.chkNull(currency.getText()).toString().trim());
            intent.putExtra(AvenuesParams.AMOUNT, ServiceUtility.chkNull(Amount.getText()).toString().trim());

            intent.putExtra(AvenuesParams.REDIRECT_URL, ServiceUtility.chkNull(redirectUrl.getText()).toString().trim());
            intent.putExtra(AvenuesParams.CANCEL_URL, ServiceUtility.chkNull(cancelUrl.getText()).toString().trim());
            intent.putExtra(AvenuesParams.RSA_KEY_URL, ServiceUtility.chkNull(rsaKeyUrl.getText()).toString().trim());


            startActivity(intent);
        }else{
            showToast("All parameters are mandatory.");
        }

    }

    private void showToast(String s) {
        Toast.makeText(this, "Toast: " + s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
