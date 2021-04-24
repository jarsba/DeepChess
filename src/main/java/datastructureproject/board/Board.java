package datastructureproject.board;

import datastructureproject.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private Piece[][] positions;

    public Board() {
        positions = new Piece[8][8];
        initializePositions();
    }

    public Board(Piece[][] boardPositions) {
        this.positions = boardPositions;
    }

    public Piece[][] getPositions() {
        return positions;
    }

    public void setPositions(Piece[][] positions) {
        this.positions = positions;
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
        this.movePiece(startSquare.getRow(), startSquare.getColumn(), endSquare.getRow(), endSquare.getColumn());
        if(move.getPromotionPiece() != null) {
            this.setPieceAt(endSquare.getRow(), endSquare.getColumn(), move.getPromotionPiece());
        }
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
        Board newBoard = new Board(newPositions);
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

