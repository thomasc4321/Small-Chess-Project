package mechanics;

public record Coordinate(int file, int rank) {
    public static boolean isValid(Coordinate coordinate){
        return coordinate.file() >= 1 && coordinate.file() <= 8 && coordinate.rank() >= 1 && coordinate.rank() <= 8;
    }
}
