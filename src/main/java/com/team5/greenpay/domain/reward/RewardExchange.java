package com.team5.greenpay.domain.reward;

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
@Table(name = "reward_exchanges")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RewardExchange extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exchangeId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reward_id")
    private Reward reward;

    @Column(nullable = false)
    private int pointUsed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RewardExchangeStatus status;

    public RewardExchange(User user, Reward reward) {
        this.user = user;
        this.reward = reward;
        this.pointUsed = reward.getPointCost();
        this.status = RewardExchangeStatus.REQUESTED;
    }

    public void complete() {
        this.status = RewardExchangeStatus.COMPLETED;
    }

    public void cancel() {
        this.status = RewardExchangeStatus.CANCELED;
    }
}
