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
import com.nearking_web.model.CategoryProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryProductAdapter extends RecyclerView.Adapter<CategoryProductAdapter.ProductViewHolder> {
    private Context context;
    List<CategoryProductModel> categoryProductModels = new ArrayList<>();
    String proId, proName, proSlug, proType, proStatus, proShotDesc, proDesc, proPrice, proRegularprice,
            proSalePrice, proTaxStatus,stockStatus, averageRating, proImage;

    public CategoryProductAdapter(Context context, List<CategoryProductModel> categoryProductModels) {
        this.context = context;
        this.categoryProductModels = categoryProductModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_details, viewGroup, false);
        return new CategoryProductAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
        holder.tvProductName.setText(categoryProductModels.get(position).getProName());
        holder.tvProductPrice.setText("Rs."+categoryProductModels.get(position).getProPrice());
        Picasso.with(context)
                .load(categoryProductModels.get(position).getProImage())
                .placeholder(R.drawable.nk1)
                .error(R.drawable.nk1)
                .fit()
                .into(holder.productImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proId = categoryProductModels.get(position).getProId();
                proName = categoryProductModels.get(position).getProName();
                proSlug = categoryProductModels.get(position).getProSlug();
                proType = categoryProductModels.get(position).getProType();
                proStatus = categoryProductModels.get(position).getProStatus();
                proShotDesc = categoryProductModels.get(position).getProShotDesc();
                proDesc = categoryProductModels.get(position).getProDesc();
                proPrice = categoryProductModels.get(position).getProPrice();
                proRegularprice = categoryProductModels.get(position).getProRegularprice();
                proSalePrice = categoryProductModels.get(position).getProSalePrice();
                proTaxStatus = categoryProductModels.get(position).getProTaxStatus();
                stockStatus = categoryProductModels.get(position).getStockStatus();
                averageRating = categoryProductModels.get(position).getAverageRating();
                proImage = categoryProductModels.get(position).getProImage();

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
        return categoryProductModels.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvProductName,tvProductPrice;
        public ImageView productImage;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = (TextView)itemView.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView)itemView.findViewById(R.id.tvProductPrice);
            productImage = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
