package datastructureproject.piece;

import datastructureproject.board.Square;
import datastructureproject.pieces.Knight;
import datastructureproject.pieces.Pawn;
import datastructureproject.pieces.Rook;
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
        List<Square> moves = Pawn.getPossibleMoves(1,1, Side.WHITE);
        List<Square> correctMoves = Arrays.asList(
                new Square(2,1),
                new Square(2,0),
                new Square(2,2),
                new Square(3,1)
        );

        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }

    @Test
    public void whitePawnHasNoPossibleMoves() {
        List<Square> moves = Pawn.getPossibleMoves(7,0, Side.WHITE);
        assertEquals(moves, new ArrayList<Square>());
    }

    @Test
    public void blackPawnHasPossibleMoves() {
        List<Square> moves = Pawn.getPossibleMoves(6,6, Side.BLACK);
        List<Square> correctMoves = Arrays.asList(
                new Square(5,5),
                new Square(5,6),
                new Square(5,7),
                new Square(4,6)
        );

        Collections.sort(moves);
        Collections.sort(correctMoves);

        assertEquals(moves, correctMoves);

    }

    @Test
    public void blackPawnHasNoPossibleMoves() {
        List<Square> moves = Pawn.getPossibleMoves(0,0, Side.BLACK);
        assertEquals(moves, new ArrayList<Square>());
    }
}
