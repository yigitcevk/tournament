package com.dream_tournament.dto.response;

public class ClaimRewardResponse {
    private Integer coins;
    private Boolean rewardsClaimed;

    public ClaimRewardResponse(Integer coins, Boolean rewardsClaimed) {
        this.coins = coins;
        this.rewardsClaimed = rewardsClaimed;
    }
}
