package com.team5.greenpay.api.point;

import com.team5.greenpay.domain.point.PointTransactionRepository;
import com.team5.greenpay.domain.user.User;
import com.team5.greenpay.global.prototype.PrototypeUserProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/points")
@RequiredArgsConstructor
public class PointController {

    private final PrototypeUserProvider prototypeUserProvider;
    private final PointTransactionRepository pointTransactionRepository;

    @GetMapping
    public List<PointTransactionResponse> getPointTransactions() {
        User user = prototypeUserProvider.getCurrentUser();
        return pointTransactionRepository.findByUserUserIdOrderByCreatedAtDesc(user.getUserId()).stream()
                .map(PointTransactionResponse::from)
                .toList();
    }
}
