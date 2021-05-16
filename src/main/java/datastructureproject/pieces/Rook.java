package datastructureproject.pieces;

import datastructureproject.board.Board;
import datastructureproject.board.Move;
import datastructureproject.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Rook implements Piece {
    private final PieceType pieceType = PieceType.ROOK;
    private final Side side;

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

    // Doesn't consider board position
    public static List<Move> getPossibleMoves(Square startSquare) {
        int row = startSquare.getRow();
        int column = startSquare.getColumn();

        ArrayList<Move> possibleMoves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            if (i != column) {
                possibleMoves.add(new Move(startSquare, new Square(row, i)));
            }
        }

        for (int i = 0; i < 8; i++) {
            if (i != row) {
                possibleMoves.add(new Move(startSquare, new Square(i, column)));
            }
        }

        return possibleMoves;

    }

    // Does consider board positions, but not situations where the piece can't move because of pinning etc.
    public static List<Move> getPossibleMoves(Square startSquare, Board board) {
        int row = startSquare.getRow();
        int column = startSquare.getColumn();

        ArrayList<Move> possibleMoves = new ArrayList<>();
        Side side = board.getPieceAt(row, column).getSide();

        for (int i = row + 1; i <= 7; i++) {
            if (board.hasPiece(i, column)) {
                if (board.getPieceAt(i, column).getSide().equals(side)) {
                    break;
                } else {
                    possibleMoves.add(new Move(startSquare, new Square(i, column)));
                    break;
                }
            }
            possibleMoves.add(new Move(startSquare, new Square(i, column)));
        }

        for (int i = row - 1; i >= 0; i--) {
            if (board.hasPiece(i, column)) {
                if (board.getPieceAt(i, column).getSide().equals(side)) {
                    break;
                } else {
                    possibleMoves.add(new Move(startSquare, new Square(i, column)));
                    break;
                }
            }
            possibleMoves.add(new Move(startSquare, new Square(i, column)));
        }

        for (int i = column + 1; i <= 7; i++) {
            if (board.hasPiece(row, i)) {
                if (board.getPieceAt(row, i).getSide().equals(side)) {
                    break;
                } else {
                    possibleMoves.add(new Move(startSquare, new Square(row, i)));
                    break;
                }
            }
            possibleMoves.add(new Move(startSquare, new Square(row, i)));
        }

        for (int i = column - 1; i >= 0; i--) {
            if (board.hasPiece(row, i)) {
                if (board.getPieceAt(row, i).getSide().equals(side)) {
                    break;
                } else {
                    possibleMoves.add(new Move(startSquare, new Square(row, i)));
                    break;
                }
            }
            possibleMoves.add(new Move(startSquare, new Square(row, i)));
        }
        return possibleMoves;
    }

    public String getUnicodeCharacter() {
        if (this.side.equals(Side.WHITE)) {
            return "\u2656";
        } else {
            return "\u265C";
        }
    }

    public String getPieceNotion() {
        if (this.side.equals(Side.WHITE)) {
            return "R";
        } else {
            return "r";
        }
    }

    public static Piece fromPieceNotation(String pieceNotation) {
        if (pieceNotation.equals("R")) {
            return new Rook(Side.WHITE);
        } else {
            return new Rook(Side.BLACK);
        }
    }

    public int zobristIndex() {
        if (this.side.equals(Side.WHITE)) {
            return 4;
        } else {
            return 10;
        }
    }

    public double pieceValue() {
        if (this.side.equals(Side.WHITE)) {
            return 500;
        } else {
            return -500;
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
