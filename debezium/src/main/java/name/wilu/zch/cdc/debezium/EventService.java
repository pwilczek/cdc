package name.wilu.zch.cdc.debezium;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
class EventService {
    //
    final RedisTemplate redisTemplate;

    List<EventBalance> events() {
        return redisTemplate.opsForHash().values(EventBalance.EventBalanceKey);
    }

    void addEvent(EventBalance event) {
        redisTemplate.opsForHash().putIfAbsent(EventBalance.EventBalanceKey, event.id, event);
    }
}