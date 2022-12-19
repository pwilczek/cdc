package name.wilu.zch.cdc.balance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration @Slf4j
class DebeziumConfiguration {

    @Value("${donations.db.host}") private String host;
    @Value("${donations.db.port}") private String port;
    @Value("${donations.db.name}") private String db;
    @Value("${donations.db.username}") private String user;
    @Value("${donations.db.password}") private String pass;

    @Bean
    io.debezium.config.Configuration connectorConfig() throws IOException {
        File offsetStorage = File.createTempFile("offsets_", ".dat");
        File schemaHistory = File.createTempFile("dbhistory_", ".dat");
        //
        io.debezium.config.Configuration config = io.debezium.config.Configuration.create()
                .with("database.hostname", host)
                .with("database.port", port)
                .with("database.user", user)
                .with("database.password", pass)
                .with("database.dbname", db)
                .with("database.include.list", db)
                .with("name", "donations-mysql-connector")
                .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", offsetStorage.getAbsolutePath())
                .with("offset.flush.interval.ms", "60000")
                .with("include.schema.changes", "false")
                .with("database.allowPublicKeyRetrieval", "true")
                .with("database.server.id", "85744")
                .with("database.server.name", "donations-mysql-connector")
                .with("topic.prefix", "donations")
                .with("schema.history.internal", "io.debezium.storage.file.history.FileSchemaHistory")
                .with("schema.history.internal.file.filename", schemaHistory.getAbsolutePath())
                .build();
        log.info("Built debezium configuration for db {}", db);
        return config;
    }
}
