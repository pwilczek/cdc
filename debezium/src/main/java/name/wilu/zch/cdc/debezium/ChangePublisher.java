package name.wilu.zch.cdc.debezium;

import io.debezium.engine.RecordChangeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.stereotype.Service;

import static io.debezium.data.Envelope.FieldName.AFTER;

@Service @Slf4j @RequiredArgsConstructor
class ChangePublisher {
    //
    final EventService servie;

    public void publish(RecordChangeEvent<SourceRecord> event) {
        log.info("Got new event {}", event);
        Struct change = (Struct) event.record().value();
        Struct state = (Struct) change.get(AFTER);
        if (isEvent(state, "name")) {
            EventBalance eb = new EventBalance();
            eb.setName(value(state, "name"));
            eb.setId(Long.parseLong(value(state, "id")));
            eb.setBalance(0L);
            servie.addEvent(eb);
        }
        log.info("Event handled");
    }

    private String value(Struct after, String name) {
        return after.get(name).toString();
    }

    private boolean isEvent(Struct state, String name) {
        return state.schema().fields().stream().anyMatch(it -> StringUtils.equals(it.name(), name));
    }
}
