package com.fquer.springchess.service;

import com.fquer.springchess.model.dto.Game;
import com.fquer.springchess.model.dto.GameDb;
import com.fquer.springchess.model.dto.GamePlayDb;
import com.fquer.springchess.model.dto.Player;
import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.Coordinates;
import com.fquer.springchess.model.enums.PieceEnum;
import com.fquer.springchess.model.piece.Empty;
import com.fquer.springchess.model.piece.Piece;
import com.fquer.springchess.storage.GameHistoryStorage;
import com.fquer.springchess.storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GameHistoryService {

    public GamePlayDb getMatchHistory(String gameId, String moveOrder) {
        DatabaseService databaseService = DatabaseService.getInstance();
        return databaseService.getMatchHistory(gameId, moveOrder);
    }

    public List<GameDb> getMatches() {
        DatabaseService databaseService = DatabaseService.getInstance();
        return databaseService.getMatches();
    }

    public GameDb getMatchByGameId(String gameId) {
        DatabaseService databaseService = DatabaseService.getInstance();
        return databaseService.getMatchByGameId(gameId);
    }

    public void createHistoryGame(String gameId) {
        Game game = new Game();
        MapService map = new MapService();
        game.setGameId(gameId);
        game.setBoard(map);
        Player player1 = new Player();
        player1.setLogin(getMatchByGameId(gameId).getPlayer1());
        Player player2 = new Player();
        player2.setLogin(getMatchByGameId(gameId).getPlayer2());
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        GameService.generatePieces(game.getBoard());
        GameHistoryStorage.getInstance().setGame(game);
    }

    public Game playHistory(String moveOrder, String gameId) {
        if (Objects.equals(moveOrder, "1")) {
            createHistoryGame(gameId);
        }
        Game game = GameHistoryStorage.getInstance().getGame();
        MapService map = game.getBoard();

        String selectedCoordinate = getMatchHistory(gameId, moveOrder).getSelectedCoordinate();
        String moveToCoordinate = getMatchHistory(gameId, moveOrder).getMoveToCoordinate();

        Piece selectedPiece = map.getPieceByCoordinate(Coordinates.valueOf(selectedCoordinate));
        Piece moveToPiece = map.getPieceByCoordinate(Coordinates.valueOf(moveToCoordinate));
        map.setPieceCoordinate(Coordinates.valueOf(moveToCoordinate), selectedPiece);

        if (moveToPiece.getPiece() == PieceEnum.Empty) {
            map.setPieceCoordinate(Coordinates.valueOf(selectedCoordinate), moveToPiece);
        }
        else {
            Piece emptyPiece = new Empty();
            emptyPiece.setColour(ColorEnum.Empty);
            map.setPieceCoordinate(Coordinates.valueOf(selectedCoordinate), emptyPiece);
        }
        GameHistoryStorage.getInstance().setGame(game);
        return game;
    }

}
