package datastructureproject.board;

import datastructureproject.pieces.*;
import org.junit.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
            for (int j = 0; j < 8; j++) {
                assertNull(this.board.getPieceAt(i, j));
            }
        }

    }

    @Test
    public void boardCanMovePiece() {
        Piece pieceToMove = board.getPieceAt(1, 1);
        this.board.movePiece(1, 1, 2, 1);
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
            for (int j = 0; j < 8; j++) {
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

    @Test
    public void boardCanMakeCastlingMove() {
        String FEN = "r3k2r/p1ppqpp1/bpnb1n1p/1B2p3/2P1P3/2NPBN2/PP1Q1PPP/R3K2R w KQkq - 0 1";
        this.board.initializeFromFEN(FEN);
        Square kingStartSquare = new Square(0, 4);
        Square kingEndSquare = new Square(0, 6);
        Square rookStartSquare = new Square(0, 7);
        Square rookEndSquare = new Square(0, 5);
        Move move = new Move(kingStartSquare, kingEndSquare);


        assertEquals(board.hasPiece(kingStartSquare), true);
        assertEquals(board.hasPiece(rookStartSquare), true);

        board.makeCastlingMove(move);

        assertEquals(board.hasPiece(kingStartSquare), false);
        assertEquals(board.hasPiece(rookStartSquare), false);

        assertEquals(board.hasPiece(kingEndSquare), true);
        assertEquals(board.hasPiece(rookEndSquare), true);

        assertEquals(board.getPieceAt(kingEndSquare), new King(Side.WHITE));
        assertEquals(board.getPieceAt(rookEndSquare), new Rook(Side.WHITE));
    }

    @Test
    public void boardCanFilterPiecesBySide() {
        String FEN = "6P1/2q5/4N3/6n1/1Q3B2/4p3/1k6/8 w - - 0 1";
        this.board.initializeFromFEN(FEN);
        Map<Square, Piece> squareMap = board.filterPiecesBySide(Side.WHITE);

        Square bishopSquare = Square.fromAlgebraicNotation("f4");
        Square knightSquare = Square.fromAlgebraicNotation("e6");
        Square queenSquare = Square.fromAlgebraicNotation("b4");
        Square pawnSquare = Square.fromAlgebraicNotation("g8");

        Map<Square, Piece> correctMap = Map.of(bishopSquare, new Bishop(Side.WHITE), knightSquare, new Knight(Side.WHITE), queenSquare, new Queen(Side.WHITE), pawnSquare, new Pawn(Side.WHITE));

        assertEquals(squareMap, correctMap);
    }

    @Test
    public void boardCanFilterPiecesBySideAndType() {
        String FEN = "6P1/2q1p3/2pPN3/6n1/1Q1P1B2/4p1p1/1k6/8 w - - 0 1";
        this.board.initializeFromFEN(FEN);
        Map<Square, Piece> squareMap = board.filterPiecesBySideAndType(Side.BLACK, PieceType.PAWN);

        Square pawnSquare1 = Square.fromAlgebraicNotation("e7");
        Square pawnSquare2 = Square.fromAlgebraicNotation("c6");
        Square pawnSquare3 = Square.fromAlgebraicNotation("e3");
        Square pawnSquare4 = Square.fromAlgebraicNotation("g3");

        Map<Square, Piece> correctMap = Map.of(pawnSquare1, new Pawn(Side.BLACK), pawnSquare2, new Pawn(Side.BLACK), pawnSquare3, new Pawn(Side.BLACK), pawnSquare4, new Pawn(Side.BLACK));

        assertEquals(squareMap, correctMap);
    }
}
