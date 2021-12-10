package com.techelevator.dao;

import com.techelevator.model.Game;
import com.techelevator.model.InviteType;

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
    void approveGameInvite(InviteType pendingInvite, Principal principal);
    List<Game> getAllUserGames(Principal principal);
    void rejectGameInvite(InviteType pendingInvite, Principal principal);

    void sendGameInvite(InviteType inviteType, Principal principal);

    List<InviteType> listPendingGameInvites(Principal principal);

}
