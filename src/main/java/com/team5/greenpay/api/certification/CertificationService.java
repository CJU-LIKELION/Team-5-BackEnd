package com.team5.greenpay.api.certification;

import com.team5.greenpay.domain.activity.EcoActivity;
import com.team5.greenpay.domain.activity.StoreActivity;
import com.team5.greenpay.domain.activity.StoreActivityRepository;
import com.team5.greenpay.domain.certification.Certification;
import com.team5.greenpay.domain.certification.CertificationRepository;
import com.team5.greenpay.domain.point.PointTransaction;
import com.team5.greenpay.domain.point.PointTransactionRepository;
import com.team5.greenpay.domain.point.PointType;
import com.team5.greenpay.domain.qr.StoreQrCode;
import com.team5.greenpay.domain.qr.StoreQrCodeRepository;
import com.team5.greenpay.domain.user.User;
import com.team5.greenpay.global.prototype.PrototypeUserProvider;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private final PrototypeUserProvider prototypeUserProvider;
    private final StoreQrCodeRepository storeQrCodeRepository;
    private final StoreActivityRepository storeActivityRepository;
    private final CertificationRepository certificationRepository;
    private final PointTransactionRepository pointTransactionRepository;

    @Transactional
    public CertificationResponse certifyByQr(String qrToken) {
        User user = prototypeUserProvider.getCurrentUser();
        StoreQrCode qrCode = storeQrCodeRepository.findByQrTokenAndActiveTrue(qrToken)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유효하지 않은 QR 코드입니다."));

        boolean alreadyCertified = certificationRepository.existsByUserUserIdAndStoreStoreIdAndCertifiedDate(
                user.getUserId(),
                qrCode.getStore().getStoreId(),
                LocalDate.now()
        );
        if (alreadyCertified) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "오늘 이미 인증한 매장입니다.");
        }

        List<StoreActivity> activities = storeActivityRepository.findByStoreStoreIdAndActiveTrue(qrCode.getStore().getStoreId());
        if (activities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "매장에 등록된 친환경 활동이 없습니다.");
        }

        EcoActivity activity = activities.get(0).getActivity();
        Certification certification = certificationRepository.save(new Certification(
                user,
                qrCode.getStore(),
                qrCode,
                activity.getRewardPoint(),
                activity.getCarbonReductionKg()
        ));

        user.addPoints(activity.getRewardPoint());
        pointTransactionRepository.save(new PointTransaction(
                user,
                certification,
                PointType.EARN,
                activity.getRewardPoint(),
                qrCode.getStore().getStoreName() + " 친환경 소비 인증"
        ));

        return CertificationResponse.of(certification, user.getPointBalance());
    }
}
