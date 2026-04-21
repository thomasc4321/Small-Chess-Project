package pieces;

import mechanics.Coordinate;
import mechanics.GameLogic;

import java.util.ArrayList;

public final class MoveUtils {

    private MoveUtils(){}

    /**
     * Searches all diagonal lines for possible moves
     * @param piece
     * @return
     */
    public static Coordinate[] checkDiagonalsLong(Piece piece){
        Coordinate[] moves = new Coordinate[13];
        int moveIndex = 0;

        //gather all diagonals
        ArrayList<Coordinate> combinedCoordinates = searchDirectionLong(1, 1, piece);
        combinedCoordinates.addAll(searchDirectionLong(1, -1, piece));
        combinedCoordinates.addAll(searchDirectionLong(-1, 1, piece));
        combinedCoordinates.addAll(searchDirectionLong(-1, -1, piece));

        for(Coordinate validMove : combinedCoordinates){
            moves[moveIndex] = validMove;
            moveIndex++;
        }

        return moves;
    }

    /**
     * Searches all straight lines for possible moves
     * @param piece
     * @return
     */
    public static Coordinate[] checkStraightLinesLong(Piece piece){
        Coordinate[] moves = new Coordinate[14];
        int moveIndex = 0;

        //gather all diagonals
        ArrayList<Coordinate> combinedCoordinates = searchDirectionLong(1, 0, piece);
        combinedCoordinates.addAll(searchDirectionLong(-1, 0, piece));
        combinedCoordinates.addAll(searchDirectionLong(0, 1, piece));
        combinedCoordinates.addAll(searchDirectionLong(0, -1, piece));

        for(Coordinate validMove : combinedCoordinates){
            moves[moveIndex] = validMove;
            moveIndex++;
        }

        return moves;
    }

    /**
     * Searches in a line along the given direction. Direction inputs: 1,0 = up; 1,1 = up and right, 0,1 = right etc
     * @param moveDirectionHorizontal one of -1,0,1
     * @param moveDirectionVertical one of -1,0,1
     * @param piece
     * @return
     */
    private static ArrayList<Coordinate> searchDirectionLong(int moveDirectionHorizontal, int moveDirectionVertical, Piece piece){
        Coordinate checkCoordinate = piece.getPosition();
        //int moveDirectionHorizontal = searchLeft ? -1 : 1;
        //int moveDirectionVertical = searchUp ? 1 : -1;

        ArrayList<Coordinate> moves = new ArrayList<>(8);

        checkCoordinate = new Coordinate(checkCoordinate.file()+moveDirectionHorizontal, checkCoordinate.rank()+moveDirectionVertical);

        while(checkCoordinate.file() > 0 && checkCoordinate.rank() > 0 && checkCoordinate.file() <= 8 && checkCoordinate.rank() <= 8){
            Piece checkedTile = piece.board.getPiece(checkCoordinate);
            if(checkedTile != null){
                if(GameLogic.arePiecesOpposing(piece, checkedTile)){
                    moves.add(checkCoordinate);
                }
                break;
            }
            moves.add(checkCoordinate);
            checkCoordinate = new Coordinate(checkCoordinate.file()+moveDirectionHorizontal, checkCoordinate.rank()+moveDirectionVertical);
        }

        return moves;
    }

}
