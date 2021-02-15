package datastructureproject;

import datastructureproject.board.Board;
import datastructureproject.board.Square;
import datastructureproject.exceptions.TooManyPiecesOnBoard;
import datastructureproject.pieces.*;
import datastructureproject.pieces.Piece;
import datastructureproject.pieces.PieceType;
import datastructureproject.pieces.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoveUtils {

    private Board board = new Board();
    private Side turn = Side.WHITE;

    public MoveUtils() {

    }

    public List<Square> getPossibleMoves(Board board, int srcRow, int srcColumn) {
        Piece piece = board.getPieceAt(srcRow, srcColumn);
        PieceType type = piece.getPieceType();
        Side side = piece.getSide();
        List<Square> possibleSquares = PieceUtils.getPossibleMovesWithPieceType(srcRow, srcColumn, type, side);
        // TODO: Finish function
        return null;
    }

    public Boolean isBlockingCheck(int srcRow, int srcColumn, Side side) throws TooManyPiecesOnBoard {
        Map<Square, Piece> kingSquareMap = board.filterPiecesBySideAndType(side, PieceType.KING);
        if (kingSquareMap.keySet().size() != 1) {
            throw new TooManyPiecesOnBoard("Couldn't find a king on the board!");
        }

        Square kingSquare = kingSquareMap.keySet().stream().iterator().next();

        List<Square> possibleSquares = getSquaresBetween(srcRow, srcColumn, kingSquare.getRow(), kingSquare.getColumn());

        // King is not "on the line"
        if (possibleSquares.size() == 0) {
            return false;
        }

        // Check if any other pieces between the line
        for (Square square : possibleSquares) {
            Boolean hasPiece = board.hasPiece(square.getRow(), square.getColumn());
            if (hasPiece) {
                return false;
            }
        }

        if (srcRow == kingSquare.getRow()) {

        } else if (srcColumn == kingSquare.getColumn()) {
            // TODO: Finish function

        } else if (srcColumn+srcRow == kingSquare.getRow()+ kingSquare.getColumn()) {
            // TODO: Finish function

        } else if (Math.abs(srcRow-kingSquare.getRow()) == Math.abs(srcColumn-kingSquare.getColumn())) {
            // TODO: Finish function

        }

        // TODO: Finish function
        return null;
    }

    public List<Square> getSquaresBetween(int srcRow, int srcColumn, int destRow, int destColumn) {

        List<Square> squaresBetween = new ArrayList<>();

        // Pieces on same row
        if (srcRow == destRow) {
            for (int i = Math.min(srcColumn, destColumn)+1; i < Math.max(srcColumn, destColumn); i++ ) {
                squaresBetween.add(new Square(srcRow, i));
            }
            return squaresBetween;
        }

        // Pieces on same column
        if (srcColumn == destColumn) {
            for (int i = Math.min(srcRow, destRow)+1; i < Math.max(srcRow, destRow); i++ ) {
                squaresBetween.add(new Square(i, srcColumn));
            }
            return squaresBetween;
        }

        // Pieces on downward slope
        if (srcColumn+srcRow == destColumn+destRow) {
            int newColumn = Math.max(srcColumn, destColumn)-1;
            for (int i = Math.min(srcRow, destRow)+1; i < Math.max(srcRow, destRow); i++ ) {
                squaresBetween.add(new Square(i, newColumn));
                newColumn--;
            }
            return squaresBetween;
        }

        // Pieces on upward slope
        if (Math.abs(srcRow-destRow) == Math.abs(srcColumn-destColumn)) {
            int newColumn = Math.min(srcColumn, destColumn)+1;
            for (int i = Math.min(srcRow, destRow); i < Math.max(srcRow, destRow); i++) {
                squaresBetween.add(new Square(i, newColumn));
                newColumn++;
            }
            return squaresBetween;
        }

        return squaresBetween;
    }

    public Boolean checkMove(int srcRow, int srcColumn, int destRow, int destColumn) {
        // TODO: finish
        return Boolean.TRUE;
    }

    public Boolean checkIfOwnPiece(int srcRow, int srcColumn) {
        Piece piece = board.getPieceAt(srcRow, srcColumn);
        if (piece != null) {
            if (piece.getSide() == turn) {
                return true;
            }
        }
        return Boolean.FALSE;
    }

    public Boolean checkIfKingIsPinned(int srcRow, int srcColumn, int destRow, int destColumn) {
        Map<Square, Piece> moves = board.filterPiecesBySideAndType(turn, PieceType.KING);
        Square kingSquare = moves.keySet().stream().iterator().next();
        // TODO: finish
        return true;
    }

    public void endTurn() {

    }
}
