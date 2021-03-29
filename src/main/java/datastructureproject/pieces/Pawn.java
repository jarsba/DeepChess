package datastructureproject.pieces;

import datastructureproject.board.Board;
import datastructureproject.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {
    private final PieceType pieceType = PieceType.PAWN;
    private Side side;

    public Pawn(Side side) {
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
    public static List<Square> getPossibleMoves(int row, int column, Side side) {
        ArrayList<Square> possibleMoves = new ArrayList<>();

        if (side == Side.WHITE) {
            if (7 - row >= 1) {

                possibleMoves.add(new Square(row + 1, column));

                if (column > 0) {
                    possibleMoves.add(new Square(row + 1, column - 1));
                }

                if (column < 7) {
                    possibleMoves.add(new Square(row + 1, column + 1));
                }
            }

            if (row == 1) {
                possibleMoves.add(new Square(row + 2, column));
            }
        } else {
            if (row >= 1) {

                possibleMoves.add(new Square(row - 1, column));

                if (column > 0) {
                    possibleMoves.add(new Square(row - 1, column - 1));
                }

                if (column < 7) {
                    possibleMoves.add(new Square(row - 1, column + 1));
                }
            }

            if (row == 6) {
                possibleMoves.add(new Square(row - 2, column));
            }
        }

        return possibleMoves;

    }

    // Does consider board positions, but not situations where the piece can't move because of pinning etc.
    public static List<Square> getPossibleMoves(int row, int column, Side side, Board board) {
        ArrayList<Square> possibleMoves = new ArrayList<>();

        if (side == Side.WHITE) {
            if (7 - row >= 1) {

                possibleMoves.add(new Square(row + 1, column));

                if (column > 0) {
                    possibleMoves.add(new Square(row + 1, column - 1));
                }

                if (column < 7) {
                    possibleMoves.add(new Square(row + 1, column + 1));
                }
            }

            if (row == 1) {
                possibleMoves.add(new Square(row + 2, column));
            }
        } else {
            if (row >= 1) {

                possibleMoves.add(new Square(row - 1, column));

                if (column > 0) {
                    possibleMoves.add(new Square(row - 1, column - 1));
                }

                if (column < 7) {
                    possibleMoves.add(new Square(row - 1, column + 1));
                }
            }

            if (row == 6) {
                possibleMoves.add(new Square(row - 2, column));
            }
        }

        return possibleMoves;

    }

    public String getUnicodeCharacter() {
        if (this.side.equals(Side.WHITE)) {
            return "\u2659";
        } else {
            return "\u265F";
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
        Pawn otherPiece = (Pawn) obj;
        return this.hashCode() == otherPiece.hashCode();
    }

    @Override
    public String toString() {
        return getUnicodeCharacter();
    }
}
