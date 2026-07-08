package com.team5.greenpay.api.reward;

import com.team5.greenpay.domain.reward.Reward;

public record RewardResponse(
        Long rewardId,
        String name,
        String description,
        int pointCost,
        int stock
) {

    public static RewardResponse from(Reward reward) {
        return new RewardResponse(
                reward.getRewardId(),
                reward.getName(),
                reward.getDescription(),
                reward.getPointCost(),
                reward.getStock()
        );
    }
}
