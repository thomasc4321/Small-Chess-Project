package mechanics;

import pieces.PieceType;

public final class BoardFactory {
    private BoardFactory(){}

    public static Board createClassicBoard(){
        Board board = new Board();

        board.setPiecesRank(2, PieceType.PAWN, true);
        board.setPiecesRank(7, PieceType.PAWN, false);
        //board.setPiece(new Coordinate());

        return board;
    }

    public static Board createTestBoard(){
        Board board = new Board();

        board.setPiecesRank(2, PieceType.PAWN, true);

        GameLogic.createPieceFromType(board, PieceType.QUEEN,
                new Coordinate(5,2), true);
        GameLogic.createPieceFromType(board, PieceType.KING,
                new Coordinate(5,1), true);

        board.setPiecesRank(7, PieceType.PAWN, false);
        GameLogic.createPieceFromType(board, PieceType.KING,
                new Coordinate(5,8), false);
        GameLogic.createPieceFromType(board, PieceType.ROOK,
                new Coordinate(5,6), false);

        return board;
    }

    public static Board create960Board(){
        return null;
    }
}
