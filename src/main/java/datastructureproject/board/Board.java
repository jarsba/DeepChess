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

    public Piece[][] getPositions() {
        return positions;
    }

    private void initializePositions() {
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
            removePieceAt(srcRow, srcColumn);
        }
    }

    public List<Square> getPossibleMoves(int row, int column) {
        Piece piece = this.getPieceAt(row, column);

        if (piece != null) {
            return null;
        } else {
            return getPossibleMoves(row, column, piece);
        }
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


    public List<Square> getPossibleMoves(int row, int column, Piece piece) {

        switch (piece.getPieceType()) {
            case BISHOP:
                return Bishop.getPossibleMoves(row,column);
            case ROOK:
                return Rook.getPossibleMoves(row, column);
            case PAWN:
                return Pawn.getPossibleMoves(row, column, piece.getSide());
            case KNIGHT:
                return Knight.getPossibleMoves(row, column);
            case QUEEN:
                return Queen.getPossibleMoves(row, column);
            case KING:
                return King.getPossibleMoves(row, column);
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder("\n----- BOARD -----\n\n");
        boardString.append("     a   b   c   d   e   f   g   h     \n");
        boardString.append("   ---------------------------------   \n");
        for (int row = 0; row < 8; row++) {
            boardString.append(8-row + "  ");
            for (int column = 0; column < 8; column++) {
                boardString.append("| ").append(this.getPieceAt(row, column) == null ? " " : this.getPieceAt(row, column)).append(" ");
            }
            boardString.append("|").append("  " + (8-row)).append("\n");
        }
        boardString.append("   ---------------------------------   \n");
        boardString.append("     a   b   c   d   e   f   g   h     \n");
        boardString.append("\n----- BOARD END -----\n");

        return boardString.toString();
    }


}

