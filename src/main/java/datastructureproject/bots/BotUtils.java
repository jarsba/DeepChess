package datastructureproject.bots;

import chess.engine.GameState;
import datastructureproject.board.MoveUtils;
import datastructureproject.board.Board;
import datastructureproject.board.Move;
import datastructureproject.pieces.Side;

import java.util.List;

// All bots need to parse moves from GameState to Board, use MoveUtils to create legal moves and return best move and pass the new move as UCI-string.
// This class is to help bots to parse GameState-moves to Board.

public class BotUtils {

    public static datastructureproject.pieces.Side getSideFromGameState(GameState gs) {
        if (gs.playing.equals(chess.model.Side.WHITE)) {
            return datastructureproject.pieces.Side.WHITE;
        } else {
            return datastructureproject.pieces.Side.BLACK;
        }
    }

    public static void initializeGame(GameState gs, Board board, List<Move> pastMoves, Side side) {
        side = BotUtils.getSideFromGameState(gs);
        board.initializePositions();
        pastMoves.clear();
    }

    public static void parseGameState(GameState gs, Board board, MoveUtils moveUtils, List<Move> pastMoves, Side side) {
        List<String> gsMoves = gs.moves;

        // If new game, make sure we have clear board and set correct side for moveUtils
        if (gsMoves.size() == 0 || (gsMoves.size() == 1 && side.equals(Side.BLACK))) {
            BotUtils.initializeGame(gs, board, pastMoves, side);
        }

        if (gs.moves.size() != pastMoves.size()) {
            for (int i = 0; i < gs.moves.size(); i++) {

                String moveString = gs.moves.get(i);
                Move gsMove = Move.fromUCIString(moveString);

                if (pastMoves.size() > i) {
                    Move pastMove = pastMoves.get(i);
                    if (gsMove.equals(pastMove)) {
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
