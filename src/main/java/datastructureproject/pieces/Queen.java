package datastructureproject.pieces;

import datastructureproject.board.Board;
import datastructureproject.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Queen implements Piece {
    private final PieceType pieceType = PieceType.QUEEN;
    private Side side;

    public Queen(Side side) {
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

        possibleMoves.addAll(Rook.getPossibleMoves(row, column));
        possibleMoves.addAll(Bishop.getPossibleMoves(row, column));

        return possibleMoves;
    }

    // Does consider board positions, but not situations where the piece can't move because of pinning etc.
    public static List<Square> getPossibleMoves(int row, int column, Board board) {
        ArrayList<Square> possibleMoves = new ArrayList<>();
        Side side = board.getPieceAt(row, column).getSide();

        // Rook moves

        for (int i = row + 1; i <= 7; i++) {
            if(board.hasPiece(i, column)) {
                if(board.getPieceAt(i, column).getSide().equals(side)) {
                    break;
                } else {
                    possibleMoves.add(new Square(i, column));
                    break;
                }
            }
            possibleMoves.add(new Square(i, column));
        }

        for (int i = row - 1; i >= 0; i--) {
            if(board.hasPiece(i, column)) {
                if(board.getPieceAt(i, column).getSide().equals(side)) {
                    break;
                } else {
                    possibleMoves.add(new Square(i, column));
                    break;
                }
            }
            possibleMoves.add(new Square(i, column));
        }

        for (int i = column + 1; i <= 7; i++) {
            if(board.hasPiece(row, i)) {
                if(board.getPieceAt(row, i).getSide().equals(side)) {
                    break;
                } else {
                    possibleMoves.add(new Square(row, i));
                    break;
                }
            }
            possibleMoves.add(new Square(row, i));
        }

        for (int i = column - 1; i >= 0; i--) {
            if(board.hasPiece(row, i)) {
                if(board.getPieceAt(row, i).getSide().equals(side)) {
                    break;
                } else {
                    possibleMoves.add(new Square(row, i));
                    break;
                }
            }
            possibleMoves.add(new Square(row, i));
        }

        // Knight moves
        // Row decrease, column decrease
        int minDistanceFromZero = Math.min(row, column);

        for (int i = 1; i <= minDistanceFromZero; i++) {
            if(board.hasPiece(row-i, column-i)) {
                if(board.getPieceAt(row-i, column-i).getSide().equals(side)) {
                    break;
                } else {
                    possibleMoves.add(new Square(row-i, column-i));
                    break;
                }
            }
            possibleMoves.add(new Square(row-i, column-i));
        }

        // Row decrease, column increase
        int minDistanceFromZeroOrEight = Math.min(row, 7 - column);

        for (int i = 1; i <= minDistanceFromZeroOrEight; i++) {
            if(board.hasPiece(row-i, column+i)) {
                if(board.getPieceAt(row-i, column+i).getSide().equals(side)) {
                    break;
                } else {
                    possibleMoves.add(new Square(row-i, column+i));
                    break;
                }
            }
            possibleMoves.add(new Square(row-i, column+i));
        }

        // Row increase, column decrease

        int minDistanceFromEightOrZero = Math.min(7 - row, column);

        for (int i = 1; i <= minDistanceFromEightOrZero; i++) {
            if(board.hasPiece(row+i, column-i)) {
                if(board.getPieceAt(row+i, column-i).getSide().equals(side)) {
                    break;
                } else {
                    possibleMoves.add(new Square(row+i, column-i));
                    break;
                }
            }
            possibleMoves.add(new Square(row+i, column-i));
        }

        // Row increase, column increase

        int minDistanceFromEight = Math.min(7 - row, 7 - column);

        for (int i = 1; i <= minDistanceFromEight; i++) {
            if(board.hasPiece(row+i, column+i)) {
                if(board.getPieceAt(row+i, column+i).getSide().equals(side)) {
                    break;
                } else {
                    possibleMoves.add(new Square(row+i, column+i));
                    break;
                }
            }
            possibleMoves.add(new Square(row+i, column+i));
        }


        return possibleMoves;
    }

    public String getUnicodeCharacter() {
        if(this.side.equals(Side.WHITE)) {
            return "\u2655";
        } else {
            return "\u265B";
        }
    }

    public String getPieceNotion() {
        if (this.side.equals(Side.WHITE)) {
            return "Q";
        } else {
            return "q";
        }
    }

    public static Piece fromPieceNotation(String pieceNotation) {
        if (pieceNotation.equals("Q")) {
            return new Queen(Side.WHITE);
        } else {
            return new Queen(Side.BLACK);
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
        Queen otherPiece = (Queen) obj;
        return this.hashCode() == otherPiece.hashCode();
    }

    @Override
    public String toString() {
        return getUnicodeCharacter();
    }
}
