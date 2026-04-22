package pieces;
import mechanics.*;

public abstract class Piece {
    public final boolean isWhite;
    public final String representation;
    protected Coordinate position;
    protected final Board board;
    protected final int MAX_MOVES;//theoretical max number of valid moves on one turn, used for optimisation
    public final PieceType pieceType;

    public Piece(Board board, String representation, Coordinate position, boolean isWhite, int maxMoves, PieceType pieceType){
        this.board = board;
        this.representation = representation;
        this.isWhite = isWhite;
        this.position = position;
        MAX_MOVES = maxMoves;
        this.pieceType = pieceType;
    }

    /**
     * @return a 64 element array of possible move coordinates
     */
    abstract public Coordinate[] getPossibleMoves();

    /**
     * Moves the piece on the board to the given space
     * Intended external function to move a piece, calls Board from here
     * @param newCoordinate
     */
    public void move(Coordinate newCoordinate){
        board.movePiece(this, newCoordinate);
        position = newCoordinate;
    }

    public Coordinate getPosition() {
        return new Coordinate(position.file(), position.rank());
    }

    public Board getBoard(){
        return board;
    }

    @Override
    public String toString(){
        return representation;
    }

    protected void filterKingExposed(Coordinate[] validMoves){
        Board simulation;
        King simulationKing;
        for(int i = 0; i < validMoves.length; i++){
            if(validMoves[i] == null){
                continue;
            }

            simulation = new Board();
            simulation.setBoard(board.getBoardState());
            simulation.getPiece(getPosition()).move(validMoves[i]);

            simulationKing = simulation.getKing(isWhite);

            if(simulationKing.inCheck()){
                validMoves[i] = null;
            }
        }
    }
}
