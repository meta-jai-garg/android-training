package com.metacube.firstapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RewardsFragment extends Fragment {

    private BottomSheetBehavior bottomSheetBehavior;
    private LinearLayout linearLayoutBottomSheet;
    private MaterialButton earnRewardBtn, redeemRewardBtn;
    private ImageView upArrowImg;
    private TextView swipeUpTv;
    private RecyclerView rewardsRecyclerView;
    private List<RewardsModel> rewardsModelList;
    private RewardsRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public RewardsFragment() {
    }

    public static RewardsFragment newInstance() {
        return new RewardsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rewards, container, false);
        fillListView();
        init(view);
        methodListener();
        return view;
    }

    private void init(View view) {
        linearLayoutBottomSheet = view.findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(linearLayoutBottomSheet);
        earnRewardBtn = view.findViewById(R.id.earn_reward_btn);
        redeemRewardBtn = view.findViewById(R.id.redeem_reward_btn);
        upArrowImg = view.findViewById(R.id.up_arrow_img);
        swipeUpTv = view.findViewById(R.id.swipe_up_tv);
        rewardsRecyclerView = view.findViewById(R.id.rewards_recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new RewardsRecyclerViewAdapter(rewardsModelList);
        rewardsRecyclerView.setAdapter(adapter);
        rewardsRecyclerView.setLayoutManager(layoutManager);
    }

    private void methodListener() {
        earnRewardBtn.setOnClickListener(view -> {
        });

        redeemRewardBtn.setOnClickListener(view -> {
        });

        upArrowImg.setOnClickListener(view -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED));

        swipeUpTv.setOnClickListener(view -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED));

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                upArrowImg.setAlpha(1 - v);
                swipeUpTv.setAlpha(1 - v);
            }
        });
    }

    private void fillListView() {
        rewardsModelList = new ArrayList<>();
        rewardsModelList.add(
                new RewardsModel(
                        R.drawable.cover,
                        getString(R.string.expire_in_3_days),
                        getString(R.string.free_ramen),
                        getString(R.string.reward_description)
                )
        );
        rewardsModelList.add(
                new RewardsModel(
                        R.drawable.cover,
                        getString(R.string.expire_in_3_days),
                        getString(R.string.free_ramen),
                        getString(R.string.reward_description)
                )
        );
        rewardsModelList.add(
                new RewardsModel(
                        R.drawable.cover,
                        getString(R.string.expire_in_3_days),
                        getString(R.string.free_ramen),
                        getString(R.string.reward_description)
                )
        );
        rewardsModelList.add(
                new RewardsModel(
                        R.drawable.cover,
                        getString(R.string.expire_in_3_days),
                        getString(R.string.free_ramen),
                        getString(R.string.reward_description)
                )
        );
        rewardsModelList.add(
                new RewardsModel(
                        R.drawable.cover,
                        getString(R.string.expire_in_3_days),
                        getString(R.string.free_ramen),
                        getString(R.string.reward_description)
                )
        );
    }

}
