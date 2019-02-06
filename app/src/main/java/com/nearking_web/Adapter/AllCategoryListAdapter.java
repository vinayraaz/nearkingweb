package com.nearking_web.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nearking_web.Activity.CategoryProduct_Activity;
import com.nearking_web.R;
import com.nearking_web.model.CategoryModel;
import java.util.ArrayList;
import java.util.List;



public class AllCategoryListAdapter extends RecyclerView.Adapter<AllCategoryListAdapter.CategoryView> {
    Context context;
    List<CategoryModel> categoryModels = new ArrayList<>();


    public AllCategoryListAdapter(Context context, List<CategoryModel> categoryModels) {
        this.context =context;
        this.categoryModels = categoryModels;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CategoryView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_details, viewGroup, false);

        return new AllCategoryListAdapter.CategoryView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryView categoryView, final int i) {

        categoryView.categoryName.setText(categoryModels.get(i).getName());
        categoryView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryId = String.valueOf(categoryModels.get(i).getId());
                Intent intent = new Intent(context,CategoryProduct_Activity.class);
                intent.putExtra("CATEGORY_ID",categoryId);
                context.startActivity(intent);

                //Toast.makeText(context,String.valueOf(categoryModels.get(i).getId()),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class CategoryView extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView categoryName;
        public CategoryView(@NonNull View itemView) {
            super(itemView);
            categoryName = (TextView)itemView.findViewById(R.id.cat_name);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
