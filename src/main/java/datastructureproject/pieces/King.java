package datastructureproject.pieces;

import datastructureproject.board.Board;
import datastructureproject.board.Square;

import java.util.ArrayList;
import java.util.List;

public class King implements Piece {

    private final PieceType pieceType = PieceType.KING;
    private Side side;

    public King(Side side) {
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


    // Doesn't consider board position
    public static List<Square> getPossibleMoves(int row, int column) {
        ArrayList<Square> possibleMoves = new ArrayList<>();

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                Square possibleSquare = new Square(i, j);
                if (possibleSquare.isValidPosition() && (row != i | column != j)) {
                    possibleMoves.add(possibleSquare);
                }
            }
        }

        return possibleMoves;

    }

    // Does consider board positions, but not situations where the piece can't move because of pinning etc.
    public static List<Square> getPossibleMoves(int row, int column, Board board) {
        ArrayList<Square> possibleMoves = new ArrayList<>();
        Side side = board.getPieceAt(row, column).getSide();
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                Square possibleSquare = new Square(i, j);
                if (possibleSquare.isValidPosition() && (row != i || column != j)) {
                    // Check if square has piece
                    if(board.hasPiece(i, j)) {
                        if(!board.getPieceAt(i, j).getSide().equals(side)) {

                        }
                    } else {
                        possibleMoves.add(possibleSquare);
                    }
                }
            }
        }
        return possibleMoves;
    }

    public String getUnicodeCharacter() {
        if (this.side.equals(Side.WHITE)) {
            return "\u2654";
        } else {
            return "\u265A";
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
        King otherPiece = (King) obj;
        return this.hashCode() == otherPiece.hashCode();
    }

    @Override
    public String toString() {
        return getUnicodeCharacter();
    }
}
