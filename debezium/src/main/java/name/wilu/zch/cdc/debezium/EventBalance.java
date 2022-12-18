package name.wilu.zch.cdc.debezium;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

import static name.wilu.zch.cdc.debezium.EventBalance.EventBalanceKey;

@RedisHash(EventBalanceKey) @Data
class EventBalance implements Serializable {
    //
    final static String EventBalanceKey = "EventBalance";

    @Id Long id;
    Long balance;
    String name;
}