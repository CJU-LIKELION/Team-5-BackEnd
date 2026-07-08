package com.team5.greenpay.api.certification;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/certifications")
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;

    @PostMapping("/qr")
    public CertificationResponse certifyByQr(@Valid @RequestBody QrCertificationRequest request) {
        return certificationService.certifyByQr(request.qrToken());
    }
}
