package com.team5.greenpay.api.store;

import com.team5.greenpay.domain.activity.EcoActivity;
import java.math.BigDecimal;

public record ActivityResponse(
        Long activityId,
        String activityName,
        int rewardPoint,
        BigDecimal carbonReductionKg
) {

    public static ActivityResponse from(EcoActivity activity) {
        return new ActivityResponse(
                activity.getActivityId(),
                activity.getActivityName(),
                activity.getRewardPoint(),
                activity.getCarbonReductionKg()
        );
    }
}
