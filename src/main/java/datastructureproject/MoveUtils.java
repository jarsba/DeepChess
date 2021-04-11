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
    private Side side = Side.WHITE;
    private Boolean kingSideCastlingAllowed = true;
    private Boolean queenSideCastlingAllowed = true;

    public MoveUtils() {

    }

    public Board getBoard() {
        return this.board;
    }

    public Boolean getKingSideCastlingAllowed() {
        return this.kingSideCastlingAllowed;
    }

    public void setKingSideCastlingAllowed(Boolean value) {
        this.kingSideCastlingAllowed = value;
    }

    public Boolean getQueenSideCastlingAllowed() {
        return this.queenSideCastlingAllowed;
    }

    public void setQueenSideCastlingAllowed(Boolean value) {
        this.queenSideCastlingAllowed = value;
    }



    public List<Square> getPossibleMoves(int row, int column, Piece piece) {
        switch (piece.getPieceType()) {
            case BISHOP:
                return Bishop.getPossibleMoves(row,column, board);
            case ROOK:
                return Rook.getPossibleMoves(row, column, this);
            case PAWN:
                return Pawn.getPossibleMoves(row, column, piece.getSide(), this);
            case KNIGHT:
                return Knight.getPossibleMoves(row, column, board);
            case QUEEN:
                return Queen.getPossibleMoves(row, column, board);
            case KING:
                return King.getPossibleMoves(row, column, this, queenSideCastlingAllowed, kingSideCastlingAllowed);
        }
        return null;
    }

    public List<Square> getPossibleAttackingMoves(int row, int column, Piece piece) {
        switch (piece.getPieceType()) {
            case BISHOP:
                return Bishop.getPossibleMoves(row, column, board);
            case ROOK:
                return Rook.getPossibleMoves(row, column, this);
            case PAWN:
                return Pawn.getAttackingMoves(row, column, piece.getSide());
            case KNIGHT:
                return Knight.getPossibleMoves(row, column, board);
            case QUEEN:
                return Queen.getPossibleMoves(row, column, board);
            case KING:
                return King.getPossibleMoves(row, column, this, queenSideCastlingAllowed, kingSideCastlingAllowed);
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

    public Boolean isBlockingCheck(int srcRow, int srcColumn, Side side) {
        Map<Square, Piece> kingSquareMap = board.filterPiecesBySideAndType(side, PieceType.KING);
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

        // Check for pieces that can attack on the same row
        if (srcRow == kingRow) {
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
        // Check for pieces that can attack on the same column, identical to previous case except on different axis
        } else if (srcColumn == kingColumn) {

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

        // Check for pieces that can attack on the descending diagonal
        } else if (srcRow+srcColumn == kingRow+kingColumn) {
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

        // Check for pieces that can attack on the ascending diagonal
        } else if (Math.abs(srcRow-kingRow) == Math.abs(srcColumn-kingColumn)) {
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

    public Boolean checkIfPositionInvalid(Board board) {
        Map<Square, Piece> kingSquareMap = board.filterPiecesBySideAndType(side, PieceType.KING);
        Square kingSquare = kingSquareMap.keySet().stream().iterator().next();
        return checkIfSquareAttacked(kingSquare.getRow(), kingSquare.getColumn());
    }

    public Boolean checkIfSquareAttacked(int row, int column)  {
        Side oppositeSide = side.getOppositeSide();
        Board boardCopy = new Board(this.board.getPositions());
        boardCopy.removePieceAt(row, column);
        Map<Square, Piece> oppositePieces = boardCopy.filterPiecesBySide(oppositeSide);

        List<Square> possibleMoves = new ArrayList<>();

        for (Map.Entry<Square, Piece> entry : oppositePieces.entrySet()) {
            Square square = entry.getKey();
            Piece piece = entry.getValue();

            if(isBlockingCheck(square.getRow(), square.getColumn(), oppositeSide)) {
                continue;
            }

            possibleMoves.addAll(getPossibleAttackingMoves(square.getRow(), square.getColumn(), piece));
        }

        for (Square square : possibleMoves) {
            if (row == square.getRow() && column == square.getColumn()) {
                return true;
            }
        }

        return false;
    }



    public Boolean checkIfOwnPiece(int srcRow, int srcColumn) {
        Piece piece = board.getPieceAt(srcRow, srcColumn);
        if (piece != null) {
            if (piece.getSide() == side) {
                return true;
            }
        }
        return Boolean.FALSE;
    }

    public void endTurn() {

    }
}
