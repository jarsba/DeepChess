package datastructureproject.board;

import datastructureproject.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private Piece[][] positions;
    private Boolean whiteKingSideCastlingAllowed = true;
    private Boolean whiteQueenSideCastlingAllowed = true;
    private Boolean blackKingSideCastlingAllowed = true;
    private Boolean blackQueenSideCastlingAllowed = true;

    public Boolean getWhiteKingSideCastlingAllowed() {
        return whiteKingSideCastlingAllowed;
    }

    public void setWhiteKingSideCastlingAllowed(Boolean whiteKingSideCastlingAllowed) {
        this.whiteKingSideCastlingAllowed = whiteKingSideCastlingAllowed;
    }

    public Boolean getWhiteQueenSideCastlingAllowed() {
        return whiteQueenSideCastlingAllowed;
    }

    public void setWhiteQueenSideCastlingAllowed(Boolean whiteQueenSideCastlingAllowed) {
        this.whiteQueenSideCastlingAllowed = whiteQueenSideCastlingAllowed;
    }

    public Boolean getBlackKingSideCastlingAllowed() {
        return blackKingSideCastlingAllowed;
    }

    public void setBlackKingSideCastlingAllowed(Boolean blackKingSideCastlingAllowed) {
        this.blackKingSideCastlingAllowed = blackKingSideCastlingAllowed;
    }

    public Boolean getBlackQueenSideCastlingAllowed() {
        return blackQueenSideCastlingAllowed;
    }

    public void setBlackQueenSideCastlingAllowed(Boolean blackQueenSideCastlingAllowed) {
        this.blackQueenSideCastlingAllowed = blackQueenSideCastlingAllowed;
    }

    public Board() {
        positions = new Piece[8][8];
        initializePositions();
    }

    public Board(Piece[][] boardPositions, Boolean whiteKingSideCastlingAllowed, Boolean whiteQueenSideCastlingAllowed, Boolean blackKingSideCastlingAllowed, Boolean blackQueenSideCastlingAllowed) {
        this.positions = boardPositions;
        this.whiteKingSideCastlingAllowed = whiteKingSideCastlingAllowed;
        this.whiteQueenSideCastlingAllowed = whiteQueenSideCastlingAllowed;
        this.blackKingSideCastlingAllowed = blackKingSideCastlingAllowed;
        this.blackQueenSideCastlingAllowed = blackQueenSideCastlingAllowed;
    }

    public Piece[][] getPositions() {
        return positions;
    }

    public void initializePositions() {
        this.setPieceAt(0, 0, new Rook(Side.WHITE));
        this.setPieceAt(0, 1, new Knight(Side.WHITE));
        this.setPieceAt(0, 2, new Bishop(Side.WHITE));
        this.setPieceAt(0, 3, new Queen(Side.WHITE));
        this.setPieceAt(0, 4, new King(Side.WHITE));
        this.setPieceAt(0, 5, new Bishop(Side.WHITE));
        this.setPieceAt(0, 6, new Knight(Side.WHITE));
        this.setPieceAt(0, 7, new Rook(Side.WHITE));

        this.setPieceAt(7, 0, new Rook(Side.BLACK));
        this.setPieceAt(7, 1, new Knight(Side.BLACK));
        this.setPieceAt(7, 2, new Bishop(Side.BLACK));
        this.setPieceAt(7, 3, new Queen(Side.BLACK));
        this.setPieceAt(7, 4, new King(Side.BLACK));
        this.setPieceAt(7, 5, new Bishop(Side.BLACK));
        this.setPieceAt(7, 6, new Knight(Side.BLACK));
        this.setPieceAt(7, 7, new Rook(Side.BLACK));

        for (int column = 0; column < 8; column++) {
            this.setPieceAt(1, column, new Pawn(Side.WHITE));
        }

        for (int column = 0; column < 8; column++) {
            this.setPieceAt(6, column, new Pawn(Side.BLACK));
        }
    }

    public Piece getPieceAt(int row, int column) {
        return this.positions[row][column];
    }

    public Boolean hasPiece(int row, int column) {
        if(Square.isValidPosition(row, column)) {
            return this.positions[row][column] != null;
        } else {
            throw new Error(String.format("Tried to get piece outside the board: row %s, column %s", row, column));
        }
    }

    public Side getPieceSideFromSquare(Square square) {
        int row = square.getRow();
        int column = square.getColumn();
        Piece piece = getPieceAt(row, column);
        if(piece == null) {
            throw new Error(String.format("Couldn't find a piece: row %s, column %s", row, column));
        } else {
            return piece.getSide();
        }
    }

    public void setPieceAt(int row, int column, Piece piece) {
        this.positions[row][column] = piece;
    }

    public void removePieceAt(int row, int column) {
        this.positions[row][column] = null;
    }

    public void movePiece(int srcRow, int srcColumn, int destRow, int destColumn) {
        Piece pieceToMove = getPieceAt(srcRow, srcColumn);
        if (pieceToMove != null) {
            this.setPieceAt(destRow, destColumn, pieceToMove);
            this.removePieceAt(srcRow, srcColumn);
        } else {
            throw new Error("Piece was not found from the board!");
        }
    }

    public void makeMove(Move move) {
        Square startSquare = move.getStartSquare();
        Square endSquare = move.getEndSquare();

        if(this.checkIfCastlingMove(move)) {
            this.makeCastlingMove(move);
        } else {
            this.movePiece(startSquare.getRow(), startSquare.getColumn(), endSquare.getRow(), endSquare.getColumn());
            if(move.getPromotionPiece() != null) {
                this.setPieceAt(endSquare.getRow(), endSquare.getColumn(), move.getPromotionPiece());
            }
        }
    }

    public void makeCastlingMove(Move move) {
        Square startSquare = move.getStartSquare();
        Square endSquare = move.getEndSquare();

        if(startSquare.getRow() == 0 && startSquare.getColumn() == 4 && endSquare.getRow() == 0 && endSquare.getColumn() == 2) {
            Piece kingPiece = getPieceAt(0, 4);
            Piece rookPiece = getPieceAt(0, 0);
            if (!this.hasPiece(0, 1) && !this.hasPiece(0, 2) && !this.hasPiece(0, 3) && kingPiece.getPieceType() == PieceType.KING && kingPiece.getSide() == Side.WHITE && rookPiece.getPieceType() == PieceType.ROOK && rookPiece.getSide() == Side.WHITE) {
                this.movePiece(startSquare.getRow(), startSquare.getColumn(), endSquare.getRow(), endSquare.getColumn());
                this.movePiece(0, 0, 0, 3);
            } else {
                throw new Error(String.format("Cannot castle move %s", move));
            }
        } else if (startSquare.getRow() == 0 && startSquare.getColumn() == 4 && endSquare.getRow() == 0 && endSquare.getColumn() == 6) {
            Piece kingPiece = getPieceAt(0, 4);
            Piece rookPiece = getPieceAt(0, 7);
            if (!this.hasPiece(0, 5) && !this.hasPiece(0, 6) && kingPiece.getPieceType() == PieceType.KING && kingPiece.getSide() == Side.WHITE && rookPiece.getPieceType() == PieceType.ROOK && rookPiece.getSide() == Side.WHITE) {
                this.movePiece(startSquare.getRow(), startSquare.getColumn(), endSquare.getRow(), endSquare.getColumn());
                this.movePiece(0, 7, 0, 5);
            } else {
                throw new Error(String.format("Cannot castle move %s", move));
            }
        } else if (startSquare.getRow() == 7 && startSquare.getColumn() == 4 && endSquare.getRow() == 7 && endSquare.getColumn() == 2) {
            Piece kingPiece = getPieceAt(7, 4);
            Piece rookPiece = getPieceAt(7, 0);
            if (!this.hasPiece(7, 1) && !this.hasPiece(7, 2) && !this.hasPiece(7, 3) && kingPiece.getPieceType() == PieceType.KING && kingPiece.getSide() == Side.BLACK && rookPiece.getPieceType() == PieceType.ROOK && rookPiece.getSide() == Side.BLACK) {
                this.movePiece(startSquare.getRow(), startSquare.getColumn(), endSquare.getRow(), endSquare.getColumn());
                this.movePiece(7, 0, 7, 3);
            } else {
                throw new Error(String.format("Cannot castle move %s", move));
            }
        } else if (startSquare.getRow() == 7 && startSquare.getColumn() == 4 && endSquare.getRow() == 7 && endSquare.getColumn() == 6) {
            Piece kingPiece = getPieceAt(7, 4);
            Piece rookPiece = getPieceAt(7, 7);
            if (!this.hasPiece(7, 5) && !this.hasPiece(7, 6) && kingPiece.getPieceType() == PieceType.KING && kingPiece.getSide() == Side.BLACK && rookPiece.getPieceType() == PieceType.ROOK && rookPiece.getSide() == Side.BLACK) {
                this.movePiece(startSquare.getRow(), startSquare.getColumn(), endSquare.getRow(), endSquare.getColumn());
                this.movePiece(7, 7, 7, 5);
            } else {
                throw new Error(String.format("Cannot castle move %s", move));
            }
        } else {
            System.out.println(this);
            throw new Error(String.format("Cannot castle move %s", move));
        }
    }

    public Boolean checkIfCastlingMove(Move move) {
        Square startSquare = move.getStartSquare();
        Square endSquare = move.getEndSquare();
        Piece piece = this.getPieceAt(startSquare.getRow(), startSquare.getColumn());

        if(piece.getPieceType().equals(PieceType.KING)) {
            if(startSquare.getRow() == 0 && startSquare.getColumn() == 4 && endSquare.getRow() == 0 && endSquare.getColumn() == 2) {
                return true;
            } else if (startSquare.getRow() == 0 && startSquare.getColumn() == 4 && endSquare.getRow() == 0 && endSquare.getColumn() == 6) {
                return true;
            } else if (startSquare.getRow() == 7 && startSquare.getColumn() == 4 && endSquare.getRow() == 7 && endSquare.getColumn() == 2) {
                return true;
            } else if (startSquare.getRow() == 7 && startSquare.getColumn() == 4 && endSquare.getRow() == 7 && endSquare.getColumn() == 6) {
                return true;
            }
        }

        return false;

    }

    public void makePromotionMove(Move move, Piece promoteTo) {
        Square startSquare = move.getStartSquare();
        Square endSquare = move.getEndSquare();
        this.movePiece(startSquare.getRow(), startSquare.getColumn(), endSquare.getRow(), endSquare.getColumn());
        this.setPieceAt(endSquare.getRow(), endSquare.getColumn(), promoteTo);
    }


    public Map<Square, Piece> filterPiecesBySide(Side side) {

        Map<Square, Piece> filteredPieces = new HashMap<>();

        for (int row = 0; row < this.getPositions().length; row++) {
            for (int column = 0; column < this.getPositions()[0].length; column++) {
                Piece piece = this.getPieceAt(row, column);
                if (piece != null) {
                    if (piece.getSide() == side) {
                        filteredPieces.put(new Square(row, column), piece);
                    }
                }
            }
        }

        return filteredPieces;
    }

    public Map<Square, Piece> filterPiecesBySideAndType(Side side, PieceType pieceType) {

        Map<Square, Piece> filteredPieces = new HashMap<>();

        for (int row = 0; row < this.getPositions().length; row++) {
            for (int column = 0; column < this.getPositions()[0].length; column++) {
                Piece piece = this.getPieceAt(row, column);
                if (piece != null) {
                    if (piece.getSide() == side && piece.getPieceType() == pieceType) {
                        filteredPieces.put(new Square(row, column), piece);
                    }
                }
            }
        }

        return filteredPieces;
    }

    public Board copyBoard() {

        Piece[][] newPositions = new Piece[8][8];
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                newPositions[row][column] = this.positions[row][column];
            }
        }

        Board newBoard = new Board(newPositions, this.whiteKingSideCastlingAllowed, this.whiteQueenSideCastlingAllowed, this.blackKingSideCastlingAllowed, this.blackQueenSideCastlingAllowed);
        return newBoard;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder("\n----- BOARD -----\n\n");
        boardString.append("     a   b   c   d   e   f   g   h     \n");
        boardString.append("   ---------------------------------   \n");
        for (int row = 7; row >= 0; row--) {
            boardString.append(row+1).append("  ");
            for (int column = 0; column < 8; column++) {
                boardString.append("| ").append(this.getPieceAt(row, column) == null ? " " : this.getPieceAt(row, column)).append(" ");
            }
            boardString.append("|").append("  ").append(row+1).append("\n");
        }
        boardString.append("   ---------------------------------   \n");
        boardString.append("     a   b   c   d   e   f   g   h     \n");
        boardString.append("\n----- BOARD END -----\n");

        return boardString.toString();
    }


}

