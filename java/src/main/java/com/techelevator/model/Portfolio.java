package com.techelevator.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Portfolio {

    private Long id;
    private Long userId;
    private Long gameId;
    private BigDecimal cashBalance;
    private BigDecimal portfolioValue;

}
