package com.tradex.trade.service.application.allocation;

import com.tradex.trade.service.domain.common.enums.Status;
import com.tradex.trade.service.infrastructure.persistence.trade.StandardTradeEntity;
import com.tradex.trade.service.domain.repository.StandardTradeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AllocationScheduler {

    private final StandardTradeRepository standardTradeRepository;
    private final TradeAllocationService tradeAllocationService;

    private static final int BATCH_SIZE = 50;

    @Scheduled(fixedDelayString = "${allocation.scheduler.delay.ms:2000}")
    @Transactional
    public void allocatePendingTrades() {

        List<StandardTradeEntity> trades =
                standardTradeRepository.lockPendingTrades(BATCH_SIZE);

        for (StandardTradeEntity trade : trades) {
                trade.setStatus(Status.ALLOCATING);
                trade.setAllocationAttemptedAt(Instant.now());
                trade.setAllocationAttempts(
                        trade.getAllocationAttempts() + 1
                );

                tradeAllocationService.allocate(trade.getTradeExecutionId());

        }
    }
}
