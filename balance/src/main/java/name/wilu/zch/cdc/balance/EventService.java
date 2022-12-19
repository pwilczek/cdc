package name.wilu.zch.cdc.balance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static name.wilu.zch.cdc.balance.EventBalance.EventBalanceKey;

@Service @RequiredArgsConstructor @Slf4j
class EventService {
    //
    final RedisTemplate<String, EventBalance> redisTemplate;

    List<EventBalance> events() {
        return repo().values(EventBalanceKey);
    }

    EventBalance addEvent(EventBalance event) {
        if (repo().putIfAbsent(EventBalanceKey, event.id, event)) log.info("Event added {}", event);
        else log.info("Event already registered {}", event);
        return event;
    }

    EventBalance addDonation(long eventId, long amount) {
        EventBalance maybe = repo().get(EventBalanceKey, eventId);
        if (maybe == null) throw new IllegalStateException("Can't find event id " + eventId);
        EventBalance balance = maybe.donate(amount);
        repo().put(EventBalanceKey, eventId, balance);
        log.info("Donation added {}", balance);
        return balance;
    }

    private HashOperations<String, Long, EventBalance> repo() {
        return redisTemplate.opsForHash();
    }
}