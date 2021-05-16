package datastructureproject.hashing;

import datastructureproject.board.Board;
import datastructureproject.pieces.Piece;

import java.util.Random;

public class Zobrist {

    private long[][][] table;
    private long[] castling;
    private final Random random;

    public Zobrist() {
        this.random = new Random();
        initializeTable();
    }

    // Necessary for testing to have control on random seed
    public Zobrist(long randomSeed) {
        this.random = new Random(randomSeed);
        initializeTable();
    }

    public void initializeTable() {
        this.table = new long[8][8][12];
        this.castling = new long[4];

        for (int piece = 0; piece < 12; piece++) {
            for (int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    this.table[row][column][piece] = this.random.nextLong();
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            this.castling[i] = this.random.nextLong();
        }

    }

    public long getHash(Board board) {
        long zobristHash = 0L;

        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (board.hasPiece(row, column)) {
                    Piece piece = board.getPieceAt(row, column);
                    zobristHash ^= this.table[row][column][piece.zobristIndex() - 1];
                }
            }
        }

        if (board.getWhiteKingSideCastlingAllowed()) {
            zobristHash ^= this.castling[0];
        }
        if (board.getWhiteQueenSideCastlingAllowed()) {
            zobristHash ^= this.castling[1];
        }
        if (board.getBlackKingSideCastlingAllowed()) {
            zobristHash ^= this.castling[2];
        }
        if (board.getBlackQueenSideCastlingAllowed()) {
            zobristHash ^= this.castling[3];
        }

        return zobristHash;

    }
}
