package mechanics;

import pieces.PieceType;

public final class BoardFactory {
    private BoardFactory(){}

    public static Board createClassicBoard(){
        Board board = new Board();

        setSide(board, true);
        setSide(board, false);

        return board;
    }

    private static void setSide(Board board, boolean isWhite){
        int backRank = isWhite ? 1 : 8;
        int frontRank = isWhite ? 2 : 7;

        board.setPiecesRank(frontRank, PieceType.PAWN, isWhite);

        GameLogic.createPieceFromType(board, PieceType.ROOK,
                new Coordinate(1,backRank), isWhite);
        GameLogic.createPieceFromType(board, PieceType.KNIGHT,
                new Coordinate(2,backRank), isWhite);
        GameLogic.createPieceFromType(board, PieceType.BISHOP,
                new Coordinate(3,backRank), isWhite);
        GameLogic.createPieceFromType(board, PieceType.QUEEN,
                new Coordinate(4,backRank), isWhite);
        GameLogic.createPieceFromType(board, PieceType.KING,
                new Coordinate(5,backRank), isWhite);
        GameLogic.createPieceFromType(board, PieceType.BISHOP,
                new Coordinate(6,backRank), isWhite);
        GameLogic.createPieceFromType(board, PieceType.KNIGHT,
                new Coordinate(7,backRank), isWhite);
        GameLogic.createPieceFromType(board, PieceType.ROOK,
                new Coordinate(8,backRank), isWhite);
    }

    public static Board create960Board(){
        return null;
    }

    public static Board createTestBoard1(){
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

    public static Board createTestBoard2(){
        Board board = new Board();

        GameLogic.createPieceFromType(board, PieceType.KING,
                new Coordinate(5,5), false);

        return board;

    }
}
