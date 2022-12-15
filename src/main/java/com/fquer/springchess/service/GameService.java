package com.fquer.springchess.service;

import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.Coordinates;
import com.fquer.springchess.model.enums.PieceEnum;
import com.fquer.springchess.model.piece.*;
import com.fquer.springchess.model.rto.Game;
import com.fquer.springchess.model.rto.Logins;
import com.fquer.springchess.model.rto.Player;
import com.fquer.springchess.storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
@AllArgsConstructor
public class GameService {
    private final PieceFactory pieceFactory = new PieceFactory();

    private boolean checkStatement(Coordinates coordinate, PieceEnum piece, ColorEnum color){
        if (piece == PieceEnum.Pawn && color == ColorEnum.White && Pawn.whiteInitCoordinates.contains(coordinate)){
            return true;
        }
        else if (piece == PieceEnum.Pawn && color == ColorEnum.Black && Pawn.blackInitCoordinates.contains(coordinate)){
            return true;
        }
        else if (piece == PieceEnum.Bishop && color == ColorEnum.White && Bishop.whiteInitCoordinates.contains(coordinate)){
            return true;
        }
        else if (piece == PieceEnum.Bishop && color == ColorEnum.Black && Bishop.blackInitCoordinates.contains(coordinate)){
            return true;
        }
        else if (piece == PieceEnum.Knight && color == ColorEnum.White && Knight.whiteInitCoordinates.contains(coordinate)){
            return true;
        }
        else if (piece == PieceEnum.Knight && color == ColorEnum.Black && Knight.blackInitCoordinates.contains(coordinate)){
            return true;
        }
        else if (piece == PieceEnum.Rook && color == ColorEnum.White && Rook.whiteInitCoordinates.contains(coordinate)){
            return true;
        }
        else if (piece == PieceEnum.Rook && color == ColorEnum.Black && Rook.blackInitCoordinates.contains(coordinate)){
            return true;
        }
        else if (piece == PieceEnum.Queen && color == ColorEnum.White && Queen.whiteInitCoordinates.contains(coordinate)){
            return true;
        }
        else if (piece == PieceEnum.Queen && color == ColorEnum.Black && Queen.blackInitCoordinates.contains(coordinate)){
            return true;
        }
        else if (piece == PieceEnum.King && color == ColorEnum.White && King.whiteInitCoordinates.contains(coordinate)){
            return true;
        }
        else if (piece == PieceEnum.King && color == ColorEnum.Black && King.blackInitCoordinates.contains(coordinate)){
            return true;
        }
        return false;
    }

    private void generatePieces(MapService map){

        for (Coordinates coordinate: Coordinates.values()){
            for (PieceEnum piece: PieceEnum.values()){
                for (ColorEnum color: ColorEnum.values()){
                    if (checkStatement(coordinate, piece, color)){
                        map.setPieceCoordinate(coordinate, pieceFactory.getPiece(piece, color));
                    }
                }
            }
        }


        for (Coordinates coordinate: Coordinates.values()){
            if (map.getPieceByCoordinate(coordinate) == null){
                map.setPieceCoordinate(coordinate, pieceFactory.getPiece(PieceEnum.Empty,ColorEnum.Empty));
            }
        }

    }
    public Game createGame(Player player) {
        if (GameStorage.getInstance().getGame() == null) {
            Game game = new Game();
            MapService map = new MapService();
            game.setBoard(map);
            if (player.getColor() == ColorEnum.White) {
                game.setPlayer1(player);
            }
            else if (player.getColor() == ColorEnum.Black) {
                game.setPlayer2(player);
            }
            else {
                throw new InvalidParameterException("Invalid color");
            }
            GameStorage.getInstance().setGame(game);
            this.generatePieces(game.getBoard());
            return game;
        }
        else {
            throw new InvalidParameterException("Game already started!");
        }
    }

