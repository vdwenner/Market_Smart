package com.techelevator.controller;

import com.techelevator.dao.GameDao;
import com.techelevator.model.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class GameController {

    private final GameDao gameDao;

    public GameController(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    //CREATE A NEW GAME
    @RequestMapping(path = "/createGame", method = RequestMethod.POST)
    public void createGame(@RequestBody Game game, Principal principal) {
        gameDao.createGame(game.getGameName(),  game.getStartingAmount(), game.getEndDate(),principal);
    }

    @RequestMapping(path = "/games/{gameName}", method = RequestMethod.GET)
    public Game getGameByGameName(@PathVariable String gameName) {
        return gameDao.getGameByGameName(gameName);
    }


    @RequestMapping(path = "/games/Id/{gameId}", method = RequestMethod.GET)
    public Game getGameByGameId(@PathVariable Long gameId) {
        return gameDao.getGameByGameId(gameId);
    }

    @RequestMapping(path = "/user/games", method = RequestMethod.GET)
    public List<Game> getAllUserGames(Principal principal){
        return gameDao.getAllUserGames(principal);
    }

    @RequestMapping(path= "/games/invite/request", method = RequestMethod.POST)
    public void requestGameInvite(@RequestBody InviteType inviteType, Principal principal) {
        gameDao.sendGameInvite(inviteType, principal); }

    @RequestMapping(path= "/games/invite/pending", method = RequestMethod.GET)
    public List<InviteType> listPendingGameInvites (Principal principal) { return gameDao.listPendingGameInvites(principal); }

    @RequestMapping(path="/games/invite/accept", method = RequestMethod.PUT)
    public void approveGameInvite(@RequestBody Game pendingGame, Principal principal){
        gameDao.approveGameInvite(pendingGame, principal);
    }
    @RequestMapping(path="/games/invite/reject", method = RequestMethod.PUT)
    public void rejectGameInvite(@RequestBody Game pendingGame, Principal principal){
        gameDao.rejectGameInvite(pendingGame,principal);
    }

    @RequestMapping(path="/user/games/{gameId}/leaderboard", method = RequestMethod.GET)
    public List<Portfolio> viewLeaderboard(@PathVariable Long gameId, Principal principal) {
        return gameDao.viewLeaderboard(gameId, principal);
    }

    @RequestMapping(path="/user/games/{gameId}/{symbol}/buy", method = RequestMethod.POST)
    public void buyStock (@RequestBody Transaction transaction, @PathVariable("gameId") Long gameId, @PathVariable("symbol") String symbol,  Principal  principal ){
        gameDao.buyStock(symbol,transaction.getPrice(),transaction.getQuantity(),transaction.getPortfolioId(),principal);
    }

    @RequestMapping(path="/user/games/{gameId}/{symbol}/sell", method = RequestMethod.POST)
    public void sellStock (@RequestBody Transaction transaction, @PathVariable("gameId") Long gameId, @PathVariable("symbol") String symbol,  Principal  principal ){
        gameDao.sellStock(symbol,transaction.getPrice(),transaction.getQuantity(),transaction.getPortfolioId(),principal);
    }
}
