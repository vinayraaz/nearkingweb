package com.nearking_web.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nearking_web.Adapter.AllCategoryListAdapter;
import com.nearking_web.Api.ApiClient;
import com.nearking_web.Api.ApiInterface;
import com.nearking_web.R;
import com.nearking_web.extra.CommonConstant;
import com.nearking_web.extra.Utilities;
import com.nearking_web.model.CategoryModel;
import com.nearking_web.model.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*1 Category list & categoryAdapter*
2 CategoryProduct_Activity
3 CategoryProductAdapter
4 ProductDetails_Activity
 */
public class AllCategoryList_Activity extends NearKingNavigation implements View.OnClickListener {
    View custToolbar;
    LinearLayout linearBack;
    TextView titleName;
    RecyclerView recyclerView;
    Utilities utilities;
    ApiInterface nkService;
    List<CategoryModel> categoryModels = new ArrayList<>();
    AllCategoryListAdapter categoryAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_layout);
        custToolbar = findViewById(R.id.cust_toolbar);
        nkService = ApiClient.getClient().create(ApiInterface.class);
        linearBack = (LinearLayout) custToolbar.findViewById(R.id.ll_back);
        titleName = (TextView) custToolbar.findViewById(R.id.tvtitle);
        titleName.setText("All Category");
        linearBack.setOnClickListener(this);
        recyclerView =(RecyclerView)findViewById(R.id.recyclerView);
        utilities.displayProgressDialog(this, "Please wait...", false);
        CategoryList();

    }

    private void CategoryList() {

        Call<List<CategoryResponse>> call = nkService.getCategory(CommonConstant.CONSUMER_KEY, CommonConstant.CONSUMER_SECRET);
        call.enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {
                utilities.cancelProgressDialog();
                if (response.body().size()>0) {
                    for (int i = 0; i < response.body().size(); i++) {
                        categoryModels.add(new CategoryModel(response.body().get(i).getId(), response.body().get(i).getName()));
                    }
                    CategoryLoadMethod(categoryModels);
                }else {

                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
                utilities.cancelProgressDialog();
                System.out.println("REsponse* Error**" + t.toString());

            }
        });

    }


    private void CategoryLoadMethod(List<CategoryModel> categoryModels) {
        categoryAdapter =  new AllCategoryListAdapter(this,categoryModels);
        recyclerView.setAdapter(categoryAdapter);
       LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_back:
                Intent backintent = new Intent(AllCategoryList_Activity.this, NearKingHome.class);
               // backintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(backintent);
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backintent = new Intent(AllCategoryList_Activity.this, NearKingHome.class);
        //backintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(backintent);

    }
}
