package com.tradex.trade.service.infrastructure.messaging.producer;

import com.tradex.trade.service.infrastructure.persistence.outbox.OutboxEntity;
import com.tradex.trade.service.domain.repository.OutboxRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Relay {

    private final OutboxRepository repository;
    private final KafkaTemplate<String,Object> kafkaTemplate;

    @Scheduled(fixedDelayString = "${outbox.poll.interval-ms}")
    @Transactional
    public void publish(){
        List<OutboxEntity> batch =
                repository.findNextBatchForUpdate(
                        PageRequest.of(0, 50)
                );

        for(OutboxEntity event : batch){
            kafkaTemplate.send(
                    event.getTopic(),
                    event.getAggregateId(),
                    event.getPayload()
            ).whenComplete((res,ex)->{
                if(ex==null){
                    event.markPublished();
                }
            });

        }

    }
}
