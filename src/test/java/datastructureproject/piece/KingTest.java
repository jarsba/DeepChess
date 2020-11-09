package datastructureproject.piece;

import datastructureproject.board.Square;
import datastructureproject.pieces.King;
import datastructureproject.pieces.Queen;
import datastructureproject.pieces.Side;
import org.junit.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KingTest {

    King whiteKing;
    King blackKing;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        whiteKing = new King(Side.WHITE);
        blackKing = new King(Side.BLACK);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void kingHasPossibleMovesFromCorner() {
        List<Square> moves = King.getPossibleMoves(0,0);
        List<Square> correctMoves = Arrays.asList(
                new Square(0,1),
                new Square(1,0),
                new Square(1,1)
        );

        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }

    @Test
    public void kingHasPossibleMovesFromCenter() {
        List<Square> moves = King.getPossibleMoves(4,4);
        List<Square> correctMoves = Arrays.asList(
                new Square(3,3),
                new Square(3,4),
                new Square(3,5),
                new Square(4,3),
                new Square(4,5),
                new Square(5,3),
                new Square(5,4),
                new Square(5,5)
        );

        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }
}
