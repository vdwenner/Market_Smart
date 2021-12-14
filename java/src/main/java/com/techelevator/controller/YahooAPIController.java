package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.dao.YahooAPIDao;
import com.techelevator.model.Portfolio;
import com.techelevator.model.PortfolioStock;
import com.techelevator.model.StockWrapper;
import com.techelevator.services.YahooService;
import org.springframework.web.bind.annotation.*;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockStats;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class YahooAPIController {
    private final YahooAPIDao yahooAPIDao;
    private final YahooService yahooService;
    private final GameDao gameDao;

    public YahooAPIController(YahooAPIDao yahooAPIDao, YahooService yahooService, GameDao gameDao) {
        this.yahooAPIDao = yahooAPIDao;
        this.yahooService = yahooService;
        this.gameDao = gameDao;
    }

    //GET ALL STOCK INFORMATION BY SYMBOL
    @RequestMapping(path = "/stock/{symbol}", method = RequestMethod.GET)
    public Stock getStock(@PathVariable String symbol) throws IOException {
        return yahooAPIDao.findStock(symbol.toUpperCase()).getStock();
    }

    //GET STOCK NAME BY SYMBOL
    @RequestMapping(path = "/stock/name/{symbol}", method = RequestMethod.GET)
    public String getStockName(@PathVariable String symbol) throws IOException {
        return yahooAPIDao.findStock(symbol.toUpperCase()).getStock().getName();
    }

    //GET STOCK STATS BY SYMBOL
    @RequestMapping(path = "/stock/stats/{symbol}", method = RequestMethod.GET)
    public StockStats getStockStats(@PathVariable String symbol) throws IOException {
        return yahooAPIDao.findStock(symbol.toUpperCase()).getStock().getStats();
    }

    //GET TOP 20 TRENDING STOCK SYMBOLS
    @RequestMapping(path = "/trending", method = RequestMethod.GET)
    public String getTrending() throws IOException, InterruptedException {
        return yahooService.listTrendingStocks();
    }

    @RequestMapping(path ="/update/leaderboard/{gameId}", method = RequestMethod.PUT)
    public void updateLeaderboard(@PathVariable Long gameId, Principal principal){
        List<Portfolio> portfolios = gameDao.viewLeaderboard(gameId,principal);
        for(Portfolio portfolio: portfolios){
            List<PortfolioStock> ps = gameDao.getPortfolioStocksByPortfolioId(portfolio.getId());
            double portfolioValue = 0.00;

            for(PortfolioStock portfolioStock: ps){
                BigDecimal stockPrice = yahooAPIDao.findStock(portfolioStock.getStockSymbol()).getStock().getQuote().getPrice();
                portfolioValue +=  (stockPrice.doubleValue() * portfolioStock.getQuantity());
            }

            portfolio.setPortfolioValue(BigDecimal.valueOf(portfolioValue));
            gameDao.setPortfolioValue(portfolio,principal);
        }


    }


}
