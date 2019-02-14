package com.nearking_web.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nearking_web.Adapter.AddCartAdapter;
import com.nearking_web.Adapter.PaymentModeAdapter;
import com.nearking_web.Api.ApiClient;
import com.nearking_web.Api.ApiInterface;
import com.nearking_web.NKDatabase.DBAdapter;
import com.nearking_web.R;
import com.nearking_web.RequestModel.CODModel.CODBilling;
import com.nearking_web.RequestModel.CODModel.CODLineItem;
import com.nearking_web.RequestModel.CODModel.CODOrderRequest;
import com.nearking_web.RequestModel.CODModel.CODShipping;
import com.nearking_web.RequestModel.CODModel.CODVariations;
import com.nearking_web.RequestModel.OrderMain.BillingAddress;
import com.nearking_web.RequestModel.OrderMain.LineItem;
import com.nearking_web.RequestModel.OrderMain.Order;
import com.nearking_web.RequestModel.OrderMain.OrderRequest;
import com.nearking_web.RequestModel.OrderMain.PaymentDetails;
import com.nearking_web.RequestModel.OrderMain.ShippingAddress;
import com.nearking_web.RequestModel.OrderMain.ShippingLine;
import com.nearking_web.RequestModel.OrderMain.Variations;
import com.nearking_web.extra.CommonConstant;
import com.nearking_web.extra.Utilities;
import com.nearking_web.model.AddCartList;
import com.nearking_web.model.BuyNowProduct;
import com.nearking_web.model.PaymentModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyNow_Activity extends AppCompatActivity implements View.OnClickListener {
    List<BuyNowProduct> buyNowProducts = new ArrayList<>();
    String proId, proName, proSlug, proType, proStatus, proShotDesc, proDesc, proPrice, proRegularprice,
            proSalePrice, proTaxStatus, stockStatus, averageRating, proImage;
    DBAdapter db;
    ArrayList<AddCartList> addcartproductDB;
    ArrayList<AddCartList> addCartModels = new ArrayList<AddCartList>();
    List<AddCartList> withoutaddCartModels = new ArrayList<AddCartList>();
    HashSet<String> removeDuplicateValues = new HashSet<String>();

    double pricevalue = 0, totalPrice = 0;
    String pricevalue1;
    RecyclerView recyclerView, PaymentMode;
    AddCartAdapter addCartAdapter;
    View custToolbar;
    LinearLayout linearBack;
    TextView titleName;
    LinearLayout placeOrder;
    TextView subTotal, priceTotal;
    ApiInterface nkService;
    Utilities utilities;
    EditText billingFName, billingLName, billingAdd1, billingAdd2,
            billingMobile, billingEmail, billingCity, billingState, billingCountry, billingPCode;
    String billingFNameValue = "null", billingLNameValue = "null", billingAdd1Value = "null", billingAdd2Value = "null",
            billingMobileValue = "null", billingEmailValue = "null", billingCityValue = "null", billingStateValue = "null",
            billingCountryValue = "null", billingPCodeValue = "null";

    List<PaymentModel> paymentModels = new ArrayList<>();
    String paymentId, paymentTitle, paymenMethodTitle;
    PaymentModeAdapter paymentModeAdapter;

    OrderRequest orderRequest;
    Order order;
    PaymentDetails paymentDetails;
    BillingAddress billingAddress;
    ShippingAddress shippingAddress;
    List<ShippingLine> shippingLine;
    List<LineItem> lineItem;
    Variations variations;
    String OrderId, OrderNumber, OrderKey, OrderCurrency, OrderTotal;
    CODOrderRequest codOrderRequest;
    CODBilling codBilling;
    CODShipping codShipping;
    CODVariations codVariations;
    List<CODLineItem> codLineItem;
    CODLineItem codLineItem_Model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcart_layout);
        custToolbar = findViewById(R.id.cust_toolbar);
        nkService = ApiClient.getClient().create(ApiInterface.class);
        linearBack = (LinearLayout) custToolbar.findViewById(R.id.ll_back);
        titleName = (TextView) custToolbar.findViewById(R.id.tvtitle);
        placeOrder = (LinearLayout) findViewById(R.id.placeorder);
        subTotal = (TextView) findViewById(R.id.subtotal);
        priceTotal = (TextView) findViewById(R.id.totalprice);

        billingFName = (EditText) findViewById(R.id.billing_fname);
        billingLName = (EditText) findViewById(R.id.billing_lname);
        billingAdd1 = (EditText) findViewById(R.id.billing_add1);
        billingMobile = (EditText) findViewById(R.id.billing_mobile);
        billingEmail = (EditText) findViewById(R.id.billing_email);
        billingCity = (EditText) findViewById(R.id.billing_city);
        billingState = (EditText) findViewById(R.id.billing_state);
        billingCountry = (EditText) findViewById(R.id.billing_country);
        billingPCode = (EditText) findViewById(R.id.billing_pcode);

        billingFNameValue = billingFName.getText().toString();
        billingLNameValue = billingLName.getText().toString();
        billingAdd1Value = billingAdd1.getText().toString();
        billingMobileValue = billingMobile.getText().toString();
        billingEmailValue = billingEmail.getText().toString();
        billingCityValue = billingCity.getText().toString();
        billingStateValue = billingState.getText().toString();
        billingCountryValue = billingCountry.getText().toString();
        billingPCodeValue = billingPCode.getText().toString();

        placeOrder.setOnClickListener(this);
        titleName.setText("CheckOut");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        PaymentMode = (RecyclerView) findViewById(R.id.paymentmode);
        db = new DBAdapter(this);
        db.open();
        addcartproductDB = db.getcartPro();
        int cartsize = addcartproductDB.size();
        utilities.displayProgressDialog(this, "Please wait...", false);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            // categoryId = b.getString("CATEGORY_ID");
            proId = b.getString("proId");
            proName = b.getString("proName");
            proSlug = b.getString("proSlug");
            //  proType = b.getString("proType");
            proStatus = b.getString("proStatus");
            proShotDesc = b.getString("proShotDesc");
            proDesc = b.getString("proDesc");
            proPrice = b.getString("proPrice");
            proRegularprice = b.getString("proRegularprice");
            proSalePrice = b.getString("proSalePrice");
            proTaxStatus = b.getString("proTaxStatus");
            stockStatus = b.getString("stockStatus");
            averageRating = b.getString("averageRating");
            proImage = b.getString("proImage");
        }

        if (cartsize > 0) {
            for (int i = 0; i < addcartproductDB.size(); i++) {
                addCartModels.add(new AddCartList(
                        addcartproductDB.get(i).getPid(),
                        addcartproductDB.get(i).getProduct_name(),
                        addcartproductDB.get(i).getProduct_price(),
                        addcartproductDB.get(i).getProduct_image(),
                        addcartproductDB.get(i).getProduct_type()));
                pricevalue1 = addcartproductDB.get(i).getProduct_price();
                pricevalue = Double.parseDouble(pricevalue1);
                totalPrice = totalPrice + pricevalue;

            }

            subTotal.setText(String.valueOf(totalPrice));
            priceTotal.setText(String.valueOf(totalPrice));

            addCartLoad(addCartModels);

            /*ORDER RELATED DATA*/
            codBilling = new CODBilling();
            codShipping = new CODShipping();
            codVariations = new CODVariations();
            codLineItem = new ArrayList<>();
            codLineItem_Model = new CODLineItem();
            codOrderRequest = new CODOrderRequest();

          //  utilities.displayProgressDialog(this, "Please wait...", false);
            PaymentGetwaysAPI();
        }else {
            Toast.makeText(BuyNow_Activity.this,"NO Records", Toast.LENGTH_SHORT).show();

            Intent nodataIntent = new Intent(BuyNow_Activity.this,NearKingHome.class);
           // nodataIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(nodataIntent);
        }

    }

    private void PaymentGetwaysAPI() {
        try {
            paymentModels.clear();
            retrofit2.Call<JsonArray> call = nkService.getPaymentGetwa(CommonConstant.CONSUMER_KEY, CommonConstant.CONSUMER_SECRET);
            call.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(retrofit2.Call<JsonArray> call, Response<JsonArray> response) {
                   utilities.cancelProgressDialog();
                    System.out.println("RESponse***" + response.body());
                    String resStr = String.valueOf(response.body());
                    System.out.println("RESponse***resStr**" + resStr);
                    try {
                        JSONArray jsonArray = new JSONArray(resStr);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String Enable = String.valueOf(jsonObject.getBoolean("enabled"));
                            System.out.println("Enable***********" + Enable);
                            if (Enable.equals("true")) {

                                paymentId = jsonObject.getString("id");
                                paymentTitle = jsonObject.getString("title");
                                paymenMethodTitle = jsonObject.getString("method_title");
                                paymentModels.add(new PaymentModel(paymentId, paymentTitle, paymenMethodTitle));
                            }


                        }
                        PaymentModeLoad(paymentModels);
                        System.out.println("paymentModels****" + paymentModels.size());

                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(retrofit2.Call<JsonArray> call, Throwable t) {
                    utilities.cancelProgressDialog();
                    System.out.println("RESponse***" + t.toString());
                }
            });
        } catch (Exception e) {

        }
    }

    private void PaymentModeLoad(List<PaymentModel> paymentModels) {
        paymentModeAdapter = new PaymentModeAdapter(this, paymentModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        PaymentMode.setLayoutManager(linearLayoutManager);
        PaymentMode.setAdapter(paymentModeAdapter);
    }

    private void addCartLoad(ArrayList<AddCartList> addCartModels) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        addCartAdapter = new AddCartAdapter(this, addCartModels);
        recyclerView.setAdapter(addCartAdapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.placeorder:
                if (CommonConstant.PAYMENTMODE == 100) {
                    Toast.makeText(BuyNow_Activity.this, "Please Select Payment Mode", Toast.LENGTH_SHORT).show();

                }else if(billingFName.getText().toString().isEmpty() || billingLName.getText().toString().isEmpty() ||
                        billingAdd1.getText().toString().isEmpty() || billingMobile.getText().toString().isEmpty() ||
                        billingEmail.getText().toString().isEmpty()){
                    Toast.makeText(BuyNow_Activity.this, "enter all mandatory fields", Toast.LENGTH_SHORT).show();
                }
                else if (CommonConstant.PAYMENTMODE == 0) {
                    codBilling = new CODBilling();
                    codShipping = new CODShipping();
                    codVariations = new CODVariations();
                    codLineItem = new ArrayList<>();
                    codLineItem_Model = new CODLineItem();
                    codOrderRequest = new CODOrderRequest();

                    utilities.displayProgressDialog(this, "Please wait...", false);
                    CommonConstant.PAYMENT_METHOD = "bacs";
                    CommonConstant.PAYMENT_METHODTITLE = "Direct Bank Transfer";
                    CommonConstant.PAYMENT_SETPAID = true;

                    codVariations.setPaColor(CommonConstant.COLOR);

                    for (int i = 0; i < addcartproductDB.size(); i++) {
                        codLineItem_Model.setProductId(Integer.parseInt(addcartproductDB.get(i).getPid()));
                        codLineItem_Model.setQuantity(Integer.parseInt(addcartproductDB.get(i).getProduct_type()));
                        codLineItem_Model.setVariations(codVariations);
                        codLineItem.add(codLineItem_Model);
                    }
                    codBilling.setFirstName(billingFNameValue);
                    codBilling.setLastName(billingLNameValue);
                    codBilling.setAddress1(billingAdd1Value);
                    codBilling.setAddress2(billingAdd1Value);
                    codBilling.setCity(billingCityValue);
                    codBilling.setState(billingStateValue);
                    codBilling.setPostcode(billingPCodeValue);
                    codBilling.setCountry(billingCountryValue);
                    codBilling.setEmail(CommonConstant.USEREMAIL);
                    codBilling.setPhone(billingMobileValue);

                    codShipping.setFirstName(billingFNameValue);
                    codShipping.setLastName(billingLNameValue);
                    codShipping.setAddress1(billingAdd1Value);
                    codShipping.setAddress2(billingAdd1Value);
                    codShipping.setCity(billingCityValue);
                    codShipping.setState(billingStateValue);
                    codShipping.setPostcode(billingPCodeValue);
                    codShipping.setCountry(billingCountryValue);

                    codOrderRequest.setPaymentMethod(CommonConstant.PAYMENT_METHOD);
                    codOrderRequest.setPaymentMethodTitle(CommonConstant.PAYMENT_METHODTITLE);
                    codOrderRequest.setSetPaid(CommonConstant.PAYMENT_SETPAID);
                    codOrderRequest.setBilling(codBilling);
                    codOrderRequest.setShipping(codShipping);
                    codOrderRequest.setLineItems(codLineItem);
                    Gson gson = new Gson();
                    System.out.println("COD REQUEST**" + gson.toJson(codOrderRequest));

                    Call<JsonObject> call = nkService.getCODOrder(CommonConstant.AUTH_TOKEN, codOrderRequest);
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            utilities.cancelProgressDialog();
                            System.out.println("COD ENUERESPONSE**"+response.body());
                            String str = response.body().toString();
                            JsonObject jsonObject = response.body().getAsJsonObject();
                            OrderId = jsonObject.get("id").getAsString();
                            OrderNumber = jsonObject.get("number").getAsString();
                            OrderKey = jsonObject.get("order_key").getAsString();
                            OrderCurrency = jsonObject.get("currency").getAsString();
                            OrderTotal = jsonObject.get("total").getAsString();

                            Intent codIntent = new Intent(BuyNow_Activity.this, OrderComplete_Activity.class);
                            //codIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                            codIntent.putExtra("OrderID",OrderId);
                            codIntent.putExtra("TotalPrice",OrderTotal);
                            startActivity(codIntent);
                            finish();
                            System.out.println("SYS******json" + OrderId + "***" + OrderNumber + "***" + OrderKey + "***" + OrderCurrency + "****" + OrderTotal);


                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            utilities.cancelProgressDialog();
                            System.out.println("COD RESPONSE**Error" + t.toString());
                        }
                    });


                } else if (CommonConstant.PAYMENTMODE == 1) {
                    utilities.displayProgressDialog(this, "Please wait...", false);
                    CommonConstant.PAYMENT_METHOD = "ccavenue";
                    CommonConstant.PAYMENT_METHODTITLE = "CCAvenue";
                    CommonConstant.PAYMENT_SETPAID = true;
                    codVariations.setPaColor(CommonConstant.COLOR);

                    for (int i = 0; i < addcartproductDB.size(); i++) {
                        codLineItem_Model.setProductId(Integer.parseInt(addcartproductDB.get(i).getPid()));
                        codLineItem_Model.setQuantity(Integer.parseInt(addcartproductDB.get(i).getProduct_type()));
                        codLineItem_Model.setVariations(codVariations);
                        codLineItem.add(codLineItem_Model);
                    }
                    codBilling.setFirstName(billingFNameValue);
                    codBilling.setLastName(billingLNameValue);
                    codBilling.setAddress1(billingAdd1Value);
                    codBilling.setAddress2(billingAdd1Value);
                    codBilling.setCity(billingCityValue);
                    codBilling.setState(billingStateValue);
                    codBilling.setPostcode(billingPCodeValue);
                    codBilling.setCountry(billingCountryValue);
                    codBilling.setEmail(CommonConstant.USEREMAIL);
                    codBilling.setPhone(billingMobileValue);

                    codShipping.setFirstName(billingFNameValue);
                    codShipping.setLastName(billingLNameValue);
                    codShipping.setAddress1(billingAdd1Value);
                    codShipping.setAddress2(billingAdd1Value);
                    codShipping.setCity(billingCityValue);
                    codShipping.setState(billingStateValue);
                    codShipping.setPostcode(billingPCodeValue);
                    codShipping.setCountry(billingCountryValue);

                    codOrderRequest.setPaymentMethod(CommonConstant.PAYMENT_METHOD);
                    codOrderRequest.setPaymentMethodTitle(CommonConstant.PAYMENT_METHODTITLE);
                    codOrderRequest.setSetPaid(CommonConstant.PAYMENT_SETPAID);
                    codOrderRequest.setBilling(codBilling);
                    codOrderRequest.setShipping(codShipping);
                    codOrderRequest.setLineItems(codLineItem);

                    Call<JsonObject> call = nkService.getCODOrder(CommonConstant.AUTH_TOKEN, codOrderRequest);
                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            utilities.cancelProgressDialog();
                            System.out.println("CCAVENUERESPONSE**"+response.body());
                            String str = response.body().toString();
                            JsonObject jsonObject = response.body().getAsJsonObject();
                            OrderId = jsonObject.get("id").getAsString();
                            OrderNumber = jsonObject.get("number").getAsString();
                            OrderKey = jsonObject.get("order_key").getAsString();
                            OrderCurrency = jsonObject.get("currency").getAsString();
                            OrderTotal = jsonObject.get("total").getAsString();

                            Intent ccAvenueIntent = new Intent(getApplicationContext(), PaymentDetails_Activity.class);
                           // ccAvenueIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                            ccAvenueIntent.putExtra("OrderID",OrderId);
                            ccAvenueIntent.putExtra("TotalPrice",OrderTotal);
                            startActivity(ccAvenueIntent);
                            finish();
                            System.out.println("SYS******json" + OrderId + "***" + OrderNumber + "***" + OrderKey + "***" + OrderCurrency + "****" + OrderTotal);


                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            utilities.cancelProgressDialog();
                            System.out.println("COD RESPONSE**Error" + t.toString());
                        }
                    });

                }
               /* else {
                    orderRequest = new OrderRequest();
                    order = new Order();
                    paymentDetails = new PaymentDetails();
                    billingAddress = new BillingAddress();
                    shippingAddress = new ShippingAddress();
                    lineItem = new ArrayList<>();
                    shippingLine = new ArrayList<>();
                    variations = new Variations();

                    variations.setPaColor("Black");
                    for (int j = 0; j < shippingLine.size(); j++) {
                        shippingLine.get(j).setMethodId("2");
                        shippingLine.get(j).setMethodTitle("Flat Rate");
                        shippingLine.get(j).setTotal(100);
                    }
                    for (int i = 0; i < lineItem.size(); i++) {
                        lineItem.get(i).setProductId(10);
                        lineItem.get(i).setQuantity(5);
                        lineItem.get(i).setVariations(variations);
                    }

                    shippingAddress.setFirstName(billingFNameValue);
                    shippingAddress.setLastName(billingLNameValue);
                    shippingAddress.setAddress1(billingAdd1Value);
                    shippingAddress.setAddress2(billingAdd1Value);
                    shippingAddress.setCity(billingCityValue);
                    shippingAddress.setState(billingStateValue);
                    shippingAddress.setPostcode(billingPCodeValue);
                    shippingAddress.setCountry(billingCountryValue);

                    billingAddress.setFirstName(billingFNameValue);
                    billingAddress.setLastName(billingLNameValue);
                    billingAddress.setAddress1(billingAdd1Value);
                    billingAddress.setAddress2(billingAdd1Value);
                    billingAddress.setCity(billingCityValue);
                    billingAddress.setState(billingStateValue);
                    billingAddress.setPostcode(billingPCodeValue);
                    billingAddress.setCountry(billingCountryValue);
                    billingAddress.setEmail(billingEmailValue);
                    billingAddress.setPhone(billingMobileValue);

                    paymentDetails.setMethodId(CommonConstant.PAYMENT_METHOD);
                    paymentDetails.setMethodTitle(CommonConstant.PAYMENT_METHODTITLE);
                    paymentDetails.setPaid(CommonConstant.PAYMENT_SETPAID);

                    order.setCustomerId(Integer.parseInt(CommonConstant.USERID));
                    order.setBillingAddress(billingAddress);
                    order.setLineItems(lineItem);
                    order.setPaymentDetails(paymentDetails);
                    order.setShippingAddress(shippingAddress);
                    order.setShippingLines(shippingLine);
                    orderRequest.setOrder(order);

                }*/
               /* Call<JsonObject> call = nkService.getOrder(CommonConstant.AUTH_TOKEN, orderRequest);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        try {
                            JSONObject json = new JSONObject(String.valueOf(response.body()));

                            OrderId = json.getString("id");
                            OrderNumber = json.getString("number");
                            OrderKey = json.getString("order_key");
                            OrderCurrency = json.getString("currency");
                            OrderTotal = json.getString("total");
                            System.out.println("SYS******json" + OrderId + "***" + OrderNumber + "***" + OrderKey + "***" + OrderCurrency + "****" + OrderTotal);


                            Intent placeintent = new Intent(getApplicationContext(), PaymentMode_Activity.class);
                            placeintent.putExtra("OrderID", OrderId);
                            placeintent.putExtra("TotalPrice", String.valueOf(totalPrice));
                            startActivity(placeintent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        System.out.println("Error******" + t.toString());
                    }
                });*/
                break;
        }

    }

    private void OrderAPICall() {


    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(getApplicationContext(), NearKingHome.class);
        startActivity(back);
        finish();
    }*/
}
