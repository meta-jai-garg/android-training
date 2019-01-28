package com.metacube.firstapp;

import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RewardsRecyclerViewAdapter extends RecyclerView.Adapter<RewardsRecyclerViewAdapter.RewardsHolder> {
    private List<RewardsModel> rewardsModelList;

    public RewardsRecyclerViewAdapter(List<RewardsModel> rewardsModelList) {
        this.rewardsModelList = rewardsModelList;
    }

    @NonNull
    @Override
    public RewardsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(viewGroup.getContext()).inflate(R.layout.rewards_card_item, null);
        return new RewardsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardsHolder rewardsHolder, int i) {
        rewardsHolder.rewardImg.setImageResource(rewardsModelList.get(i).getRewardImg());
        rewardsHolder.rewardExpirationTv.setText(rewardsModelList.get(i).getRewardExpiration());
        rewardsHolder.rewardNameTv.setText(rewardsModelList.get(i).getRewardName());
        rewardsHolder.rewardDescriptionTv.setText(rewardsModelList.get(i).getRewardDescription());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class RewardsHolder extends RecyclerView.ViewHolder {
        private ImageView rewardImg;
        private TextView rewardExpirationTv, rewardNameTv, rewardDescriptionTv;
        private MaterialButton redeemRewardCardBtn;

        public RewardsHolder(@NonNull View itemView) {
            super(itemView);
            rewardImg = itemView.findViewById(R.id.reward_img);
            rewardExpirationTv = itemView.findViewById(R.id.reward_expiration_tv);
            rewardNameTv = itemView.findViewById(R.id.reward_name_tv);
            rewardDescriptionTv = itemView.findViewById(R.id.reward_description_tv);
            redeemRewardCardBtn = itemView.findViewById(R.id.redeem_reward_card_btn);
        }
    }
}
