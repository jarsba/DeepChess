package datastructureproject;

import datastructureproject.board.Square;
import datastructureproject.pieces.*;

import java.util.List;

public class PieceUtils {

    public static List<Square> getPossibleMovesWithPieceType(int srcRow, int srcCol, PieceType type, Side side) {
        switch (type) {
            case BISHOP:
                return Bishop.getPossibleMoves(srcRow, srcCol);
            case KING:
                return King.getPossibleMoves(srcRow, srcCol);
            case PAWN:
                return Pawn.getPossibleMoves(srcRow, srcCol, side);
            case KNIGHT:
                return Knight.getPossibleMoves(srcRow, srcCol);
            case QUEEN:
                return Queen.getPossibleMoves(srcRow, srcCol);
            case ROOK:
                return Rook.getPossibleMoves(srcRow, srcCol);
            default:
                return null;
        }
    }

}
