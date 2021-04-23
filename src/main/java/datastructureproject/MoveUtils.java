package datastructureproject;

import datastructureproject.board.Board;
import datastructureproject.board.Move;
import datastructureproject.board.Square;
import datastructureproject.exceptions.PieceNotFoundOnBoardException;
import datastructureproject.pieces.*;
import datastructureproject.pieces.Piece;
import datastructureproject.pieces.PieceType;
import datastructureproject.pieces.Side;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveUtils {

    private Side side;
    private Boolean kingSideCastlingAllowed = true;
    private Boolean queenSideCastlingAllowed = true;

    public MoveUtils() {

    }
    public MoveUtils(Side side) {
        this.side = side;
    }

    public Side getSide() {
        return this.side;
    }
    public void setSide(Side side) {
        this.side = side;
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


    public List<Square> getPossibleMovesForPiece(int row, int column, Piece piece, Board board) {
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
                return King.getPossibleMoves(row, column, board, queenSideCastlingAllowed, kingSideCastlingAllowed);
        }
        return null;
    }

    public List<Square> getPossibleAttackingMovesForPiece(int row, int column, Piece piece, Board board) {
        switch (piece.getPieceType()) {
            case BISHOP:
                return Bishop.getPossibleMoves(row, column, board);
            case ROOK:
                return Rook.getPossibleMoves(row, column, board);
            case PAWN:
                return Pawn.getAttackingMoves(row, column, piece.getSide());
            case KNIGHT:
                return Knight.getPossibleMoves(row, column, board);
            case QUEEN:
                return Queen.getPossibleMoves(row, column, board);
            case KING:
                return King.getPossibleMoves(row, column, board, queenSideCastlingAllowed, kingSideCastlingAllowed);
        }
        return null;
    }

    public Boolean isPieceBlockingCheck(int srcRow, int srcColumn, Side side, Board board) {
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

    public Boolean checkIfPositionInvalid(Board board, Side side) {
        Map<Square, Piece> kingSquareMap = board.filterPiecesBySideAndType(side, PieceType.KING);
        Square kingSquare = kingSquareMap.keySet().iterator().next();
        return checkIfSquareAttacked(kingSquare.getRow(), kingSquare.getColumn(), board, side);
    }

    public Boolean checkIfSquareAttacked(int row, int column, Board board, Side side)  {
        Side oppositeSide = side.getOppositeSide();

        Map<Square, Piece> oppositePieces = board.filterPiecesBySide(oppositeSide);

        List<Square> possibleMoves = new ArrayList<>();

        for (Map.Entry<Square, Piece> entry : oppositePieces.entrySet()) {
            Square square = entry.getKey();
            Piece piece = entry.getValue();

            if(isPieceBlockingCheck(square.getRow(), square.getColumn(), oppositeSide, board)) {
                continue;
            }

            possibleMoves.addAll(getPossibleAttackingMovesForPiece(square.getRow(), square.getColumn(), piece, board));
        }

        for (Square square : possibleMoves) {
            if (row == square.getRow() && column == square.getColumn()) {
                return true;
            }
        }

        return false;
    }


    public Boolean checkIfOwnPiece(int srcRow, int srcColumn, Board board) {
        Piece piece = board.getPieceAt(srcRow, srcColumn);
        if (piece != null) {
            if (piece.getSide() == side) {
                return true;
            }
        }
        return Boolean.FALSE;
    }

    public List<Move> getAllPossibleMoves(Board board) {

        List<Move> possibleMoves = new ArrayList<>();
        Map<Square, Piece> pieceMap = board.filterPiecesBySide(this.side);

        for (Map.Entry<Square, Piece> entry : pieceMap.entrySet()) {
            Square square = entry.getKey();
            Piece piece = entry.getValue();

            if (!isPieceBlockingCheck(square.getRow(), square.getColumn(), this.side, board)) {
                List<Square> possibleMovesForPiece = getPossibleMovesForPiece(square.getRow(), square.getColumn(), piece, board);
                for (Square endSquare : possibleMovesForPiece) {
                    Board newBoard = board.copyBoard();
                    newBoard.movePiece(square.getRow(), square.getColumn(), endSquare.getRow(), endSquare.getColumn());
                    if(!checkIfPositionInvalid(newBoard, this.side)) {
                        System.out.println(newBoard);
                        possibleMoves.add(new Move(square, endSquare));
                    }
                }

            } else {
                System.out.printf("Piece at row %s column %s blocking check%n", square.getRow(), square.getColumn());
            }
        }

        return possibleMoves;
    }

    public void makeMove(Move move, Board board) {
        Square startSquare = move.getStartSquare();
        Square endSquare = move.getEndSquare();
        Piece piece = board.getPieceAt(startSquare.getRow(), startSquare.getColumn());
        if (piece.getPieceType().equals(PieceType.KING)) {
            this.kingSideCastlingAllowed = false;
            this.queenSideCastlingAllowed = false;
        }
        if (piece.getPieceType().equals(PieceType.ROOK) && (this.kingSideCastlingAllowed || this.queenSideCastlingAllowed)) {
            if(this.side.equals(Side.WHITE)) {
                if(startSquare.getRow() == 0 && startSquare.getColumn() == 0) {
                    this.queenSideCastlingAllowed = false;
                } else if(startSquare.getRow() == 0 && startSquare.getColumn() == 7) {
                    this.kingSideCastlingAllowed = false;
                }
            } else {
                if(startSquare.getRow() == 7 && startSquare.getColumn() == 0) {
                    this.queenSideCastlingAllowed = false;
                } else if(startSquare.getRow() == 7 && startSquare.getColumn() == 7) {
                    this.kingSideCastlingAllowed = false;
                }
            }
        }
        board.movePiece(startSquare.getRow(), startSquare.getColumn(), endSquare.getRow(), endSquare.getColumn());
    }
}
