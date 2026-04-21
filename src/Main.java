import mechanics.Board;
import mechanics.BoardFactory;
import mechanics.Coordinate;
import pieces.*;
import ui.UtilsUI;

public class Main {
    public static void main(String[] args) {
        Board board = BoardFactory.createClassicBoard();

        System.out.println();
        System.out.println(board.toString());
        System.out.println();

        King testKing = (King) board.getPiece(new Coordinate(5,8));

        //UtilsUI.printPossibleMoves(testKing);
        UtilsUI.displayPossibleMoves(testKing);
        System.out.println();

        testKing.move(new Coordinate(3, 8));

        UtilsUI.displayPossibleMoves(testKing);


        //Knight testKnight = (Knight) board.getPiece(new Coordinate(2,1));
        //UtilsUI.printPossibleMoves(testKnight);
        //UtilsUI.displayPossibleMoves(testKnight);
    }
}
