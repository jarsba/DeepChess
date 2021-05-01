package datastructureproject.pieces;

import datastructureproject.MoveUtils;
import datastructureproject.board.Board;
import datastructureproject.board.Move;
import datastructureproject.board.Square;
import datastructureproject.exceptions.PieceInWrongSquareException;
import datastructureproject.exceptions.PieceNotFoundOnBoardException;
import datastructureproject.exceptions.TooManyPiecesOnBoardException;

import java.util.ArrayList;
import java.util.List;

public class King implements Piece {

    private final PieceType pieceType = PieceType.KING;
    private final Side side;

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
    public static List<Move> getPossibleMoves(Square startSquare) {
        int row = startSquare.getRow();
        int column = startSquare.getColumn();
        ArrayList<Move> possibleMoves = new ArrayList<>();

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                Square possibleSquare = new Square(i, j);
                if (possibleSquare.isValidPosition() && (row != i || column != j)) {
                    possibleMoves.add(new Move(startSquare, possibleSquare));
                }
            }
        }

        return possibleMoves;

    }

    // Does consider board positions, but not situations where the piece can't move because of pinning etc.
    public static List<Move> getPossibleMoves(Square startSquare, Board board, Boolean canQueenSideCastle, Boolean canKingSideCastle) {

        int row = startSquare.getRow();
        int column = startSquare.getColumn();

        ArrayList<Move> possibleMoves = new ArrayList<>();
        Side side = board.getPieceAt(row, column).getSide();
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                Square possibleSquare = new Square(i, j);
                if (possibleSquare.isValidPosition() && (row != i || column != j)) {
                    // Check if square has piece
                    if (board.hasPiece(i, j)) {
                        if (!board.getPieceAt(i, j).getSide().equals(side)) {
                            possibleMoves.add(new Move(startSquare, possibleSquare));
                        }
                    } else {
                        possibleMoves.add(new Move(startSquare, possibleSquare));
                    }
                }
            }
        }

        Boolean isCastlingSquare;

        if (side.equals(Side.WHITE)) {
            isCastlingSquare = (row == 0 && column == 4);
        } else {
            isCastlingSquare = (row == 7 && column == 4);
        }

        if (canKingSideCastle && isCastlingSquare) {
            if (!board.hasPiece(row, column + 1) && !board.hasPiece(row, column + 2)) {
                possibleMoves.add(new Move(startSquare, new Square(row, column + 2)));
            }
        }
        if (canQueenSideCastle && isCastlingSquare) {
            if (!board.hasPiece(row, column - 1) && !board.hasPiece(row, column - 2) && !board.hasPiece(row, column - 3)) {
                possibleMoves.add(new Move(startSquare, new Square(row, column - 2)));
            }
        }

        return possibleMoves;
    }

    public static List<Move> getPossibleAttackingMoves(Square startSquare, Board board) {

        int row = startSquare.getRow();
        int column = startSquare.getColumn();

        ArrayList<Move> possibleMoves = new ArrayList<>();
        Side side = board.getPieceAt(row, column).getSide();
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                Square possibleSquare = new Square(i, j);
                if (possibleSquare.isValidPosition() && (row != i || column != j)) {
                    // Check if square has piece
                    if (board.hasPiece(i, j)) {
                        if (!board.getPieceAt(i, j).getSide().equals(side)) {
                            possibleMoves.add(new Move(startSquare, possibleSquare));
                        }
                    } else {
                        possibleMoves.add(new Move(startSquare, possibleSquare));
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

    public String getPieceNotion() {
        if (this.side.equals(Side.WHITE)) {
            return "K";
        } else {
            return "k";
        }
    }

    public static Piece fromPieceNotation(String pieceNotation) {
        if (pieceNotation.equals("K")) {
            return new King(Side.WHITE);
        } else {
            return new King(Side.BLACK);
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
