package com.team5.greenpay.api.store;

import com.team5.greenpay.domain.activity.StoreActivity;
import com.team5.greenpay.domain.store.Store;
import java.math.BigDecimal;
import java.util.List;

public record StoreResponse(
        Long storeId,
        String storeName,
        String category,
        String address,
        BigDecimal latitude,
        BigDecimal longitude,
        String description,
        List<ActivityResponse> activities
) {

    public static StoreResponse of(Store store, List<StoreActivity> activities) {
        return new StoreResponse(
                store.getStoreId(),
                store.getStoreName(),
                store.getCategory(),
                store.getAddress(),
                store.getLatitude(),
                store.getLongitude(),
                store.getDescription(),
                activities.stream()
                        .map(StoreActivity::getActivity)
                        .map(ActivityResponse::from)
                        .toList()
        );
    }
}
