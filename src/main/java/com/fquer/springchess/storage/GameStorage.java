package com.fquer.springchess.storage;

import com.fquer.springchess.model.rto.Game;

public class GameStorage {
    private static Game game;
    private static GameStorage instance;

    public static synchronized GameStorage getInstance() {
        if (instance == null) {
            instance = new GameStorage();
        }
        return instance;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        GameStorage.game = game;
    }
}
