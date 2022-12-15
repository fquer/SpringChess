package com.fquer.springchess.model.piece;

import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.Coordinates;
import com.fquer.springchess.model.enums.PieceEnum;

import java.util.Arrays;
import java.util.List;

public class King implements Piece {
    public static List<Coordinates> whiteInitCoordinates = Arrays.asList(Coordinates.E1);
    public static List<Coordinates> blackInitCoordinates = Arrays.asList(Coordinates.E8);
    private ColorEnum color;
    public PieceEnum getPiece(){
        return PieceEnum.King;
    }

    public void setColour(ColorEnum color){
        this.color = color;
    }

    public ColorEnum getColour() {
        return this.color;
    }

}
