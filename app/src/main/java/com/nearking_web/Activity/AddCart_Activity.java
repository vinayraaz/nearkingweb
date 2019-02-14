package com.nearking_web.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nearking_web.Adapter.AddCartAdapter;
import com.nearking_web.NKDatabase.DBAdapter;
import com.nearking_web.R;
import com.nearking_web.model.AddCartList;
import com.nearking_web.model.AddCartModel;

import java.util.ArrayList;
import java.util.List;

public class AddCart_Activity extends AppCompatActivity implements View.OnClickListener {

    String proId, proName, proSlug, proType, proStatus, proShotDesc, proDesc, proPrice, proRegularprice,
            proSalePrice, proTaxStatus, stockStatus, averageRating, proImage;
    List<AddCartList> addCartModels = new ArrayList<AddCartList>();
    View custToolbar;
    LinearLayout linearBack;
    TextView titleName;
    RecyclerView recyclerView;
    AddCartAdapter addCartAdapter;
    Integer deleteButto = R.drawable.ic_menu_delete;
    private DBAdapter db;
    LinearLayout placeOrder;
    ArrayList<AddCartList> addcartproductDB;
    double pricevalue = 0, totalPrice = 0;
    String pricevalue1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcart_layout);
        db = new DBAdapter(this);
        custToolbar = findViewById(R.id.cust_toolbar);
        linearBack = (LinearLayout) custToolbar.findViewById(R.id.ll_back);
        titleName = (TextView) custToolbar.findViewById(R.id.tvtitle);
        titleName.setText("Cart Details");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        placeOrder = (LinearLayout) findViewById(R.id.placeorder);
        linearBack.setOnClickListener(this);
        placeOrder.setOnClickListener(this);


        Bundle b = getIntent().getExtras();
        if (b != null) {
            // categoryId = b.getString("CATEGORY_ID");

            proId = b.getString("proId");
            proName = b.getString("proName");
            proSlug = b.getString("proSlug");
            proType = b.getString("proType");
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

            System.out.println("proName***" + proName);
            System.out.println("proImage***" + stockStatus);
        }



        /*addCartModels.add(new AddCartModel(proId, proName, proSlug, proType, proStatus, proShotDesc, proDesc, proPrice, proRegularprice,
                proSalePrice, proTaxStatus, stockStatus, averageRating, proImage, deleteButto));
        addCartLoad(addCartModels);*/

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
            addCartLoad(addCartModels);
        } else {
            Toast.makeText(getApplicationContext(),"No have data in shopping cart",Toast.LENGTH_SHORT).show();
            Intent i2= new Intent(AddCart_Activity.this,NearKingHome.class);
           // i2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i2);
        }

    }


    private void addCartLoad(List<AddCartList> addCartModels) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        addCartAdapter = new AddCartAdapter(this, addCartModels);
        recyclerView.setAdapter(addCartAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.placeorder:

                Intent placeintent = new Intent(AddCart_Activity.this, PlaceOrder_Activity.class);
                placeintent.putExtra("TotalPrice",totalPrice);
                startActivity(placeintent);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i2 = new Intent(AddCart_Activity.this, AllCategoryList_Activity.class);
        startActivity(i2);
    }
}
