package com.tradex.trade.service.application.trade;

import com.tradex.trade.service.application.dto.StandardTradeDTO;
import com.tradex.trade.service.application.dto.StandardTradeFilterDTO;
import com.tradex.trade.service.domain.repository.StandardTradeRepository;
import com.tradex.trade.service.infrastructure.mapper.StandardTradeDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StandardTradeService {

    private final StandardTradeRepository repository;
    private final StandardTradeDTOMapper mapper;

    @Transactional(readOnly = true)
    public Page<StandardTradeDTO> search(StandardTradeFilterDTO filter) {
        return repository.search(filter).map(mapper::toDTO);
    }
}
