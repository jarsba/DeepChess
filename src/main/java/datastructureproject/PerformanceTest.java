package datastructureproject;

import chess.engine.GameState;
import datastructureproject.board.Board;
import datastructureproject.bots.AlphaBetaBot;
import datastructureproject.bots.MinimaxBot;
import datastructureproject.evaluators.Evaluator;
import datastructureproject.evaluators.PieceSquareEvaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Use this class to write performance tests for your bot.
 */
public class PerformanceTest {

    private List<GameState> gsList = new ArrayList();

    public void setGsList(List<GameState> gsList) {
        this.gsList = gsList;
    }


    public static void main(String[] args) {
        compareMinimaxToAlphaBeta();
    }


    public static void compareMinimaxToAlphaBeta() {
        Evaluator evaluator = new PieceSquareEvaluator();
        String FEN = "r2q1rk1/6pp/b3p3/p1bpPp1P/1p6/4BN2/PP1Q1PP1/R3K2R b KQ - 0 1";
        Board board = new Board(FEN);
        Board boardCopy1 = board.copyBoard();
        Board boardCopy2 = board.copyBoard();
        Board boardCopy3 = board.copyBoard();

        AlphaBetaBot alphabetaBotWithoutMoveOrdering = new AlphaBetaBot(boardCopy1, new ArrayList<>(), evaluator, board.getSideToMove(), 3, false, false);
        AlphaBetaBot alphabetaBotWithMoveOrdering = new AlphaBetaBot(boardCopy2, new ArrayList<>(), evaluator, board.getSideToMove(), 3, true, false);
        MinimaxBot minimaxBot = new MinimaxBot(boardCopy3, new ArrayList<>(), evaluator, board.getSideToMove(), 3, false);

        long start1 = System.currentTimeMillis();
        alphabetaBotWithoutMoveOrdering.nextMove(null);
        long finish1 = System.currentTimeMillis();
        long timeElapsed1 = finish1 - start1;
        long alphaBetaWOMOSeconds = TimeUnit.MILLISECONDS.toSeconds(timeElapsed1);

        System.out.println(String.format("ALPHABETA: time elapsed: %s min %s sec", Math.floor((double) alphaBetaWOMOSeconds / 60), (alphaBetaWOMOSeconds % 60)));
        System.out.println("ALPHABETA without move ordering: evaluated positions: " + alphabetaBotWithoutMoveOrdering.getEvaluatedPositions());

        long start2 = System.currentTimeMillis();
        alphabetaBotWithMoveOrdering.nextMove(null);
        long finish2 = System.currentTimeMillis();
        long timeElapsed2 = finish2 - start2;
        long alphaBetaWMOSeconds = TimeUnit.MILLISECONDS.toSeconds(timeElapsed2);

        System.out.println(String.format("ALPHABETA: time elapsed: %s min %s sec", Math.floor((double) alphaBetaWMOSeconds / 60), (alphaBetaWMOSeconds % 60)));
        System.out.println("ALPHABETA with move ordering: evaluated positions: " + alphabetaBotWithMoveOrdering.getEvaluatedPositions());

        long start3 = System.currentTimeMillis();
        minimaxBot.nextMove(null);
        long finish3 = System.currentTimeMillis();
        long timeElapsed3 = finish1 - start1;
        long minimaxSeconds = TimeUnit.MILLISECONDS.toSeconds(timeElapsed3);

        System.out.println(String.format("MINIMAX time elapsed: %s min %s sec", Math.floor((double) minimaxSeconds / 60), (minimaxSeconds % 60)));
        System.out.println("MINIMAX: evaluated positions: " + minimaxBot.getEvaluatedPositions());

    }

}
