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

    @Test
    public void boardEvaluationReturnsWinningForWhite() {
        String FEN = "4kbnr/1ppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQk - 0 1";
        this.board.initializeFromFEN(FEN);

        Double score = this.evaluator.evaluateBoard(this.board);
        assert score == 2300.00;
    }

    @Test
    public void boardEvaluationReturnsWinningForBlack() {
        String FEN = "rnbqk2r/1ppppppp/8/8/8/8/2PPPPPP/R2QK1N1 w Qkq - 0 1";
        this.board.initializeFromFEN(FEN);

        Double score = this.evaluator.evaluateBoard(this.board);
        assert score == -1100;
    }

}
