package com.fquer.springchess.model.piece;

import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.Coordinates;
import com.fquer.springchess.model.enums.PieceEnum;

import java.util.Arrays;
import java.util.List;

public class Rook implements Piece {
    public static List<Coordinates> whiteInitCoordinates = Arrays.asList(Coordinates.A1, Coordinates.H1);
    public static List<Coordinates> blackInitCoordinates = Arrays.asList(Coordinates.A8, Coordinates.H8);
    private ColorEnum color;
    public PieceEnum getPiece(){
        return PieceEnum.Rook;
    }

    public void setColour(ColorEnum color){
        this.color = color;
    }

    public ColorEnum getColour() {
        return this.color;
    }
}
