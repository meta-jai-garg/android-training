package com.metacube.firstapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MoreDetailFragment extends Fragment implements View.OnClickListener {

    private GridView gridView;
    private ImageView facebookLinkImg, instagramLinkImg;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_detail, container, false);
        gridView = view.findViewById(R.id.moreDetailsGridView);
        facebookLinkImg = view.findViewById(R.id.facebookLinkImg);
        instagramLinkImg = view.findViewById(R.id.instagramLinkImg);
        facebookLinkImg.setOnClickListener(this);
        instagramLinkImg.setOnClickListener(this);
        gridView.setAdapter(new ImageAdapter(getContext()));
        gridView.setNumColumns(2);
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("123456", "onScrollStateChanged: ");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                Log.d("123456", "onScroll: ");
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.facebookLinkImg:
                Toast.makeText(getContext(), "Facebook", Toast.LENGTH_SHORT).show();
                break;
            case R.id.instagramLinkImg:
                Toast.makeText(getContext(), "Instagram", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
