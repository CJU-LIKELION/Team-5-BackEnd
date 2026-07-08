package com.team5.greenpay.domain.reward;

import com.team5.greenpay.domain.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "rewards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reward extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rewardId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int pointCost;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private boolean active;

    public Reward(String name, String description, int pointCost, int stock) {
        this.name = name;
        this.description = description;
        this.pointCost = pointCost;
        this.stock = stock;
        this.active = true;
    }

    public void decreaseStock() {
        if (this.stock <= 0) {
            throw new IllegalArgumentException("리워드 재고가 부족합니다.");
        }
        this.stock -= 1;
    }

    public void deactivate() {
        this.active = false;
    }
}
