package com.fquer.springchess.model.piece;

import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.PieceEnum;

public class Empty implements Piece {
    private ColorEnum color;
    public PieceEnum getPiece(){
        return PieceEnum.Empty;
    }

    public void setColour(ColorEnum color){
        this.color = color;
    }

    public ColorEnum getColour() {
        return this.color;
    }

}
