import jdk.jshell.execution.Util;
import mechanics.Board;
import mechanics.BoardFactory;
import mechanics.Coordinate;
import pieces.*;
import ui.UtilsUI;

public class Main {
    public static void main(String[] args) {
        Board board = BoardFactory.createTestBoard();

        System.out.println();
        System.out.println(board.toString());
        System.out.println();

        Queen queen1 = (Queen) board.getPiece(new Coordinate(5,2));

        UtilsUI.displayPossibleMoves(queen1);
        System.out.println();
        
    }
}
