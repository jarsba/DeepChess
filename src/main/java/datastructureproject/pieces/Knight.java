package datastructureproject.pieces;

import datastructureproject.board.Board;
import datastructureproject.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight implements Piece {
    private final PieceType pieceType = PieceType.KNIGHT;
    private Side side;

    public Knight(Side side) {
        this.side = side;
    }

    @Override
    public PieceType getPieceType() {
        return this.pieceType;
    }

    @Override
    public Side getSide() {
        return this.side;
    }


    // Doesn't consider board position
    public static List<Square> getPossibleMoves(int row, int column) {

        ArrayList<Square> possibleMoves = new ArrayList<>();

        Square topLeft = new Square(row + 2, column - 1);
        Square topRight = new Square(row + 2, column + 1);
        Square rightTop = new Square(row + 1, column + 2);
        Square rightBottom = new Square(row - 1, column + 2);
        Square bottomLeft = new Square(row - 2, column - 1);
        Square bottomRight = new Square(row - 2, column + 1);
        Square leftTop = new Square(row + 1, column - 2);
        Square leftBottom = new Square(row - 1, column - 2);

        if (topLeft.isValidPosition()) {
            possibleMoves.add(topLeft);
        }
        if (topRight.isValidPosition()) {
            possibleMoves.add(topRight);
        }
        if (rightTop.isValidPosition()) {
            possibleMoves.add(rightTop);
        }
        if (rightBottom.isValidPosition()) {
            possibleMoves.add(rightBottom);
        }
        if (bottomLeft.isValidPosition()) {
            possibleMoves.add(bottomLeft);
        }
        if (bottomRight.isValidPosition()) {
            possibleMoves.add(bottomRight);
        }
        if (leftTop.isValidPosition()) {
            possibleMoves.add(leftTop);
        }
        if (leftBottom.isValidPosition()) {
            possibleMoves.add(leftBottom);
        }

        return possibleMoves;

    }

    // Does consider board positions, but not situations where the piece can't move because of pinning etc.
    public static List<Square> getPossibleMoves(int row, int column, Board board) {
        Side side = board.getPieceAt(row, column).getSide();
        ArrayList<Square> possibleMoves = new ArrayList<>();

        Square topLeft = new Square(row + 2, column - 1);
        Square topRight = new Square(row + 2, column + 1);
        Square rightTop = new Square(row + 1, column + 2);
        Square rightBottom = new Square(row - 1, column + 2);
        Square bottomLeft = new Square(row - 2, column - 1);
        Square bottomRight = new Square(row - 2, column + 1);
        Square leftTop = new Square(row + 1, column - 2);
        Square leftBottom = new Square(row - 1, column - 2);

        if (topLeft.isValidPosition()) {
            if(board.hasPiece(topLeft.getRow(), topLeft.getColumn())) {
                if(!board.getPieceAt(topLeft.getRow(), topLeft.getColumn()).getSide().equals(side)) {
                    possibleMoves.add(topLeft);
                }
            } else {
                possibleMoves.add(topLeft);
            }
        }
        if (topRight.isValidPosition()) {
            if(board.hasPiece(topRight.getRow(), topRight.getColumn())) {
                if(!board.getPieceAt(topRight.getRow(), topRight.getColumn()).getSide().equals(side)) {
                    possibleMoves.add(topRight);
                }
            } else {
                possibleMoves.add(topRight);
            }
        }
        if (rightTop.isValidPosition()) {
            if(board.hasPiece(rightTop.getRow(), rightTop.getColumn())) {
                if(!board.getPieceAt(rightTop.getRow(), rightTop.getColumn()).getSide().equals(side)) {
                    possibleMoves.add(rightTop);
                }
            } else {
                possibleMoves.add(rightTop);
            }
        }
        if (rightBottom.isValidPosition()) {
            if(board.hasPiece(rightBottom.getRow(), rightBottom.getColumn())) {
                if(!board.getPieceAt(rightBottom.getRow(), rightBottom.getColumn()).getSide().equals(side)) {
                    possibleMoves.add(rightBottom);
                }
            } else {
                possibleMoves.add(rightBottom);
            }
        }
        if (bottomLeft.isValidPosition()) {
            if(board.hasPiece(bottomLeft.getRow(), bottomLeft.getColumn())) {
                if(!board.getPieceAt(bottomLeft.getRow(), bottomLeft.getColumn()).getSide().equals(side)) {
                    possibleMoves.add(bottomLeft);
                }
            } else {
                possibleMoves.add(bottomLeft);
            }
        }
        if (bottomRight.isValidPosition()) {
            if(board.hasPiece(bottomRight.getRow(), bottomRight.getColumn())) {
                if(!board.getPieceAt(bottomRight.getRow(), bottomRight.getColumn()).getSide().equals(side)) {
                    possibleMoves.add(bottomRight);
                }
            } else {
                possibleMoves.add(bottomRight);
            }
        }
        if (leftTop.isValidPosition()) {
            if(board.hasPiece(leftTop.getRow(), leftTop.getColumn())) {
                if(!board.getPieceAt(leftTop.getRow(), leftTop.getColumn()).getSide().equals(side)) {
                    possibleMoves.add(leftTop);
                }
            } else {
                possibleMoves.add(leftTop);
            }
        }
        if (leftBottom.isValidPosition()) {
            if(board.hasPiece(leftBottom.getRow(), leftBottom.getColumn())) {
                if(!board.getPieceAt(leftBottom.getRow(), leftBottom.getColumn()).getSide().equals(side)) {
                    possibleMoves.add(leftBottom);
                }
            } else {
                possibleMoves.add(leftBottom);
            }
        }

        return possibleMoves;
    }

    public String getUnicodeCharacter() {
        if (this.side.equals(Side.WHITE)) {
            return "\u2658";
        } else {
            return "\u265E";
        }
    }

    public String getPieceNotion() {
        if (this.side.equals(Side.WHITE)) {
            return "N";
        } else {
            return "n";
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + side.hashCode();
        hash = 31 * hash + pieceType.hashCode();
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
        Knight otherPiece = (Knight) obj;
        return this.hashCode() == otherPiece.hashCode();
    }

    @Override
    public String toString() {
        return getUnicodeCharacter();
    }
}
