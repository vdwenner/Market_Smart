package com.techelevator.controller;

import com.techelevator.dao.YahooAPIDao;
import com.techelevator.model.StockWrapper;
import com.techelevator.services.YahooService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockStats;

import java.io.IOException;
import java.util.List;

@RestController
public class YahooAPIController {
    private final YahooAPIDao yahooAPIDao;
    private final YahooService yahooService;

    public YahooAPIController(YahooAPIDao yahooAPIDao, YahooService yahooService) {
        this.yahooAPIDao = yahooAPIDao;
        this.yahooService = yahooService;
    }

    //GET ALL STOCK INFORMATION BY SYMBOL
    @RequestMapping(path = "/stock/{symbol}", method = RequestMethod.GET)
    public Stock getStock(@PathVariable String symbol) throws IOException {
        return yahooAPIDao.findStock(symbol).getStock();
    }

    //GET STOCK NAME BY SYMBOL
    @RequestMapping(path = "/stock/name/{symbol}", method = RequestMethod.GET)
    public String getStockName(@PathVariable String symbol) throws IOException {
        return yahooAPIDao.findStock(symbol).getStock().getName();
    }

    //GET STOCK STATS BY SYMBOL
    @RequestMapping(path = "/stock/stats/{symbol}", method = RequestMethod.GET)
    public StockStats getStockStats(@PathVariable String symbol) throws IOException {
        return yahooAPIDao.findStock(symbol).getStock().getStats();
    }

    //GET TOP 20 TRENDING STOCK SYMBOLS
    @RequestMapping(path = "/trending", method = RequestMethod.GET)
    public String getTrending() throws IOException, InterruptedException {
        return yahooService.listTrendingStocks();
    }
}
