package datastructureproject.pieces;

import datastructureproject.board.Square;

import java.util.List;

public interface Piece {
    PieceType getPieceType();
    Side getSide();
    String getUnicodeCharacter();
    String getPieceNotion();
}
