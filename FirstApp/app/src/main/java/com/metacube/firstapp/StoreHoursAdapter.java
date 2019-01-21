package com.metacube.firstapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StoreHoursAdapter extends PagerAdapter {

    private List<StoreHoursModel> storeHoursModels;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private TextView dayTv, storeTimingTv;

    public StoreHoursAdapter(List<StoreHoursModel> storeHoursModels, Context mContext) {
        this.storeHoursModels = storeHoursModels;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return storeHoursModels.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.store_hours_card, container, false);
        dayTv = view.findViewById(R.id.day_tv);
        storeTimingTv = view.findViewById(R.id.store_timing_tv);

        dayTv.setText(storeHoursModels.get(position).getDay());
        storeTimingTv.setText(storeHoursModels.get(position).getTiming());
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