    public Game connectToGame(Player player) {
        if (GameStorage.getInstance().getGame() == null) {
            throw new InvalidParameterException("Game doesnt exist."); // duzgun exception ayarla
        }
        Game game = GameStorage.getInstance().getGame();

        if (player.getColor() == ColorEnum.White && game.getPlayer1() == null) {
            game.setPlayer1(player);
        }
        else if (player.getColor() == ColorEnum.Black && game.getPlayer2() == null) {
            game.setPlayer2(player);
        }
        else {
            throw new InvalidParameterException("Player slot is not empty");
        }

        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game gameStatus(Player player) {
        if (GameStorage.getInstance().getGame() == null) {
            return new Game();
        }
        Game game = GameStorage.getInstance().getGame();
        if (game.getPlayer1() != null) {
            if (player.getLogin().equals(game.getPlayer1().getLogin())) {
                return game;
            }
        }
        if (game.getPlayer2() != null) {
            if (player.getLogin().equals(game.getPlayer2().getLogin())) {
                return game;
            }
        }

        throw new InvalidParameterException("Player not match!");
    }

    public Logins checkLogin() {
        Logins logins = new Logins();
        if (GameStorage.getInstance().getGame() == null) {
            logins.setPlayer1(null);
            logins.setPlayer2(null);
        }
        else {
            Game game = GameStorage.getInstance().getGame();
            logins.setPlayer1(game.getPlayer1());
            logins.setPlayer2(game.getPlayer2());
        }

        return logins;
    }

    public Game gamePlay(Player player, String moveToCoordinate) {
        if (GameStorage.getInstance().getGame() == null) {
            throw new InvalidParameterException("Game doesnt exist."); // duzgun exception ayarla
        }

        Game game = GameStorage.getInstance().getGame();
        MapService map = game.getBoard();

        if (map.getPieceByCoordinate(map.getSelectedCoordinate()).getPiece() == PieceEnum.Empty) {
            throw new InvalidParameterException("Cant select empty area!");
        }

        if ((map.getTurn() == ColorEnum.White && player.getLogin().equals(game.getPlayer1().getLogin())) ||
            (map.getTurn() == ColorEnum.Black && player.getLogin().equals(game.getPlayer2().getLogin()))) {

            Piece moveToPiece = map.getPieceByCoordinate(Coordinates.valueOf(moveToCoordinate));
            map.setPieceCoordinate(Coordinates.valueOf(moveToCoordinate), map.getPieceByCoordinate(map.getSelectedCoordinate()));
            map.setPieceCoordinate(map.getSelectedCoordinate(), moveToPiece);

            // Tur degistirme

            /*
            if (map.getTurn() == ColorEnum.White) {
                map.setTurn(ColorEnum.Black);
            }
            else {
                map.setTurn(ColorEnum.White);
            }
            */
            map.setSelectedCoordinate(null);
            map.clearMoveableCoordinates();
            GameStorage.getInstance().setGame(game);
            return game;
        }
        else {
            throw new InvalidParameterException("Turn and player not match!");
        }
    }

    public Game checkMoveableArea(Player player, String selectedCoordinate) {
        if (GameStorage.getInstance().getGame() == null) {
            throw new InvalidParameterException("Game doesnt exist."); // duzgun exception ayarla
        }

        Game game = GameStorage.getInstance().getGame();
        MapService map = game.getBoard();
        map.setSelectedCoordinate(Coordinates.valueOf(selectedCoordinate));
        MoveCheckerService moveCheckerService = new MoveCheckerService(map);

        moveCheckerService.checkPieceMoveableCoordinates(selectedCoordinate);

        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game cancelSelectedCoordinate(Player player, String selectedCoordinate) {
        if (GameStorage.getInstance().getGame() == null) {
            throw new InvalidParameterException("Game doesnt exist."); // duzgun exception ayarla
        }

        Game game = GameStorage.getInstance().getGame();
        MapService map = game.getBoard();
        if (map.getSelectedCoordinate() == Coordinates.valueOf(selectedCoordinate)) {
            map.setSelectedCoordinate(null);
            map.clearMoveableCoordinates();
        }
        else {
            throw new InvalidParameterException("Invalid cancel selected coordinate");
        }
        GameStorage.getInstance().setGame(game);
        return game;
    }

}