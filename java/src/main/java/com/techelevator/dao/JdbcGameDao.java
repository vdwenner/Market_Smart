package com.techelevator.dao;

import com.techelevator.model.*;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sound.sampled.Port;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.sun.el.lang.ELArithmetic.multiply;

@Component
public class JdbcGameDao implements GameDao{

    private final JdbcTemplate jdbcTemplate;
    private UserDao userDao;

    public JdbcGameDao(JdbcTemplate jdbcTemplate, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.userDao = userDao;
    }

    @Override
    public void createGame(String gameName,  BigDecimal startingAmount, Date endDate, Principal principal) {
        Game game = null;
        Long id = userDao.findIdByUsername(principal.getName());

        String sql = "INSERT INTO games (game_name, creator_id, starting_amount, end_date) " +
                     "VALUES(?, ?, ?, ?);";
        jdbcTemplate.update(sql, gameName, id, startingAmount, endDate);

        String sql2 = "SELECT game_id, game_name, creator_id, starting_amount, end_date FROM games " +
                      "WHERE game_name = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql2, gameName);

        if(results.next()) {
            game = mapRowToGame(results);
        }
        String sql3 =  "INSERT INTO game_users (game_id, user_id) " +
                       "VALUES(?, ?);";
        jdbcTemplate.update(sql3, game.getId(), id);

        String sql4 = "INSERT INTO portfolio (user_id, game_id, cash_balance, portfolio_value) " +
                      "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql4, id, game.getId(), startingAmount, startingAmount);
    }
    @Override
    public void approveGameInvite(Game pendingGame, Principal principal){
        Long currentUserId = userDao.findIdByUsername(principal.getName());
        Long gameId =  pendingGame.getId();

        String sql = "Update game_invites set game_invite_type_id = 1 " + "where game_id = ?; ";

        jdbcTemplate.update(sql,gameId);

        String sql2 = "INSERT INTO game_users (game_id, user_id) " +
                "VALUES(?, ?);";
        jdbcTemplate.update(sql2,gameId,currentUserId);

        String sql3 = "INSERT INTO portfolio (user_id, game_id, cash_balance, portfolio_value) " +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql3, currentUserId, gameId, pendingGame.getStartingAmount(), pendingGame.getStartingAmount());
    }

    @Override
    public void rejectGameInvite(Game pendingGame, Principal principal){
        Long gameId = pendingGame.getId();
        String sql = "Update game_invites set game_invite_type_id = 2 " + "where game_id = ?; ";
        jdbcTemplate.update(sql,gameId);
    }

    @Override
    public void setInitialGameUsers(Long gameId, Long userID) {
        String sql = "INSERT INTO game_users (game_id, user_id) " +
                     "VALUES(?, ?);";
        jdbcTemplate.update(sql, gameId, userID);
    }

    @Override
    public Game getGameByGameName(String gameName) {
        Game game = null;
        String sql = "SELECT game_id, game_name, creator_id, starting_amount, end_date " +
                     "FROM games " +
                     "WHERE game_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameName);
        if(results.next()){
            game = mapRowToGame(results);
        }
        return game;
    }

    @Override
    public Game getGameByGameId(Long gameId) {
        Game game = null;
        String sql = "SELECT game_id, game_name, creator_id, starting_amount, end_date " +
                "FROM games " +
                "WHERE game_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
        if(results.next()){
            game = mapRowToGame(results);
        }
        return game;
    }

    @Override
    public List<Game> getAllUserGames(Principal principal) {
        List<Game> userGames = new ArrayList<>();
        Long id = userDao.findIdByUsername(principal.getName());


        String sql = "SELECT g.game_id, g.game_name, g.creator_id, g.starting_amount, g.end_date " +
                "FROM games g " +
                "join game_users gu ON g.game_id = gu.game_id " +
                "WHERE gu.user_id = " + id;
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            userGames.add(mapRowToGame(results));
        }
        return userGames;
    }

    private Game mapRowToGame(SqlRowSet rowSet) {
        Game game = new Game();
        game.setId(rowSet.getLong("game_id"));
        game.setGameName(rowSet.getString("game_name"));
        game.setCreatorId(rowSet.getLong("creator_id"));
        game.setStartingAmount(rowSet.getBigDecimal("starting_amount"));
        game.setEndDate(rowSet.getDate("end_date"));
        return game;
    }

    @Override
    public void sendGameInvite(InviteType inviteType, Principal principal) {
        Long creatorId = userDao.findIdByUsername(principal.getName());
        String sql = "INSERT into game_invites" +
                " (sender_id, receiver_id, game_id, game_invite_type_id)" +
                " VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, creatorId, inviteType.getReceiverId(), inviteType.getGameId(), inviteType.getInviteTypeId());

    }

    @Override
    public List<InviteType> listPendingGameInvites(Principal principal) {
        List<InviteType> gameInvites = new ArrayList<>();
        Long receiverId = userDao.findIdByUsername(principal.getName());
        String sql = "SELECT sender_id, receiver_id, game_id, game_invite_type_id" +
                " FROM game_invites WHERE receiver_id = ?" +
                " AND game_invite_type_id = 3";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, receiverId);
        while (results.next()) {
            InviteType inviteType = mapRowToInviteType(results);
            gameInvites.add(inviteType);
        }
        return gameInvites;
    }

    @Override
    public List<Portfolio> viewLeaderboard(Long gameId, Principal principal) {
        List<Portfolio> leaderboard = new ArrayList<>();
        String sql = "SELECT portfolio_id, user_id, game_id, cash_balance, portfolio_value " +
                     "FROM portfolio WHERE game_id = ? ORDER BY portfolio_value DESC";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, gameId);
        while(results.next()) {
            Portfolio portfolio = mapRowToPortfolio(results);
            leaderboard.add(portfolio);
        }
        return leaderboard;
    }

    public Portfolio getPortfolioByPortfolioId(Long portfolioId){
        Portfolio portfolio = null;
        String portfolioSql = "Select portfolio_id, user_id, game_id, cash_balance, portfolio_value "
                + "From portfolio where portfolio_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(portfolioSql, portfolioId);
        if(results.next()){
            portfolio = mapRowToPortfolio(results);
        }
        return portfolio;
    }

    @Override
    public void buyStock(String stockSymbol, BigDecimal stockPrice, Long quantity, Long portfolioId , Principal principal) {
        String sql ="Insert into transactions (transaction_type, price, portfolio_id, stock_symbol, quantity) "
                    + "Values(1, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, stockPrice, portfolioId, stockSymbol, quantity);

    }

    @Override
    public void sellStock(String stockSymbol, BigDecimal stockPrice, Long quantity, Long portfolioId , Principal principal){
        String sql ="Insert into transactions (transaction_type, price, portfolio_id, stock_symbol, quantity) "
                +"Values(2, ?, ?, ?, ?);";
        jdbcTemplate.update(sql, stockPrice, portfolioId, stockSymbol, quantity);
    }

    @Override
    public void subtractFromBalance(Transaction transaction, Principal principal) {
        BigDecimal transactionAmount = transaction.getPrice().multiply(BigDecimal.valueOf(transaction.getQuantity()));
        String sql = "UPDATE portfolio SET cash_balance = (cash_balance - ?) " +
                     "WHERE portfolio_id = ?;";
        jdbcTemplate.update(sql, transactionAmount, transaction.getPortfolioId());
    }

    @Override
    public void addToBalance(Transaction transaction, Principal principal) {
        BigDecimal transactionAmount = transaction.getPrice().multiply(BigDecimal.valueOf(transaction.getQuantity()));
        String sql = "UPDATE portfolio SET cash_balance = (cash_balance + ?) " +
                "WHERE portfolio_id = ?;";
        jdbcTemplate.update(sql, transactionAmount, transaction.getPortfolioId());
    }

    public void addToPortfolioStock(Transaction transaction, Principal principal){
        PortfolioStock portfolioStock = new PortfolioStock();
        portfolioStock.setAveragePrice(BigDecimal.valueOf(0));
        portfolioStock.setQuantity(0L);
        String currentPS = "Select portfolio_id, stock_symbol,quantity, average_price " +
                "From portfolio_stock " +
                "where (portfolio_id = ?) and (stock_symbol = ?);";
        SqlRowSet results = jdbcTemplate.queryForRowSet(currentPS,transaction.getPortfolioId(),transaction.getStockSymbol() );

        if(results.next()){
            portfolioStock = mapRowToPortfolioStock(results);
        }

        int currentQuantity = portfolioStock.getQuantity().intValue();
        int currentPrice = portfolioStock.getAveragePrice().intValue();
        int newQuantity =0;
        int newPrice = 0;
        if(currentQuantity > 0){
            int totalCost = (currentPrice * currentQuantity) + (transaction.getQuantity().intValue() * transaction.getPrice().intValue());
            newQuantity = (currentQuantity + transaction.getQuantity().intValue());
            Long longQuantity =  Long.valueOf(newQuantity);
            newPrice = (totalCost / newQuantity);

            String sqlUpdate = "Update portfolio_stock " +
                    "set quantity = ?, average_price = ? " +
                    "where portfolio_id = ? and stock_symbol = ?;";
            jdbcTemplate.update(sqlUpdate,longQuantity,newPrice,transaction.getPortfolioId(),transaction.getStockSymbol());
        } else {

            String sql = "Insert into portfolio_stock (portfolio_id,stock_symbol,quantity,average_price) " +
                    "Values(?,?,?,?); ";
            jdbcTemplate.update(sql, transaction.getPortfolioId(), transaction.getStockSymbol(), transaction.getQuantity(), transaction.getPrice());
        }
    }

    public void subtractFromPortfolioStock(Transaction transaction, Principal principal){
        PortfolioStock portfolioStock = new PortfolioStock();
        portfolioStock.setAveragePrice(BigDecimal.valueOf(0));
        portfolioStock.setQuantity(0L);
        String currentPS = "Select portfolio_id, stock_symbol,quantity, average_price " +
                "From portfolio_stock " +
                "where (portfolio_id = ?) and (stock_symbol = ?);";
        SqlRowSet results = jdbcTemplate.queryForRowSet(currentPS,transaction.getPortfolioId(),transaction.getStockSymbol() );

        if(results.next()){
            portfolioStock = mapRowToPortfolioStock(results);
        }

        int currentQuantity = portfolioStock.getQuantity().intValue();
        int currentPrice = portfolioStock.getAveragePrice().intValue();
        int newQuantity = 0;
        int newPrice = 0;
        if(currentQuantity > 0 && (currentQuantity - transaction.getQuantity().intValue() >= 0)){
            int totalCost = (currentPrice * currentQuantity) - (transaction.getQuantity().intValue() * transaction.getPrice().intValue());
            newQuantity = (currentQuantity - transaction.getQuantity().intValue());
            Long longQuantity =  Long.valueOf(newQuantity);
            if(newQuantity != 0) {
                newPrice = (totalCost / newQuantity);
            } else {
                newPrice = 0;
            }


            String sqlUpdate = "Update portfolio_stock " +
                    "set quantity = ?, average_price = ? " +
                    "where portfolio_id = ? and stock_symbol = ?;";
            jdbcTemplate.update(sqlUpdate,longQuantity,newPrice,transaction.getPortfolioId(),transaction.getStockSymbol());
        }
    }

    @Override
    public List<PortfolioStock> getPortfolioStocksByPortfolioId(Long portfolioId){
        List<PortfolioStock> stocks = new ArrayList<>();
        String sql = "Select portfolio_id, stock_symbol, quantity, average_price " +
                "From portfolio_stock Where portfolio_id =?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, portfolioId);
        while(results.next()) {
            PortfolioStock ps = mapRowToPortfolioStock(results);
            stocks.add(ps);
        }
        return stocks;
    }
    @Override
    public void setPortfolioValue( Portfolio portfolio, Principal principal){
        String sql = "Update portfolio set portfolio_value = ?   " +
                "Where portfolio_id = ?";
        jdbcTemplate.update(sql, portfolio.getPortfolioValue(), portfolio.getId());


    }


    private Transaction mapRowToTransaction(SqlRowSet rowSet){
        Transaction transaction = new Transaction();
        transaction.setId((rowSet.getLong("transaction_id")));
        transaction.setTransactionType((rowSet.getLong("transaction_type")));
        transaction.setPrice((rowSet.getBigDecimal("price")));
        transaction.setPortfolioId((rowSet.getLong("portfolio_id")));
        transaction.setStockSymbol((rowSet.getString("stock_symbol")));
        transaction.setQuantity((rowSet.getLong("quantity")));
        return transaction;
    }

    private InviteType mapRowToInviteType(SqlRowSet rowSet) {
        InviteType inviteType = new InviteType();
        inviteType.setReceiverId(rowSet.getLong("receiver_id"));
        inviteType.setInviteTypeId(rowSet.getLong("game_invite_type_id"));
        inviteType.setGameId(rowSet.getLong("game_id"));
        inviteType.setSenderId(rowSet.getLong("sender_id"));
        return inviteType;
    }


    private Portfolio mapRowToPortfolio(SqlRowSet rowSet) {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(rowSet.getLong("portfolio_id"));
        portfolio.setUserId(rowSet.getLong("user_id"));
        portfolio.setGameId(rowSet.getLong("game_id"));
        portfolio.setCashBalance(rowSet.getBigDecimal("cash_balance"));
        portfolio.setPortfolioValue(rowSet.getBigDecimal("portfolio_value"));
        return portfolio;
    }

    private PortfolioStock mapRowToPortfolioStock(SqlRowSet rowSet) {
        PortfolioStock portfolioStock = new PortfolioStock();
        portfolioStock.setPortfolioId(rowSet.getLong("portfolio_id"));
        portfolioStock.setStockSymbol(rowSet.getString("stock_symbol"));
        portfolioStock.setQuantity(rowSet.getLong("quantity"));
        portfolioStock.setAveragePrice(rowSet.getBigDecimal("average_price"));
        return portfolioStock;
    }



}
