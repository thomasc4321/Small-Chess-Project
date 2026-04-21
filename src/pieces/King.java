package pieces;

import mechanics.Board;
import mechanics.Coordinate;
import pieces.MoveUtils;

public class King extends Piece{
    boolean hasMoved = false; //used to check for castle validity

    public King(Board board, Coordinate position, boolean isWhite) {
        super(board, "K", position, isWhite, 8, PieceType.KING);
    }

    @Override
    public Coordinate[] getPossibleMoves() {
        //TODO
        return new Coordinate[0];
    }

    //check diagonals + straights + knight squares + pawn squares for attackers
    public boolean inCheck(){

        //check for pawns (could be folded into broader check)
        int forward = isWhite ? 1 : -1;
        if(isAttackedBy(new Coordinate(position.file()-1, position.rank()+forward), PieceType.PAWN)){
            return true;
        }
        if(isAttackedBy(new Coordinate(position.file()+1, position.rank()+forward), PieceType.PAWN)){
            return true;
        }

        //check for knights
        Coordinate[] possibleKnights = new Coordinate[8];
        possibleKnights[0] = new Coordinate(position.file()+2, position.rank()+1);
        possibleKnights[1] = new Coordinate(position.file()+2, position.rank()-1);
        possibleKnights[2] = new Coordinate(position.file()-2, position.rank()+1);
        possibleKnights[3] = new Coordinate(position.file()-2, position.rank()-1);
        possibleKnights[4] = new Coordinate(position.file()+1, position.rank()+1);
        possibleKnights[5] = new Coordinate(position.file()+1, position.rank()-1);
        possibleKnights[6] = new Coordinate(position.file()-1, position.rank()+1);
        possibleKnights[7] = new Coordinate(position.file()-1, position.rank()-1);

        for(Coordinate possibleKnight : possibleKnights){
            if(isAttackedBy(possibleKnight, PieceType.KNIGHT)){
                return true;
            }
        }

        //check diagonals for enemy bishops and queens
        for(Coordinate coord : MoveUtils.checkDiagonalsLong(this)){
            if(coord == null){
                continue;
            }
            Piece foundPiece = board.getPiece(coord);

            if(foundPiece.isWhite != isWhite && (foundPiece.pieceType == PieceType.BISHOP
                                            || foundPiece.pieceType == PieceType.QUEEN)) {
                return true;
            }
        }

        //check straights for enemy rooks and queens
        for(Coordinate coord : MoveUtils.checkStraightLinesLong(this)){
            if(coord == null){
                continue;
            }

            Piece foundPiece = board.getPiece(coord);
            if(foundPiece == null){
                continue;
            }

            if(foundPiece.isWhite != isWhite && (foundPiece.pieceType == PieceType.ROOK
                    || foundPiece.pieceType == PieceType.QUEEN)) {
                return true;
            }
        }

        return false;
    }

    private boolean isAttackedBy(Piece attackingPawn, PieceType attacker){
        if(attackingPawn == null){
            return false;
        }
        if(attackingPawn.pieceType == attacker && attackingPawn.isWhite != isWhite){
            return true;
        }
        return false;
    }

    private boolean isAttackedBy(Coordinate attackingPawn, PieceType attacker){
        return isAttackedBy(board.getPiece(attackingPawn), attacker);
    }
}
