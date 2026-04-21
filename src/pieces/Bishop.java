package pieces;

import mechanics.Board;
import mechanics.Coordinate;

public class Bishop extends Piece{
    public Bishop(Board board, Coordinate position, boolean isWhite) {
        super(board, "B", position, isWhite, 13, PieceType.BISHOP);
    }

    @Override
    public Coordinate[] getPossibleMoves() {
        return MoveUtils.checkDiagonalsLong(this);
    }
}
