package com.nearking_web.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nearking_web.Adapter.SubCategoryAdapter;
import com.nearking_web.R;

import java.util.ArrayList;
import java.util.Arrays;

public class SubCategory_Activity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView subCategoryRecycler;
    SubCategoryAdapter subCAtegoryAdapter;
    String categoryType;
    ArrayList automobileList;
    View custToolbar;
    LinearLayout linearBack;
    TextView titleName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        custToolbar = findViewById(R.id.cust_toolbar);
        linearBack = (LinearLayout) custToolbar.findViewById(R.id.ll_back);
        titleName = (TextView) custToolbar.findViewById(R.id.tvtitle);
        titleName.setText("Sub Category");
        linearBack.setOnClickListener(this);

        subCategoryRecycler = (RecyclerView) findViewById(R.id.recSubcategory);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            categoryType = b.getString("SUB_CATEGORY");
        }

        switch (categoryType) {
            case "1":
                automobileList = new ArrayList<>(Arrays.asList("Auto Replacement Parts", "Car Electronics", "Car Lights", "Car Repair Tools", "Interior Accessories", "Motorcycle Accessories Parts"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
            case "2":
                automobileList = new ArrayList<>(Arrays.asList("Car Electronics","Health Care","Makeup","Nails Art Tools","Shaving Hair Removal","Skin Care"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
            case "3":
                automobileList = new ArrayList<>(Arrays.asList("Computer Components","Computer Peripherals","External Storage","Office Electronics","Tablet Accessories","Tablets"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
            case "4":
                automobileList = new ArrayList<>(Arrays.asList("Accessories Parts","Camera Photo","Electronic Cigarette","Home Audio&Video","Portable Audio &Video","Smart Electronics"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
            case "5":
                automobileList = new ArrayList<>(Arrays.asList("Arts,Crafts Sewing","Festive Party Supplies","Home Decor","Home Storage Organization","Home Textile","Kitchen,Dining Bar"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
            case "6":
                automobileList = new ArrayList<>(Arrays.asList("Bathroom Fixtures","Electrical Equipments Supplies","Hardware","Home Appliances","Kitchen Fixtures","Lights Lighting"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
            case "7":
                automobileList = new ArrayList<>(Arrays.asList("Beads Jewelry Making","Bracelets Bangles","Earrings","Fine Jewelry","Jewelry Sets More","Necklaces Pendants"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
            case "8":
                automobileList = new ArrayList<>(Arrays.asList("Accessories","Jackets Coats","Pants","Shirts","Tops Tees","Underwear"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
            case "9":
                break;
            case "10":
                automobileList = new ArrayList<>(Arrays.asList("Baby Shoes","Boys Baby Clothing","Boys Clothing","Children's Shoes","Girls Baby Clothing","Girls Clothing"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
            case "11":
                break;
            case "12":
                automobileList = new ArrayList<>(Arrays.asList("Communication Equipments","Mobile Phone Parts","Phone Bags Cases"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
            case "13":
                automobileList = new ArrayList<>(Arrays.asList("Men's Boots","Men's Shoes","Shoe Accessories","Women's Boots","Women's Shoes"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
            case "14":
                automobileList = new ArrayList<>(Arrays.asList("Camping Hiking","Cycling","Fishing","Sneakers","Sports Clothing","Swimming"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
            case "15":
                break;
            case "16":
                automobileList = new ArrayList<>(Arrays.asList("Accessories","Bottoms","Intimates","Jackets Coats","Sweaters","TopsTees"));
                subCAtegoryAdapter = new SubCategoryAdapter(this, automobileList);
                break;
        }

        subCategoryRecycler.setLayoutManager(new LinearLayoutManager(this));
        subCategoryRecycler.setAdapter(subCAtegoryAdapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back:
                Intent backintent = new Intent(SubCategory_Activity.this, NearKingHome.class);
              //  backintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(backintent);
                break;
        }
    }
}
