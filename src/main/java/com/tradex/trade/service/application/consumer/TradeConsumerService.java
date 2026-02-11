package com.tradex.trade.service.application.consumer;

import com.tradex.trade.service.infrastructure.persistence.trade.StandardTradeEntity;
import com.tradex.trade.service.domain.repository.StandardTradeRepository;
import com.tradex.trade.service.infrastructure.mapper.ConsumedTradeMapper;
import com.tradex.trade.service.infrastructure.messaging.kafka.TradeExecutedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TradeConsumerService {

    private final StandardTradeRepository standardTradeRepository;
    private final ConsumedTradeMapper mapper;

    @Transactional
    public void consume(TradeExecutedEvent event) {

        boolean alreadyExists =
              standardTradeRepository.existsByTradeExecutionId(event.tradeExecutionId());

        if (alreadyExists) {
            return;
        }

        StandardTradeEntity entity =
                mapper.toEntity(event);

        standardTradeRepository.save(entity);

    }


}
