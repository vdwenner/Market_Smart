package com.techelevator.dao;

import com.techelevator.model.StockWrapper;
import yahoofinance.Stock;

import java.util.List;


public interface YahooAPIDao {

    StockWrapper findStock(String ticker);

}
