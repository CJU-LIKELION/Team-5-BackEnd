package com.team5.greenpay.api.user;

import com.team5.greenpay.global.prototype.PrototypeUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
@RequiredArgsConstructor
public class UserController {

    private final PrototypeUserProvider prototypeUserProvider;

    @GetMapping
    public UserResponse getMe() {
        return UserResponse.from(prototypeUserProvider.getCurrentUser());
    }
}
