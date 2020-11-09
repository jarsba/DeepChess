package datastructureproject.pieces;

import datastructureproject.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Queen implements Piece {
    private final PieceType pieceType = PieceType.QUEEN;
    private Side side;

    public Queen(Side side) {
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

        possibleMoves.addAll(Rook.getPossibleMoves(row, column));
        possibleMoves.addAll(Bishop.getPossibleMoves(row, column));

        return possibleMoves;
    }

    public String getUnicodeCharacter() {
        if(this.side.equals(Side.WHITE)) {
            return "\u2655";
        } else {
            return "\u265B";
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
        Queen otherPiece = (Queen) obj;
        return this.hashCode() == otherPiece.hashCode();
    }

    @Override
    public String toString() {
        return getUnicodeCharacter();
    }
}
