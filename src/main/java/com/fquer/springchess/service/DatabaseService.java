package com.fquer.springchess.service;

import com.fquer.springchess.model.dto.GameDb;
import com.fquer.springchess.model.dto.GamePlayDb;
import com.fquer.springchess.repository.GamePlayRepository;
import com.fquer.springchess.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DatabaseService {
    private GamePlayRepository gamePlayRepository;
    private GameRepository gameRepository;
    private static DatabaseService instance;

    public static synchronized DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    public void setRepositories(GameRepository gameRepository, GamePlayRepository gamePlayRepository) {
        this.gameRepository = gameRepository;
        this.gamePlayRepository = gamePlayRepository;
    }
    public void addGamePlay(String gameId, String player, String selectedCoordinate, String moveToCoordinate, String moveOder) {
        GamePlayDb gamePlayDb = new GamePlayDb();
        gamePlayDb.setGameId(gameId);
        gamePlayDb.setPlayer(player);
        gamePlayDb.setSelectedCoordinate(selectedCoordinate);
        gamePlayDb.setMoveToCoordinate(moveToCoordinate);
        gamePlayDb.setMoveOrder(moveOder);
        gamePlayRepository.save(gamePlayDb);
    }

    public void createGame(String gameId, String player1, String player2) {
        GameDb gameDb = new GameDb();
        gameDb.setGameId(gameId);
        gameDb.setPlayer1(player1);
        gameDb.setPlayer2(player2);
        gameDb.setWinner("Empty");
        gameDb.setCreateDate(new Date());
        gameDb.setFinishDate(null);
        gameRepository.save(gameDb);
    }

    public void setGameWinner(String gameId, String winner) {
        Optional<GameDb> gameDb = Optional.ofNullable(gameRepository.findByGameId(gameId));
        if(gameDb.isPresent()){
            GameDb foundGameDb = gameDb.get();
            System.out.println("Db cekilen bilgiler:");
            System.out.println(foundGameDb.getGameId());
            System.out.println(foundGameDb.getPlayer1());
            System.out.println(foundGameDb.getPlayer2());
            System.out.println(foundGameDb.getWinner());
            foundGameDb.setWinner(winner);
            foundGameDb.setFinishDate(new java.util.Date(System.currentTimeMillis()));
            gameRepository.save(foundGameDb);
        }
    }

    public List<GameDb> getMatches() {
        List<GameDb> gameList = gameRepository.findAll();
        System.out.println(gameList);
        return gameList;
    }

    public GameDb getMatchByGameId(String gameId) {
        return gameRepository.findByGameId(gameId);
    }

    public GamePlayDb getMatchHistory(String gameId, String moveOrder) {
        return gamePlayRepository.findByGameIdAndMoveOrder(gameId, moveOrder);
    }
}
