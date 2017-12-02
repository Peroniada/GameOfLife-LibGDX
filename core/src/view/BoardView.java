package view;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import model.Board;
import model.Cell;

public class BoardView {
    private final int boardRowsCount;
    private final int boardColumnsCount;
    private final CellView[][] cellsView;
    private final Board board;

    private final ShapeRenderer shapeRenderer;

    private final int boardHeight;
    private final int boardWidth;
    public BoardView(final Board board, final int boardHeight, final int boardWidth) {
        this.board = board;
        this.boardRowsCount = board.getCells().length;
        this.boardColumnsCount = board.getCells()[0].length;
        this.cellsView = new CellView[boardRowsCount][boardColumnsCount];
        this.shapeRenderer = new ShapeRenderer();
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;

        updateCells();

    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    private void updateCells(){

        Cell[][] cellsBoard = board.getCells();
        for (int i=0; i < boardRowsCount; i++) {
            for (int j=0; j< boardColumnsCount; j++) {
                cellsView[i][j] = new CellView(boardHeight / boardRowsCount, boardWidth / boardColumnsCount, cellsBoard[i][j].isAlive());
            }
        }
    }

    public CellView[][] getCellsView() {
        return cellsView;
    }

    public void drawBoard(){
        updateCells();
        for (int i=0; i < boardRowsCount; i++) {
            for (int j = 0; j < boardColumnsCount; j++) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                CellView cellView = cellsView[i][j];
                if(cellView.isAlive()) {
                    shapeRenderer.setColor(0, 1, 0, 1);
                } else {
                    shapeRenderer.setColor(0, 0, 0, 1);
                }
                int x = i * cellView.getWidth();
                int y = j * cellView.getHeight();
                shapeRenderer.rect(x, y, cellView.getWidth(), cellView.getHeight());
                shapeRenderer.end();
            }
        }
    }

    public int getBoardHeight() {
        return boardHeight;
    }
}


