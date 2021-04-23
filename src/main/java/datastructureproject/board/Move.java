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
