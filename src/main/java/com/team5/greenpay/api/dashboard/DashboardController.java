package com.team5.greenpay.api.dashboard;

import com.team5.greenpay.api.point.PointTransactionResponse;
import com.team5.greenpay.domain.certification.CertificationRepository;
import com.team5.greenpay.domain.certification.CertificationStatus;
import com.team5.greenpay.domain.point.PointTransactionRepository;
import com.team5.greenpay.domain.user.User;
import com.team5.greenpay.global.prototype.PrototypeUserProvider;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final PrototypeUserProvider prototypeUserProvider;
    private final CertificationRepository certificationRepository;
    private final PointTransactionRepository pointTransactionRepository;

    @GetMapping
    public DashboardResponse getDashboard() {
        User user = prototypeUserProvider.getCurrentUser();
        BigDecimal totalCarbon = certificationRepository.sumCarbonReductionKgByUserId(user.getUserId());
        BigDecimal treeEquivalent = totalCarbon.divide(new BigDecimal("6.6"), 2, RoundingMode.HALF_UP);

        return new DashboardResponse(
                user.getPointBalance(),
                certificationRepository.countByUserUserIdAndStatus(user.getUserId(), CertificationStatus.APPROVED),
                totalCarbon,
                treeEquivalent,
                pointTransactionRepository.findByUserUserIdOrderByCreatedAtDesc(user.getUserId()).stream()
                        .limit(5)
                        .map(PointTransactionResponse::from)
                        .toList()
        );
    }
}
