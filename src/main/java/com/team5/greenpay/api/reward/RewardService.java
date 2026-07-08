package com.team5.greenpay.api.reward;

import com.team5.greenpay.domain.point.PointTransaction;
import com.team5.greenpay.domain.point.PointTransactionRepository;
import com.team5.greenpay.domain.point.PointType;
import com.team5.greenpay.domain.reward.Reward;
import com.team5.greenpay.domain.reward.RewardExchange;
import com.team5.greenpay.domain.reward.RewardExchangeRepository;
import com.team5.greenpay.domain.reward.RewardRepository;
import com.team5.greenpay.domain.user.User;
import com.team5.greenpay.global.prototype.PrototypeUserProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final PrototypeUserProvider prototypeUserProvider;
    private final RewardRepository rewardRepository;
    private final RewardExchangeRepository rewardExchangeRepository;
    private final PointTransactionRepository pointTransactionRepository;

    @Transactional(readOnly = true)
    public List<RewardResponse> getRewards() {
        return rewardRepository.findByActiveTrue().stream()
                .map(RewardResponse::from)
                .toList();
    }

    @Transactional
    public RewardExchangeResponse exchangeReward(Long rewardId) {
        User user = prototypeUserProvider.getCurrentUser();
        Reward reward = rewardRepository.findById(rewardId)
                .filter(Reward::isActive)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "리워드를 찾을 수 없습니다."));

        user.usePoints(reward.getPointCost());
        reward.decreaseStock();

        RewardExchange exchange = rewardExchangeRepository.save(new RewardExchange(user, reward));
        pointTransactionRepository.save(new PointTransaction(
                user,
                null,
                PointType.USE,
                -reward.getPointCost(),
                reward.getName() + " 교환"
        ));

        return RewardExchangeResponse.of(exchange, user.getPointBalance());
    }
}
