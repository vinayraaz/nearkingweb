package com.nearking_web.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nearking_web.Api.ApiClient;
import com.nearking_web.Api.ApiInterface;
import com.nearking_web.NKDatabase.DBAdapter;
import com.nearking_web.R;
import com.nearking_web.extra.CommonConstant;
import com.nearking_web.extra.ConnectionDetector;
import com.nearking_web.extra.Utilities;
import com.nearking_web.model.CategoryProductModel;
import com.nearking_web.model.HomeProduct;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class ProductDetails_Activity extends AppCompatActivity implements View.OnClickListener {
    View custToolbar;
    LinearLayout linearBack;
    TextView titleName;
    ApiInterface nkService;
    Utilities utilities;
    String categoryId = "null";
    ConnectionDetector connectionDetector;
    List<CategoryProductModel> categoryProductModels = new ArrayList<>();
    String catProId = "", catProName = "", catProSlug = "", catProDesc = "", catProDis = "", catProImage = "", catProImageName = "";
    String proId, proName, proSlug, proType, proStatus, proShotDesc, proDesc, proPrice, proRegularprice,
            proSalePrice, proTaxStatus, stockStatus, averageRating, proImage;
    ImageView productImage;
    TextView productName, productSalePrice, productDesc, AddCart, buyNow;
    DBAdapter db;
    EditText proQty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleproductdetails_layout);
        custToolbar = findViewById(R.id.cust_toolbar);
        db = new DBAdapter(this);
        linearBack = (LinearLayout) custToolbar.findViewById(R.id.ll_back);
        titleName = (TextView) custToolbar.findViewById(R.id.tvtitle);
        proQty = (EditText) findViewById(R.id.proqty);
        titleName.setText("Products Details");

        connectionDetector = new ConnectionDetector(this);
        productImage = (ImageView) findViewById(R.id.pimage);
        productName = (TextView) findViewById(R.id.pname);
        AddCart = (TextView) findViewById(R.id.tv_addtocart);
        buyNow = (TextView) findViewById(R.id.buy_now);
        productSalePrice = (TextView) findViewById(R.id.saleprice);
        productDesc = (TextView) findViewById(R.id.productdesc);
        nkService = ApiClient.getClient().create(ApiInterface.class);
        linearBack.setOnClickListener(this);
        AddCart.setOnClickListener(this);
        buyNow.setOnClickListener(this);
        Bundle b = getIntent().getExtras();
        List<HomeProduct> homeProducts = new ArrayList<>();

        if (b != null) {
            // categoryId = b.getString("CATEGORY_ID");
            proId = b.getString("proId");
            proName = b.getString("proName");
            proSlug = b.getString("proSlug");
           // proType = b.getString("proType");
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
        proType = proQty.getText().toString();
        productName.setText(proName);
        productSalePrice.setText("Rs. " + proPrice);
        productDesc.setText(proShotDesc);
        Picasso.with(getApplicationContext())
                .load(proImage)
                .placeholder(R.drawable.nk1)
                .error(R.drawable.nk1)
                .fit()
                .into(productImage);
        if (connectionDetector.isConnectingToInternet()) {
            utilities.displayProgressDialog(getApplicationContext(), "Please wait...", false);
            // categoryProductAPI();
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            utilities.cancelProgressDialog();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                onBackPressed();
                ProductDetails_Activity.this.finish();
                break;
            case R.id.tv_addtocart:
                db.open();
                db.insertCartProduct(proId, proName, proPrice, proImage, proType);
                db.close();
                Toast.makeText(getApplicationContext(),"Product added in cart",Toast.LENGTH_SHORT).show();
            //    AddCartMethod();
                break;
            case R.id.buy_now:
                System.out.println("LoginStatus " + CommonConstant.LOGIN_STATUS);
                if (!CommonConstant.LOGIN_STATUS.equals("true")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetails_Activity.this);
                    builder.setMessage("User not SignIn! Please SignIn first")
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {


                                    Intent buynowIntent = new Intent(ProductDetails_Activity.this, SignInSignUpActivity.class);
                                    startActivity(buynowIntent);
                                    finish();
                                }
                            })
                            .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    //Now, any time you can call on:
                    dialog.show();


                } else {
                    buyNowMethod();
                   /* Intent ordercompleteIntent = new Intent(ProductDetails_Activity.this, OrderComplete_Activity.class);
                    startActivity(ordercompleteIntent);
                    finish();*/

                }
                break;
        }

    }

    private void buyNowMethod() {
        db.open();
        db.insertCartProduct(proId, proName, proPrice, proImage, proType);
        db.close();

        Intent buynow = new Intent(ProductDetails_Activity.this, BuyNow_Activity.class);
        startActivity(buynow);

    }

    private void AddCartMethod() {

        Intent cartIntent = new Intent(ProductDetails_Activity.this, AddCart_Activity.class);
        // cartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        cartIntent.putExtra("proId", proId);
        cartIntent.putExtra("proName", proName);
        cartIntent.putExtra("proSlug", proSlug);
        cartIntent.putExtra("proType", proType);
        cartIntent.putExtra("proStatus", proStatus);
        cartIntent.putExtra("proShotDesc", proShotDesc);
        cartIntent.putExtra("proDesc", proDesc);
        cartIntent.putExtra("proPrice", proPrice);
        cartIntent.putExtra("proRegularprice", proRegularprice);
        cartIntent.putExtra("proSalePrice", proSalePrice);
        cartIntent.putExtra("proTaxStatus", proTaxStatus);
        cartIntent.putExtra("stockStatus", stockStatus);
        cartIntent.putExtra("averageRating", averageRating);
        cartIntent.putExtra("proImage", proImage);

        startActivity(cartIntent);
    }
/*
    private void categoryProductAPI() {
        try {
            categoryProductModels.clear();
            Call<JsonObject> call = nkService.getCategoryProduct(categoryId, CommonConstant.CONSUMER_KEY, CommonConstant.CONSUMER_SECRET);
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.i("jsonObject***URL", "" + response.raw().request().url().toString());
                    utilities.cancelProgressDialog();
                    System.out.println("Ressss" + response.body().size());
                    JsonObject jsonObject = response.body();

                    if (response.body().size() > 0) {
                        if (jsonObject.has("id")) {
                            catProId = jsonObject.get("id").toString();
                        }
                        if (jsonObject.has("name")) {
                            catProName = jsonObject.get("name").toString();
                        }
                        if (jsonObject.has("slug")) {
                            catProSlug = jsonObject.get("slug").toString();
                        }
                        if (jsonObject.has("description")) {
                            catProDesc = jsonObject.get("description").toString();
                        }
                        if (jsonObject.has("display")) {
                            catProDesc = jsonObject.get("display").toString();
                        }
                        try {
                            JSONObject jsonObject1 = new JSONObject(String.valueOf(jsonObject)).getJSONObject("image");

                            catProImage = jsonObject1.getString("src").toString();
                            catProImageName = jsonObject1.getString("name").toString();
                            System.out.println("jsonObject1***********" + catProImage);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    utilities.cancelProgressDialog();
                    Log.i("ERROR**", t.toString());

                }
            });

        } catch (Exception e) {

        }
    }*/
}
