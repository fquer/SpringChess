package com.fquer.springchess.service;

import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.Coordinates;
import com.fquer.springchess.model.enums.PieceEnum;
import com.fquer.springchess.model.piece.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MoveCheckerService {
    private MapService map;
    private final List<Character> coordinateLabels = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H');
    private final List<Coordinates> pawnWhiteFixedCoordinates = Arrays.asList(Coordinates.A2, Coordinates.B2, Coordinates.C2, Coordinates.D2, Coordinates.E2, Coordinates.F2, Coordinates.G2, Coordinates.H2);
    private final List<Coordinates> pawnBlackFixedCoordinates = Arrays.asList(Coordinates.A7, Coordinates.B7, Coordinates.C7, Coordinates.D7, Coordinates.E7, Coordinates.F7, Coordinates.G7, Coordinates.H7);

    public MoveCheckerService(MapService map) {
        this.map = map;
    }
    public void checkPieceMoveableCoordinates(String coordinate) {
        Piece piece = map.getPieceByCoordinate(Coordinates.valueOf(coordinate));
        if (piece.getPiece() == PieceEnum.Pawn) {
            checkPawnMoveableCoordinate(piece, coordinate);
        }
        else if (piece.getPiece() == PieceEnum.Knight) {
            checkKnightMoveableCoordinate(piece, coordinate, false);
        }
        else if (piece.getPiece() == PieceEnum.Bishop) {
            checkBishopMoveableCoordinate(piece, coordinate, false);
        }
        else if (piece.getPiece() == PieceEnum.Rook) {
            checkRookMoveableCoordinate(piece, coordinate, false);
        }
        else if (piece.getPiece() == PieceEnum.Queen) {
            checkQueenMoveableCoordinate(piece, coordinate, false);
        }
        else if (piece.getPiece() == PieceEnum.King) {
            checkKingMoveableCoordinate(piece, coordinate, false);
        }
    }
    private void checkPawnMoveableCoordinate(Piece piece, String coordinate) {
        List<Coordinates> pawnFixedCoordinates = new ArrayList<>();
        if (piece.getColour() ==  ColorEnum.White) {
            pawnFixedCoordinates = pawnWhiteFixedCoordinates;
        }
        else if (piece.getColour() == ColorEnum.Black) {
            pawnFixedCoordinates = pawnBlackFixedCoordinates;
        }
        checkPawnCornerMoveableCoordinate(piece, coordinate, false);

        if (pawnFixedCoordinates.contains(Coordinates.valueOf(coordinate))) {
            for (int i = 1; i <= 2; i++) {
                if (piece.getColour() == ColorEnum.Black) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, i * -1))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(coordinateAddition(coordinate, i * -1));
                    }
                    else {
                        break;
                    }
                }
                else {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, i))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(coordinateAddition(coordinate, i));
                    }
                    else {
                        break;
                    }
                }
            }
        }
        else {
            if (piece.getColour() == ColorEnum.Black) {
                if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, -1))).getPiece() == PieceEnum.Empty) {
                    map.addMoveableCoordinates(coordinateAddition(coordinate, -1));
                }
            }
            else {
                if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, 1))).getPiece() == PieceEnum.Empty) {
                    map.addMoveableCoordinates(coordinateAddition(coordinate, 1));
                }
            }
        }
    }

    public void checkPawnCornerMoveableCoordinate(Piece piece, String coordinate, boolean forKing) {
        if (piece.getColour() == ColorEnum.Black) {
            try {
                if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, -1), -1))).getPiece() != PieceEnum.Empty &&
                        map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, -1), -1))).getColour() != piece.getColour() || forKing) {
                    map.addMoveableCoordinates(labelAddition(coordinateAddition(coordinate, -1), -1));
                }
            }
            catch (Exception e) {

            }

            try {
                if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, -1), 1))).getPiece() != PieceEnum.Empty &&
                        map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, -1), 1))).getColour() != piece.getColour() || forKing) {
                    map.addMoveableCoordinates(labelAddition(coordinateAddition(coordinate, -1), 1));
                }
            }
            catch (Exception e) {

            }
        }
        else {
            try {
                if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, 1), -1))).getPiece() != PieceEnum.Empty &&
                        map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, 1), -1))).getColour() != piece.getColour() || forKing) {
                    map.addMoveableCoordinates(labelAddition(coordinateAddition(coordinate, 1), -1));
                }
            }
            catch (Exception e) {

            }

            try {
                if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, 1), 1))).getPiece() != PieceEnum.Empty &&
                        map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, 1), 1))).getColour() != piece.getColour() || forKing) {
                    map.addMoveableCoordinates(labelAddition(coordinateAddition(coordinate, 1), 1));
                }
            }
            catch (Exception e) {

            }
        }
    }

    private void checkKnightMoveableCoordinate(Piece piece, String coordinate, boolean forKing) {
        for (int i = -2; i <= 2; i++) {
            if (i == 0) {
                i++;
            }
            if (i == 1 || i == -1) {
                try {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), 2))).getColour() != piece.getColour() || forKing) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), 2));
                    }
                }
                catch (Exception e) {

                }
                try {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), -2))).getColour() != piece.getColour() || forKing) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), -2));
                    }
                }
                catch (Exception e) {

                }
            }
            else if (i == 2 || i == -2) {
                try {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), 1))).getColour() != piece.getColour() || forKing) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), 1));
                    }
                }
                catch (Exception e) {

                }
                try {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), -1))).getColour() != piece.getColour() || forKing) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), -1));
                    }
                }
                catch (Exception e) {

                }
            }
        }
    }

    public void checkBishopMoveableCoordinate(Piece piece, String coordinate, boolean forKing) {
        boolean ff = true;
        boolean fb = true;
        boolean bf = true;
        boolean bb = true;
        for (int i = 1; i <= 7; i++) {
            try {
                if (bf) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i * -1), i))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i * -1), i));
                    }
                    else {
                        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i * -1), i))).getColour() != piece.getColour() || forKing) {
                            map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i * -1), i));
                        }
                        bf = false;
                    }
                }

            }
            catch (Exception e) {

            }


            try {
                if (bb) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i * -1), i * -1))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i * -1), i * -1));
                    }
                    else {
                        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i * -1), i * -1))).getColour() != piece.getColour() || forKing) {
                            map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i * -1), i * -1));
                        }
                        bb = false;
                    }
                }

            }
            catch (Exception e) {

            }

            try {
                if (ff) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), i))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), i));
                    }
                    else {
                        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), i))).getColour() != piece.getColour() || forKing) {
                            map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), i));
                        }
                        ff = false;
                    }
                }

            }
            catch (Exception e) {

            }

            try {
                if (fb) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), i * -1))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), i * -1));
                    }
                    else {
                        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), i * -1))).getColour() != piece.getColour() || forKing) {
                            map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), i * -1));
                        }
                        fb = false;
                    }
                }

            }
            catch (Exception e) {

            }
        }
    }

    public void checkRookMoveableCoordinate(Piece piece, String coordinate, boolean forKing) {
        boolean f = true;
        boolean b = true;
        boolean r = true;
        boolean l = true;
        for (int i = 1; i <= 7; i++) {
            try {
                if (f) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, i))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(coordinateAddition(coordinate, i));
                    }
                    else {
                        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, i))).getColour() != piece.getColour() || forKing) {
                            map.addMoveableCoordinates(coordinateAddition(coordinate, i));
                        }
                        f = false;
                    }
                }
            }
            catch (Exception e){

            }
            try {
                if (b) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, i * -1))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(coordinateAddition(coordinate, i * -1));
                    }
                    else {
                        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, i * -1))).getColour() != piece.getColour() || forKing) {
                            map.addMoveableCoordinates(coordinateAddition(coordinate, i * -1));
                        }
                        b = false;
                    }
                }
            }
            catch (Exception e){

            }
            try {
                if (r) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinate, i))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(labelAddition(coordinate, i));
                    }
                    else {
                        if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinate, i))).getColour() != piece.getColour() || forKing) {
                            map.addMoveableCoordinates(labelAddition(coordinate, i));
                        }
                        r = false;
                    }
                }
            }
            catch (Exception e){

            }
            try {
                if (l) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinate, i * -1))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(labelAddition(coordinate, i * -1));
                    }
                    else {
                        if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinate, i * -1))).getColour() != piece.getColour() || forKing) {
                            map.addMoveableCoordinates(labelAddition(coordinate, i * -1));
                        }
                        l = false;
                    }
                }
            }
            catch (Exception e){

            }
        }
    }

    public void checkQueenMoveableCoordinate(Piece piece, String coordinate, boolean forKing) {
        checkRookMoveableCoordinate(piece, coordinate, forKing);
        checkBishopMoveableCoordinate(piece, coordinate, forKing);
    }

    public void checkKingMoveableCoordinate(Piece piece, String coordinate, boolean forKing) {
        List<Coordinates> kingCantMoveCoordinates = new ArrayList<>();
        if (!forKing){
            checkKingRiskPoints(piece);
            kingCantMoveCoordinates.addAll(map.getKingCantMoveCoordinates());
        }

        try{
            if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, 1))).getColour() != piece.getColour() || forKing)
                map.addMoveableCoordinates(coordinateAddition(coordinate, 1));
        }
        catch (Exception e) {

        }
        try{
            if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, -1))).getColour() != piece.getColour() || forKing)
                map.addMoveableCoordinates(coordinateAddition(coordinate, -1));
        }
        catch (Exception e) {

        }
        try{
            if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinate, -1))).getColour() != piece.getColour() || forKing)
                map.addMoveableCoordinates(labelAddition(coordinate, -1));
        }
        catch (Exception e) {

        }
        try{
            if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinate, 1))).getColour() != piece.getColour() || forKing)
                map.addMoveableCoordinates(labelAddition(coordinate, 1));
        }
        catch (Exception e) {

        }
        try{
            if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, 1), 1))).getColour() != piece.getColour()  || forKing)
                map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, 1), 1));
        }
        catch (Exception e) {

        }
        try{
            if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, 1), -1))).getColour() != piece.getColour() || forKing)
                map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, 1), -1));
        }
        catch (Exception e) {

        }
        try{
            if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, -1), 1))).getColour() != piece.getColour() || forKing)
                map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, -1), 1));
        }
        catch (Exception e) {

        }
        try{
            if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, -1), -1))).getColour() != piece.getColour() || forKing)
                map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, -1), -1));
        }
        catch (Exception e) {

        }
        if (!forKing) {
            System.out.println("King cant move coordinates");
            System.out.println(map.getKingCantMoveCoordinates());
            System.out.println("King default move coordinates");
            System.out.println(map.getMoveableCoordinates());
            kingCantMoveCoordinates.retainAll(map.getMoveableCoordinates());
            System.out.println("King removed coordinates");
            System.out.println(kingCantMoveCoordinates);
            map.getMoveableCoordinates().removeAll(kingCantMoveCoordinates);
            System.out.println("King can move coordinates");
            System.out.println(map.getMoveableCoordinates());
        }
    }

    public void checkKingRiskPoints(Piece piece) {
        for (Coordinates i : Coordinates.values()) {
            Piece tempPiece = map.getPieceByCoordinate(i);
            if (tempPiece.getColour() != piece.getColour()) {
                if (tempPiece.getPiece() == PieceEnum.Bishop) {
                    checkBishopMoveableCoordinate(tempPiece, String.valueOf(i), true);
                }
                else if (tempPiece.getPiece() == PieceEnum.Pawn) {
                    checkPawnCornerMoveableCoordinate(tempPiece, String.valueOf(i), true);
                }
                else if (tempPiece.getPiece() == PieceEnum.Knight) {
                    checkKnightMoveableCoordinate(tempPiece, String.valueOf(i), true);
                }
                else if (tempPiece.getPiece() == PieceEnum.Rook) {
                    checkRookMoveableCoordinate(tempPiece, String.valueOf(i), true);
                }
                else if (tempPiece.getPiece() == PieceEnum.Queen) {
                    checkQueenMoveableCoordinate(tempPiece, String.valueOf(i), true);
                }
                else if (tempPiece.getPiece() == PieceEnum.King) {
                    checkKingMoveableCoordinate(tempPiece, String.valueOf(i), true);
                }
            }
        }
        map.getKingCantMoveCoordinates().clear();
        map.getKingCantMoveCoordinates().addAll(map.getMoveableCoordinates());
        map.clearMoveableCoordinates();
    }

    private char getCoordinateLabel(String coordinate) {
        return coordinate.charAt(0);
    }

    private char getCoordinateNumber(String coordinate) {
        return coordinate.charAt(1);
    }

    private String labelAddition(String coordinate, int number) {
        try {
            return String.valueOf(coordinateLabels.get(coordinateLabels.indexOf(getCoordinateLabel(coordinate)) + number)) + getCoordinateNumber(coordinate);
        }
        catch (Exception e) {
            return "";
        }
    }
    private String coordinateAddition(String coordinate, int number) {
        try {
            if (Character.getNumericValue(getCoordinateNumber(coordinate)) + number >= 1 && Character.getNumericValue(getCoordinateNumber(coordinate)) + number < 9) {
                return getCoordinateLabel(coordinate) + String.valueOf(Character.getNumericValue(getCoordinateNumber(coordinate)) + number);
            }
            else {
                return "";
            }
        }
        catch (Exception e) {
            return "";
        }
    }

    public MapService getMap() {
        return map;
    }
}
