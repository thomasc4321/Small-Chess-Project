package pieces;

import mechanics.Board;
import mechanics.Coordinate;

public class Rook extends Piece{
    boolean hasMoved = false; //used to check for castle validity

    public Rook(Board board, Coordinate position, boolean isWhite) {
        super(board, "R", position, isWhite, 15, PieceType.ROOK);
    }

    @Override
    public Coordinate[] getPossibleMoves() {
        return MoveUtils.checkStraightLinesLong(this);
    }
}
