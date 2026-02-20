package com.tradex.trade.service.infrastructure.messaging.kafka.config;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.schema-registry-url}")
    private String schemaRegistryUrl;

    @Bean
    public ProducerFactory<String, SpecificRecord> producerFactory() {

        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);

        props.put("schema.registry.url", schemaRegistryUrl);

        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);

        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,
                "trade-service-outbox-" + UUID.randomUUID());

        props.put(ProducerConfig.LINGER_MS_CONFIG, 5);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 32_768);

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, SpecificRecord> kafkaTemplate(
            ProducerFactory<String, SpecificRecord> producerFactory
    ) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public KafkaTransactionManager<String, SpecificRecord>
    kafkaTransactionManager(
            ProducerFactory<String, SpecificRecord> producerFactory
    ) {
        return new KafkaTransactionManager<>(producerFactory);
    }
}
