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
        assertEquals(board.getPieceAt(0, 0), new Rook(Side.WHITE));
        assertEquals(board.getPieceAt(0, 1), new Knight(Side.WHITE));
        assertEquals(board.getPieceAt(0, 2), new Bishop(Side.WHITE));
        assertEquals(board.getPieceAt(0, 3), new Queen(Side.WHITE));
        assertEquals(board.getPieceAt(0, 4), new King(Side.WHITE));
        assertEquals(board.getPieceAt(0, 5), new Bishop(Side.WHITE));
        assertEquals(board.getPieceAt(0, 6), new Knight(Side.WHITE));
        assertEquals(board.getPieceAt(0, 7), new Rook(Side.WHITE));

        assertEquals(board.getPieceAt(7, 0), new Rook(Side.BLACK));
        assertEquals(board.getPieceAt(7, 1), new Knight(Side.BLACK));
        assertEquals(board.getPieceAt(7, 2), new Bishop(Side.BLACK));
        assertEquals(board.getPieceAt(7, 3), new Queen(Side.BLACK));
        assertEquals(board.getPieceAt(7, 4), new King(Side.BLACK));
        assertEquals(board.getPieceAt(7, 5), new Bishop(Side.BLACK));
        assertEquals(board.getPieceAt(7, 6), new Knight(Side.BLACK));
        assertEquals(board.getPieceAt(7, 7), new Rook(Side.BLACK));

        for (int i = 0; i < 8; i++) {
            assertEquals(board.getPieceAt(1, i), new Pawn(Side.WHITE));
        }

        for (int i = 0; i < 8; i++) {
            assertEquals(board.getPieceAt(6, i), new Pawn(Side.BLACK));
        }

        for (int i = 2; i < 6; i++) {
            for(int j = 0; j < 8; j++) {
                assertNull(board.getPieceAt(i, j));
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
}
