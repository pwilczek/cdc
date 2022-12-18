package name.wilu.zch.cdc.debezium;

import io.debezium.engine.RecordChangeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.debezium.data.Envelope.FieldName.AFTER;

@Service @Slf4j @RequiredArgsConstructor
class ChangePublisher {
    //
    final List<EventHandler> handlers;

    public void publish(RecordChangeEvent<SourceRecord> event) {
        log.info("Got new event {}", event);
        Struct change = (Struct) event.record().value();
        Struct state = (Struct) change.get(AFTER);
        handlers.stream().filter(it -> it.canTake(state)).forEach(it -> it.take(state));
        log.info("Event handled");
    }
}
