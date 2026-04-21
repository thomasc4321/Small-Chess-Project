package mechanics;

import pieces.*;

public final class GameLogic {
    public static boolean arePiecesOpposing(Piece piece1, Piece piece2){
        if(piece1 == null || piece2 == null){
            return false;
        }

        if(piece1.isWhite != piece2.isWhite){
            return true;
        }

        return false;
    }

    public static Piece createPieceFromType(Board board, PieceType type, Coordinate coordinate, boolean isWhite){
        Piece newPiece;
        switch(type){
            case PAWN -> {
                newPiece = new Pawn(board, coordinate, isWhite);
                break;
            }

            case KING -> {
                newPiece = new King(board, coordinate, isWhite);
                break;
            }

            case QUEEN -> {
                newPiece = new Queen(board, coordinate, isWhite);
                break;
            }

            case ROOK -> {
                newPiece = new Rook(board, coordinate, isWhite);
                break;
            }

            case BISHOP -> {
                newPiece = new Bishop(board, coordinate, isWhite);
                break;
            }

            case KNIGHT -> {
                newPiece = new Knight(board, coordinate, isWhite);
            }

            case DUMMY -> {
                newPiece = new DummyPiece(board, coordinate, isWhite);
                break;
            }

            default -> {
                System.out.println("GAMELOGIC ERROR: attempting to create piece without defined type");
                newPiece = new Pawn(board, coordinate, isWhite);
                break;
            }
        }
        board.setPiece(coordinate, newPiece);
        return newPiece;
    }

    private GameLogic(){};
}
