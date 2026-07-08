package com.team5.greenpay.domain.activity;

import com.team5.greenpay.domain.store.Store;
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
@Table(name = "store_activities")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeActivityId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "activity_id")
    private EcoActivity activity;

    private boolean active;

    public StoreActivity(Store store, EcoActivity activity) {
        this.store = store;
        this.activity = activity;
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }
}
