package name.wilu.zch.cdc.balance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.data.Struct;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor @Slf4j
class NewEventHandler extends EventHandler {
    //
    final EventService service;

    @Override boolean canTake(Struct event) {
        return isEvent(event, "name");
    }

    @Override void take(Struct state) {
        EventBalance event = new EventBalance();
        event.setName(value(state, "name"));
        event.setId(Long.parseLong(value(state, "id")));
        event.setBalance(0L);
        EventBalance added = service.addEvent(event);
        log.info("New event added {}", added);
    }
}