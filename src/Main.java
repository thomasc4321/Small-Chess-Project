import mechanics.Board;
import mechanics.BoardFactory;
import mechanics.Coordinate;
import pieces.*;
import playing.GameController;
import playing.HumanPlayer;
import ui.UtilsUI;

public class Main {
    public static void main(String[] args) {
        Board board = BoardFactory.createClassicBoard();

        GameController gameController = new GameController(board);

        gameController.setPlayer(new HumanPlayer(true));
        gameController.setPlayer(new HumanPlayer(false));

        gameController.startGame();
    }
}
