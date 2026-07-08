package com.team5.greenpay.global.prototype;

import com.team5.greenpay.domain.user.User;
import com.team5.greenpay.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrototypeUserProvider {

    public static final String DEMO_USER_EMAIL = "demo@greenpay.local";

    private final UserRepository userRepository;

    public User getCurrentUser() {
        return userRepository.findByEmail(DEMO_USER_EMAIL)
                .orElseThrow(() -> new IllegalStateException("임시 사용자가 초기화되지 않았습니다."));
    }
}
