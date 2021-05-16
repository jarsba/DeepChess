package chess.connection;

import chess.bot.ChessBot;
import chess.engine.GameState;

public class NullBot implements ChessBot {

    public NullBot() {
    }

    @Override
    public String nextMove(GameState gamestate) {
        return null;
    }
}
