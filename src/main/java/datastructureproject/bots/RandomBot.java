package datastructureproject.bots;

import chess.bot.ChessBot;
import chess.engine.GameState;
import datastructureproject.MoveUtils;
import datastructureproject.board.Board;
import datastructureproject.board.Move;

import java.util.List;
import java.util.Random;

public class RandomBot implements ChessBot {
    private Board board;
    private MoveUtils moveUtils;
    Random random;

    public RandomBot() {
        this.board = new Board();
        this.moveUtils = new MoveUtils();
        this.random = new Random();
    }

    /**
     * @param gs Current gamestate
     * @return UCI String representation of a move
     */
    @Override
    public String nextMove(GameState gs) {
        BotUtils.parseGameState(gs, this.board, this.moveUtils);

        List<Move> moves = this.moveUtils.getAllPossibleMoves(board);
        Move selectedMove = moves.get(random.nextInt(moves.size()));
        moveUtils.makeMove(selectedMove, board);

        return selectedMove.toUCIString();
    }

}
