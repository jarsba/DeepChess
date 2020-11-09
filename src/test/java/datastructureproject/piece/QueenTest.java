package datastructureproject.piece;

import datastructureproject.board.Square;
import datastructureproject.pieces.Queen;
import datastructureproject.pieces.Rook;
import datastructureproject.pieces.Side;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class QueenTest {

    Queen whiteQueen;
    Queen blackQueen;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        whiteQueen = new Queen(Side.WHITE);
        blackQueen = new Queen(Side.BLACK);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void queenHasPossibleMovesFromCorner() {
        List<Square> moves = Queen.getPossibleMoves(0,0);
        List<Square> correctMoves = Arrays.asList(
                new Square(0,1),
                new Square(0,2),
                new Square(0,3),
                new Square(0,4),
                new Square(0,5),
                new Square(0,6),
                new Square(0,7),
                new Square(1,0),
                new Square(2,0),
                new Square(3,0),
                new Square(4,0),
                new Square(5,0),
                new Square(6,0),
                new Square(7,0),
                new Square(1,1),
                new Square(2,2),
                new Square(3,3),
                new Square(4,4),
                new Square(5,5),
                new Square(6,6),
                new Square(7,7)
        );

        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }

    @Test
    public void queenHasPossibleMovesFromCenter() {
        List<Square> moves = Queen.getPossibleMoves(4,4);
        List<Square> correctMoves = Arrays.asList(
                new Square(4,0),
                new Square(4,1),
                new Square(4,2),
                new Square(4,3),
                new Square(4,5),
                new Square(4,6),
                new Square(4,7),
                new Square(0,4),
                new Square(1,4),
                new Square(2,4),
                new Square(3,4),
                new Square(5,4),
                new Square(6,4),
                new Square(7,4),
                new Square(3,3),
                new Square(2,2),
                new Square(1,1),
                new Square(0,0),
                new Square(5,5),
                new Square(6,6),
                new Square(7,7),
                new Square(3,5),
                new Square(2,6),
                new Square(1,7),
                new Square(5,3),
                new Square(6,2),
                new Square(7,1)
        );

        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }
}
