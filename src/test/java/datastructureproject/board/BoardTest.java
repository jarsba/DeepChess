package datastructureproject.board;

import datastructureproject.board.Board;
import datastructureproject.pieces.*;
import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BoardTest {

    Board board;

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.board = new Board();
    }

    @After
    public void tearDown() {
        this.board = null;
    }

    @Test
    public void boardInitializationWorks() {
        System.out.println(this.board);
        assert (true);
    }

    @Test
    public void boardIsInitializedWithCorrectPieces() {
        assertEquals(this.board.getPieceAt(0, 0), new Rook(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 1), new Knight(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 2), new Bishop(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 3), new Queen(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 4), new King(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 5), new Bishop(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 6), new Knight(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 7), new Rook(Side.WHITE));

        assertEquals(this.board.getPieceAt(7, 0), new Rook(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 1), new Knight(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 2), new Bishop(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 3), new Queen(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 4), new King(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 5), new Bishop(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 6), new Knight(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 7), new Rook(Side.BLACK));

        for (int i = 0; i < 8; i++) {
            assertEquals(this.board.getPieceAt(1, i), new Pawn(Side.WHITE));
        }

        for (int i = 0; i < 8; i++) {
            assertEquals(this.board.getPieceAt(6, i), new Pawn(Side.BLACK));
        }

        for (int i = 2; i < 6; i++) {
            for(int j = 0; j < 8; j++) {
                assertNull(this.board.getPieceAt(i, j));
            }
        }

    }

    @Test
    public void boardCanMovePiece() {
        Piece pieceToMove = board.getPieceAt(1, 1);
        this.board.movePiece(1, 1, 2,1);
        assertNull(board.getPieceAt(1, 1));
        assertEquals(board.getPieceAt(2, 1), pieceToMove);
    }

    @Test
    public void boardIsInitializedWithStartingPositionFromFEN() {
        String FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        this.board.initializeFromFEN(FEN);

        assertEquals(this.board.getPieceAt(0, 0), new Rook(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 1), new Knight(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 2), new Bishop(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 3), new Queen(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 4), new King(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 5), new Bishop(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 6), new Knight(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 7), new Rook(Side.WHITE));

        assertEquals(this.board.getPieceAt(7, 0), new Rook(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 1), new Knight(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 2), new Bishop(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 3), new Queen(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 4), new King(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 5), new Bishop(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 6), new Knight(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 7), new Rook(Side.BLACK));

        for (int i = 0; i < 8; i++) {
            assertEquals(this.board.getPieceAt(1, i), new Pawn(Side.WHITE));
        }

        for (int i = 0; i < 8; i++) {
            assertEquals(this.board.getPieceAt(6, i), new Pawn(Side.BLACK));
        }

        for (int i = 2; i < 6; i++) {
            for(int j = 0; j < 8; j++) {
                assertNull(this.board.getPieceAt(i, j));
            }
        }
        assertEquals(this.board.getWhiteKingSideCastlingAllowed(), true);
        assertEquals(this.board.getWhiteQueenSideCastlingAllowed(), true);
        assertEquals(this.board.getBlackKingSideCastlingAllowed(), true);
        assertEquals(this.board.getBlackQueenSideCastlingAllowed(), true);


        assertEquals(this.board.getSideToMove(), Side.WHITE);
        assertEquals(this.board.getHalfMoveCounter(), 0);
        assertEquals(this.board.getFullMoveCounter(), 1);
    }

    @Test
    public void boardIsInitializedWithCorrectPositionFromFEN() {
        String FEN = "r2q1rk1/6pp/b3p3/p1bpPp1P/1p6/4BN2/PP1Q1PP1/R3K2R b KQ - 0 1";
        this.board.initializeFromFEN(FEN);

        assertEquals(this.board.getPieceAt(0, 0), new Rook(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 4), new King(Side.WHITE));
        assertEquals(this.board.getPieceAt(0, 7), new Rook(Side.WHITE));
        assertEquals(this.board.getPieceAt(1, 0), new Pawn(Side.WHITE));
        assertEquals(this.board.getPieceAt(1, 1), new Pawn(Side.WHITE));
        assertEquals(this.board.getPieceAt(1, 3), new Queen(Side.WHITE));
        assertEquals(this.board.getPieceAt(1, 5), new Pawn(Side.WHITE));
        assertEquals(this.board.getPieceAt(1, 6), new Pawn(Side.WHITE));
        assertEquals(this.board.getPieceAt(2, 4), new Bishop(Side.WHITE));
        assertEquals(this.board.getPieceAt(2, 5), new Knight(Side.WHITE));
        assertEquals(this.board.getPieceAt(3, 1), new Pawn(Side.BLACK));
        assertEquals(this.board.getPieceAt(4, 0), new Pawn(Side.BLACK));
        assertEquals(this.board.getPieceAt(4, 2), new Bishop(Side.BLACK));
        assertEquals(this.board.getPieceAt(4, 3), new Pawn(Side.BLACK));
        assertEquals(this.board.getPieceAt(4, 4), new Pawn(Side.WHITE));
        assertEquals(this.board.getPieceAt(4, 5), new Pawn(Side.BLACK));
        assertEquals(this.board.getPieceAt(4, 7), new Pawn(Side.WHITE));
        assertEquals(this.board.getPieceAt(5, 0), new Bishop(Side.BLACK));
        assertEquals(this.board.getPieceAt(5, 4), new Pawn(Side.BLACK));
        assertEquals(this.board.getPieceAt(6, 6), new Pawn(Side.BLACK));
        assertEquals(this.board.getPieceAt(6, 7), new Pawn(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 0), new Rook(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 3), new Queen(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 5), new Rook(Side.BLACK));
        assertEquals(this.board.getPieceAt(7, 6), new King(Side.BLACK));

        assertEquals(this.board.getWhiteKingSideCastlingAllowed(), true);
        assertEquals(this.board.getWhiteQueenSideCastlingAllowed(), true);
        assertEquals(this.board.getBlackKingSideCastlingAllowed(), false);
        assertEquals(this.board.getBlackQueenSideCastlingAllowed(), false);

        assertEquals(this.board.getSideToMove(), Side.BLACK);
        assertEquals(this.board.getHalfMoveCounter(), 0);
        assertEquals(this.board.getFullMoveCounter(), 1);
    }
}
