package datastructureproject.board;

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class SquareTest {

    public SquareTest() {
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
    public void squareReturnsCorrectAlgebraicNotation() {
        Square square = new Square(4, 6);
        square.getAlgebraicNotation();
        assertEquals("g5", square.getAlgebraicNotation());
    }

    @Test
    public void squareCanBeInitializedFromAlgebraicNotation() {
        Square square = Square.fromAlgebraicNotation("g5");
        assertEquals(4, square.getRow());
        assertEquals(6, square.getColumn());
    }

    @Test
    public void squareComparisonReturnCorrectOrder() {
        Square square1 = new Square(2, 5);
        Square square2 = new Square(5, 1);

        assertEquals(-1, square1.compareTo(square2));

        square1 = new Square(5, 2);
        square2 = new Square(1, 2);

        assertEquals(1, square1.compareTo(square2));

        square1 = new Square(5, 4);
        square2 = new Square(5, 2);

        assertEquals(1, square1.compareTo(square2));

        square1 = new Square(2, 2);
        square2 = new Square(2, 2);

        assertEquals(0, square1.compareTo(square2));

    }
}
