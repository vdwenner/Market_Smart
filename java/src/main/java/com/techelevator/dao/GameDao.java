package com.techelevator.dao;

import com.techelevator.model.Game;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface GameDao {

    void createGame(String gameName, Long creatorId, BigDecimal startingAmount, LocalDateTime endDate);

    void setInitialGameUsers(Long gameId, Long userID);

}
