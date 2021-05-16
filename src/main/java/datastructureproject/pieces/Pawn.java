package datastructureproject.pieces;

import datastructureproject.board.Board;
import datastructureproject.board.Move;
import datastructureproject.board.Square;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {

    private final PieceType pieceType = PieceType.PAWN;
    private final Side side;

    public Pawn(Side side) {
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
    public static List<Move> getPossibleMoves(Square startSquare, Side side) {
        int row = startSquare.getRow();
        int column = startSquare.getColumn();
        ArrayList<Move> possibleMoves = new ArrayList<>();

        if (side == Side.WHITE) {
            if (7 - row >= 1) {

                possibleMoves.add(new Move(startSquare, new Square(row + 1, column)));

                if (column > 0) {
                    possibleMoves.add(new Move(startSquare, new Square(row + 1, column - 1)));
                }

                if (column < 7) {
                    possibleMoves.add(new Move(startSquare, new Square(row + 1, column + 1)));
                }
            }

            if (row == 1) {
                possibleMoves.add(new Move(startSquare, new Square(row + 2, column)));
            }
        } else {
            if (row >= 1) {

                possibleMoves.add(new Move(startSquare, new Square(row - 1, column)));

                if (column > 0) {
                    possibleMoves.add(new Move(startSquare, new Square(row - 1, column - 1)));
                }

                if (column < 7) {
                    possibleMoves.add(new Move(startSquare, new Square(row - 1, column + 1)));
                }
            }

            if (row == 6) {
                possibleMoves.add(new Move(startSquare, new Square(row - 2, column)));
            }
        }

        return possibleMoves;

    }

    // Does consider board positions, but not situations where the piece can't move because of pinning etc.
    public static List<Move> getPossibleMoves(Square startSquare, Side side, Board board) {
        int row = startSquare.getRow();
        int column = startSquare.getColumn();

        List<Piece> promotionPieces = new ArrayList<>(List.of(new Queen(side), new Knight(side)));

        ArrayList<Move> possibleMoves = new ArrayList<>();

        if (side == Side.WHITE) {
            if (7 - row >= 1) {
                // Next move promotes
                if (7 - row == 1) {
                    if (!board.hasPiece(row + 1, column)) {
                        for (Piece promotionPiece : promotionPieces) {
                            possibleMoves.add(new Move(startSquare, new Square(row + 1, column), promotionPiece));
                        }
                    }

                    if (column > 0 && board.hasPiece(row + 1, column - 1) && board.getPieceAt(row + 1, column - 1).getSide().getOppositeSide() == side) {
                        for (Piece promotionPiece : promotionPieces) {
                            possibleMoves.add(new Move(startSquare, new Square(row + 1, column - 1), promotionPiece));
                        }
                    }

                    if (column < 7 && board.hasPiece(row + 1, column + 1) && board.getPieceAt(row + 1, column + 1).getSide().getOppositeSide() == side) {
                        for (Piece promotionPiece : promotionPieces) {
                            possibleMoves.add(new Move(startSquare, new Square(row + 1, column + 1), promotionPiece));
                        }
                    }
                } else {
                    if (!board.hasPiece(row + 1, column)) {
                        possibleMoves.add(new Move(startSquare, new Square(row + 1, column)));
                    }

                    if (column > 0 && board.hasPiece(row + 1, column - 1) && board.getPieceAt(row + 1, column - 1).getSide().getOppositeSide() == side) {
                        possibleMoves.add(new Move(startSquare, new Square(row + 1, column - 1)));
                    }

                    if (column < 7 && board.hasPiece(row + 1, column + 1) && board.getPieceAt(row + 1, column + 1).getSide().getOppositeSide() == side) {
                        possibleMoves.add(new Move(startSquare, new Square(row + 1, column + 1)));
                    }
                }
            }

            if (row == 1 && !board.hasPiece(row + 1, column) && !board.hasPiece(row + 2, column)) {
                possibleMoves.add(new Move(startSquare, new Square(row + 2, column)));
            }
        } else {
            if (row >= 1) {
                // Next move promotes
                if (row == 1) {
                    if (!board.hasPiece(row - 1, column)) {
                        for (Piece promotionPiece : promotionPieces) {
                            possibleMoves.add(new Move(startSquare, new Square(row - 1, column), promotionPiece));
                        }
                    }
                    if (column > 0 && board.hasPiece(row - 1, column - 1) && board.getPieceAt(row - 1, column - 1).getSide().getOppositeSide() == side) {
                        for (Piece promotionPiece : promotionPieces) {
                            possibleMoves.add(new Move(startSquare, new Square(row - 1, column - 1), promotionPiece));
                        }
                    }

                    if (column < 7 && board.hasPiece(row - 1, column + 1) && board.getPieceAt(row - 1, column + 1).getSide().getOppositeSide() == side) {
                        for (Piece promotionPiece : promotionPieces) {
                            possibleMoves.add(new Move(startSquare, new Square(row - 1, column + 1), promotionPiece));
                        }
                    }
                } else {
                    if (!board.hasPiece(row - 1, column)) {
                        possibleMoves.add(new Move(startSquare, new Square(row - 1, column)));
                    }
                    if (column > 0 && board.hasPiece(row - 1, column - 1) && board.getPieceAt(row - 1, column - 1).getSide().getOppositeSide() == side) {
                        possibleMoves.add(new Move(startSquare, new Square(row - 1, column - 1)));
                    }

                    if (column < 7 && board.hasPiece(row - 1, column + 1) && board.getPieceAt(row - 1, column + 1).getSide().getOppositeSide() == side) {
                        possibleMoves.add(new Move(startSquare, new Square(row - 1, column + 1)));
                    }
                }
            }

            if (row == 6 && !board.hasPiece(row - 1, column) && !board.hasPiece(row - 2, column)) {
                possibleMoves.add(new Move(startSquare, new Square(row - 2, column)));
            }
        }

        return possibleMoves;
    }

    // Necessary for checking if square is attacked in MoveUtils
    public static List<Move> getAttackingMoves(Square startSquare, Side side) {
        int row = startSquare.getRow();
        int column = startSquare.getColumn();

        ArrayList<Move> possibleMoves = new ArrayList<>();

        if (side == Side.WHITE) {
            if (7 - row >= 1) {
                if (column > 0) {
                    possibleMoves.add(new Move(startSquare, new Square(row + 1, column - 1)));
                }

                if (column < 7) {
                    possibleMoves.add(new Move(startSquare, new Square(row + 1, column + 1)));
                }
            }

        } else {
            if (row >= 1) {
                if (column > 0) {
                    possibleMoves.add(new Move(startSquare, new Square(row - 1, column - 1)));
                }

                if (column < 7) {
                    possibleMoves.add(new Move(startSquare, new Square(row - 1, column + 1)));
                }
            }
        }

        return possibleMoves;
    }

    public String getUnicodeCharacter() {
        if (this.side.equals(Side.WHITE)) {
            return "\u2659";
        } else {
            return "\u265F";
        }
    }

    public String getPieceNotion() {
        if (this.side.equals(Side.WHITE)) {
            return "P";
        } else {
            return "p";
        }
    }

    public static Piece fromPieceNotation(String pieceNotation) {
        if (pieceNotation.equals("P")) {
            return new Pawn(Side.WHITE);
        } else {
            return new Pawn(Side.BLACK);
        }
    }

    public int zobristIndex() {
        if (this.side.equals(Side.WHITE)) {
            return 1;
        } else {
            return 7;
        }
    }

    public double pieceValue() {
        if (this.side.equals(Side.WHITE)) {
            return 100;
        } else {
            return -100;
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
        Pawn otherPiece = (Pawn) obj;
        return this.hashCode() == otherPiece.hashCode();
    }

    @Override
    public String toString() {
        return getUnicodeCharacter();
    }
}
