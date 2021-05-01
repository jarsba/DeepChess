package datastructureproject.bots;

import chess.bot.ChessBot;
import chess.engine.GameState;
import datastructureproject.MoveUtils;
import datastructureproject.board.Board;
import datastructureproject.board.Move;
import datastructureproject.evaluators.DumbEvaluator;
import datastructureproject.evaluators.Evaluator;
import datastructureproject.evaluators.PieceSquareEvaluator;
import datastructureproject.pieces.Side;

import java.util.ArrayList;
import java.util.List;

public class AlphaBetaBot implements ChessBot {

    private Board board;
    private MoveUtils moveUtils;
    private List<Move> pastMoves;
    private Evaluator evaluator;
    private Side side;
    private final int depth = 3;

    public AlphaBetaBot() {
        this.board = new Board();
        this.moveUtils = new MoveUtils();
        this.pastMoves = new ArrayList<>();
        this.evaluator = new PieceSquareEvaluator();
    }

    /**
     * @param gs Current gamestate
     * @return UCI String representation of a move
     */
    @Override
    public String nextMove(GameState gs) {

        this.side = BotUtils.getSideFromGameState(gs);

        BotUtils.parseGameState(gs, this.board, this.moveUtils, this.pastMoves, this.side);
        List<Move> moves = this.moveUtils.getAllPossibleMoves(this.board, this.side);

        if(moves.size() == 0) {
            System.out.println("Could not find any moves");
            return null;
        }

        Move bestMove = moves.get(0);
        double bestMoveScore = 0.0;

        for (Move move : moves) {
            Board boardCopy = this.board.copyBoard();
            moveUtils.makeMove(move, boardCopy);
            double evaluation = alphabeta(boardCopy, depth, side, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            if (this.side.equals(Side.WHITE) && evaluation > bestMoveScore) {
                bestMove = move;
                bestMoveScore = evaluation;
            } else if(this.side.equals(Side.BLACK) && evaluation < bestMoveScore) {
                bestMove = move;
                bestMoveScore = evaluation;
            }
        }

        moveUtils.makeMove(bestMove, this.board);
        pastMoves.add(bestMove);
        return bestMove.toUCIString();
    }


    public double alphabeta(Board board, int depth, Side side, double alpha, double beta) {
        if (side.equals(Side.WHITE)) {
            List<Move> moves = moveUtils.getAllPossibleMoves(board, side);
            if (moves.size() == 0) {
                if (moveUtils.checkIfPositionInvalid(board, side)) {
                    return Double.NEGATIVE_INFINITY;
                } else {
                    return 0;
                }
            }

            if (depth == 0) {
                return this.evaluator.evaluateBoard(board);
            }

            double evaluation = Double.NEGATIVE_INFINITY;

            for (Move move : moves) {
                Board newBoard = board.copyBoard();
                moveUtils.makeMove(move, newBoard);
                evaluation = Math.max(evaluation, this.alphabeta(newBoard, depth - 1, side.getOppositeSide(), alpha, beta));
                alpha = Math.max(alpha, evaluation);
                if (alpha >= beta) {
                    return evaluation;
                }
            }

            return evaluation;
        } else {
            List<Move> moves = moveUtils.getAllPossibleMoves(board, side);
            if (moves.size() == 0) {
                if (moveUtils.checkIfPositionInvalid(board, side)) {
                    return Double.POSITIVE_INFINITY;
                } else {
                    return 0;
                }
            }

            if (depth == 0) {
                return this.evaluator.evaluateBoard(board);
            }

            double evaluation = Double.POSITIVE_INFINITY;

            for (Move move : moves) {
                Board newBoard = board.copyBoard();
                moveUtils.makeMove(move, newBoard);
                evaluation = Math.min(evaluation, this.alphabeta(newBoard, depth - 1, side.getOppositeSide(), alpha, beta));
                beta = Math.min(beta, evaluation);
                if (alpha >= beta) {
                    return evaluation;
                }
            }

            return evaluation;
        }
    }
}
