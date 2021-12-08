package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.model.Game;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GameController {

    private final GameDao gameDao;

    //CREATE A NEW GAME
    @RequestMapping(path = "/createGame", method = RequestMethod.POST)
    public void createGame(@RequestBody Game game) {
        gameDao.createGame(game.getGameName(), game.getCreatorId(), game.getStartingAmount(), game.getEndDate());
    }

}
