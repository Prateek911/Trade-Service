package com.tradex.trade.service.infrastructure.messaging.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradex.trade.service.domain.allocation.TradeAllocation;
import com.tradex.trade.service.domain.allocation.TradeAllocationFailure;
import com.tradex.trade.service.infrastructure.mapper.TradeAllocatedSuccessAvroMapper;
import com.tradex.trade.service.infrastructure.mapper.TradeAllocationFailedAvroMapper;
import com.tradex.trade.service.infrastructure.persistence.outbox.OutboxEntity;
import com.tradex.trade.service.domain.repository.OutboxRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Relay {

    private final OutboxRepository repository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final TradeAllocatedSuccessAvroMapper tradeAllocatedSuccessAvroMapper;
    private final TradeAllocationFailedAvroMapper tradeAllocationFailedAvroMapper;

    @Scheduled(fixedDelayString = "${outbox.poll.interval-ms}")
    @Transactional
    public void publish() {
        List<OutboxEntity> batch =
                repository.findNextBatchForUpdate(
                        PageRequest.of(0, 50)
                );

        kafkaTemplate.executeInTransaction(it -> {

            for (OutboxEntity event : batch) {

                SpecificRecord record = resolvePayload(event);

                kafkaTemplate.send(
                        event.getTopic(),
                        event.getAggregateId(),
                        record
                ).whenComplete((res, ex) -> {
                    if (ex == null) {
                        event.markPublished();
                    }
                });


            }

            return true;
        });

    }

    private SpecificRecord resolvePayload(OutboxEntity event) {

        try {

            switch (event.getEventType()) {

                case "TradeAllocated":
                    TradeAllocation allocation =
                            objectMapper.readValue(
                                    event.getPayload(),
                                    TradeAllocation.class
                            );

                    return tradeAllocatedSuccessAvroMapper.toAvro(allocation);

                case "AllocationFailed":
                    TradeAllocationFailure failed =
                            objectMapper.readValue(
                                    event.getPayload(),
                                    TradeAllocationFailure.class
                            );

                    return tradeAllocationFailedAvroMapper.toAvro(failed);

                default:
                    throw new IllegalStateException(
                            "Unknown event type: " + event.getEventType()
                    );
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to convert to Avro", e);
        }
    }



}
