package datastructureproject.board;

import com.github.bhlangonijr.chesslib.Constants;
import datastructureproject.pieces.Piece;

public class Move {

    private final Square startSquare;
    private final Square endSquare;
    private Piece promotionPiece;

    public Move(Square startSquare, Square endSquare) {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
    }

    public Move(Square startSquare, Square endSquare, Piece promotionPiece) {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        this.promotionPiece = promotionPiece;
    }

    public Square getStartSquare() {
        return this.startSquare;
    }
    public Square getEndSquare() {
        return this.endSquare;
    }
    public Piece getPromotionPiece() { return this.promotionPiece; }
    public void setPromotionPiece(Piece piece) {
        this.promotionPiece = piece;
    }


    @Override
    public int hashCode() {
        int hash = 13;
        hash = 37 * hash + this.startSquare.hashCode();
        hash = 11 * hash + this.endSquare.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return String.format("Move<%s, %s>", startSquare, endSquare);
    }

    public static Move fromUCIString(String string) {

        Square startSquare = Square.fromAlgebraicNotation(string.substring(0, 2));
        Square endSquare = Square.fromAlgebraicNotation(string.substring(2, 4));

        if (string.length() == 4) {
            return new Move(startSquare, endSquare);
        } else if(string.length() == 5) {
            // Lichess don't return promotion-piece type in capital if promotion is done to white
            Piece promotionPiece;
            if(endSquare.getRow() == 7) {
                // Promotion piece for white
                promotionPiece = Piece.fromPieceNotation(string.substring(4, 5).toUpperCase());
            } else if (endSquare.getRow() == 0) {
                // Promotion piece for black
                promotionPiece = Piece.fromPieceNotation(string.substring(4, 5).toLowerCase());
            } else {
                throw new Error(String.format("Promotion done to wrong square: row %s, column %s", endSquare.getRow(), endSquare.getColumn() ));
            }
            return new Move(startSquare, endSquare, promotionPiece);
        } else {
            throw new Error("Could not recognize UCI string format");
        }
    }

    public String toUCIString() {
        if(this.promotionPiece == null) {
            return String.format("%s%s",this.startSquare.getAlgebraicNotation().toLowerCase(),this.endSquare.getAlgebraicNotation().toLowerCase());
        } else {
            return String.format("%s%s%s",this.startSquare.getAlgebraicNotation().toLowerCase(), this.endSquare.getAlgebraicNotation().toLowerCase(), this.promotionPiece.getPieceNotion());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        datastructureproject.board.Move otherMove = (datastructureproject.board.Move) obj;
        return this.hashCode() == otherMove.hashCode();
    }


}
