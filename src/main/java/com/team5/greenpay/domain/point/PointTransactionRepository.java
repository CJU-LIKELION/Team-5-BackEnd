package com.team5.greenpay.domain.point;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointTransactionRepository extends JpaRepository<PointTransaction, Long> {

    List<PointTransaction> findByUserUserIdOrderByCreatedAtDesc(Long userId);
}
