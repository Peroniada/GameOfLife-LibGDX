package model;

public class GameOFLifeApplication {

    public static void main(String[] args) {
        Board board = new Board(10,10);
        board.setAlive(
                new Coord(3,4),
                new Coord(3,5),
                new Coord(3,6),
                new Coord(2,6),
                new Coord(1,5)
                );

        for(int i = 0; i< 5; i++) {
            System.out.println( board.toString());
            board.updateState();
        }
    }
}
