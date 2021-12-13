package com.techelevator.dao;

import com.techelevator.model.Gamer;
import com.techelevator.model.Portfolio;
import com.techelevator.model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(Long userId);

    User findByUsername(String username);

    Long findIdByUsername(String username);

    Gamer findGamerByUsername(String username);

    boolean create(String username, String password, String role);


    Portfolio getPortfolioByUserIdAndGameId(Long userId, Long gameId);
}
