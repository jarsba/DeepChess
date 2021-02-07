package datastructureproject;

import datastructureproject.board.Board;
import datastructureproject.board.Square;
import datastructureproject.pieces.*;
import datastructureproject.pieces.Piece;
import datastructureproject.pieces.PieceType;
import datastructureproject.pieces.Side;

import java.util.Map;

public class MoveUtils {

    private Board board = new Board();
    private Side turn = Side.WHITE;

    public MoveUtils() {

    }


    public Boolean checkMove(int srcRow, int srcColumn, int destRow, int destColumn) {
        // TODO: finish
        return Boolean.TRUE;
    }

    public Boolean checkIfOwnPiece(int srcRow, int srcColumn) {
        Piece piece = board.getPieceAt(srcRow, srcColumn);
        if (piece != null) {
            if (piece.getSide() == turn) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Boolean checkIfKingIsPinned(int srcRow, int srcColumn, int destRow, int destColumn) {
        Map<Square, Piece> moves = board.filterPiecesBySideAndType(turn, PieceType.KING);
        Square kingSquare = moves.keySet().stream().iterator().next();
        // TODO: finish
        return Boolean.TRUE;
    }

    public void endTurn() {

    }
}
