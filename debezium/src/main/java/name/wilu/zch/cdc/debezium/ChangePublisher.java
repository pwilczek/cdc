package name.wilu.zch.cdc.debezium;

import io.debezium.engine.RecordChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.stereotype.Service;

@Service @Slf4j
class ChangePublisher {
    //
    public void publish(RecordChangeEvent<SourceRecord> changeEvent) {
        log.info("Publishing event {}", changeEvent);
    }
}
