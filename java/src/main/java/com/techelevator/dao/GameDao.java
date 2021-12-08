package com.techelevator.dao;

import com.techelevator.model.Game;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

public interface GameDao {

    void createGame(String gameName, Long creatorId, BigDecimal startingAmount, Date endDate);

    void setInitialGameUsers(Long gameId, Long userID);

    Game getGameByGameName(String gameName);



}
