package playing;

import mechanics.Board;
import mechanics.BoardFactory;
import mechanics.Coordinate;
import mechanics.GameSettings;
import pieces.Piece;
import ui.UtilsUI;

import java.util.ArrayList;

public class GameController {
    final Board board;
    boolean isWhiteTurn = true;
    ArrayList<Piece> whitePieces = new ArrayList<>();
    ArrayList<Piece> blackPieces = new ArrayList<>();
    ArrayList<Move> possibleMoves = new ArrayList<>();
    ArrayList<String> pastMoves = new ArrayList<>();

    Player playerWhite;
    Player playerBlack;


    public GameController(Board board){
        this.board = board;
    }
    public GameController(){
        board = BoardFactory.createClassicBoard();
    }

    public void setPlayer(Player player){
        if(player.isWhite){
            playerWhite = player;
        }
        else{
            playerBlack = player;
        }
    }

    public void startGame(){
        Piece foundPiece;
        for(int file = 1; file <= GameSettings.FILE_LENGTH; file++){
            for(int rank = 1; rank <= GameSettings.RANK_LENGTH; rank++){
                foundPiece = board.getPiece(new Coordinate(file, rank));
                if(foundPiece == null){
                    continue;
                }
                if(foundPiece.isWhite){
                    whitePieces.add(foundPiece);
                }
                else{
                    blackPieces.add(foundPiece);
                }
            }
        }

        System.out.println(board);
        System.out.println();
        gameLoop();
    }

    private void gameLoop(){
        ArrayList<Piece> turnsPieces;
        Player currentPlayer;
        Move move;
        while(true){
            turnsPieces = isWhiteTurn ? whitePieces : blackPieces;
            possibleMoves.clear();

            //gather all potential moves
            for(Piece piece : turnsPieces){
                Coordinate[] pieceMoves = piece.getPossibleMoves();
                for(int i = 0; i < pieceMoves.length; i++){
                    if(pieceMoves[i] != null){
                        possibleMoves.add(new Move(piece, pieceMoves[i]));
                    }
                }
            }

            currentPlayer = isWhiteTurn ? playerWhite : playerBlack;

            //check if no moves -> player has lost or drawn

            move = currentPlayer.getNextMove(possibleMoves);

            //remove piece from list if taken
            Piece takenPiece = board.getPiece(move.newCoordinate());
            if(takenPiece != null){
                if(turnsPieces == whitePieces){
                    blackPieces.remove(takenPiece);
                }
                else{
                    whitePieces.remove(takenPiece);
                }
            }

            move.execute();

            System.out.println(board);
            System.out.println();
            isWhiteTurn = !isWhiteTurn;
            pastMoves.add(move.toString());
        }
    }
}
