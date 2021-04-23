package datastructureproject;

import datastructureproject.board.Board;
import datastructureproject.board.Move;
import datastructureproject.board.Square;
import datastructureproject.exceptions.PieceNotFoundOnBoardException;
import datastructureproject.pieces.*;
import org.junit.*;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MoveUtilsTest {

    MoveUtils moveUtils;
    Board board;

    public MoveUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.board = new Board();
        this.moveUtils = new MoveUtils(Side.WHITE);
    }

    @After
    public void tearDown() {
        this.board = null;
        this.moveUtils = null;
    }

    @Test
    public void boardInitializationWorks() {
        System.out.println(this.board);
        assert (true);
    }

    @Test
    public void rightPossibleMovesFromStart() throws PieceNotFoundOnBoardException {
        List<Move> possibleMovesFromStart = moveUtils.getAllPossibleMoves(board);
        assert possibleMovesFromStart.size() == 20;

    }

}
