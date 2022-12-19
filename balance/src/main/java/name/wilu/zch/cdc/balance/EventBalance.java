package name.wilu.zch.cdc.balance;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

import static name.wilu.zch.cdc.balance.EventBalance.EventBalanceKey;

@RedisHash(EventBalanceKey) @Data
class EventBalance implements Serializable {
    //
    final static String EventBalanceKey = "EventBalance";

    @Id Long id;
    Long balance;
    String name;

    /**
     * This is unsafe
     */
    EventBalance donate(long amount) {
        balance = balance + amount;
        return this;
    }
}