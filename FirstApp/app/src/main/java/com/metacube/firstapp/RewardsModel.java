package com.metacube.firstapp;

public class RewardsModel {
    private int rewardImg;
    private String rewardExpiration, rewardName, rewardDescription;

    public RewardsModel(int rewardImg, String rewardExpiration, String rewardName,
                        String rewardDescription) {
        this.rewardImg = rewardImg;
        this.rewardExpiration = rewardExpiration;
        this.rewardName = rewardName;
        this.rewardDescription = rewardDescription;
    }

    public int getRewardImg() {
        return rewardImg;
    }

    public String getRewardExpiration() {
        return rewardExpiration;
    }

    public String getRewardName() {
        return rewardName;
    }

    public String getRewardDescription() {
        return rewardDescription;
    }
}
