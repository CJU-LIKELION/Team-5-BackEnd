package com.team5.greenpay.domain.qr;

import com.team5.greenpay.domain.common.BaseTimeEntity;
import com.team5.greenpay.domain.store.Store;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "store_qr_codes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreQrCode extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qrId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false, unique = true)
    private String qrToken;

    private String qrImageUrl;

    @Column(nullable = false)
    private boolean active;

    public StoreQrCode(Store store, String qrToken, String qrImageUrl) {
        this.store = store;
        this.qrToken = qrToken;
        this.qrImageUrl = qrImageUrl;
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }
}
