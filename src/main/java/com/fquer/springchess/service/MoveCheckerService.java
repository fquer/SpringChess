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
            checkPawnMoveableCoordinates(piece, coordinate);
        }
        else if (piece.getPiece() == PieceEnum.Knight) {
            checkKnightMoveableCoordinate(piece, coordinate);
        }
    }
    private void checkPawnMoveableCoordinates(Piece piece, String coordinate) {
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
                    map.addMoveableCoordinates(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, -1), -1)));
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }

            try {
                if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, -1), 1))).getPiece() != PieceEnum.Empty &&
                        map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, -1), 1))).getColour() != piece.getColour()) {
                    map.addMoveableCoordinates(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, -1), 1)));
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
                    map.addMoveableCoordinates(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, 1), -1)));
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }

            try {
                if (map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, 1), 1))).getPiece() != PieceEnum.Empty &&
                        map.getPieceByCoordinate(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, 1), 1))).getColour() != piece.getColour()) {
                    map.addMoveableCoordinates(Coordinates.valueOf(labelAddition(coordinateAddition(coordinate, 1), 1)));
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
                        map.addMoveableCoordinates(Coordinates.valueOf(coordinateAddition(coordinate, i * -1)));
                    }
                    else {
                        break;
                    }
                }
                else {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, i))).getPiece() == PieceEnum.Empty) {
                        map.addMoveableCoordinates(Coordinates.valueOf(coordinateAddition(coordinate, i)));
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
                    map.addMoveableCoordinates(Coordinates.valueOf(coordinateAddition(coordinate, -1)));
                }
            }
            else {
                if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(coordinate, 1))).getPiece() == PieceEnum.Empty) {
                    map.addMoveableCoordinates(Coordinates.valueOf(coordinateAddition(coordinate, 1)));
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
                        map.addMoveableCoordinates(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), 2)));
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                try {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), -2))).getColour() != piece.getColour()) {
                        map.addMoveableCoordinates(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), -2)));
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
            else if (i == 2 || i == -2) {
                try {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), 1))).getColour() != piece.getColour()) {
                        map.addMoveableCoordinates(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), 1)));
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
                try {
                    if (map.getPieceByCoordinate(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), -1))).getColour() != piece.getColour()) {
                        map.addMoveableCoordinates(Coordinates.valueOf(coordinateAddition(labelAddition(coordinate, i), -1)));
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
    private char getCoordinateLabel(String coordinate) {
        return coordinate.charAt(0);
    }

    private char getCoordinateNumber(String coordinate) {
        return coordinate.charAt(1);
    }

    private String labelAddition(String coordinate, int number) {
        return String.valueOf(coordinateLabels.get(coordinateLabels.indexOf(getCoordinateLabel(coordinate)) + number)) + getCoordinateNumber(coordinate);
    }
    private String coordinateAddition(String coordinate, int number) {
        if (Character.getNumericValue(getCoordinateNumber(coordinate)) + number >= 1 && Character.getNumericValue(getCoordinateNumber(coordinate)) + number < 9) {
            return getCoordinateLabel(coordinate) + String.valueOf(Character.getNumericValue(getCoordinateNumber(coordinate)) + number);
        }
        else {
            throw new InvalidParameterException("sayi az");
        }
    }

    public MapService getMap() {
        return map;
    }
}
