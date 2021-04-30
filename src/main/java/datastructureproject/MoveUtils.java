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

    public MoveUtils() {

    }

    public List<Move> getPossibleMovesForPiece(Square startSquare, Piece piece, Board board) {
        switch (piece.getPieceType()) {
            case BISHOP:
                return Bishop.getPossibleMoves(startSquare, board);
            case ROOK:
                return Rook.getPossibleMoves(startSquare, board);
            case PAWN:
                return Pawn.getPossibleMoves(startSquare, piece.getSide(), board);
            case KNIGHT:
                return Knight.getPossibleMoves(startSquare, board);
            case QUEEN:
                return Queen.getPossibleMoves(startSquare, board);
            case KING:
                Side side = board.getPieceSideFromSquare(startSquare);
                Boolean queenSideCastlingAllowed = side.equals(Side.WHITE) ? board.getWhiteQueenSideCastlingAllowed() : board.getBlackQueenSideCastlingAllowed();
                Boolean kingSideCastlingAllowed = side.equals(Side.WHITE) ? board.getWhiteKingSideCastlingAllowed() : board.getBlackKingSideCastlingAllowed();

                return King.getPossibleMoves(startSquare, board, queenSideCastlingAllowed, kingSideCastlingAllowed);
        }
        return null;
    }

    public List<Move> getPossibleAttackingMovesForPiece(Square startSquare, Piece piece, Board board) {
        switch (piece.getPieceType()) {
            case BISHOP:
                return Bishop.getPossibleMoves(startSquare, board);
            case ROOK:
                return Rook.getPossibleMoves(startSquare, board);
            case PAWN:
                return Pawn.getAttackingMoves(startSquare, piece.getSide());
            case KNIGHT:
                return Knight.getPossibleMoves(startSquare, board);
            case QUEEN:
                return Queen.getPossibleMoves(startSquare, board);
            case KING:
                Side side = board.getPieceSideFromSquare(startSquare);
                Boolean queenSideCastlingAllowed = side.equals(Side.WHITE) ? board.getWhiteQueenSideCastlingAllowed() : board.getBlackQueenSideCastlingAllowed();
                Boolean kingSideCastlingAllowed = side.equals(Side.WHITE) ? board.getWhiteKingSideCastlingAllowed() : board.getBlackKingSideCastlingAllowed();
                return King.getPossibleMoves(startSquare, board, queenSideCastlingAllowed, kingSideCastlingAllowed);
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
                for (int i = srcColumn+1; i < 8; i++) {
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
                for (int i = srcRow+1; i < 8; i++) {
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
                    if (board.hasPiece(srcRow-i, srcColumn+i)) {
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
                    if (board.hasPiece(srcRow+i, srcColumn+i)) {
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
                    if (board.hasPiece(srcRow-i, srcColumn-i)) {
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

    // Check if king from the side is attacked
    public Boolean checkIfPositionInvalid(Board board, Side side) {
        Map<Square, Piece> kingSquareMap = board.filterPiecesBySideAndType(side, PieceType.KING);
        Square kingSquare = kingSquareMap.keySet().iterator().next();
        return checkIfSquareAttacked(kingSquare, board, side);
    }

    // Works for single pieces (king, queen)
    public Boolean checkIfPieceIsAttacked(Board board, PieceType pieceType, Side side) {
        Map<Square, Piece> pieceMap = board.filterPiecesBySideAndType(side, pieceType);
        if (pieceMap.size() > 0) {
            Square pieceSquare = pieceMap.keySet().iterator().next();
            return checkIfSquareAttacked(pieceSquare, board, side);
        } else {
            throw new Error("Could not find piece from board!");
        }
    }

    public Boolean checkIfSquareAttacked(Square attackedSquare, Board board, Side side)  {
        int row = attackedSquare.getRow();
        int column = attackedSquare.getColumn();

        Side oppositeSide = side.getOppositeSide();
        Map<Square, Piece> oppositePieces = board.filterPiecesBySide(oppositeSide);

        List<Move> possibleMoves = new ArrayList<>();

        for (Map.Entry<Square, Piece> entry : oppositePieces.entrySet()) {
            Square square = entry.getKey();
            Piece piece = entry.getValue();

            /*

            Caused problems when found a move for pinning opponents piece that tried to check, illegal in chess
            Don't know if should remove entirely from the function or move it later in function

            if(isPieceBlockingCheck(square.getRow(), square.getColumn(), oppositeSide, board)) {
                continue;
            }*/

            List<Move> possibleMovesForPiece = getPossibleAttackingMovesForPiece(square, piece, board);
            possibleMoves.addAll(possibleMovesForPiece);
        }

        for (Move move : possibleMoves) {
            Square square = move.getEndSquare();
            if (row == square.getRow() && column == square.getColumn()) {
                return true;
            }
        }

        return false;
    }


    public Boolean checkIfOwnPiece(int srcRow, int srcColumn, Board board, Side side) {
        Piece piece = board.getPieceAt(srcRow, srcColumn);
        if (piece != null) {
            if (piece.getSide() == side) {
                return true;
            }
        }
        return false;
    }

    public List<Move> getAllPossibleMoves(Board board, Side side) {
        List<Move> possibleMoves = new ArrayList<>();
        Map<Square, Piece> pieceMap = board.filterPiecesBySide(side);

        for (Map.Entry<Square, Piece> entry : pieceMap.entrySet()) {
            Square square = entry.getKey();
            Piece piece = entry.getValue();

            if (!isPieceBlockingCheck(square.getRow(), square.getColumn(), side, board)) {
                List<Move> possibleMovesForPiece = getPossibleMovesForPiece(square, piece, board);
                for (Move possibleMove : possibleMovesForPiece) {
                    Board newBoard = board.copyBoard();
                    newBoard.makeMove(possibleMove);
                    if(!checkIfPositionInvalid(newBoard, side)) {
                        possibleMoves.add(possibleMove);
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
        Side side = piece.getSide();
        Boolean kingSideCastlingAllowed;
        Boolean queenSideCastlingAllowed;
        if (side.equals(Side.WHITE)) {
            kingSideCastlingAllowed = board.getWhiteKingSideCastlingAllowed();
            queenSideCastlingAllowed = board.getWhiteQueenSideCastlingAllowed();
        } else {
            kingSideCastlingAllowed = board.getBlackKingSideCastlingAllowed();
            queenSideCastlingAllowed = board.getWhiteQueenSideCastlingAllowed();
        }
        if (piece.getPieceType().equals(PieceType.KING)) {
            if (side.equals(Side.WHITE)) {
                board.setWhiteKingSideCastlingAllowed(false);
                board.setWhiteQueenSideCastlingAllowed(false);
            } else {
                board.setBlackKingSideCastlingAllowed(false);
                board.setBlackQueenSideCastlingAllowed(false);
            }
        }
        if (piece.getPieceType().equals(PieceType.ROOK) && (kingSideCastlingAllowed || queenSideCastlingAllowed)) {
            if(side.equals(Side.WHITE)) {
                if(startSquare.getRow() == 0 && startSquare.getColumn() == 0) {
                    board.setWhiteQueenSideCastlingAllowed(false);
                } else if(startSquare.getRow() == 0 && startSquare.getColumn() == 7) {
                    board.setWhiteKingSideCastlingAllowed(false);
                }
            } else {
                if(startSquare.getRow() == 7 && startSquare.getColumn() == 0) {
                    board.setBlackKingSideCastlingAllowed(false);
                } else if(startSquare.getRow() == 7 && startSquare.getColumn() == 7) {
                    board.setBlackKingSideCastlingAllowed(false);
                }
            }
        }
        board.makeMove(move);
    }
}
