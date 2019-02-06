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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nearking_web.Adapter.AddCartAdapter;
import com.nearking_web.Api.ApiClient;
import com.nearking_web.Api.ApiInterface;
import com.nearking_web.NKDatabase.DBAdapter;
import com.nearking_web.R;
import com.nearking_web.RequestModel.OrderMain.BillingAddress;
import com.nearking_web.RequestModel.OrderMain.LineItem;
import com.nearking_web.RequestModel.OrderMain.Order;
import com.nearking_web.RequestModel.OrderMain.OrderRequest;
import com.nearking_web.RequestModel.OrderMain.PaymentDetails;
import com.nearking_web.RequestModel.OrderMain.ShippingAddress;
import com.nearking_web.RequestModel.OrderMain.ShippingLine;
import com.nearking_web.RequestModel.OrderMain.Variations;
import com.nearking_web.extra.CommonConstant;
import com.nearking_web.model.AddCartList;
import com.nearking_web.model.BuyNowProduct;

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
    RecyclerView recyclerView;
    AddCartAdapter addCartAdapter;
    View custToolbar;
    LinearLayout linearBack;
    TextView titleName;
    LinearLayout placeOrder;
    TextView subTotal, priceTotal;
    ApiInterface nkService;
    EditText billingFName,billingLName,billingAdd1,billingAdd2,
            billingMobile,billingEmail,billingCity,billingState,billingCountry,billingPCode;
    String billingFNameValue ="null",billingLNameValue="null",billingAdd1Value="null",billingAdd2Value="null",
            billingMobileValue="null",billingEmailValue="null",billingCityValue="null",billingStateValue="null",
            billingCountryValue="null",billingPCodeValue="null";

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

        billingFName =(EditText)findViewById(R.id.billing_fname);
        billingLName =(EditText)findViewById(R.id.billing_lname);
        billingAdd1 =(EditText)findViewById(R.id.billing_add1);
        billingMobile =(EditText)findViewById(R.id.billing_mobile);
        billingEmail =(EditText)findViewById(R.id.billing_email);
        billingCity =(EditText)findViewById(R.id.billing_city);
        billingState =(EditText)findViewById(R.id.billing_state);
        billingCountry =(EditText)findViewById(R.id.billing_country);
        billingPCode =(EditText)findViewById(R.id.billing_pcode);

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
        db = new DBAdapter(this);
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
        db.open();
        addcartproductDB = db.getcartPro();
        int cartsize = addcartproductDB.size();
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
            System.out.println("totalPrice***" + totalPrice);
            subTotal.setText(String.valueOf(totalPrice));
            priceTotal.setText(String.valueOf(totalPrice));

            addCartLoad(addCartModels);

        }
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

                OrderRequest orderRequest = new OrderRequest();
                Order order =new Order();
                PaymentDetails paymentDetails = new PaymentDetails();
                BillingAddress billingAddress = new BillingAddress();
                ShippingAddress shippingAddress = new ShippingAddress();
                List<LineItem> lineItem = new ArrayList<>();
                List<ShippingLine> shippingLine = new ArrayList<>();
                Variations variations = new Variations();

                variations.setPaColor("Black");
                for (int j=0;j<shippingLine.size();j++){
                    shippingLine.get(j).setMethodId("2");
                    shippingLine.get(j).setMethodTitle("Flat Rate");
                    shippingLine.get(j).setTotal(100);
                }
                for (int i=0;i<lineItem.size();i++){
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

                paymentDetails.setMethodId("RBS");
                paymentDetails.setMethodTitle("Direct Bank Transfer");
                paymentDetails.setPaid(true);

                order.setCustomerId(Integer.valueOf(CommonConstant.USERID));
                order.setBillingAddress(billingAddress);
                order.setLineItems(lineItem);
                order.setPaymentDetails(paymentDetails);
                order.setShippingAddress(shippingAddress);
                order.setShippingLines(shippingLine);
                orderRequest.setOrder(order);

                Gson gson = new Gson();
                System.out.println("GSON***"+gson.toJson(orderRequest));
                Call<JsonObject> call = nkService.getOrder(CommonConstant.AUTH_TOKEN,orderRequest);
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        try {
                          //  JsonObject res = response.body().getAsJsonObject();
                            JSONObject json = new JSONObject(String.valueOf(response.body()));
                            System.out.println("SYS******"+response.raw().request().url());
                          //  System.out.println("SYS******json1"+res);
                            System.out.println("SYS******json"+json);
                            String OrderId = json.getString("id").toString();
                            String OrderNumber = json.getString("number").toString();
                            String OrderKey = json.getString("order_key").toString();
                            String OrderCurrency = json.getString("currency").toString();
                            String OrderTotal = json.getString("total").toString();
                            System.out.println("SYS******json"+OrderId+"***"+OrderNumber+"***"+OrderKey+"***"+OrderCurrency+"****"+OrderTotal);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        System.out.println("Error******"+t.toString());
                    }
                });





               /* Intent placeintent = new Intent(getApplicationContext(), PaymentDetails_Activity.class);
                placeintent.putExtra("TotalPrice", totalPrice);
                startActivity(placeintent);*/
                break;
        }

    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back = new Intent(getApplicationContext(), NearKingHome.class);
        startActivity(back);
        finish();
    }*/
}
