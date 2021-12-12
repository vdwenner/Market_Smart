package com.techelevator.model;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PortfolioStock {

    private Long portfolioId;
    private String stockSymbol;
    private Long quantity;
    private BigDecimal averagePrice;

}
