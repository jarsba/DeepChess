package datastructureproject.evaluator;

import datastructureproject.board.Board;
import datastructureproject.evaluators.PieceSquareEvaluator;
import org.junit.*;

import java.util.Arrays;

public class PieceSquareEvaluatorTest {

    PieceSquareEvaluator evaluator;
    Board board;

    public PieceSquareEvaluatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.evaluator = new PieceSquareEvaluator();
        this.board = new Board();
    }

    @After
    public void tearDown() {
        this.evaluator = null;
        this.board = null;
    }


    @Test
    public void flippedBoardIsCorrect() {
        int[][] whitePawnScores = PieceSquareEvaluator.pawnLocationScores;

        int[][] whitePawnScoresFlipped = PieceSquareEvaluator.flipTable(whitePawnScores);

        int[][] pawnLocationScoresFlipped = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {-50, -50, -50, -50, -50, -50, -50, -50},
                {-10, -10, -20, -30, -30, -20, -10, -10},
                {-5, -5, -10, -25, -25, -10, -5, -5},
                {0, 0, 0, -20, -20, 0, 0, 0},
                {-5, 5, 10, 0, 0, 10, 5, -5},
                {-5, -10, -10, 20, 20, -10, -10, -5},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        assert Arrays.deepEquals(whitePawnScoresFlipped, pawnLocationScoresFlipped);
    }

    @Test
    public void initialBoardEvaluationReturnsZero() {
        Double score = this.evaluator.evaluateBoard(this.board);
        assert score == 0.0;
    }

    @Test
    public void whiteIsWinningAfterPawnMove() {
        this.board.movePiece(1, 3, 3,3);

        Double score = this.evaluator.evaluateBoard(this.board);
        assert score == 40.0;
    }

    @Test
    public void blackIsWinningAfterPawnMove() {
        this.board.movePiece(6, 3, 4,3);

        Double score = this.evaluator.evaluateBoard(this.board);
        assert score == -40.0;
    }

    @Test
    public void scoreIsEvenAfterSymmetricPawnMoves() {
        this.board.movePiece(1, 3, 3,3);
        this.board.movePiece(6, 3, 4,3);

        Double score = this.evaluator.evaluateBoard(this.board);
        assert score == 0.0;
    }

    @Test
    public void scoreIsCorrectForBoardFromUCIString() {
        String FEN = "rnbqkbnr/2pppppp/8/pp6/PP6/B1NQ4/2PPPPPP/RK3BNR w KQkq - 0 1";
        this.board.initializeFromFEN(FEN);

        Double score = this.evaluator.evaluateBoard(this.board);
        assert score == 90.0;

    }
}
