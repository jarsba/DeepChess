package datastructureproject.bots;

import chess.bot.ChessBot;
import chess.engine.GameState;
import datastructureproject.board.MoveUtils;
import datastructureproject.board.Board;
import datastructureproject.board.Move;
import datastructureproject.evaluators.DumbEvaluator;
import datastructureproject.evaluators.Evaluator;
import datastructureproject.pieces.Side;

import java.util.ArrayList;
import java.util.List;

public class MinimaxBot implements ChessBot {

    private final Board board;
    private final MoveUtils moveUtils;
    private final List<Move> pastMoves;
    // Implement interface for easy evaluator swapping
    private final Evaluator evaluator;
    private Side side;
    private int depth = 3;
    private int evaluatedPositions = 0;
    private boolean useGameState = true;

    public MinimaxBot() {
        this.board = new Board();
        this.moveUtils = new MoveUtils();
        this.pastMoves = new ArrayList<>();
        this.evaluator = new DumbEvaluator();
    }

    public MinimaxBot(Board board, List<Move> pastMoves, Evaluator evaluator, Side side, int depth, Boolean useGameState) {
        this.board = board;
        this.moveUtils = new MoveUtils();
        this.pastMoves = pastMoves;
        this.evaluator = evaluator;
        this.side = side;
        this.depth = depth;
        // For testing
        this.useGameState = useGameState;

    }

    public int getEvaluatedPositions() {
        return evaluatedPositions;
    }

    /**
     * @param gs Current gamestate
     * @return UCI String representation of a move
     */
    @Override
    public String nextMove(GameState gs) {

        if (useGameState) {
            this.side = BotUtils.getSideFromGameState(gs);
            BotUtils.parseGameState(gs, this.board, this.moveUtils, this.pastMoves, this.side);
        }

        List<Move> moves = this.moveUtils.getAllPossibleMoves(this.board, this.side);

        if (moves.size() == 0) {
            System.out.println("Could not find any moves");
            BotUtils.initializeGame(gs, this.board, this.pastMoves, this.side);
            return null;
        }

        Move bestMove = moves.get(0);
        double bestMoveScore = 0.0;

        for (Move move : moves) {
            Board boardCopy = this.board.copyBoard();
            moveUtils.makeMove(move, boardCopy);
            double evaluation = this.minimax(boardCopy, depth, side.getOppositeSide());
            if (this.side.equals(Side.WHITE) && evaluation >= bestMoveScore) {
                bestMove = move;
                bestMoveScore = evaluation;
            } else if (this.side.equals(Side.BLACK) && evaluation <= bestMoveScore) {
                bestMove = move;
                bestMoveScore = evaluation;
            }
        }

        moveUtils.makeMove(bestMove, this.board);
        pastMoves.add(bestMove);

        return bestMove.toUCIString();
    }


    // Side represents is it white or blacks move next
    public double minimax(Board board, int depth, Side side) {
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
                evaluation = Math.max(evaluation, this.minimax(newBoard, depth - 1, side.getOppositeSide()));
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
                evaluation = Math.min(evaluation, this.minimax(newBoard, depth - 1, side.getOppositeSide()));
            }

            return evaluation;
        }
    }
}
