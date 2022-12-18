package name.wilu.zch.cdc.donations;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity @Data
class Donation {
    //
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private Long amount;
    @ManyToOne(optional = false, fetch = LAZY)
    Event event;
}
