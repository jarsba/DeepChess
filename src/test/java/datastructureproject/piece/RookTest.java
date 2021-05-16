package datastructureproject.piece;

import datastructureproject.board.Move;
import datastructureproject.board.Square;
import datastructureproject.pieces.Rook;
import datastructureproject.pieces.Side;
import org.junit.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RookTest {

    Rook whiteRook;
    Rook blackRook;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        whiteRook = new Rook(Side.WHITE);
        blackRook = new Rook(Side.BLACK);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void rookHasPossibleMovesFromCorner() {
        Square startSquare = new Square(0,0);
        List<Move> moves = Rook.getPossibleMoves(startSquare);
        List<Move> correctMoves = Arrays.asList(
                new Move(startSquare, new Square(0,1)),
                new Move(startSquare, new Square(0,2)),
                new Move(startSquare, new Square(0,3)),
                new Move(startSquare, new Square(0,4)),
                new Move(startSquare, new Square(0,5)),
                new Move(startSquare, new Square(0,6)),
                new Move(startSquare, new Square(0,7)),
                new Move(startSquare, new Square(1,0)),
                new Move(startSquare, new Square(2,0)),
                new Move(startSquare, new Square(3,0)),
                new Move(startSquare, new Square(4,0)),
                new Move(startSquare, new Square(5,0)),
                new Move(startSquare, new Square(6,0)),
                new Move(startSquare, new Square(7,0))
        );
        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }

    @Test
    public void rookHasPossibleMovesFromCenter() {
        Square startSquare = new Square(4,4);
        List<Move> moves = Rook.getPossibleMoves(startSquare);
        List<Move> correctMoves = Arrays.asList(
                new Move(startSquare, new Square(4,0)),
                new Move(startSquare, new Square(4,1)),
                new Move(startSquare, new Square(4,2)),
                new Move(startSquare, new Square(4,3)),
                new Move(startSquare, new Square(4,5)),
                new Move(startSquare, new Square(4,6)),
                new Move(startSquare, new Square(4,7)),
                new Move(startSquare, new Square(0,4)),
                new Move(startSquare, new Square(1,4)),
                new Move(startSquare, new Square(2,4)),
                new Move(startSquare, new Square(3,4)),
                new Move(startSquare, new Square(5,4)),
                new Move(startSquare, new Square(6,4)),
                new Move(startSquare, new Square(7,4))
        );
        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }
}