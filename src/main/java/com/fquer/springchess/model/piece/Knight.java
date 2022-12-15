package com.fquer.springchess.model.piece;

import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.Coordinates;
import com.fquer.springchess.model.enums.PieceEnum;

import java.util.Arrays;
import java.util.List;

public class Knight implements Piece {
    public static List<Coordinates> whiteInitCoordinates = Arrays.asList(Coordinates.B1, Coordinates.G1);
    public static List<Coordinates> blackInitCoordinates = Arrays.asList(Coordinates.B8, Coordinates.G8);
    private ColorEnum color;
    public PieceEnum getPiece(){
        return PieceEnum.Knight;
    }

    public void setColour(ColorEnum color){
        this.color = color;
    }

    public ColorEnum getColour() {
        return this.color;
    }
}
