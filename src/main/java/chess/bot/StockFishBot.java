// THIS CLASS IS ONLY FOR INTERNAL GAMESTATE TRACKING AND TESTING
// THIS CLASS SHOULD NOT BE USED FOR WRITING A CHESS BOT FOR 
// DATA STRUCTURES AND ALGORITHMS PROJECTS

package chess.bot;

import chess.engine.GameState;
import logging.Logger;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;

public class StockFishBot implements ChessBot {

    private Logger logger;
    private Process stockfish;
    private BufferedReader reader;
    private BufferedWriter writer;
    // milliseconds
    private final Integer moveTime = 1000;

    public StockFishBot() {
        this.logger = new Logger().useStdOut();
        this.stockfish = startStockfish();
    }

    /**
     * @param gs Current gamestate
     * @return UCI String representation of a move
     */
    @Override
    public String nextMove(GameState gs) {
        String move = this.getStockfishMove(gs);
        return move;
    }

    private Process startStockfish() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        logger.logMessage(System.getProperty("user.dir"));
        String stockfishPath = String.format("%s/%s", System.getProperty("user.dir"), "stockfish_12");
        processBuilder.command("bash", "-c", stockfishPath);
        Process stockfishProcess = null;
        try {
            logger.logMessage("Starting StockFish");
            stockfishProcess = processBuilder.start();

            OutputStream stdin = stockfishProcess.getOutputStream();
            InputStream stdout = stockfishProcess.getInputStream();

            this.reader = new BufferedReader(new InputStreamReader(stdout));
            this.writer = new BufferedWriter(new OutputStreamWriter(stdin));

            while (true) {
                String line = reader.readLine();
                logger.logMessage(line);

                if (line.contains("Stockfish 12 by the Stockfish developers (see AUTHORS file)")) {
                    logger.logMessage("Stockfish started");
                    break;
                }

                if (line == null) {
                    break;
                }
            }


        } catch (IOException e) {
            logger.logError(e.getMessage());
        }

        return stockfishProcess;
    }

    private void writeToStockfishStdin(String message) {
        Process stockFishProcess = this.stockfish;
        OutputStream stdin = stockFishProcess.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
        try {
            writer.write(String.format("%s", message));
            writer.flush();
            writer.close();

        } catch (IOException e) {
            logger.logError(e.getMessage());
        }

    }

    /**
     * Fetches the best stockfish-move based on current board state
     *
     * @return A stockfish move
     */
    public String getStockfishMove(GameState gs) {

        Process stockFishProcess = this.stockfish;

        BufferedReader reader = this.reader;
        BufferedWriter writer = this.writer;

        String pastMoves = parseMoves(gs);
        String UCIMove = null;

        try {
            logger.logMessage(String.format("Call Stockfish to evaluate position: %s", pastMoves));

            int moveCount = gs.getMoveCount();

            // If movecount less than 2, initiate new game
            if(moveCount < 2) {
                logger.logMessage(String.format("writing: ucinewgame"));
                writer.write(String.format("ucinewgame\n"));
            }

            if (moveCount > 0) {
                logger.logMessage(String.format("writing: position startpos moves %s", pastMoves));
                writer.write(String.format("position startpos moves %s\n", pastMoves));
            } else {
                logger.logMessage(String.format("writing: position startpos"));
                writer.write(String.format("position startpos\n"));
            }

            writer.write(String.format("go movetime %s}\n", moveTime));

            writer.flush();

            while (true) {
                String line = reader.readLine();
                logger.logMessage(String.format("line: %s", line));

                if (line == null) {
                    break;
                }

                if (line.contains("bestmove")) {
                    logger.logMessage("Stockfish found a move");
                    UCIMove = line.split(" ")[1];
                    break;
                }

            }

        } catch (IOException e) {
            logger.logError(e.getMessage());
        }

        return UCIMove;
    }

    /**
     * Parses all moves in UCI to string
     *
     * @param gs Current gamestate
     */
    public String parseMoves(GameState gs) {
        String UCIString = String.join(" ", gs.moves);
        logger.logMessage(String.format("Moves: %s", UCIString));
        return UCIString;
    }
}
