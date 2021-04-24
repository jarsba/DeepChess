package datastructureproject.pieces;

import datastructureproject.board.Board;
import datastructureproject.board.Square;

import java.util.List;

public interface Piece {
    PieceType getPieceType();
    Side getSide();
    String getUnicodeCharacter();
    String getPieceNotion();

    static Piece fromPieceNotation(String pieceNotation) {
        switch (pieceNotation.toLowerCase()) {
            case "b":
                return Bishop.fromPieceNotation(pieceNotation);
            case "r":
                return Rook.fromPieceNotation(pieceNotation);
            case "p":
                return Pawn.fromPieceNotation(pieceNotation);
            case "n":
                return Knight.fromPieceNotation(pieceNotation);
            case "q":
                return Queen.fromPieceNotation(pieceNotation);
            case "k":
                return King.fromPieceNotation(pieceNotation);
        }
        return null;
    }
}
