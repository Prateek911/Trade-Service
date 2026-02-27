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

    public static Pageable tradeAllocationBuilder(TradeAllocationFilterDTO dto, int maxPageSize) {

        int pageNumber = 0;
        int pageSize = maxPageSize;

        List<Sort.Order> orders = new ArrayList<>();

        if (dto != null) {
            Integer size = dto.getSize();
            if (size != null && size > 0) {
                pageSize = Math.min(size, maxPageSize);
            }

            Integer page = dto.getPage();
            if (page != null && page > 0) {
                pageNumber = page - 1;
            }

            if (Boolean.TRUE.equals(dto.getIsMultipleSort())) {

                addOrder(orders, "id", dto.getOrderId());
                addOrder(orders, "ruleCode", dto.getOrderRuleCode());
                addOrder(orders, "tradeExecutionId", dto.getOrderTradeExecutionId());
                addOrder(orders, "status", dto.getOrderStatus());

            } else {
                String sortBy = normalizeSortBy(dto.getSortBy());
                if (sortBy != null) {
                    FilterDTO.Order order = dto.getOrder();
                    Sort.Direction direction = (order != null && !order.asc())
                            ? Sort.Direction.DESC
                            : Sort.Direction.ASC;
                    orders.add(new Sort.Order(direction, sortBy));
                }
            }
        }

        Sort sort = orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);

        return PageRequest.of(pageNumber, pageSize, sort);
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

    private static String normalizeSortBy(String sortBy) {
        if (!hasText(sortBy)) {
            return null;
        }

        return switch (sortBy.trim()) {
            case "id" -> "id";
            case "tradeExecutionId" -> "tradeExecutionId";
            case "ruleCode" -> "ruleCode";
            case "status" -> "status";
            case "createdAt" -> "createdAt";
            case "updatedAt" -> "updatedAt";
            default -> null;
        };
    }

    private static boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
