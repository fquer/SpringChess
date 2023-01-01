package com.fquer.springchess.storage;

import com.fquer.springchess.model.dto.Game;

public class GameHistoryStorage {
    private static Game game;
    private static GameHistoryStorage instance;

    public static synchronized GameHistoryStorage getInstance() {
        if (instance == null) {
            instance = new GameHistoryStorage();
        }
        return instance;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        GameHistoryStorage.game = game;
    }
}
