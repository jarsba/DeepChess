package datastructureproject.pieces;

public enum Side {
    WHITE,
    BLACK;

    public Side getOppositeSide() {
        if (this == Side.BLACK) {
            return Side.WHITE;
        } else {
            return Side.BLACK;
        }
    }

    public int getDirection() {
        if (this == Side.BLACK) {
            return -1;
        } else {
            return 1;
        }
    }
}
