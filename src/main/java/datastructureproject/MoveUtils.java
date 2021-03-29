package datastructureproject;

import datastructureproject.board.Board;
import datastructureproject.board.Square;
import datastructureproject.exceptions.*;
import datastructureproject.pieces.*;
import datastructureproject.pieces.Piece;
import datastructureproject.pieces.PieceType;
import datastructureproject.pieces.Side;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoveUtils {

    private Board board = new Board();
    private Side turn = Side.WHITE;

    public MoveUtils() {

    }

    public List<Square> getPossibleMoves(int row, int column, Piece piece) {
        switch (piece.getPieceType()) {
            case BISHOP:
                return Bishop.getPossibleMoves(row,column, board);
            case ROOK:
                return Rook.getPossibleMoves(row, column, board);
            case PAWN:
                return Pawn.getPossibleMoves(row, column, piece.getSide(), board);
            case KNIGHT:
                return Knight.getPossibleMoves(row, column, board);
            case QUEEN:
                return Queen.getPossibleMoves(row, column, board);
            case KING:
                return King.getPossibleMoves(row, column, board);
        }

        return null;
    }

    public List<Square> getPossibleMoves(int row, int column) {
        Piece piece = board.getPieceAt(row, column);

        if (piece == null) {
            return null;
        } else {
            return getPossibleMoves(row, column, piece);
        }
    }

    public Boolean isBlockingCheck(int srcRow, int srcColumn, Side side) throws TooManyPiecesOnBoard, PieceNotFoundOnBoard {
        Map<Square, Piece> kingSquareMap = board.filterPiecesBySideAndType(side, PieceType.KING);
        if (kingSquareMap.keySet().size() == 0) {
            throw new PieceNotFoundOnBoard("Couldn't find a king on the board!");
        } else if (kingSquareMap.keySet().size() > 1) {
            throw new TooManyPiecesOnBoard("Found too many kings on the board!");
        }

        Square kingSquare = kingSquareMap.keySet().stream().iterator().next();

        int kingRow = kingSquare.getRow();
        int kingColumn = kingSquare.getColumn();

        if (kingRow == srcRow && kingColumn == srcColumn) {
            return false;
        }

        List<Square> possibleSquares = getSquaresBetween(srcRow, srcColumn, kingRow, kingColumn);

        // King is not "on the line" and king is not next to the piece
        if (possibleSquares.size() == 0 && (Math.abs(srcRow-kingRow) > 1 || Math.abs(srcColumn-kingColumn) > 1)) {
            return false;
        }

        // Check if any other pieces between the line before checking if any piece is attacking
        for (Square square : possibleSquares) {
            Boolean hasPiece = board.hasPiece(square.getRow(), square.getColumn());
            if (hasPiece) {
                return false;
            }
        }

        // Check if attacking piece found on the line
        if (srcRow == kingRow) {

            // Check for pieces that can attack on the same row
            if (srcColumn > kingColumn) {
                for (int i = srcColumn+1; i <= 8; i++) {
                    if(board.hasPiece(srcRow, i)) {
                        Piece piece = board.getPieceAt(srcRow, i);
                        if (piece.getSide().equals(side)) {
                            break;
                        } else {
                            PieceType pieceType = piece.getPieceType();
                            if(pieceType.equals(PieceType.QUEEN) || pieceType.equals(PieceType.ROOK)) {
                                return true;
                            } else {
                                break;
                            }
                        }
                    }
                }
            } else {
                for (int i = srcColumn-1; i >= 0; i--) {
                    if(board.hasPiece(srcRow, i)) {
                        Piece piece = board.getPieceAt(srcRow, i);
                        if (piece.getSide().equals(side)) {
                            break;
                        } else {
                            PieceType pieceType = piece.getPieceType();
                            if(pieceType.equals(PieceType.QUEEN) || pieceType.equals(PieceType.ROOK)) {
                                return true;
                            } else {
                                break;
                            }
                        }
                    }
                }

            }
        } else if (srcColumn == kingColumn) {

            // Check for pieces that can attack on the same column, identical to previous case except on different axis
            if (srcRow > kingRow) {
                for (int i = srcRow+1; i <= 8; i++) {
                    if(board.hasPiece(i, srcColumn)) {
                        Piece piece = board.getPieceAt(i, srcColumn);
                        if (piece.getSide().equals(side)) {
                            break;
                        } else {
                            PieceType pieceType = piece.getPieceType();
                            if(pieceType.equals(PieceType.QUEEN) || pieceType.equals(PieceType.ROOK)) {
                                return true;
                            } else {
                                break;
                            }
                        }
                    }
                }
            } else {
                for (int i = srcRow - 1; i >= 0; i--) {
                    if (board.hasPiece(i, srcColumn)) {
                        Piece piece = board.getPieceAt(i, srcColumn);
                        if (piece.getSide().equals(side)) {
                            break;
                        } else {
                            PieceType pieceType = piece.getPieceType();
                            if (pieceType.equals(PieceType.QUEEN) || pieceType.equals(PieceType.ROOK)) {
                                return true;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }

        } else if (srcRow+srcColumn == kingRow+kingColumn) {

            // Check for pieces that can attack on the descending diagonal
            if (srcRow > kingRow) {
                int minDistanceFromLimit = Math.min(Math.abs(srcRow-7), srcColumn);
                for (int i = 1; i <= minDistanceFromLimit; i++) {
                    if (board.hasPiece(srcRow+i, srcColumn-i)) {
                        Piece piece = board.getPieceAt(srcRow+i, srcColumn-i);
                        if (piece.getSide().equals(side)) {
                            break;
                        } else {
                            PieceType pieceType = piece.getPieceType();
                            if (pieceType.equals(PieceType.QUEEN) || pieceType.equals(PieceType.BISHOP)) {
                                return true;
                            } else {
                                break;
                            }
                        }
                    }
                }
            } else {
                int minDistanceFromLimit = Math.min(srcRow, Math.abs(srcColumn-7));
                for (int i = 1; i <= minDistanceFromLimit; i++) {
                    if (board.hasPiece(srcRow-i, srcColumn+1)) {
                        Piece piece = board.getPieceAt(srcRow-i, srcColumn+i);
                        if (piece.getSide().equals(side)) {
                            break;
                        } else {
                            PieceType pieceType = piece.getPieceType();
                            if (pieceType.equals(PieceType.QUEEN) || pieceType.equals(PieceType.BISHOP)) {
                                return true;
                            } else {
                                break;
                            }
                        }
                    }
                }

            }

        } else if (Math.abs(srcRow-kingRow) == Math.abs(srcColumn-kingColumn)) {

            // Check for pieces that can attack on the ascending diagonal
            if (srcRow > kingRow) {
                int minDistanceFromLimit = Math.min((7-srcRow), (7-srcColumn));
                for (int i = 1; i <= minDistanceFromLimit; i++) {
                    if (board.hasPiece(srcRow+i, srcColumn+1)) {
                        Piece piece = board.getPieceAt(srcRow+i, srcColumn+i);
                        if (piece.getSide().equals(side)) {
                            break;
                        } else {
                            PieceType pieceType = piece.getPieceType();
                            if (pieceType.equals(PieceType.QUEEN) || pieceType.equals(PieceType.BISHOP)) {
                                return true;
                            } else {
                                break;
                            }
                        }
                    }
                }
            } else {
                int minDistanceFromLimit = Math.min(srcRow, srcColumn);
                for (int i = 1; i <= minDistanceFromLimit; i++) {
                    if (board.hasPiece(srcRow-i, srcColumn-1)) {
                        Piece piece = board.getPieceAt(srcRow-i, srcColumn-i);
                        if (piece.getSide().equals(side)) {
                            break;
                        } else {
                            PieceType pieceType = piece.getPieceType();
                            if (pieceType.equals(PieceType.QUEEN) || pieceType.equals(PieceType.BISHOP)) {
                                return true;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public List<Square> getSquaresBetween(int srcRow, int srcColumn, int destRow, int destColumn) {

        List<Square> squaresBetween = new ArrayList<>();

        // Pieces on same row
        if (srcRow == destRow) {
            for (int i = Math.min(srcColumn, destColumn)+1; i < Math.max(srcColumn, destColumn); i++ ) {
                squaresBetween.add(new Square(srcRow, i));
            }
            return squaresBetween;
        }

        // Pieces on same column
        if (srcColumn == destColumn) {
            for (int i = Math.min(srcRow, destRow)+1; i < Math.max(srcRow, destRow); i++ ) {
                squaresBetween.add(new Square(i, srcColumn));
            }
            return squaresBetween;
        }

        // Pieces on descending diagonal
        if (srcColumn+srcRow == destColumn+destRow) {
            int newColumn = Math.max(srcColumn, destColumn)-1;
            for (int i = Math.min(srcRow, destRow)+1; i < Math.max(srcRow, destRow); i++ ) {
                squaresBetween.add(new Square(i, newColumn));
                newColumn--;
            }
            return squaresBetween;
        }

        // Pieces on ascending diagonal
        if (Math.abs(srcRow-destRow) == Math.abs(srcColumn-destColumn)) {
            int newColumn = Math.min(srcColumn, destColumn)+1;
            for (int i = Math.min(srcRow, destRow); i < Math.max(srcRow, destRow); i++) {
                squaresBetween.add(new Square(i, newColumn));
                newColumn++;
            }
            return squaresBetween;
        }

        return squaresBetween;
    }

    public List<Square> getProtectorPieces(int row, int column, Side side) {
        // TODO: finish function
        List<Square> squareList = new ArrayList<>();
        return squareList;
    }

    public Boolean checkIfPieceIsProtected(int row, int column) {
        // TODO: finish function
        Side side = board.getPieceAt(row, column).getSide();

        return false;
    }

    public Boolean checkMove(int srcRow, int srcColumn, int destRow, int destColumn) {
        // TODO: finish
        return Boolean.TRUE;
    }

    public Boolean checkIfOwnPiece(int srcRow, int srcColumn) {
        Piece piece = board.getPieceAt(srcRow, srcColumn);
        if (piece != null) {
            if (piece.getSide() == turn) {
                return true;
            }
        }
        return Boolean.FALSE;
    }


    public void endTurn() {

    }
}
