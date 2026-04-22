package playing;

import mechanics.Board;
import pieces.PieceType;

import java.util.ArrayList;

public abstract class Player {
    public final boolean isWhite;
    public final String colour;

    public Player(boolean isWhite){
        this.isWhite = isWhite;
        colour = isWhite ? "white" : "black";
    }

    public abstract Move getNextMove(ArrayList<Move> possibleMoves);

}
