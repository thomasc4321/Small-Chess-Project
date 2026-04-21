package pieces;

import mechanics.Board;
import mechanics.Coordinate;

public class Queen extends Piece{

    public Queen(Board board, Coordinate position, boolean isWhite) {
        super(board, "Q", position, isWhite, 27, PieceType.QUEEN);
    }

    @Override
    public Coordinate[] getPossibleMoves() {
        Coordinate[] moves = new Coordinate[MAX_MOVES];

        Coordinate[] diagonalMoves = MoveUtils.checkDiagonalsLong(this);
        Coordinate[] straightMoves = MoveUtils.checkStraightLinesLong(this);

        for(int i = 0; i < diagonalMoves.length + straightMoves.length; i++){
            if(i < diagonalMoves.length){
                moves[i] = diagonalMoves[i];
            }
            else{
                moves[i] = straightMoves[i-diagonalMoves.length];
            }
        }

        filterKingExposed(moves);

        return moves;
    }
}
