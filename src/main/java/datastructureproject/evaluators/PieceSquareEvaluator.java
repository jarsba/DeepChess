package datastructureproject.evaluators;

// Used Sebastian Lagues code as base https://github.com/SebLague/Chess-AI/blob/main/Assets/Scripts/Core/PieceSquareTable.cs

import datastructureproject.board.Board;
import datastructureproject.pieces.*;

import java.util.HashMap;
import java.util.Map;

public class PieceSquareEvaluator {

    Map<Piece, Double> pieceScoreTable = new HashMap<>();

    // All scores for white, for black table is flipped
    public static final int[][] pawnLocationScores = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {5, 10, 10, -20, -20, 10, 10, 5},
            {5, -5, -10, 0, 0, -10, -5, 5},
            {0, 0, 0, 20, 20, 0, 0, 0},
            {5, 5, 10, 25, 25, 10, 5, 5},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static final int[][] knightLocationScores = {
            {-50, -40, -30, -30, -30, -30, -40, -50},
            {-40, -20, 0, 5, 5, 0, -20, -40},
            {-30, -5, 10, 15, 15, 10, 5, -30},
            {-30, 0, 15, 20, 20, 15, 0, -30},
            {-30, 5, 15, 20, 20, 15, 5, -30},
            {-30, 0, 10, 15, 15, 10, 0, -30},
            {-40, -20, 0, 0, 0, 0, -20, -40},
            {-50, -40, -30, -30, -30, -30, -40, -50}
    };

    public static final int[][] bishopLocationScores = {
            {-20, -10, -10, -10, -10, -10, -10, -20},
            {-10, 5, 0, 0, 0, 0, 5, -10},
            {-10, 10, 10, 10, 10, 10, 10, -10},
            {-10, 0, 10, 10, 10, 10, 0, -10},
            {-10, 5, 5, 10, 10, 5, 5, -10},
            {-10, 0, 5, 10, 10, 5, 0, -10},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-20, -10, -10, -10, -10, -10, -10, -20}};

    public static final int[][] rookLocationScores = {
            {0, 0, 0, 5, 5, 0, 0, 0},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 0, 0, 0, 0, 0, 0, -5},
            {-5, 10, 10, 10, 10, 10, 10, -5},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static final int[][] queenLocationScores = {
            {-20, -10, -10, -5, -5, -10, -10, -20},
            {-10, 0, 5, 0, 0, 0, 0, -10},
            {-10, 5, 5, 5, 5, 5, 0, -10},
            {0, 0, 5, 5, 5, 5, 0, -5},
            {-5, 0, 5, 5, 5, 5, 0, -5},
            {-10, 0, 5, 5, 5, 5, 0, -10},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-20, -10, -10, -5, -5, -10, -10, -20}
    };

    public static final int[][] kingLocationScores = {
            {20, 30, 10, 0, 0, 10, 30, 20},
            {20, 20, 0, 0, 0, 0, 20, 20},
            {-10, -20, -20, -20, -20, -20, -20, -10},
            {-20, -30, -30, -40, -40, -30, -30, -20},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30}
    };

    public PieceSquareEvaluator() {
        this.initializeScoreTable();
    }

    public int[][] flipTable(int[][] table) {
        int[][] flippedTable = new int[8][8];
        // Iterate table in reverse row order, same column order and flip all signs
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < table[0].length; j++) {
                flippedTable[7 - i][j] = table[i][j] * -1;
            }
        }

        return flippedTable;
    }

    public int[][] getLocationScoreTable(Piece piece) {
        switch (piece.getPieceType()) {
            case BISHOP:
                return bishopLocationScores;
            case ROOK:
                return rookLocationScores;
            case PAWN:
                return pawnLocationScores;
            case KNIGHT:
                return knightLocationScores;
            case QUEEN:
                return queenLocationScores;
            case KING:
                return kingLocationScores;
        }
        return null;
    }

    public void initializeScoreTable() {
        pieceScoreTable.put(new Pawn(Side.WHITE), 100.0);
        pieceScoreTable.put(new Rook(Side.WHITE), 500.0);
        pieceScoreTable.put(new Bishop(Side.WHITE), 500.0);
        pieceScoreTable.put(new Queen(Side.WHITE), 900.0);
        pieceScoreTable.put(new Knight(Side.WHITE), 300.0);

        pieceScoreTable.put(new Pawn(Side.BLACK), -100.0);
        pieceScoreTable.put(new Rook(Side.BLACK), -500.0);
        pieceScoreTable.put(new Bishop(Side.BLACK), -500.0);
        pieceScoreTable.put(new Queen(Side.BLACK), -900.0);
        pieceScoreTable.put(new Knight(Side.BLACK), -300.0);
    }

    public Double evaluateBoard(Board board) {
        double score = 0.0;

        Piece[][] positions = board.getPositions();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = positions[i][j];
                if (piece != null) {
                    if(pieceScoreTable.containsKey(piece)) {
                        Double pieceScore = pieceScoreTable.get(piece);
                        score += pieceScore;
                    }

                    int[][] locationScoreTable = getLocationScoreTable(piece);

                    if(piece.getSide().equals(Side.WHITE)) {
                        score += locationScoreTable[i][j];
                    } else {
                        int[][] flippedTable = flipTable(locationScoreTable);
                        score += flippedTable[i][j];
                    }
                }
            }
        }


        return score;
    }
}
