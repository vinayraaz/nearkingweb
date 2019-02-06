package com.nearking_web.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.nearking_web.Adapter.CategoryProductAdapter;
import com.nearking_web.Api.ApiClient;
import com.nearking_web.Api.ApiInterface;
import com.nearking_web.R;
import com.nearking_web.extra.CommonConstant;
import com.nearking_web.extra.ConnectionDetector;
import com.nearking_web.extra.Utilities;
import com.nearking_web.model.CategoryProductModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryProduct_Activity extends AppCompatActivity implements View.OnClickListener {
    View custToolbar;
    LinearLayout linearBack;
    TextView titleName;
    String categoryId = "null";
    ApiInterface nkService;
    Utilities utilities;
    ConnectionDetector connectionDetector;
    String proId, proName, proSlug, proType, proStatus, proShotDesc, proDesc, proPrice, proRegularprice, proSalePrice, proTaxStatus,
            stockStatus, averageRating, proImage, ratingCount, purchageNots;
    List<CategoryProductModel> categoryProductModels = new ArrayList<>();
    RecyclerView recyclerView;
    CategoryProductAdapter categoryProductAdapter;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);
        custToolbar = findViewById(R.id.cust_toolbar);
        linearBack = (LinearLayout) custToolbar.findViewById(R.id.ll_back);
        titleName = (TextView) custToolbar.findViewById(R.id.tvtitle);
        titleName.setText("Category Products");
        linearBack.setOnClickListener(this);

        utilities.displayProgressDialog(this, "Please wait...", false);
        nkService = ApiClient.getClient().create(ApiInterface.class);
        connectionDetector = new ConnectionDetector(this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        Bundle b = getIntent().getExtras();
        if (b != null) {

            categoryId = b.getString("CATEGORY_ID");
        }
        System.out.println("categoryId***" + categoryId);
        if (connectionDetector.isConnectingToInternet()) {

            categoryProductAPI();
        } else {
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            utilities.cancelProgressDialog();
        }
    }

    private void categoryProductAPI() {
        try {

            Call<JsonArray> call = nkService.getCategoryProduct(categoryId, CommonConstant.CONSUMER_KEY, CommonConstant.CONSUMER_SECRET);
            call.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    Log.i("jsonObject***URL", "" + response.raw().request().url().toString());
                    utilities.cancelProgressDialog();

                    String resStr = String.valueOf(response.body());
                    System.out.println("Ressss" + response.body());
                   if (response.body().size()>0) {
                       try {
                           JSONArray jsonArray = new JSONArray(resStr);
                           for (int i = 0; i < jsonArray.length(); i++) {
                               JSONObject jsonObject = jsonArray.getJSONObject(i);

                               proId = jsonObject.getString("id").toString();
                               proName = jsonObject.getString("name").toString();
                               proSlug = jsonObject.getString("slug").toString();
                               proType = jsonObject.getString("type").toString();
                               proStatus = jsonObject.getString("status").toString();
                               proDesc = jsonObject.getString("description").toString();
                               proShotDesc = jsonObject.getString("short_description").toString();
                               proPrice = jsonObject.getString("price").toString();
                               proRegularprice = jsonObject.getString("regular_price").toString();
                               proSalePrice = jsonObject.getString("sale_price").toString();
                               proTaxStatus = jsonObject.getString("tax_status").toString();
                               stockStatus = jsonObject.getString("stock_status").toString();
                               averageRating = jsonObject.getString("average_rating").toString();
                               ratingCount = jsonObject.getString("rating_count").toString();
                               purchageNots = jsonObject.getString("purchase_note").toString();
                               JSONArray jsonArray1 = jsonObject.getJSONArray("images");
                               for (int j = 0; j < jsonArray1.length(); j++) {
                                   proImage = jsonArray1.getJSONObject(j).getString("src").toString();
                               }
                               categoryProductModels.add(new CategoryProductModel(
                                       proId, proName, proSlug, proType, proStatus, proShotDesc, proDesc, proPrice, proRegularprice, proSalePrice, proTaxStatus,
                                       stockStatus, averageRating, proImage, ratingCount, purchageNots
                               ));
                           }
                           categoryProductLoads(categoryProductModels);

                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }else {
                       Toast.makeText(getApplicationContext(),"No have Data",Toast.LENGTH_LONG).show();
                   }


                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    utilities.cancelProgressDialog();
                    Log.i("ERROR**JSONObject", t.toString());

                }
            });
        } catch (Exception e) {

        }
    }

    private void categoryProductLoads(List<CategoryProductModel> categoryProductModels) {
        categoryProductAdapter =  new CategoryProductAdapter(this,categoryProductModels);
        recyclerView.setAdapter(categoryProductAdapter);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
        Intent i2= new Intent(CategoryProduct_Activity.this,AllCategoryList_Activity.class);
        startActivity(i2);
        finish();
       // CategoryProduct_Activity.this.finish();
    }
}
