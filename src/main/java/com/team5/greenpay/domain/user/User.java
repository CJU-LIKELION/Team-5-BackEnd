package com.team5.greenpay.domain.user;

import com.team5.greenpay.domain.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;

    @Column(nullable = false)
    private int pointBalance;

    public User(String email, String name, String phone, UserRole role) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.pointBalance = 0;
    }

    public void addPoints(int amount) {
        this.pointBalance += amount;
    }

    public void usePoints(int amount) {
        if (this.pointBalance < amount) {
            throw new IllegalArgumentException("포인트가 부족합니다.");
        }
        this.pointBalance -= amount;
    }
}
