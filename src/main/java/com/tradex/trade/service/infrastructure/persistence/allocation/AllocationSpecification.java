package com.tradex.trade.service.infrastructure.persistence.allocation;

import com.tradex.trade.service.application.dto.TradeAllocationFilterDTO;
import jakarta.persistence.criteria.Predicate;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public final class AllocationSpecification {

    public static Specification<TradeAllocationEntity> filter(TradeAllocationFilterDTO dto) {

        return (root,query,cb)->{

            List<Predicate> predicates = new ArrayList<>();

            if (dto.getId() != null) {
                predicates.add(cb.equal(root.get("id"), dto.getId()));
            }

            if (hasText(dto.getRuleCode())) {
                predicates.add(cb.like(
                        cb.lower(root.get("ruleCode")),
                        "%" + dto.getRuleCode().toLowerCase() + "%"));
            }

            if (hasText(String.valueOf(dto.getStatus()))) {
                predicates.add(cb.equal(
                        root.get("status"),
                        dto.getStatus()
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));

        };

    }

    private static boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
