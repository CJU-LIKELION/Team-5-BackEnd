package com.team5.greenpay.domain.qr;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreQrCodeRepository extends JpaRepository<StoreQrCode, Long> {

    Optional<StoreQrCode> findByQrTokenAndActiveTrue(String qrToken);
}
