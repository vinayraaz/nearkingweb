package com.nearking_web.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nearking_web.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Home on 21-09-2016.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mResources;

    public ViewPagerAdapter(Context mContext, List<String> gallery_images) {
        this.mContext = mContext;
        this.mResources = gallery_images;
        notifyDataSetChanged();
    }




    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_restro_gallery_adapter, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
        Picasso.with(mContext).load(mResources.get(position))
                .error(R.color.white)
                .placeholder(R.drawable.nk1)
                .into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
