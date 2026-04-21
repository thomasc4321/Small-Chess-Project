package pieces;

import mechanics.Board;
import mechanics.Coordinate;

public class Rook extends Piece{
    private boolean hasMoved = false; //used to check for castle validity

    public Rook(Board board, Coordinate position, boolean isWhite) {
        super(board, "R", position, isWhite, 15, PieceType.ROOK);
    }

    @Override
    public void move(Coordinate newCoordinate){
        hasMoved = true;
        super.move(newCoordinate);
    }

    @Override
    public Coordinate[] getPossibleMoves() {
        Coordinate[] moves = MoveUtils.checkStraightLinesLong(this);
        filterKingExposed(moves);
        return moves;
    }

    public boolean hasMoved(){
        return hasMoved;
    }

    public void setHasMoved() {
        hasMoved = true;
    }
}
