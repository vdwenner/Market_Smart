package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.dao.YahooAPIDao;
import com.techelevator.model.Portfolio;
import com.techelevator.model.PortfolioStock;
import com.techelevator.model.StockWrapper;
import com.techelevator.model.Transaction;
import com.techelevator.services.YahooService;
import org.springframework.web.bind.annotation.*;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockStats;

import javax.sound.sampled.Port;
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

    //GET ALL STOCK INFO BY NAME
//    @RequestMapping(path = "/stock/stockName/{name}", method = RequestMethod.GET)
//    public Stock getStockByName(@PathVariable String name) throws IOException {
//        return yahooAPIDao.findStockByName(name).getStock();
//
//    }

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

    //GET STOCK SUMMARY
    @RequestMapping(path = "/{symbol}/summary", method = RequestMethod.GET)
    public String getStockSummary(@PathVariable String symbol) throws IOException, InterruptedException {
        return yahooService.getStockSummary(symbol);
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

    @RequestMapping(path = "/{gameId}/endgame", method = RequestMethod.PUT)
    public void endGame(@PathVariable Long gameId, Principal principal) {
        List<Portfolio> portfolios = gameDao.viewLeaderboard(gameId, principal);
        for(Portfolio portfolio: portfolios) {
            List<PortfolioStock> ps = gameDao.getPortfolioStocksByPortfolioId(portfolio.getId());
            for(PortfolioStock portfolioStock: ps) {
                BigDecimal currentPrice = yahooAPIDao.findStock(portfolioStock.getStockSymbol()).getStock().getQuote().getPrice();
                gameDao.sellStock(portfolioStock.getStockSymbol(), currentPrice, portfolioStock.getQuantity(), portfolioStock.getPortfolioId(), principal);
                Transaction transaction = new Transaction();
                transaction.setPortfolioId(portfolioStock.getPortfolioId());
                transaction.setPrice(currentPrice);
                transaction.setQuantity(portfolioStock.getQuantity());
                transaction.setTransactionType(2L);
                transaction.setStockSymbol(portfolioStock.getStockSymbol());
                gameDao.addToBalance(transaction, principal);
                gameDao.subtractFromPortfolioStock(transaction, principal);


            }
            //Change cash to 0
            gameDao.setCashTo0(portfolio,principal);
        }
    }

}
