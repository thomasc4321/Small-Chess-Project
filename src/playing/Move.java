package playing;

import mechanics.Coordinate;
import pieces.Piece;

public record Move(Piece piece, Coordinate newCoordinate) {
    public void execute(){
        piece.move(newCoordinate);
    }

    @Override
    public String toString(){
        return String.format("%s%s%s%s",
                piece.getPosition().file(),
                piece.getPosition().rank(),
                newCoordinate.file(),
                newCoordinate.rank());
    }
}
