package com.fquer.springchess.model.piece;

import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.PieceEnum;

public interface Piece {

    PieceEnum getPiece();
    void setColour(ColorEnum color);
    ColorEnum getColour();
}
