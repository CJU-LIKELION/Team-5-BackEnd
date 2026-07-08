package com.team5.greenpay.global.config;

import com.team5.greenpay.domain.activity.EcoActivity;
import com.team5.greenpay.domain.activity.EcoActivityRepository;
import com.team5.greenpay.domain.activity.StoreActivity;
import com.team5.greenpay.domain.activity.StoreActivityRepository;
import com.team5.greenpay.domain.qr.StoreQrCode;
import com.team5.greenpay.domain.qr.StoreQrCodeRepository;
import com.team5.greenpay.domain.reward.Reward;
import com.team5.greenpay.domain.reward.RewardRepository;
import com.team5.greenpay.domain.store.Store;
import com.team5.greenpay.domain.store.StoreRepository;
import com.team5.greenpay.domain.user.User;
import com.team5.greenpay.domain.user.UserRepository;
import com.team5.greenpay.domain.user.UserRole;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final EcoActivityRepository ecoActivityRepository;
    private final RewardRepository rewardRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final StoreActivityRepository storeActivityRepository;
    private final StoreQrCodeRepository storeQrCodeRepository;

    @Bean
    CommandLineRunner initializeBaseData() {
        return args -> {
            if (ecoActivityRepository.count() == 0) {
                ecoActivityRepository.save(new EcoActivity("텀블러 사용", "일회용 컵 대신 개인 텀블러를 사용합니다.", 300, new BigDecimal("0.300")));
                ecoActivityRepository.save(new EcoActivity("다회용기 사용", "배달 또는 포장 시 다회용기를 사용합니다.", 500, new BigDecimal("0.800")));
                ecoActivityRepository.save(new EcoActivity("친환경 포장재 이용", "친환경 포장재를 사용하는 매장을 이용합니다.", 400, new BigDecimal("0.500")));
            }

            if (rewardRepository.count() == 0) {
                rewardRepository.save(new Reward("아메리카노 무료 쿠폰", "지역 카페에서 사용할 수 있는 무료 쿠폰", 2000, 100));
                rewardRepository.save(new Reward("나무 0.1그루 심기", "지역 숲 조성 캠페인에 기부되는 리워드", 3500, 200));
                rewardRepository.save(new Reward("에코백 교환권", "친환경 장바구니 교환권", 5000, 50));
            }

            User demoUser = userRepository.findByEmail("demo@greenpay.local")
                    .orElseGet(() -> userRepository.save(new User("demo@greenpay.local", "최지구", "010-0000-0000", UserRole.USER)));
            User owner = userRepository.findByEmail("owner@greenpay.local")
                    .orElseGet(() -> userRepository.save(new User("owner@greenpay.local", "그린사장", "010-1111-1111", UserRole.OWNER)));
            User admin = userRepository.findByEmail("admin@greenpay.local")
                    .orElseGet(() -> userRepository.save(new User("admin@greenpay.local", "관리자", "010-2222-2222", UserRole.ADMIN)));

            if (storeRepository.count() == 0) {
                Store cafe = new Store(owner, "그린한 카페", "카페", "서울시 성동구 그린로 12",
                        new BigDecimal("37.5445000"), new BigDecimal("127.0557000"), "텀블러 사용을 권장하는 로컬 카페");
                cafe.approve(admin);

                Store bookstore = new Store(owner, "에코마켓 북스", "서점", "서울시 성동구 지속길 5",
                        new BigDecimal("37.5462000"), new BigDecimal("127.0521000"), "친환경 굿즈와 독립출판물을 함께 판매하는 서점");
                bookstore.approve(admin);

                Store savedCafe = storeRepository.save(cafe);
                Store savedBookstore = storeRepository.save(bookstore);

                EcoActivity tumbler = ecoActivityRepository.findAll().get(0);
                EcoActivity container = ecoActivityRepository.findAll().get(1);
                EcoActivity packageActivity = ecoActivityRepository.findAll().get(2);

                storeActivityRepository.save(new StoreActivity(savedCafe, tumbler));
                storeActivityRepository.save(new StoreActivity(savedCafe, container));
                storeActivityRepository.save(new StoreActivity(savedBookstore, packageActivity));

                storeQrCodeRepository.save(new StoreQrCode(savedCafe, "GREENPAY-CAFE-DEMO", null));
                storeQrCodeRepository.save(new StoreQrCode(savedBookstore, "GREENPAY-BOOKS-DEMO", null));
            }
        };
    }
}
