package pieces;

import mechanics.Board;
import mechanics.Coordinate;

public class King extends Piece{
    private boolean hasMoved = false; //used to check for castle validity

    public King(Board board, Coordinate position, boolean isWhite) {
        super(board, "K", position, isWhite, 8, PieceType.KING);
    }

    @Override
    public void move(Coordinate newCoordinate){
        //castling
        if(!hasMoved && newCoordinate.file() == 3){
            Rook rook = (Rook) board.getPiece(new Coordinate(1, position.rank()));
            rook.hasMoved();
            rook.move(new Coordinate(4, position.rank()));
        }
        if(!hasMoved && newCoordinate.file() == 7){
            Rook rook = (Rook) board.getPiece(new Coordinate(8, position.rank()));
            rook.hasMoved();
            rook.move(new Coordinate(6, position.rank()));
        }

        hasMoved = true;
        super.move(newCoordinate);
    }

    @Override
    public Coordinate[] getPossibleMoves() {
        Coordinate[] moves = new Coordinate[8];
        int moveIndex = 0;

        Piece targetPiece;
        Coordinate checkCoordinate;

        //normal moves
        for(int i : new int[]{-1,0,1}){
            for(int j : new int[]{-1,0,1}){
                if(i == 0 && j == 0){
                    continue;
                }
                checkCoordinate = new Coordinate(position.file()+i, position.rank()+j);
                if(!Coordinate.isValid(checkCoordinate)){
                    continue;
                }

                targetPiece = board.getPiece(checkCoordinate);

                if(targetPiece == null || targetPiece.isWhite != isWhite){
                    moves[moveIndex] = checkCoordinate;
                    moveIndex++;
                }
            }
        }

        //castling
        if(!hasMoved){
            for(int rookFile : new int[]{1,8}) {
                targetPiece = board.getPiece(new Coordinate(rookFile, position.rank()));

                //is rook valid
                if (targetPiece != null && targetPiece.isWhite == isWhite && targetPiece.pieceType == PieceType.ROOK) {
                    if (!((Rook) targetPiece).hasMoved()) {
                        boolean pathOpen = false;

                        //is path unattacked
                        if(rookFile == 1){
                            if (testSpace(new Coordinate(4, position.rank()))){
                                pathOpen = true;
                            }
                            checkCoordinate = new Coordinate(3, position.rank());
                        }
                        else{
                            if(testSpace(new Coordinate(6, position.rank()))){
                                pathOpen = true;
                            }
                            checkCoordinate = new Coordinate(7, position.rank());
                        }

                        if(pathOpen){
                            moves[moveIndex] = checkCoordinate;
                            moveIndex++;
                        }
                    }
                }
            }
        }

        filterKingExposed(moves);

        return moves;
    }

    //checks if a space could be entered by running filterKingExposed on one given tile
    private boolean testSpace(Coordinate target){
        if(board.getPiece(target) != null){
            return false;
        }

        Coordinate[] targetAsArray = new Coordinate[1];
        targetAsArray[0] = target;
        filterKingExposed(targetAsArray);
        return targetAsArray[0] != null;
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
            if(foundPiece == null){
                continue;
            }

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
        return attackingPawn != null && attackingPawn.pieceType == attacker && attackingPawn.isWhite != isWhite;
    }
    private boolean isAttackedBy(Coordinate attackingPawn, PieceType attacker){
        return isAttackedBy(board.getPiece(attackingPawn), attacker);
    }


    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved() {
        this.hasMoved = true;
    }
}
