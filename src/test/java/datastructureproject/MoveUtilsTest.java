package datastructureproject;

import datastructureproject.board.Board;
import datastructureproject.board.Move;
import datastructureproject.board.Square;
import datastructureproject.pieces.Side;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveUtilsTest {

    MoveUtils moveUtils;
    Board board;

    public MoveUtilsTest() {
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
        this.moveUtils = new MoveUtils();
    }

    @After
    public void tearDown() {
        this.board = null;
        this.moveUtils = null;
    }

    @Test
    public void boardInitializationWorks() {
        assert (true);
    }

    @Test
    public void rightPossibleMovesFromStart() {
        List<Move> possibleMovesFromStart = moveUtils.getAllPossibleMoves(board, Side.WHITE);

        assert possibleMovesFromStart.size() == 20;
    }

    @Test
    public void getSquaresBetweenReturnsCorrectSquares() {
        List<Square> squaresBetween = moveUtils.getSquaresBetween(1, 1, 1, 6);
        List<Square> correctSquares = Arrays.asList(
                new Square(1,2),
                new Square(1,3),
                new Square(1,4),
                new Square(1,5)
        );

        assertEquals(correctSquares, squaresBetween);
    }

    @Test
    public void getSquaresBetweenReturnsEmptyList() {
        List<Square> squaresBetween = moveUtils.getSquaresBetween(1, 1, 4, 7);
        List<Square> correctSquares = new ArrayList<>();

        assertEquals(correctSquares, squaresBetween);
    }

    @Test
    public void checkIfSquareAttackedReturnsTrue() {
        String FEN = "rnbqkbnr/ppp1pppp/2p5/8/8/4P3/PPP1PPPP/RNBQKBNR w KQkq - 0 1";
        this.board.initializeFromFEN(FEN);
        assertTrue(moveUtils.checkIfSquareAttacked(new Square(0, 3), this.board, Side.WHITE));

    }

    @Test
    public void checkIfSquareAttackedReturnsFalse() {
        String FEN = "rnbqkbnr/ppp1pppp/2p5/8/8/4P3/PPP1PPPP/RNBQKBNR w KQkq - 0 1";
        this.board.initializeFromFEN(FEN);

        assertFalse(moveUtils.checkIfSquareAttacked(new Square(1, 6), this.board, Side.WHITE));
    }

}
