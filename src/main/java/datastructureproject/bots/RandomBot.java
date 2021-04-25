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

public class RandomBot implements ChessBot {
    private Board board;
    private MoveUtils moveUtils;
    private List<Move> pastMoves;
    private Side side;
    Random random;

    public RandomBot() {
        this.board = new Board();
        this.moveUtils = new MoveUtils();
        this.random = new Random();
        this.pastMoves = new ArrayList<>();
    }

    /**
     * @param gs Current gamestate
     * @return UCI String representation of a move
     */
    @Override
    public String nextMove(GameState gs) {
        BotUtils.parseGameState(gs, this.board, this.moveUtils, this.pastMoves, this.side);
        List<Move> moves = this.moveUtils.getAllPossibleMoves(this.board, this.side);
        if(moves.size() == 0) {
            System.out.println("Could not find any moves");
            return null;
        }
        Move selectedMove = moves.get(random.nextInt(moves.size()));
        moveUtils.makeMove(selectedMove, board);
        pastMoves.add(selectedMove);
        return selectedMove.toUCIString();
    }

}
