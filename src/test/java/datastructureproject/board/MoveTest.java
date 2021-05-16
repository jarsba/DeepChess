package datastructureproject.board;

import datastructureproject.pieces.Piece;
import datastructureproject.pieces.Queen;
import datastructureproject.pieces.Side;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class MoveTest {


    public MoveTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void moveInitializationWorksFromUCIString() {
        Move move = Move.fromUCIString("c3d5");
        Square startSquare = new Square(2, 2);
        Square endSquare = new Square(4, 3);

        assertEquals(move.getStartSquare(), startSquare);
        assertEquals(move.getEndSquare(), endSquare);

        move = Move.fromUCIString("g7g8q");
        startSquare = new Square(6, 6);
        endSquare = new Square(7, 6);
        Piece promotionPiece = new Queen(Side.WHITE);

        assertEquals(move.getStartSquare(), startSquare);
        assertEquals(move.getEndSquare(), endSquare);
        assertEquals(move.getPromotionPiece(), promotionPiece);
    }

    @Test
    public void moveToStringIsCorrectUCIString() {
        Square startSquare = new Square(2, 2);
        Square endSquare = new Square(4, 3);

        Move move = new Move(startSquare, endSquare);

        assertEquals("Move<Square(c3), Square(d5)>", move.toString());
    }

    @Test
    public void moveComparisonReturnCorrectOrder() {

        Square startSquare1 = new Square(2, 2);
        Square endSquare1 = new Square(4, 3);
        Square startSquare2 = new Square(3, 5);
        Square endSquare2 = new Square(4, 5);

        Move move1 = new Move(startSquare1, endSquare1);
        Move move2 = new Move(startSquare2, endSquare2);

        assertEquals(-1 , move1.compareTo(move2));

        startSquare1 = new Square(6, 3);
        endSquare1 = new Square(5, 7);
        startSquare2 = new Square(6, 2);
        endSquare2 = new Square(1, 4);

        move1 = new Move(startSquare1, endSquare1);
        move2 = new Move(startSquare2, endSquare2);

        assertEquals(1 , move1.compareTo(move2));

        startSquare1 = new Square(6, 6);
        endSquare1 = new Square(5, 7);
        startSquare2 = new Square(6, 6);
        endSquare2 = new Square(5, 7);

        move1 = new Move(startSquare1, endSquare1);
        move2 = new Move(startSquare2, endSquare2);

        assertEquals(0 , move1.compareTo(move2));

    }

}
