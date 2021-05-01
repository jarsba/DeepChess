package datastructureproject.piece;

import datastructureproject.board.Move;
import datastructureproject.board.Square;
import datastructureproject.pieces.King;
import datastructureproject.pieces.Knight;
import datastructureproject.pieces.Side;
import org.checkerframework.checker.units.qual.K;
import org.junit.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KnightTest {

    Knight whiteKnight;
    Knight blackKnight;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        whiteKnight = new Knight(Side.WHITE);
        blackKnight = new Knight(Side.BLACK);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void knightHasPossibleMovesFromCorner() {
        Square startSquare = new Square(0,0);
        List<Move> moves = Knight.getPossibleMoves(startSquare);
        List<Move> correctMoves = Arrays.asList(
                new Move(startSquare, new Square(2,1)),
                new Move(startSquare, new Square(1,2))
        );

        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }

    @Test
    public void knightHasPossibleMovesFromCenter() {
        Square startSquare = new Square(4,4);
        List<Move> moves = Knight.getPossibleMoves(startSquare);
        List<Move> correctMoves = Arrays.asList(
                new Move(startSquare, new Square(2,3)),
                new Move(startSquare, new Square(2,5)),
                new Move(startSquare, new Square(3,2)),
                new Move(startSquare, new Square(3,6)),
                new Move(startSquare, new Square(5,2)),
                new Move(startSquare, new Square(5,6)),
                new Move(startSquare, new Square(6,3)),
                new Move(startSquare, new Square(6,5))
        );

        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }
}
