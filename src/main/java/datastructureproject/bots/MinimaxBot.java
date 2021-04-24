package datastructureproject.bots;

import chess.bot.ChessBot;
import chess.engine.GameState;
import datastructureproject.MoveUtils;
import datastructureproject.board.Board;
import datastructureproject.board.Move;
import datastructureproject.pieces.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MinimaxBot implements ChessBot {

    private Board board;
    private MoveUtils moveUtils;
    private List<Move> pastMoves;
    private final int depth = 3;

    public MinimaxBot() {
        this.board = new Board();
        this.moveUtils = new MoveUtils();
        this.pastMoves = new ArrayList<>();

    }

    /**
     * @param gs Current gamestate
     * @return UCI String representation of a move
     */
    @Override
    public String nextMove(GameState gs) {
        BotUtils.parseGameState(gs, this.board, this.moveUtils, this.pastMoves);


        List<Move> moves = this.moveUtils.getAllPossibleMoves(this.board);
        if(moves.size() == 0) {
            System.out.println("Could not find any moves");
            return null;
        }

        Move selectedMove = moves.get(0);

        moveUtils.makeMove(selectedMove, board);
        pastMoves.add(selectedMove);
        return selectedMove.toUCIString();
    }


    public double minimax(Board board, int depth, Side side) {
        if (side.equals(Side.WHITE)) {
            // TODO: return min
            return 0.0;
        } else {
            // TODO: return max
            return 0.0;
        }
    }

}
