package com.fquer.springchess.service;

import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.Coordinates;
import com.fquer.springchess.model.piece.Piece;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MapService {
    private Hashtable<Coordinates, Piece> coordinates = new Hashtable<>();
    private ColorEnum turn = ColorEnum.White;
    private Coordinates selectedCoordinate;
    private List<Coordinates> moveableCoordinates = new ArrayList<>();
    private List<Coordinates> kingCantMoveCoordinates = new ArrayList<>();
    private static MapService instance;
    private ColorEnum winner = ColorEnum.Empty;
    private boolean checkStatus = false;
    private Coordinates whiteKingCoordinate = Coordinates.E1;
    private Coordinates blackKingCoordinate = Coordinates.E8;
    private Integer moveOrder = 0;

    public static synchronized MapService getInstance() {
        if (instance == null) {
            instance = new MapService();
        }
        return instance;
    }

    public Hashtable getMap(){
        return coordinates;
    }

    public void setMap(Hashtable<Coordinates, Piece> map) {
        this.coordinates = map;
    }

    public void setPieceCoordinate(Coordinates coordinate, Piece piece){
        coordinates.put(coordinate, piece);
    }

    public Piece getPieceByCoordinate(Coordinates coordinate){
        Piece piece = coordinates.get(coordinate);
        if (piece != null){
            return piece;
        }
        else{
            return null;
        }
    }


    public ColorEnum getTurn() {
        return turn;
    }

    public void setTurn(ColorEnum turn) {
        this.turn = turn;
    }

    public List<Coordinates> getMoveableCoordinates() {
        return moveableCoordinates;
    }

    public void clearMoveableCoordinates() {
        moveableCoordinates.clear();
    }

    public void addMoveableCoordinates(String coordinate) {
        if (coordinate != "") {
            moveableCoordinates.add(Coordinates.valueOf(coordinate));
        }
    }

    public Coordinates getSelectedCoordinate() {
        return selectedCoordinate;
    }

    public void setSelectedCoordinate(Coordinates selectedCoordinate) {
        this.selectedCoordinate = selectedCoordinate;
    }

    public List<Coordinates> getKingCantMoveCoordinates() {
        return kingCantMoveCoordinates;
    }

    public void setKingCantMoveCoordinates(List<Coordinates> kingCantMoveCoordinates) {
        this.kingCantMoveCoordinates = kingCantMoveCoordinates;
    }

    public Coordinates getWhiteKingCoordinate() {
        return whiteKingCoordinate;
    }

    public void setWhiteKingCoordinate(Coordinates whiteKingCoordinate) {
        this.whiteKingCoordinate = whiteKingCoordinate;
    }

    public Coordinates getBlackKingCoordinate() {
        return blackKingCoordinate;
    }

    public void setBlackKingCoordinate(Coordinates blackKingCoordinate) {
        this.blackKingCoordinate = blackKingCoordinate;
    }

    public void setWinner(ColorEnum winner) {
        this.winner = winner;
    }

    public ColorEnum getWinner() {
        return winner;
    }

    public boolean isCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getMoveOrder() {
        return moveOrder;
    }

    public void moveOrderIncrement() {
        this.moveOrder += 1;
    }
}
