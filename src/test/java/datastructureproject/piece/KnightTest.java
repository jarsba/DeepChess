package datastructureproject.piece;

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
        List<Square> moves = Knight.getPossibleMoves(0,0);
        List<Square> correctMoves = Arrays.asList(
                new Square(2,1),
                new Square(1,2)
        );

        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }

    @Test
    public void knightHasPossibleMovesFromCenter() {
        List<Square> moves = Knight.getPossibleMoves(4,4);
        List<Square> correctMoves = Arrays.asList(
                new Square(2,3),
                new Square(2,5),
                new Square(3,2),
                new Square(3,6),
                new Square(5,2),
                new Square(5,6),
                new Square(6,3),
                new Square(6,5)
        );

        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }
}
