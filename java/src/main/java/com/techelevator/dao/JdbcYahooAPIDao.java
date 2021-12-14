package com.techelevator.dao;

import com.techelevator.model.Stock;
import com.techelevator.model.StockWrapper;
import org.springframework.stereotype.Component;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.List;

@Component
public class JdbcYahooAPIDao implements YahooAPIDao{
    @Override
    public StockWrapper findStock(final String ticker) {
        try {
            return new StockWrapper(YahooFinance.get(ticker));
        } catch (IOException e) {
            System.out.println("Error");
        }
        return null;
    }

//    @Override
//    public StockWrapper findStockByName(String name) {
//        try {
//            return new StockWrapper(YahooFinance.get(name));
//        } catch (IOException e) {
//            System.out.println("Error");
//        }
//        return null;
//    }


}
