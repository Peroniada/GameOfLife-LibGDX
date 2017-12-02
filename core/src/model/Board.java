package model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Cell[][] cells;

    private boolean[][] boardHelper;

    public Board(int lengthX, int lengthY) {
        this.cells = new Cell[lengthX][lengthY];
        this.boardHelper = new boolean[lengthX][lengthY];
        wipeBoard(cells, lengthX, lengthY);
        setNeighbours();
    }

    public Cell[][] getCells() {
        return cells;
    }

    private void wipeBoard(Cell[][] board, int lengthX, int lengthY) {
        for (int x = 0; x < lengthX; x++) {
            for (int y = 0; y < lengthY; y++) {
                board[x][y] = new Cell(false);
            }
        }
    }

    public int getSize() {
        return cells.length * cells[0].length;
    }

    public void updateState() {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0 ; y < cells[0].length; y++) {
                if (cells[x][y].willBeAlive()) {
                    boardHelper[x][y] =true;
                }
                else{
                    boardHelper[x][y]=false;
                }
            }
        }
        setStatusFromHelper(boardHelper);
    }


    private boolean willChange(Cell cell) {
        return !(cell.willBeAlive() && cell.isAlive());
    }

    private void setNeighbours() {
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {
                cells[x][y].setNeighbours(getNeighbours(x, y));
            }
        }

    }

    private List<Cell> getNeighbours(int x, int y) {
        int coords[][] = {{x - 1, y - 1}, {x, y - 1}, {x + 1, y - 1}, {x - 1, y}, {x + 1, y}, {x - 1, y + 1}, {x, y + 1}, {x + 1, y + 1}};
        List<Cell> neighbours = new ArrayList<>();
        for (int[] row : coords) {
            if (row[0] >= 0 && row[0] < cells.length
                    && row[1] >= 0 && row[1] < cells[0].length) {
                neighbours.add(cells[row[0]][row[1]]);
            }
        }
        return neighbours;
    }

    public void setAlive(Coord... cords) {
        for (Coord coord: cords) {
            this.getCells()[coord.x][coord.y].toggleAlive();
        }
    }

    @Override
    public String toString() {
        StringBuilder game = new StringBuilder();

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                game.append(cell.toString());
            }
            game.append("\n");
        }
        return game.toString();
    }

    private void setStatusFromHelper(boolean[][] board) {
        for (int i = 0 ; i < board.length; i++) {
            for (int j= 0; j < board[i].length; j++) {
                cells[i][j].setAlive(board[i][j]);
            }
        }
    }
}

