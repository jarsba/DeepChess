package datastructureproject.evaluators;

import datastructureproject.board.Board;
import datastructureproject.pieces.*;

import java.util.HashMap;
import java.util.Map;

public class Evaluator {

    Map<Piece, Double> scoreTable = new HashMap<>();

    public Evaluator() {
        this.initializeScoreTable();
    }

    public void initializeScoreTable() {
        scoreTable.put(new Pawn(Side.WHITE), 100.0);
        scoreTable.put(new Rook(Side.WHITE), 500.0);
        scoreTable.put(new Bishop(Side.WHITE), 500.0);
        scoreTable.put(new Queen(Side.WHITE), 900.0);
        scoreTable.put(new Knight(Side.WHITE), 300.0);

        scoreTable.put(new Pawn(Side.BLACK), -100.0);
        scoreTable.put(new Rook(Side.BLACK), -500.0);
        scoreTable.put(new Bishop(Side.BLACK), -500.0);
        scoreTable.put(new Queen(Side.BLACK), -900.0);
        scoreTable.put(new Knight(Side.BLACK), -300.0);
    }

    public Double evaluateBoard(Board board) {
        Double score = 0.0;

        Piece[][] positions = board.getPositions();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = positions[i][j];
                if (piece != null && scoreTable.containsKey(piece)) {
                    Double pieceScore = scoreTable.get(piece);
                    score += pieceScore;
                }
            }
        }
        return score;
    }
}
