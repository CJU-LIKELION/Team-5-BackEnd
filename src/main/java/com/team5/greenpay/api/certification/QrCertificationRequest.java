package com.team5.greenpay.api.certification;

import jakarta.validation.constraints.NotBlank;

public record QrCertificationRequest(
        @NotBlank String qrToken
) {
}
