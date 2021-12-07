package com.techelevator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import yahoofinance.Stock;

import java.time.LocalDateTime;

@Getter //Creates getters for all instance variables(lombok dependency)
@With
@AllArgsConstructor
public class StockWrapper {
    private final Stock stock;
    private final LocalDateTime lastAccessed;

    public StockWrapper(final Stock stock) {
        this.stock = stock;
        lastAccessed = LocalDateTime.now();
    }
}
