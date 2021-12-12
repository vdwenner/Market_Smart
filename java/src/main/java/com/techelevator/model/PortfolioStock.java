package com.techelevator.model;

import lombok.*;

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
