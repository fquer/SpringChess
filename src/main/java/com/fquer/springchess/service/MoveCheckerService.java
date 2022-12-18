package com.fquer.springchess.service;

import com.fquer.springchess.model.enums.ColorEnum;
import com.fquer.springchess.model.enums.Coordinates;
import com.fquer.springchess.model.enums.PieceEnum;
import com.fquer.springchess.model.piece.Piece;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
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
        map.clearMoveableCoordinates();
        Piece piece = map.getPieceByCoordinate(Coordinates.valueOf(coordinate));
        if (piece.getPiece() == PieceEnum.Pawn) {
            checkPawnMoveableCoordinate(piece, coordinate);
        }
        else if (piece.getPiece() == PieceEnum.Knight) {
            checkKnightMoveableCoordinate(piece, coordinate);
        }
        else if (piece.getPiece() == PieceEnum.Bishop) {
            checkBishopMoveableCoordinate(piece, coordinate);
        }
        else if (piece.getPiece() == PieceEnum.Rook) {
            checkRookMoveableCoordinate(piece, coordinate);
        }
        else if (piece.getPiece() == PieceEnum.Queen) {
            checkQueenMoveableCoordinate(piece, coordinate);
        }
        else if (piece.getPiece() == PieceEnum.King) {
            checkKingMoveableCoordinate(piece, coordinate);
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

        if (piece.getColour() == ColorEnum.Black) {
            try {
                if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, -1), -1))).getPiece() != PieceEnum.Empty &&
                        map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, -1), -1))).getColour() != piece.getColour()) {
                    map.addMoveableCoordinates(labelAddition(coordinateAddition(coordinate, -1), -1));
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }

            try {
                if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, -1), 1))).getPiece() != PieceEnum.Empty &&
                        map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, -1), 1))).getColour() != piece.getColour()) {
                    map.addMoveableCoordinates(labelAddition(coordinateAddition(coordinate, -1), 1));
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            try {
                if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, 1), -1))).getPiece() != PieceEnum.Empty &&
                        map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, 1), -1))).getColour() != piece.getColour()) {
                    map.addMoveableCoordinates(labelAddition(coordinateAddition(coordinate, 1), -1));
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }

            try {
                if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, 1), 1))).getPiece() != PieceEnum.Empty &&
                        map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, 1), 1))).getColour() != piece.getColour()) {
                    map.addMoveableCoordinates(labelAddition(coordinateAddition(coordinate, 1), 1));
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }


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

    private void checkKnightMoveableCoordinate(Piece piece, String coordinate) {
        for (int i = -2; i <= 2; i++) {
            if (i == 0) {
                i++;
            }
            if (i == 1 || i == -1) {
                try {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), 2))).getColour() != piece.getColour()) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), 2));
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                try {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), -2))).getColour() != piece.getColour()) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), -2));
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
            else if (i == 2 || i == -2) {
                try {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), 1))).getColour() != piece.getColour()) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), 1));
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                try {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), -1))).getColour() != piece.getColour()) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), -1));
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public void checkBishopMoveableCoordinate(Piece piece, String coordinate) {
        boolean ff = true;
        boolean fb = true;
        boolean bf = true;
        boolean bb = true;
        for (int i = 1; i <= 7; i++) {
            System.out.println(i);
            try {
                if (bf) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i * -1), i))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i * -1), i));
                    }
                    else {
                        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i * -1), i))).getColour() != piece.getColour()) {
                            map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i * -1), i));
                        }
                        bf = false;
                    }
                }

            }
            catch (Exception e) {
                System.out.println(e);
            }


            try {
                if (bb) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i * -1), i * -1))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i * -1), i * -1));
                    }
                    else {
                        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i * -1), i * -1))).getColour() != piece.getColour()) {
                            map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i * -1), i * -1));
                        }
                        bb = false;
                    }
                }

            }
            catch (Exception e) {
                System.out.println(e);
            }

            try {
                if (ff) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), i))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), i));
                    }
                    else {
                        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), i))).getColour() != piece.getColour()) {
                            map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), i));
                        }
                        ff = false;
                    }
                }

            }
            catch (Exception e) {
                System.out.println(e);
            }

            try {
                if (fb) {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), i * -1))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), i * -1));
                    }
                    else {
                        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), i * -1))).getColour() != piece.getColour()) {
                            map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, i), i * -1));
                        }
                        fb = false;
                    }
                }

            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void checkRookMoveableCoordinate(Piece piece, String coordinate) {
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
                        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, i))).getColour() != piece.getColour()) {
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
                        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, i * -1))).getColour() != piece.getColour()) {
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
                        if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinate, i))).getColour() != piece.getColour()) {
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
                        if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinate, i * -1))).getColour() != piece.getColour()) {
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

    public void checkQueenMoveableCoordinate(Piece piece, String coordinate) {
        checkRookMoveableCoordinate(piece, coordinate);
        checkBishopMoveableCoordinate(piece, coordinate);
    }

    public void checkKingMoveableCoordinate(Piece piece, String coordinate) {
        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, 1))).getColour() != piece.getColour())
            map.addMoveableCoordinates(coordinateAddition(coordinate, 1));
        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, -1))).getColour() != piece.getColour())
            map.addMoveableCoordinates(coordinateAddition(coordinate, -1));
        if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinate, -1))).getColour() != piece.getColour())
            map.addMoveableCoordinates(labelAddition(coordinate, -1));
        if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinate, 1))).getColour() != piece.getColour())
            map.addMoveableCoordinates(labelAddition(coordinate, 1));
        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, 1), 1))).getColour() != piece.getColour())
            map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, 1), 1));
        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, 1), -1))).getColour() != piece.getColour())
            map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, 1), -1));
        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, -1), 1))).getColour() != piece.getColour())
            map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, -1), 1));
        if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, -1), -1))).getColour() != piece.getColour())
            map.addMoveableCoordinates(coordinateAddition(labelAddition(coordinate, -1), -1));
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
            System.out.println(e);
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
            System.out.println(e);
            return "";
        }
    }

    public MapService getMap() {
        return map;
    }
}
