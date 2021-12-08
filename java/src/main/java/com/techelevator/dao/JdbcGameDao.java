package com.techelevator.dao;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class JdbcGameDao implements GameDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createGame(String gameName, Long creatorId, BigDecimal startingAmount, LocalDateTime endDate) {
        String sql = "INSERT INTO games (game_name, creator_id, starting_amount, end_date) " +
                     "VALUES(?, ?, ?, ?);";
        jdbcTemplate.update(sql, gameName, creatorId, startingAmount, endDate);
    }

    @Override
    public void setInitialGameUsers(Long gameId, Long userID) {
        String sql = "INSERT INTO game_users (game_id, user_id) " +
                     "VALUES(?, ?);";
        jdbcTemplate.update(sql, gameId, userID);
    }

}
