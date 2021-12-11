package com.techelevator.dao;

import com.techelevator.model.Game;
import com.techelevator.model.InviteType;
import com.techelevator.model.Portfolio;

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

    List<Portfolio> viewLeaderboard(Game game, Principal principal);

}
