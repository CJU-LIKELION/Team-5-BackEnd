package com.team5.greenpay.domain.certification;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

    boolean existsByUserUserIdAndStoreStoreIdAndCertifiedDate(Long userId, Long storeId, LocalDate certifiedDate);

    List<Certification> findByUserUserIdOrderByCreatedAtDesc(Long userId);

    long countByUserUserIdAndStatus(Long userId, CertificationStatus status);

    @Query("select coalesce(sum(c.carbonReductionKg), 0) from Certification c where c.user.userId = :userId and c.status = com.team5.greenpay.domain.certification.CertificationStatus.APPROVED")
    java.math.BigDecimal sumCarbonReductionKgByUserId(@Param("userId") Long userId);
}
