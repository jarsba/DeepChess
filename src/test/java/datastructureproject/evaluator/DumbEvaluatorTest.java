package datastructureproject.evaluator;

import datastructureproject.board.Board;
import datastructureproject.evaluators.DumbEvaluator;
import org.junit.*;

public class DumbEvaluatorTest {

    DumbEvaluator evaluator;
    Board board;

    public DumbEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new DumbEvaluator();
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
