package com.nearking_web.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.nearking_web.Adapter.HomeProductAdapter;
import com.nearking_web.Adapter.ViewPagerAdapter;
import com.nearking_web.Api.ApiClient;
import com.nearking_web.Api.BannerApiClient;
import com.nearking_web.Api.ApiInterface;
import com.nearking_web.R;
import com.nearking_web.ResponseModel.BannerResponse;
import com.nearking_web.extra.CommonConstant;
import com.nearking_web.extra.ConnectionDetector;
import com.nearking_web.extra.Utilities;
import com.nearking_web.model.HomeProduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearKingHome extends NearKingNavigation implements View.OnClickListener {
    Utilities utilities;
    ViewPagerAdapter mAdapter;
    ViewPager viewPager;
    private int dotsCount;
    private ImageView[] dots;
    private LinearLayout pager_indicator;
    private int page = 0;
    private Handler handler;
    ApiInterface apiService, nkService;
    List<HomeProduct> homeProducts = new ArrayList<>();
    String proId, proName, proSlug, proType, proStatus, proShotDesc, proDesc, proPrice, proRegularprice, proSalePrice, proTaxStatus,
            stockStatus, averageRating, proImage;
    LinearLayout allCategory;
    ConnectionDetector connectionDetector;
    Runnable runnable = new Runnable() {
        public void run() {
            try {
                if (mAdapter.getCount() == page) {
                    page = 0;
                } else {
                    page++;
                }
                viewPager.setCurrentItem(page, true);
                handler.postDelayed(this, 4000);
            } catch (Exception e) {

            }
        }
    };
    RecyclerView recyclerView;
    HomeProductAdapter homeProductAdapter;
    GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll_main_data = (LinearLayout) findViewById(R.id.main_include);
        View child = getLayoutInflater().inflate(R.layout.activity_nearking_home, null);
        ll_main_data.addView(child);
        handler = new Handler();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        apiService = BannerApiClient.getClient().create(ApiInterface.class);
        nkService = ApiClient.getClient().create(ApiInterface.class);
        viewPager = (ViewPager) findViewById(R.id.pager_introduction);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        allCategory = (LinearLayout) findViewById(R.id.all_category);
        allCategory.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        connectionDetector = new ConnectionDetector(this);

        sharpf = getSharedPreferences("NKing_Login", Context.MODE_PRIVATE);
        CommonConstant.LOGIN_STATUS = sharpf.getString("LOGIN_STATUS","0");
        CommonConstant.USERID =sharpf.getString("USER_ID","0");
        CommonConstant.USEREMAIL =sharpf.getString("USER_EMAIL","0");
        CommonConstant.USERLOGIN =sharpf.getString("USER_LOGIN","0");
        CommonConstant.DISPLAYNAME =sharpf.getString("DISPLAY_NAME","0");

        System.out.println(" CommonConstant.LASTNAME***" +  CommonConstant.USEREMAIL+"**"+ CommonConstant.USERLOGIN +"***"+ CommonConstant.DISPLAYNAME);
        if (connectionDetector.isConnectingToInternet()) {
            utilities.displayProgressDialog(this, "Please wait...", false);
            loadBannerImages();
        } else {
            Toast.makeText(NearKingHome.this, "No Internet Connection", Toast.LENGTH_LONG).show();
            //utilities.showInternetError(this, NearKingHome.class);
        }

    }

    private void loadBannerImages() {
        Call<List<BannerResponse>> call = apiService.getBanner();
        call.enqueue(new Callback<List<BannerResponse>>() {
            @Override
            public void onResponse(Call<List<BannerResponse>> call, Response<List<BannerResponse>> response) {
                try {
                    List<String> gallery_images = new ArrayList<String>();
                    for (int i = 0; i < response.body().size(); i++) {
                        gallery_images.add(response.body().get(i).getGuid());
                    }

                    mAdapter = new ViewPagerAdapter(NearKingHome.this, gallery_images);
                    viewPager.setAdapter(mAdapter);

                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {
                            for (int i = 0; i < dotsCount; i++) {
                                dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
                            }

                            dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
                            page = position;
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });

                    try {
                        handler.postDelayed(runnable, 4000);
                    } catch (Exception e) {

                    }

                    setUiPageViewController();
                    productApi();
                    //CategoryList();

                } catch (Exception je) {

                }


            }

            @Override
            public void onFailure(Call<List<BannerResponse>> call, Throwable t) {
                utilities.cancelProgressDialog();
                Log.i("Response**" , t.toString());
            }
        });


    }


    private void productApi() {
        try {
            Call<JsonArray> call = nkService.getProduct(CommonConstant.CONSUMER_KEY, CommonConstant.CONSUMER_SECRET);
            call.enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    utilities.cancelProgressDialog();
                    homeProducts.clear();
                    String resStr = String.valueOf(response.body());

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

                            JSONArray jsonArray1 = jsonObject.getJSONArray("images");
                            for (int j = 0; j < jsonArray1.length(); j++) {
                                proImage = jsonArray1.getJSONObject(j).getString("src").toString();
                            }
                            homeProducts.add(new HomeProduct(
                                    proId, proName, proSlug, proType, proStatus, proShotDesc, proDesc, proPrice, proRegularprice, proSalePrice, proTaxStatus,
                                    stockStatus, averageRating, proImage));
                        }
                        productLoad(homeProducts);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    utilities.cancelProgressDialog();
                    Log.i("ERROR**" , t.toString());
                }
            });

        } catch (Exception e) {

        }
    }

    private void productLoad(List<HomeProduct> homeProducts) {
        homeProductAdapter = new HomeProductAdapter(this, homeProducts);
        recyclerView.setAdapter(homeProductAdapter);
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

   /* private void loadProduct() {
        homeProductAdapter = new HomeProductAdapter(this, homeProducts);
        recyclerView.setAdapter(homeProductAdapter);
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);

    }*/

    private void setUiPageViewController() {
        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_category:
                Intent category_intent = new Intent(NearKingHome.this, AllCategoryList_Activity.class);
             //   category_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(category_intent);


                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

