package com.techelevator.dao;

import com.techelevator.model.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface GameDao {

    void createGame(String gameName, BigDecimal startingAmount, Date endDate, Principal principal);

    void setInitialGameUsers(Long gameId, Long userID);

    Game getGameByGameName(String gameName);

    Game getGameByGameId(Long gameId);

    void approveGameInvite(Game pendingGame, Principal principal);

    List<Game> getAllUserGames(Principal principal);

    void rejectGameInvite(Game pendingGame, Principal principal);

    void sendGameInvite(InviteType inviteType, Principal principal);

    List<InviteType> listPendingGameInvites(Principal principal);

    List<Portfolio> viewLeaderboard(Long gameId, Principal principal);

    void buyStock(String stockSymbol,  BigDecimal stockPrice,Long quantity, Long portfolioId ,Principal principal);

    void sellStock(String stockSymbol,  BigDecimal stockPrice,Long quantity, Long portfolioId ,Principal principal);

    void subtractFromBalance(Transaction transaction, Principal principal);

    void addToBalance(Transaction transaction, Principal principal);

    Portfolio getPortfolioByPortfolioId(Long portfolioId);

    void addToPortfolioStock(Transaction transaction, Principal principal);

    void subtractFromPortfolioStock(Transaction transaction, Principal principal);

    List<PortfolioStock> getPortfolioStocksByPortfolioId(Long portfolioId);

    void setPortfolioValue( Portfolio portfolio ,Principal principal);

    public void setCashTo0(Portfolio portfolio, Principal principal);

}
