package com.tradex.trade.service.infrastructure.pagination;

import com.tradex.trade.service.application.dto.TradeAllocationFilterDTO;
import com.tradex.trade.service.infrastructure.persistence.dto.FilterDTO;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public final class PageableBuilder {

    public static Pageable tradeAllocationBuilder(TradeAllocationFilterDTO dto) {

        int page = dto.getPage() != null ? dto.getPage() : 0;
        int size = dto.getSize() != null ? dto.getSize() : 20;

        List<Sort.Order> orders = new ArrayList<>();

        if (Boolean.TRUE.equals(dto.getIsMultipleSort())) {

            addOrder(orders, "id", dto.getOrderId());
            addOrder(orders, "ruleCode", dto.getOrderRuleCode());
            addOrder(orders, "tradeExecutionId", dto.getOrderTradeExecutionId());
            addOrder(orders, "status", dto.getOrderStatus());

        } else if (dto.getSortBy() != null) {

            orders.add(new Sort.Order(
                    dto.getOrder().asc() ? Sort.Direction.ASC : Sort.Direction.DESC,
                    dto.getSortBy()
            ));
        }

        Sort sort = orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);

        return PageRequest.of(page, size, sort);
    }

    private static void addOrder(List<Sort.Order> orders,
                                 String field,
                                 FilterDTO.Order order) {
        if (order != null) {
            orders.add(new Sort.Order(
                    order.asc() ? Sort.Direction.ASC : Sort.Direction.DESC,
                    field
            ));
        }
    }
}
