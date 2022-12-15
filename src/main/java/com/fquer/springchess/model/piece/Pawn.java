package com.fquer.springchess.model.piece;

import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.Coordinates;
import com.fquer.springchess.model.enums.PieceEnum;

import java.util.Arrays;
import java.util.List;

public class Pawn implements Piece {
    public static List<Coordinates> whiteInitCoordinates = Arrays.asList(Coordinates.A2, Coordinates.B2, Coordinates.C2, Coordinates.D2, Coordinates.E2, Coordinates.F2, Coordinates.G2, Coordinates.H2);
    public static List<Coordinates> blackInitCoordinates = Arrays.asList(Coordinates.A7, Coordinates.B7, Coordinates.C7, Coordinates.D7, Coordinates.E7, Coordinates.F7, Coordinates.G7, Coordinates.H7);
    private ColorEnum color;
    public PieceEnum getPiece(){
        return PieceEnum.Pawn;
    }
    public void setColour(ColorEnum color){
        this.color = color;
    }

    public ColorEnum getColour() {
        return this.color;
    }
}
