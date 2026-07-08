package com.team5.greenpay.domain.certification;

import com.team5.greenpay.domain.common.BaseTimeEntity;
import com.team5.greenpay.domain.qr.StoreQrCode;
import com.team5.greenpay.domain.store.Store;
import com.team5.greenpay.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "certifications")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Certification extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "qr_id")
    private StoreQrCode qrCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CertificationStatus status;

    @Column(nullable = false)
    private LocalDate certifiedDate;

    @Column(nullable = false)
    private int rewardPoint;

    @Column(nullable = false, precision = 8, scale = 3)
    private BigDecimal carbonReductionKg;

    public Certification(User user, Store store, StoreQrCode qrCode, int rewardPoint, BigDecimal carbonReductionKg) {
        this.user = user;
        this.store = store;
        this.qrCode = qrCode;
        this.status = CertificationStatus.APPROVED;
        this.certifiedDate = LocalDate.now();
        this.rewardPoint = rewardPoint;
        this.carbonReductionKg = carbonReductionKg;
    }

    public void cancel() {
        this.status = CertificationStatus.CANCELED;
    }
}
