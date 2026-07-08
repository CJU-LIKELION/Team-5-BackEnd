package com.team5.greenpay.domain.activity;

import com.team5.greenpay.domain.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "eco_activities")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EcoActivity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @Column(nullable = false, length = 100)
    private String activityName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int rewardPoint;

    @Column(nullable = false, precision = 8, scale = 3)
    private BigDecimal carbonReductionKg;

    public EcoActivity(String activityName, String description, int rewardPoint, BigDecimal carbonReductionKg) {
        this.activityName = activityName;
        this.description = description;
        this.rewardPoint = rewardPoint;
        this.carbonReductionKg = carbonReductionKg;
    }
}
