package com.team5.greenpay.domain.activity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreActivityRepository extends JpaRepository<StoreActivity, Long> {

    List<StoreActivity> findByStoreStoreIdAndActiveTrue(Long storeId);
}
