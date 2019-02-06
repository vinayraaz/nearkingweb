package com.nearking_web.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nearking_web.Activity.ProductList_Activity;
import com.nearking_web.R;

import java.util.ArrayList;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolderAdapter> {

    Context context;
    ArrayList automobileList;

    public SubCategoryAdapter(Context context, ArrayList automobileList) {
        this.context = context;
        this.automobileList = automobileList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subcategory, viewGroup, false);
        return new SubCategoryAdapter.ViewHolderAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapter holder, final int position) {
        final String subCategoryName = automobileList.get(position).toString();
        holder.subName.setText(automobileList.get(position).toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sub_category = new Intent(context, ProductList_Activity.class);
                sub_category.putExtra("SUB_CATEGORY",subCategoryName);
             //   sub_category.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(sub_category);

            }
        });
    }

    @Override
    public int getItemCount() {
        return automobileList.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView subName;

        public ViewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            subName = (TextView) itemView.findViewById(R.id.subname);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
