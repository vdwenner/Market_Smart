package com.techelevator.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class JdbcGameDao implements GameDao{

    private final JdbcTemplate jdbcTemplate;
    private final GameDao gameDao;

    @Override
    public void createGame(String gameName, Long creatorId, BigDecimal startingAmount, LocalDateTime endDate) {
        String sql = "INSERT INTO games (game_name, creator_id, starting_amount, end_date) " +
                     "Values(?, ?, ?, ?);";
        jdbcTemplate.update(sql, gameName, creatorId, startingAmount, endDate);
    }
}
