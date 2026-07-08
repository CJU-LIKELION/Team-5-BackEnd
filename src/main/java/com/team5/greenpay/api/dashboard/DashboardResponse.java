package com.team5.greenpay.api.dashboard;

import com.team5.greenpay.api.point.PointTransactionResponse;
import java.math.BigDecimal;
import java.util.List;

public record DashboardResponse(
        int pointBalance,
        long certificationCount,
        BigDecimal totalCarbonReductionKg,
        BigDecimal treeEquivalent,
        List<PointTransactionResponse> recentPointTransactions
) {
}
