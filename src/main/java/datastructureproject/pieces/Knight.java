package datastructureproject.pieces;

import datastructureproject.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight implements Piece {
    private final PieceType pieceType = PieceType.KNIGHT;
    private Side side;

    public Knight(Side side) {
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

        Square topLeft = new Square(row + 2, column - 1);
        Square topRight = new Square(row + 2, column + 1);
        Square rightTop = new Square(row + 1, column + 2);
        Square rightBottom = new Square(row - 1, column + 2);
        Square bottomLeft = new Square(row - 2, column - 1);
        Square bottomRight = new Square(row - 2, column + 1);
        Square leftTop = new Square(row + 1, column - 2);
        Square leftBottom = new Square(row - 1, column - 2);

        if (topLeft.isValidPosition()) {
            possibleMoves.add(topLeft);
        }
        if (topRight.isValidPosition()) {
            possibleMoves.add(topRight);
        }
        if (rightTop.isValidPosition()) {
            possibleMoves.add(rightTop);
        }
        if (rightBottom.isValidPosition()) {
            possibleMoves.add(rightBottom);
        }
        if (bottomLeft.isValidPosition()) {
            possibleMoves.add(bottomLeft);
        }
        if (bottomRight.isValidPosition()) {
            possibleMoves.add(bottomRight);
        }
        if (leftTop.isValidPosition()) {
            possibleMoves.add(leftTop);
        }
        if (leftBottom.isValidPosition()) {
            possibleMoves.add(leftBottom);
        }

        return possibleMoves;

    }

    public String getUnicodeCharacter() {
        if (this.side.equals(Side.WHITE)) {
            return "\u2658";
        } else {
            return "\u265E";
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
        Knight otherPiece = (Knight) obj;
        return this.hashCode() == otherPiece.hashCode();
    }

    @Override
    public String toString() {
        return getUnicodeCharacter();
    }
}
