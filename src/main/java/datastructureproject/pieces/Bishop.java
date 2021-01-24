package datastructureproject.pieces;

import datastructureproject.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements Piece {

    private final PieceType pieceType = PieceType.BISHOP;
    private Side side;

    public Bishop(Side side) {
        this.side = side;
    }

    @Override
    public PieceType getPieceType() {
        return this.pieceType;
    }

    @Override
    public Side getSide() {
        return this.side;
    }

    public static List<Square> getPossibleMoves(int row, int column) {

        ArrayList<Square> possibleMoves = new ArrayList<>();

        // Row decrease, column decrease

        int minDistanceFromZero = Math.min(row, column);

        for (int i = 1; i <= minDistanceFromZero; i++) {
                possibleMoves.add(new Square(row-i, column-i));
        }

        // Row decrease, column increase

        int minDistanceFromZeroOrEight = Math.min(row, 7 - column);

        for (int i = 1; i <= minDistanceFromZeroOrEight; i++) {
            possibleMoves.add(new Square(row-i, column+i));
        }

        // Row increase, column decrease

        int minDistanceFromEightOrZero = Math.min(7 - row, column);

        for (int i = 1; i <= minDistanceFromEightOrZero; i++) {
            possibleMoves.add(new Square(row+i, column-i));
        }

        // Row increase, column increase

        int minDistanceFromEight = Math.min(7 - row, 7 - column);

        for (int i = 1; i <= minDistanceFromEight; i++) {
            possibleMoves.add(new Square(row+i, column+i));
        }


        return possibleMoves;
    }

    public String getUnicodeCharacter() {
        if(this.side.equals(Side.WHITE)) {
            return "\u2657";
        } else {
            return "\u265D";
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + side.hashCode();
        hash = 31 * hash + pieceType.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bishop otherPiece = (Bishop) obj;
        return this.hashCode() == otherPiece.hashCode();
    }

    @Override
    public String toString() {
        return getUnicodeCharacter();
    }
}