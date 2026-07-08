package com.team5.greenpay.api.certification;

import com.team5.greenpay.domain.certification.Certification;
import java.math.BigDecimal;

public record CertificationResponse(
        Long certificationId,
        String storeName,
        int rewardPoint,
        BigDecimal carbonReductionKg,
        int currentPointBalance
) {

    public static CertificationResponse of(Certification certification, int currentPointBalance) {
        return new CertificationResponse(
                certification.getCertificationId(),
                certification.getStore().getStoreName(),
                certification.getRewardPoint(),
                certification.getCarbonReductionKg(),
                currentPointBalance
        );
    }
}
