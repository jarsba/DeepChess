package datastructureproject.pieces;

import datastructureproject.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Rook implements Piece {
    private final PieceType pieceType = PieceType.ROOK;
    private Side side;

    public Rook(Side side) {
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

        for (int i = 0; i < 8; i++) {
            if (i != column) {
                possibleMoves.add(new Square(row, i));
            }
        }

        for (int i = 0; i < 8; i++) {
            if (i != row) {
                possibleMoves.add(new Square(i, column));
            }
        }

        return possibleMoves;

    }

    public String getUnicodeCharacter() {
        if(this.side.equals(Side.WHITE)) {
            return "\u2656";
        } else {
            return "\u265C";
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
        Rook otherPiece = (Rook) obj;
        return this.hashCode() == otherPiece.hashCode();
    }

    @Override
    public String toString() {
        return getUnicodeCharacter();
    }
}
