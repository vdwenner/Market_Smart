package com.techelevator.dao;

import com.techelevator.model.Game;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Component
public class JdbcGameDao implements GameDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcGameDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createGame(String gameName, Long creatorId, BigDecimal startingAmount, Date endDate) {
        String sql = "INSERT INTO games (game_name, creator_id, starting_amount, end_date) " +
                     "VALUES(?, ?, ?, ?);";
        jdbcTemplate.update(sql, gameName, creatorId, startingAmount, endDate);

        String sql2 = "SELECT game_id, game_name, creator_id, starting_amount, end_date FROM games " +
                      "WHERE game_name = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql2, gameName);

        Game game = null;

        if(results.next()) {

            game = mapRowToGame(results);
        }

        String sql3 =  "INSERT INTO game_users (game_id, user_id) " +
                       "VALUES(?, ?);";
        jdbcTemplate.update(sql3, game.getId(), game.getCreatorId());
    }

    @Override
    public void setInitialGameUsers(Long gameId, Long userID) {
        String sql = "INSERT INTO game_users (game_id, user_id) " +
                     "VALUES(?, ?);";
        jdbcTemplate.update(sql, gameId, userID);
    }

    @Override
    public Game getGameByGameName(String gameName) {
        String sql = "SELECT game_id, game_name, creator_id, starting_amount, end_date " +
                     "FROM games " +
                     "WHERE game_name = ?;";
        return mapRowToGame(jdbcTemplate.queryForRowSet(sql, gameName));
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

}
