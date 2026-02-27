package com.tradex.trade.service.infrastructure.persistence.trade;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tradex.trade.service.application.dto.StandardTradeFilterDTO;
import com.tradex.trade.service.domain.exception.RecordNotFoundException;
import com.tradex.trade.service.domain.trade.StandardTrade;
import com.tradex.trade.service.domain.repository.StandardTradeRepository;
import com.tradex.trade.service.infrastructure.mapper.StandardTradeMapper;
import com.tradex.trade.service.infrastructure.persistence.dto.FilterDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
public class StandardTradeRepositoryImpl implements StandardTradeRepository {

    private final JpaStandardTradeRepository repository;
    private final StandardTradeMapper mapper;
    private final int maxPageSize;

    public StandardTradeRepositoryImpl(
            JpaStandardTradeRepository repository,
            StandardTradeMapper mapper,
            @Value("${service.query.size:100}") int maxPageSize
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.maxPageSize = maxPageSize;
    }

    @Override
    public StandardTrade findById(Long id) {
        return mapper.toModel(repository.findById(id).orElseThrow(()-> new RecordNotFoundException(StandardTradeEntity.class,id)));
    }


    @Override
    public StandardTrade save(StandardTradeEntity entity) {
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public Boolean existsByTradeExecutionId(String tradeExecutionId) {
        return repository.existsByTradeExecutionId(tradeExecutionId);
    }

    @Override
    public List<StandardTradeEntity> lockPendingTrades(Pageable pageable) {
        return  repository.lockPendingTrades(pageable);
    }

    @Override
    public Page<StandardTrade> search(StandardTradeFilterDTO filter) {
        Predicate predicate = buildPredicate(filter);
        Pageable pageable = buildPageable(filter);
        return repository.findAll(predicate, pageable).map(mapper::toModel);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public StandardTrade findByTradeExecutionId(String tradeExecutionId) {
        return mapper.toModel(repository.findByTradeExecutionId(tradeExecutionId).orElseThrow(()-> new RecordNotFoundException(StandardTradeEntity.class,"tradeExecutionId")));
    }

    private Predicate buildPredicate(StandardTradeFilterDTO filter) {
        var trade = QStandardTradeEntity.standardTradeEntity;
        var builder = new BooleanBuilder();

        if (filter == null) {
            return builder;
        }

        if (hasText(filter.getTradeExecutionId())) {
            builder.and(trade.tradeExecutionId.equalsIgnoreCase(filter.getTradeExecutionId()));
        }
        if (hasText(filter.getInstrumentId())) {
            builder.and(trade.instrumentId.equalsIgnoreCase(filter.getInstrumentId()));
        }
        if (filter.getSide() != null) {
            builder.and(trade.side.eq(filter.getSide()));
        }
        if (hasText(filter.getCurrency())) {
            builder.and(trade.currency.equalsIgnoreCase(filter.getCurrency()));
        }
        if (hasText(filter.getSourceSystem())) {
            builder.and(trade.sourceSystem.equalsIgnoreCase(filter.getSourceSystem()));
        }
        if (filter.getId() != null) {
            builder.and(trade.id.eq(filter.getId()));
        }

        return builder;
    }

    private Pageable buildPageable(StandardTradeFilterDTO filter) {
        int pageSize = maxPageSize;
        int pageNumber = 0;

        if (filter != null) {
            Integer size = filter.getSize();
            if (size != null && size > 0) {
                pageSize = Math.min(size, maxPageSize);
            }
            Integer page = filter.getPage();
            if (page != null && page > 0) {
                pageNumber = page - 1;
            }
        }

        Sort sort = buildSort(filter);
        return PageRequest.of(pageNumber, pageSize, sort);
    }

    private Sort buildSort(StandardTradeFilterDTO filter) {
        if (filter == null) {
            return Sort.unsorted();
        }

        if (Boolean.TRUE.equals(filter.getIsMultipleSort())) {
            List<Sort.Order> orders = new ArrayList<>();
            addOrder(orders, filter.getOrderId(), "id");
            addOrder(orders, filter.getOrderTradeExecutionId(), "tradeExecutionId");
            addOrder(orders, filter.getOrderInstrumentId(), "instrumentId");
            addOrder(orders, filter.getOrderSide(), "side");
            addOrder(orders, filter.getOrderCurrency(), "currency");
            addOrder(orders, filter.getOrderSourceSystem(), "sourceSystem");

            if (!orders.isEmpty()) {
                return Sort.by(orders);
            }
        }

        String sortBy = normalizeSortBy(filter.getSortBy());
        if (sortBy == null) {
            return Sort.unsorted();
        }

        FilterDTO.Order order = filter.getOrder();
        Sort.Direction direction = (order != null && !order.asc())
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return Sort.by(direction, sortBy);
    }

    private void addOrder(List<Sort.Order> orders, FilterDTO.Order order, String property) {
        if (order == null) {
            return;
        }
        Sort.Direction direction = order.asc() ? Sort.Direction.ASC : Sort.Direction.DESC;
        orders.add(new Sort.Order(direction, property));
    }

    private String normalizeSortBy(String sortBy) {
        if (!hasText(sortBy)) {
            return null;
        }

        return switch (sortBy.trim()) {
            case "id" -> "id";
            case "tradeExecutionId" -> "tradeExecutionId";
            case "instrumentId" -> "instrumentId";
            case "side" -> "side";
            case "currency" -> "currency";
            case "sourceSystem" -> "sourceSystem";
            case "tradeTimestamp" -> "tradeTimestamp";
            case "ingestedAt" -> "ingestedAt";
            case "status" -> "status";
            default -> null;
        };
    }
}
