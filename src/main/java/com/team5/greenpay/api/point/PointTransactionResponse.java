package com.team5.greenpay.api.point;

import com.team5.greenpay.domain.point.PointTransaction;
import com.team5.greenpay.domain.point.PointType;
import java.time.LocalDateTime;

public record PointTransactionResponse(
        Long pointId,
        PointType pointType,
        int amount,
        String description,
        LocalDateTime createdAt
) {

    public static PointTransactionResponse from(PointTransaction transaction) {
        return new PointTransactionResponse(
                transaction.getPointId(),
                transaction.getPointType(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getCreatedAt()
        );
    }
}
