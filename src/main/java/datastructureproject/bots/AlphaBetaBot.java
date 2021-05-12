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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AlphaBetaBot implements ChessBot {

    private Board board;
    private MoveUtils moveUtils;
    private List<Move> pastMoves;
    private Evaluator evaluator;
    private Side side;
    private int depth = 3;
    private int evaluatedPositions = 0;
    private boolean useGameState = true;
    private boolean useMoveOrdering = true;

    public AlphaBetaBot() {
        this.board = new Board();
        this.moveUtils = new MoveUtils();
        this.pastMoves = new ArrayList<>();
        this.evaluator = new PieceSquareEvaluator();
    }

    public AlphaBetaBot(Board board, List<Move> pastMoves, Evaluator evaluator, Side side, int depth, Boolean useMoveOrdering, Boolean useGameState) {
        this.board = board;
        this.moveUtils = new MoveUtils();
        this.pastMoves = pastMoves;
        this.evaluator = evaluator;
        this.side = side;
        this.depth = depth;
        this.useMoveOrdering = useMoveOrdering;
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

        System.out.println(this.useMoveOrdering);

        long start = System.currentTimeMillis();

        this.evaluatedPositions = 0;

        if(useGameState) {
            this.side = BotUtils.getSideFromGameState(gs);
            BotUtils.parseGameState(gs, this.board, this.moveUtils, this.pastMoves, this.side);
        }

        List<Move> moves = this.moveUtils.getAllPossibleMoves(this.board, this.side);

        if(moves.size() == 0) {
            System.out.println("Could not find any moves");
            BotUtils.initializeGame(gs, this.board, this.pastMoves, this.side);
            return null;
        }

        if(this.useMoveOrdering) {
            System.out.println("USE MOVE ORDERING");
            moves = moveUtils.orderMoves(moves, this.board, this.side);
        }

        Move bestMove = null;
        double bestMoveScore;
        
        if (this.side.equals(Side.WHITE)) {
            bestMoveScore = Double.NEGATIVE_INFINITY;
        } else {
            bestMoveScore = Double.POSITIVE_INFINITY;
        }

        Map<Move, Double> moveMap = new HashMap<>();

        for (Move move : moves) {
            Board boardCopy = this.board.copyBoard();
            moveUtils.makeMove(move, boardCopy);
            double evaluation = this.alphabeta(boardCopy, depth, side.getOppositeSide(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            moveMap.put(move, evaluation);
            if (this.side.equals(Side.WHITE) && evaluation >= bestMoveScore) {
                bestMove = move;
                bestMoveScore = evaluation;
            } else if(this.side.equals(Side.BLACK) && evaluation <= bestMoveScore) {
                bestMove = move;
                bestMoveScore = evaluation;
            }
        }

        moveUtils.makeMove(bestMove, this.board);
        pastMoves.add(bestMove);

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeElapsed);

        System.out.println(String.format("TIME ELAPSED: %s min %s sec", Math.floor((double) seconds / 60), (seconds % 60)));
        System.out.println("EVALUATED POSITIONS: " + this.evaluatedPositions);
        System.out.println(moveMap);


        return bestMove.toUCIString();
    }


    public double alphabeta(Board board, int depth, Side side, double alpha, double beta) {

        List<Move> moves = moveUtils.getAllPossibleMoves(board, side);

        if(this.useMoveOrdering && depth > 0) {
            moves = moveUtils.orderMoves(moves, board, side);
        }

        if (side.equals(Side.WHITE)) {

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
                this.evaluatedPositions++;
                alpha = Math.max(alpha, evaluation);
                if (alpha >= beta) {
                    return evaluation;
                }
            }

            return evaluation;
        } else {

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
                this.evaluatedPositions++;
                beta = Math.min(beta, evaluation);
                if (alpha >= beta) {
                    return evaluation;
                }
            }

            return evaluation;
        }
    }
}
