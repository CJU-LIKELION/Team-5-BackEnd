package com.team5.greenpay.domain.point;

import com.team5.greenpay.domain.certification.Certification;
import com.team5.greenpay.domain.common.BaseTimeEntity;
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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "point_transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointTransaction extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certification_id")
    private Certification certification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PointType pointType;

    @Column(nullable = false)
    private int amount;

    private String description;

    public PointTransaction(User user, Certification certification, PointType pointType, int amount, String description) {
        this.user = user;
        this.certification = certification;
        this.pointType = pointType;
        this.amount = amount;
        this.description = description;
    }
}
