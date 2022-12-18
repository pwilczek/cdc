package name.wilu.zch.cdc.debezium;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static name.wilu.zch.cdc.debezium.EventBalance.EventBalanceKey;

@Service @RequiredArgsConstructor @Slf4j
class EventService {
    //
    final RedisTemplate redisTemplate;

    List<EventBalance> events() {
        return repo().values(EventBalanceKey);
    }

    void addEvent(EventBalance event) {
        if (repo().putIfAbsent(EventBalanceKey, event.id, event)) log.info("Event added");
        else log.info("Event already registered");
    }

    private HashOperations repo() {
        return redisTemplate.opsForHash();
    }
}