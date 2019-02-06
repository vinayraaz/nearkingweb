package com.nearking_web.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nearking_web.Activity.AddCart_Activity;
import com.nearking_web.R;
import com.nearking_web.model.AddCartList;
import com.nearking_web.model.AddCartModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AddCartAdapter extends RecyclerView.Adapter<AddCartAdapter.CartViewHolder> {
    Context context;
    List<AddCartList> addCartModels = new ArrayList<>();



    public AddCartAdapter(Context context, List<AddCartList> addCartModels) {
        this.context = context;
        this.addCartModels = addCartModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_list_details, viewGroup, false);
        return new AddCartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {
        holder.productName.setText(addCartModels.get(position).getProduct_name());
        holder.productPrice.setText("Rs. "+addCartModels.get(position).getProduct_price());
        String proimage = addCartModels.get(position).getProduct_image();
        Picasso.with(context)
                .load(proimage)
                .placeholder(R.drawable.nk1)
                .error(R.drawable.nk1)
                .fit()
                .into(holder.productImage);
        holder.productQty.setText("Qty : "+addCartModels.get(position).getProduct_type());
        holder.Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCartModels.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return addCartModels.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView productName, productPrice,productQty;
        ImageView productImage,Remove;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.cart_imageview);
            productName = (TextView) itemView.findViewById(R.id.cart_product_name);
            productPrice = (TextView) itemView.findViewById(R.id.cart_product_price);
            Remove = (ImageView) itemView.findViewById(R.id.remove);
            productQty = (TextView) itemView.findViewById(R.id.qty);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
