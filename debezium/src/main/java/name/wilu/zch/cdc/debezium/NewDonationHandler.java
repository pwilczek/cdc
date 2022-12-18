package name.wilu.zch.cdc.debezium;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.data.Struct;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor @Slf4j
class NewDonationHandler extends EventHandler {
    //
    final EventService service;

    @Override boolean canTake(Struct event) {
        return isEvent(event, "amount");
    }

    @Override void take(Struct state) {
        long amount = Long.parseLong(value(state, "amount"));
        long eventId = Long.parseLong(value(state, "event_id"));
        EventBalance added = service.addDonation(eventId, amount);
        log.info("New donation added {}", added);
    }
}