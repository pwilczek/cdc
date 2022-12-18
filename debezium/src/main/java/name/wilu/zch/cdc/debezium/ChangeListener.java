package name.wilu.zch.cdc.debezium;

import io.debezium.config.Configuration;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component @Slf4j
class ChangeListener {
    //
    private final Executor executor = Executors.newSingleThreadExecutor();
    //
    private final DebeziumEngine<RecordChangeEvent<SourceRecord>> engine;
    private final ChangePublisher publisher;

    ChangeListener(Configuration configuration, ChangePublisher publisher) {
        this.publisher = publisher;
        engine = DebeziumEngine.create(ChangeEventFormat.of(Connect.class))
                .using(configuration.asProperties())
                .notifying(this::handleChangeEvent)
                .build();
    }

    private void handleChangeEvent(RecordChangeEvent<SourceRecord> changeEvent) {
        log.info("Incoming new change event");
        publisher.publish(changeEvent);
    }

    @PostConstruct
    private void start() {
        log.info("Starting debezium engine");
        executor.execute(engine);
    }

    @PreDestroy
    private void stop() throws IOException {
        log.info("Stopping debezium engine");
        if (engine != null) engine.close();
    }
}
