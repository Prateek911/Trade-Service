package com.tradex.trade.service.infrastructure.scheduling;

import com.tradex.trade.service.application.retry.TradeRetryService;
import com.tradex.trade.service.domain.allocation.TradeAllocationRetry;
import com.tradex.trade.service.domain.allocation.TradeRetryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllocationRetryScheduler {

    private static final int BATCH_SIZE = 25;

    private final TradeRetryService retryService;
    private final TradeRetryRepository retryRepository;

    @Scheduled(fixedDelayString = "${allocation.retry.poll.interval-ms:30000}")
    void runRetry(){
        var batch = retryRepository.findRetryableBatch(Instant.now(), PageRequest.of(0, BATCH_SIZE));
        for(TradeAllocationRetry retry : batch){
            try{
                retryService.retry(retry);
            }catch(Exception e){
                log.error("Error in retry for trade :{} with reason :{}",retry.getTradeExecutionId(),e.getMessage());
            }
        }
    }

}
