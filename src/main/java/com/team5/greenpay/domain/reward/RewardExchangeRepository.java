package com.team5.greenpay.domain.reward;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardExchangeRepository extends JpaRepository<RewardExchange, Long> {

    List<RewardExchange> findByUserUserIdOrderByCreatedAtDesc(Long userId);
}
