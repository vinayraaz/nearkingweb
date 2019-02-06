package com.nearking_web.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nearking_web.Activity.ProductDetails_Activity;
import com.nearking_web.R;
import com.nearking_web.model.HomeProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.ViewProduct> {
    Context context;
    List<HomeProduct> homeProducts = new ArrayList<>();
    String proId, proName, proSlug, proType, proStatus, proShotDesc, proDesc, proPrice, proRegularprice,
            proSalePrice, proTaxStatus,stockStatus, averageRating, proImage;
    public HomeProductAdapter(Context context, List<HomeProduct> homeProducts) {
        this.context = context;
        this.homeProducts = homeProducts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_details, viewGroup, false);

        return new HomeProductAdapter.ViewProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewProduct holder, final int position) {
        String proImagevalue, finalProImage,proNamevalue,proPricevalue;
        proNamevalue = homeProducts.get(position).getProName();
        proPricevalue = homeProducts.get(position).getProPrice();
        proImagevalue = homeProducts.get(position).getProImage();

       // finalProImage = proImagevalue.substring(1, proImagevalue.length() - 1);
        holder.tvProductName.setText(proNamevalue);


        Picasso.with(context)
                .load(proImagevalue)
                .placeholder(R.drawable.nk1)
                .error(R.drawable.nk1)
                .fit()
                .into(holder.productImage);
        holder.tvProductPrice.setText("Rs."+proPricevalue);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                proId = homeProducts.get(position).getProId();
                proName = homeProducts.get(position).getProName();
                proSlug = homeProducts.get(position).getProSlug();
                proType = homeProducts.get(position).getProType();
                proStatus = homeProducts.get(position).getProStatus();
                proShotDesc = homeProducts.get(position).getProShotDesc();
                proDesc = homeProducts.get(position).getProDesc();
                proPrice = homeProducts.get(position).getProPrice();
                proRegularprice = homeProducts.get(position).getProRegularprice();
                proSalePrice = homeProducts.get(position).getProSalePrice();
                proTaxStatus = homeProducts.get(position).getProTaxStatus();
                stockStatus = homeProducts.get(position).getStockStatus();
                averageRating = homeProducts.get(position).getAverageRating();
                proImage = homeProducts.get(position).getProImage();

                Intent i2 = new Intent(context,ProductDetails_Activity.class);
                i2.putExtra("proId",proId);
                i2.putExtra("proName",proName);
                i2.putExtra("proSlug",proSlug);
                i2.putExtra("proType",proType);
                i2.putExtra("proStatus",proStatus);
                i2.putExtra("proShotDesc",proShotDesc);
                i2.putExtra("proDesc",proDesc);
                i2.putExtra("proPrice",proPrice);
                i2.putExtra("proRegularprice",proRegularprice);
                i2.putExtra("proSalePrice",proSalePrice);
                i2.putExtra("proTaxStatus",proTaxStatus);
                i2.putExtra("stockStatus",stockStatus);
                i2.putExtra("averageRating",averageRating);
                i2.putExtra("proImage",proImage);
                context.startActivity(i2);

            }
        });


    }

    @Override
    public int getItemCount() {
        return homeProducts.size();
    }

    public class ViewProduct extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvProductName, tvProductPrice;
        public ImageView productImage;

        public ViewProduct(@NonNull View itemView) {
            super(itemView);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            productImage = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
