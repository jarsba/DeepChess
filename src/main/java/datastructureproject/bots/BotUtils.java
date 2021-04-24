package datastructureproject.bots;

import chess.engine.GameState;
import chess.model.Side;
import datastructureproject.MoveUtils;
import datastructureproject.board.Board;
import datastructureproject.board.Move;

import java.util.List;

// All bots need to parse moves from GameState to Board, use MoveUtils to create legal moves and return best move and pass the new move as UCI-string.
// This class is to help bots to parse GameState-moves to Board.

public class BotUtils {

    public static void parseGameState(GameState gs, Board board, MoveUtils moveUtils, List<Move> pastMoves) {
        List<String> gsMoves = gs.moves;

        if (moveUtils.getSide() == null) {
            moveUtils.setSide(gs.playing.equals(Side.WHITE) ? datastructureproject.pieces.Side.WHITE : datastructureproject.pieces.Side.BLACK);
        }

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
                if (moveUtils.getSide().equals(datastructureproject.pieces.Side.WHITE)) {
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
                    if(ownMove) {
                        moveUtils.makeMove(gsMove, board);
                    } else {
                        board.makeMove(gsMove);
                    }
                    pastMoves.add(gsMove);
                }
            }
        }
    }
}
