package com.fquer.springchess.model.piece;

import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.Coordinates;
import com.fquer.springchess.model.enums.PieceEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop implements Piece {
    public static List<Coordinates> whiteInitCoordinates = Arrays.asList(Coordinates.C1, Coordinates.F1);
    public static List<Coordinates> blackInitCoordinates = Arrays.asList(Coordinates.C8, Coordinates.F8);
    private ColorEnum color;
    private List<String> canBeat = new ArrayList<String>();
    public PieceEnum getPiece(){
        return PieceEnum.Bishop;
    }
    public void setColour(ColorEnum color){
        this.color = color;
    }
    public ColorEnum getColour() {
        return this.color;
    }
}