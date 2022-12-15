package com.fquer.springchess.model.piece;

import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.PieceEnum;
import com.fquer.springchess.model.piece.*;

public class PieceFactory {
    private Piece piece;
    public Piece getPiece(PieceEnum pieceEnum, ColorEnum color) {
        if (pieceEnum == PieceEnum.Pawn){
            this.piece = new Pawn();
        }
        else if (pieceEnum == PieceEnum.Bishop){
            this.piece = new Bishop();
        }
        else if (pieceEnum == PieceEnum.King){
            this.piece = new King();
        }
        else if (pieceEnum == PieceEnum.Knight){
            this.piece = new Knight();
        }
        else if (pieceEnum == PieceEnum.Queen){
            this.piece = new Queen();
        }
        else if (pieceEnum == PieceEnum.Rook){
            this.piece = new Rook();
        }
        else if (pieceEnum == PieceEnum.Empty){
            this.piece = new Empty();
        }
        else{
            return null;
        }
        if (color == ColorEnum.White){
            this.piece.setColour(ColorEnum.White);
        }
        else if (color == ColorEnum.Black){
            this.piece.setColour(ColorEnum.Black);
        }
        else if (color == ColorEnum.Empty){
            this.piece.setColour(ColorEnum.Empty);
        }
        else{
            return null;
        }
        return this.piece;
    }
}
