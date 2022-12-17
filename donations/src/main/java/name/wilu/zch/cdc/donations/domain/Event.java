package name.wilu.zch.cdc.donations.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity @Data
class Event {
    //
    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
}