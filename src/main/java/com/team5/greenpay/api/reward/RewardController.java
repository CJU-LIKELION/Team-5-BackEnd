package com.team5.greenpay.api.reward;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    @GetMapping
    public List<RewardResponse> getRewards() {
        return rewardService.getRewards();
    }

    @PostMapping("/{rewardId}/exchange")
    public RewardExchangeResponse exchangeReward(@PathVariable Long rewardId) {
        return rewardService.exchangeReward(rewardId);
    }
}
