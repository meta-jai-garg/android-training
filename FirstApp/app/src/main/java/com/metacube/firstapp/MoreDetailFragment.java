package com.metacube.firstapp;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MoreDetailFragment extends Fragment implements View.OnClickListener {

    private GridView gridView;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerViewGridAdapter adapter;
    private ImageView facebookLinkImg, instagramLinkImg;
    private Integer[] mThumbIds = null;
    private String[] mTexts = null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_more, container, false);
        init(view);

        facebookLinkImg.setOnClickListener(this);
        instagramLinkImg.setOnClickListener(this);

        return view;
    }

    private void init(View view) {
        mThumbIds = new Integer[]{R.drawable.profile, R.drawable.invite_friends, R.drawable
                .history, R.drawable.need_help, R.drawable.fill_6, R.drawable.logout};
        mTexts = new String[]{getString(R.string.profile), getString(R.string.invite_friends),
                getString(R.string.history), getString(R.string.need_help), getString(R.string
                .fill_6), getString(R.string.logout)};

        recyclerView = view.findViewById(R.id.gridRecyclerView);
//        gridView = view.findViewById(R.id.moreDetailsGridView);
        facebookLinkImg = view.findViewById(R.id.facebookLinkImg);
        instagramLinkImg = view.findViewById(R.id.instagramLinkImg);
//        gridView.setAdapter(new GridAdapter(getContext(), mThumbIds, mTexts));
        layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        ((GridLayoutManager) layoutManager).setSpanCount(2);
        adapter = new RecyclerViewGridAdapter(mThumbIds, mTexts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull
                    RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(32, 32, 32, 32);
            }
        });
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
