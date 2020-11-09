package datastructureproject.board;

import datastructureproject.pieces.Rook;

import java.util.Map;

public class Square implements Comparable<Square> {

    private final int row;
    private final int column;
    private final String letters = "abcdefgh";

    public Square(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isValidPosition() {
        return column >= 0 && column < 8 && row >= 0 && row < 8;
    }

    public String getAlgebraicNotation() {
        return String.format("%s%d", (char) (this.column + 'a'), this.row);
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 37 * hash + this.row;
        hash = 11 * hash + this.column;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Square otherSquare = (Square) obj;
        return this.hashCode() == otherSquare.hashCode();
    }

    public int compareTo(Square otherSquare) {
        if (this.row > otherSquare.getRow()) {
            return 1;
        } else if (this.row == otherSquare.getRow()) {
            if(this.column > otherSquare.getColumn()) {
                return 1;
            } else if (this.column == otherSquare.getColumn()) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    };
}
