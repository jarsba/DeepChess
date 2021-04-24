package datastructureproject.evaluator;

import datastructureproject.MoveUtils;
import datastructureproject.board.Board;
import datastructureproject.board.Move;
import datastructureproject.evaluators.Evaluator;
import datastructureproject.exceptions.PieceNotFoundOnBoardException;
import datastructureproject.pieces.Side;
import org.junit.*;

import java.util.List;

public class EvaluatorTest {

    Evaluator evaluator;
    Board board;

    public EvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new Evaluator();
        this.board = new Board();
    }

    @After
    public void tearDown() {
        this.evaluator = null;
        this.board = null;
    }


    @Test
    public void initialBoardEvaluationReturnsZero() {
        Double score = this.evaluator.evaluateBoard(this.board);
        assert score == 0.0;
    }

}
