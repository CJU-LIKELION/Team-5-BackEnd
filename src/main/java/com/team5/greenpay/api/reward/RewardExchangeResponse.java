package com.team5.greenpay.api.reward;

import com.team5.greenpay.domain.reward.RewardExchange;
import com.team5.greenpay.domain.reward.RewardExchangeStatus;

public record RewardExchangeResponse(
        Long exchangeId,
        String rewardName,
        int pointUsed,
        RewardExchangeStatus status,
        int currentPointBalance
) {

    public static RewardExchangeResponse of(RewardExchange exchange, int currentPointBalance) {
        return new RewardExchangeResponse(
                exchange.getExchangeId(),
                exchange.getReward().getName(),
                exchange.getPointUsed(),
                exchange.getStatus(),
                currentPointBalance
        );
    }
}
