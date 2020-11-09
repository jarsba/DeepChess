package datastructureproject.piece;

import datastructureproject.board.Board;
import datastructureproject.board.Square;
import datastructureproject.pieces.Bishop;
import datastructureproject.pieces.Queen;
import datastructureproject.pieces.Side;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BishopTest {

    Bishop whiteBishop;
    Bishop blackBishop;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        whiteBishop = new Bishop(Side.WHITE);
        blackBishop = new Bishop(Side.BLACK);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void bishopHasPossibleMovesFromCorner() {
        List<Square> moves = Bishop.getPossibleMoves(0,0);
        List<Square> correctMoves = Arrays.asList(
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
    public void bishopHasPossibleMovesFromCenter() {
        List<Square> moves = Bishop.getPossibleMoves(4,4);
        List<Square> correctMoves = Arrays.asList(
                new Square(3,3),
                new Square(2,2),
                new Square(1,1),
                new Square(0,0),
                new Square(3,5),
                new Square(2,6),
                new Square(1,7),
                new Square(5,3),
                new Square(6,2),
                new Square(7,1),
                new Square(5,5),
                new Square(7,7),
                new Square(6,6)
        );
        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }
}
