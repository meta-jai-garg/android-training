package com.metacube.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
//    private Integer[] mThumbIds = {
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7,
//            R.drawable.sample_0, R.drawable.sample_1,
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7,
//            R.drawable.sample_0, R.drawable.sample_1,
//            R.drawable.sample_2, R.drawable.sample_3,
//            R.drawable.sample_4, R.drawable.sample_5,
//            R.drawable.sample_6, R.drawable.sample_7
//    };
//
//    private String[] mTexts = {
//            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
//            "16", "17", "18", "19", "20", "21", "22"
//    };

    private Integer[] mThumbIds = {R.drawable.profile, R.drawable.invite_friends, R.drawable
            .history, R.drawable.need_help, R.drawable.fill_6, R.drawable.logout};
    private String[] mTexts;

    public ImageAdapter(Context c) {
        mContext = c;
        mTexts = new String[]{c.getString(R.string.profile), c.getString(R.string.invite_friends), c
                .getString(R.string.history), c.getString(R.string.need_help), c.getString(R.string
                .fill_6), c.getString(R.string.logout)};
    }

    public int getCount() {
        return mThumbIds.length;
//        return 3;
    }


    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
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
