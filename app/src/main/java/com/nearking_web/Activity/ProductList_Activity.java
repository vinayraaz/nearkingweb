package com.nearking_web.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nearking_web.Adapter.CategoryProductAdapter;
import com.nearking_web.R;
import com.nearking_web.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductList_Activity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    List<ProductModel> productModels = new ArrayList<>();
    CategoryProductAdapter categoryProductAdapter;
    GridLayoutManager gridLayoutManager;
    String subCategoryName;
    View custToolbar;
    LinearLayout linearBack;
    TextView titleName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);
        custToolbar = findViewById(R.id.cust_toolbar);
        linearBack = (LinearLayout) custToolbar.findViewById(R.id.ll_back);
        titleName = (TextView) custToolbar.findViewById(R.id.tvtitle);
        linearBack.setOnClickListener(this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            subCategoryName = b.getString("SUB_CATEGORY");
        }
        titleName.setText(subCategoryName);

        productModels.clear();
        for (int i=0;i<=10;i++){
            productModels.add(new ProductModel("TV","15000",R.drawable.nk1));
        }
      /*  categoryProductAdapter =  new CategoryProductAdapter(this,productModels);
        recyclerView.setAdapter(categoryProductAdapter);
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);*/
    }

    @Override
    public void onClick(View v) {

    }
}
