package com.tradex.trade.service.infrastructure.messaging.kafka;

import com.tradex.trade.service.application.service.ITradeAllocationService;
import com.tradex.trade.service.domain.entity.ProcessedEventEntity;
import com.tradex.trade.service.domain.repository.ProcessedEventRepository;
import com.tradex.trade.service.infrastructure.messaging.envelope.TradeExecutedPayload;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.SchemaException;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class TradeExecutionConsumer {

    private final ITradeAllocationService allocationService;
    private final ProcessedEventRepository processedEventRepository;
    private final KafkaTemplate<String, TradeExecutedPayload> kafkaTemplate;

    @KafkaListener(
            topics = "${kafka.topics.trades-executed}",
            groupId = "${kafka.consumer.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consume(
            EventEnvelope<TradeExecutedPayload> envelope
    ) {
        try{
            if (processedEventRepository.existsByEventId(envelope.eventId())) {
                return;
            }

            allocationService.allocate(
                    envelope.payload().tradeExecutionId()
            );

            processedEventRepository.save(
                    new ProcessedEventEntity(
                            envelope.eventId(),
                            Instant.now()
                    )
            );
        }catch (DeserializationException | SchemaException ex) {
            kafkaTemplate.send("trades.executed.dlq", envelope);
        }
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object>
    kafkaListenerContainerFactory(
            ConsumerFactory<String, Object> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties()
                .setAckMode(ContainerProperties.AckMode.RECORD);

        return factory;
    }



}
