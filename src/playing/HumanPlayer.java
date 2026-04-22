package playing;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends Player{
    Scanner textInput = new Scanner(System.in);
    String myMove;

    public HumanPlayer(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public Move getNextMove(ArrayList<Move> possibleMoves) {
        System.out.println();
        System.out.println(colour + "'s turn!");
        System.out.println("possible moves:");
        for(Move possibleMove : possibleMoves){
            System.out.println(possibleMove);
        }

        while(true) {
            myMove = textInput.nextLine();
            for(Move possibleMove: possibleMoves){
                if(possibleMove.toString().equals(myMove)){
                    return possibleMove;
                }
            }
            System.out.println("Incorrect input");
        }
    }
}
