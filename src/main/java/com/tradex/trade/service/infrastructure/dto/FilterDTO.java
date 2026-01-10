package com.tradex.trade.service.infrastructure.dto;

import com.tradex.trade.service.application.dto.ServerDTO;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class FilterDTO extends ServerDTO {

    @Positive
    private Integer page;

    @Positive
    private Integer size;

    private Order order= Order.ASC;

    private Boolean isMultipleSort = false;

    private String sortBy;

    public enum Order {
        ASC,
        DESC;

        public boolean asc() {
            return ASC == this;
        }
    }

}
