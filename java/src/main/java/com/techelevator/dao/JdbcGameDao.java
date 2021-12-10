package com.techelevator.dao;

import com.techelevator.model.Game;
import com.techelevator.model.InviteType;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private InviteType mapRowToInviteType(SqlRowSet rowSet) {
        InviteType inviteType = new InviteType();
        inviteType.setReceiverId(rowSet.getLong("receiver_id"));
        inviteType.setInviteTypeId(rowSet.getLong("game_invite_type_id"));
        inviteType.setGameId(rowSet.getLong("game_id"));
        inviteType.setSenderId(rowSet.getLong("sender_id"));
        return inviteType;
    }


}
