package com.tradex.trade.service.application.scheduled;

import com.tradex.trade.service.application.service.ITradeRetryService;
import com.tradex.trade.service.domain.model.TradeAllocationRetry;
import com.tradex.trade.service.domain.repository.TradeRetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AllocationRetryScheduler {

    private static final int BATCH_SIZE = 25;

    private final ITradeRetryService retryService;
    private final TradeRetryRepository retryRepository;

    @Scheduled(fixedDelayString = "${allocation.retry.poll.interval-ms:30000}")
    void runRetry(){
        var batch = retryRepository.findRetryableBatch(Instant.now(), PageRequest.of(0, BATCH_SIZE));
        for(TradeAllocationRetry retry : batch){
            try{
                retryService.retry(retry);
            }catch(Exception e){
                //Intentionally swallow to avoid blocking other retries
                //log error
            }
        }
    }

}
