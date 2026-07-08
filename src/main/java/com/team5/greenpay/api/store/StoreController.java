package com.team5.greenpay.api.store;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public List<StoreResponse> getStores() {
        return storeService.getStores();
    }

    @GetMapping("/{storeId}")
    public StoreResponse getStore(@PathVariable Long storeId) {
        return storeService.getStore(storeId);
    }
}
