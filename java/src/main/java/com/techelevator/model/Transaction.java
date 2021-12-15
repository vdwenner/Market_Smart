package com.techelevator.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {

    private Long id;
    private Long transactionType;
    private BigDecimal price;
    private Long portfolioId;
    private String stockSymbol;
    private Long quantity;
}
