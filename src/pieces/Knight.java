package pieces;

import mechanics.Board;
import mechanics.Coordinate;

public class Knight extends Piece{
    public Knight(Board board, Coordinate position, boolean isWhite){
        super(board, "N", position, isWhite, 8, PieceType.KNIGHT);
    }

    @Override
    public Coordinate[] getPossibleMoves() {
        Coordinate[] moves = new Coordinate[8];
        int moveIndex = 0;
        Coordinate[] toCheck = new Coordinate[8];

        toCheck[0] = new Coordinate(position.file()+1, position.rank()+2);
        toCheck[1] = new Coordinate(position.file()+1, position.rank()-2);
        toCheck[2] = new Coordinate(position.file()-1, position.rank()+2);
        toCheck[3] = new Coordinate(position.file()-1, position.rank()-2);
        toCheck[4] = new Coordinate(position.file()+2, position.rank()+1);
        toCheck[5] = new Coordinate(position.file()+2, position.rank()-1);
        toCheck[6] = new Coordinate(position.file()-2, position.rank()+1);
        toCheck[7] = new Coordinate(position.file()-2, position.rank()-1);

        for(int i = 0; i < 8; i++){
            if(Coordinate.isValid(toCheck[i])){
                Piece piece = board.getPiece(toCheck[i]);
                if(piece == null || piece.isWhite != isWhite){
                    moves[moveIndex] = toCheck[i];
                    moveIndex++;
                }
            }
        }

        filterKingExposed(moves);
        return moves;
    }
}
