package com.metacube.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] mThumbIds;
    private String[] mTexts;

    public GridAdapter(Context mContext, Integer[] mThumbIds, String[] mTexts) {
        this.mContext = mContext;
        this.mThumbIds = mThumbIds;
        this.mTexts = mTexts;
    }

    public int getCount() {
        return mThumbIds.length;
    }


    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View gridItem;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            gridItem = inflater.inflate(R.layout.grid_item, null);
            TextView tv = gridItem.findViewById(R.id.android_gridview_text);
            ImageView iv = gridItem.findViewById(R.id.android_gridview_image);
            tv.setText(mTexts[position]);
            iv.setImageResource(mThumbIds[position]);
        } else {
            gridItem = convertView;
        }
        return gridItem;
    }
}
