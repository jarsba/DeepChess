package datastructureproject.bots;

import chess.engine.GameState;
import datastructureproject.MoveUtils;
import datastructureproject.board.Board;
import datastructureproject.board.Move;
import datastructureproject.pieces.Side;

import java.util.List;

// All bots need to parse moves from GameState to Board, use MoveUtils to create legal moves and return best move and pass the new move as UCI-string.
// This class is to help bots to parse GameState-moves to Board.

public class BotUtils {

    public static datastructureproject.pieces.Side getSideFromGameState(GameState gs) {
        if(gs.playing.equals(chess.model.Side.WHITE)) {
            return datastructureproject.pieces.Side.WHITE;
        } else {
            return datastructureproject.pieces.Side.BLACK;
        }
    }

    public static void parseGameState(GameState gs, Board board, MoveUtils moveUtils, List<Move> pastMoves, Side side) {
        List<String> gsMoves = gs.moves;

        // If new game, make sure we have clear board and set correct side for moveUtils
        if(gs.moves.size() == 0) {
            board.initializePositions();
            pastMoves.clear();
        }

        // Check if past moves are stored from previous game and empty past moves if that is the case
        if(pastMoves.size() > 1 && gsMoves.size() <= 1) {
            pastMoves.clear();
        }

        if(gs.moves.size() != pastMoves.size()) {
            for (int i = 0; i < gs.moves.size(); i++) {
                boolean ownMove;
                if (side.equals(datastructureproject.pieces.Side.WHITE)) {
                    ownMove = i % 2 == 0;
                } else {
                    ownMove = i % 2 != 0;
                }

                String moveString = gs.moves.get(i);
                Move gsMove = Move.fromUCIString(moveString);

                if(pastMoves.size() > i) {
                    Move pastMove = pastMoves.get(i);
                    if(gsMove.equals(pastMove)) {
                        continue;
                    } else {
                        throw new Error("gsMoves and pastMoves histories differ!");
                    }
                } else {
                    moveUtils.makeMove(gsMove, board);
                    pastMoves.add(gsMove);
                }
            }
        }
    }
}
