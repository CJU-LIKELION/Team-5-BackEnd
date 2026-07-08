package com.team5.greenpay.api.user;

import com.team5.greenpay.domain.user.User;
import com.team5.greenpay.domain.user.UserRole;

public record UserResponse(
        Long userId,
        String email,
        String name,
        String phone,
        UserRole role,
        int pointBalance
) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getRole(),
                user.getPointBalance()
        );
    }
}
