package datastructureproject.piece;

import datastructureproject.board.Move;
import datastructureproject.board.Square;
import datastructureproject.pieces.Pawn;
import datastructureproject.pieces.Side;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PawnTest {

    Pawn whitePawn;
    Pawn blackPawn;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        whitePawn = new Pawn(Side.WHITE);
        blackPawn = new Pawn(Side.BLACK);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void whitePawnHasPossibleMoves() {
        Square startSquare = new Square(1,1);
        List<Move> moves = Pawn.getPossibleMoves(startSquare, Side.WHITE);
        List<Move> correctMoves = Arrays.asList(
                new Move(startSquare, new Square(2,1)),
                new Move(startSquare, new Square(2,0)),
                new Move(startSquare, new Square(2,2)),
                new Move(startSquare, new Square(3,1))
        );

        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }

    @Test
    public void whitePawnHasNoPossibleMoves() {
        Square startSquare = new Square(7,0);
        List<Move> moves = Pawn.getPossibleMoves(startSquare, Side.WHITE);
        assertEquals(moves, new ArrayList<Move>());
    }

    @Test
    public void blackPawnHasPossibleMoves() {
        Square startSquare = new Square(6,6);
        List<Move> moves = Pawn.getPossibleMoves(startSquare, Side.BLACK);
        List<Move> correctMoves = Arrays.asList(
                new Move(startSquare, new Square(5,5)),
                new Move(startSquare, new Square(5,6)),
                new Move(startSquare, new Square(5,7)),
                new Move(startSquare, new Square(4,6))
        );

        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }

    @Test
    public void blackPawnHasNoPossibleMoves() {
        Square startSquare = new Square(0,0);
        List<Move> moves = Pawn.getPossibleMoves(startSquare, Side.BLACK);
        assertEquals(moves, new ArrayList<Move>());
    }
}
