package com.team5.greenpay.api.store;

import com.team5.greenpay.domain.activity.StoreActivityRepository;
import com.team5.greenpay.domain.store.StoreRepository;
import com.team5.greenpay.domain.store.StoreStatus;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreActivityRepository storeActivityRepository;

    @Transactional(readOnly = true)
    public List<StoreResponse> getStores() {
        return storeRepository.findByStatus(StoreStatus.APPROVED).stream()
                .map(store -> StoreResponse.of(store, storeActivityRepository.findByStoreStoreIdAndActiveTrue(store.getStoreId())))
                .toList();
    }

    @Transactional(readOnly = true)
    public StoreResponse getStore(Long storeId) {
        return storeRepository.findById(storeId)
                .filter(store -> store.getStatus() == StoreStatus.APPROVED)
                .map(store -> StoreResponse.of(store, storeActivityRepository.findByStoreStoreIdAndActiveTrue(store.getStoreId())))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found."));
    }
}
