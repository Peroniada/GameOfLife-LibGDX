package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import model.Board;
import model.Cell;
import model.Coord;
import view.BoardView;
import view.CellView;

import javax.xml.soap.Text;

public class GameOfLife extends ApplicationAdapter {
	public static final String CORE_ASSETS_PATH = "core/assets/";
	SpriteBatch batch;
	Texture img;
	private Board board;
	private BoardView boardView;
	private boolean isPaused;

	@Override
	public void create () {
		batch = new SpriteBatch();
		board = new Board(100,100);
//		board.setAlive(
//				new Coord(3,4),
//				new Coord(3,5),
//				new Coord(3,6),
//				new Coord(2,6),
//				new Coord(1,5)
//		);
		boardView = new BoardView(board, 1000, 1000);
		isPaused = true;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		boardView.drawBoard();
		if(!isPaused) {
			board.updateState();
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			togglePause();
		}
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
//			System.out.println("ONE CLICK");
			System.out.println(Gdx.input.getX() + "       " + Gdx.input.getY());
			toggleCells(Gdx.input.getX(),boardView.getBoardHeight() - Gdx.input.getY());
//					Gdx.graphics.getHeight() - Gdx.input.getY());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void dispose () {
		boardView.getShapeRenderer().dispose();
	}

	private void toggleCells (int mouseCoordX, int mouseCoordY){

		CellView cellView = boardView.getCellsView()[0][0];

		Coord beChanged = getCellsCoordsThatShouldBeChanged(mouseCoordX, mouseCoordY, cellView);
		board.getCells()[beChanged.x-1][beChanged.y-1].toggleAlive();

	}

	private Coord getCellsCoordsThatShouldBeChanged(int mouseCoordX, int mouseCoordY, CellView cellView) {
		int cellCoordX = mouseCoordX / cellView.getWidth();
		if (mouseCoordX % cellView.getWidth() != 0){
			cellCoordX++;
		}

		int cellCoordY = mouseCoordY / cellView.getHeight();
		if (mouseCoordY % cellView.getWidth() != 0){
			cellCoordY++;
		}
		System.out.println("X:" + cellCoordX + " Y:" + cellCoordY );
			return new Coord(cellCoordX,cellCoordY);
	}
	private void togglePause(){
		this.isPaused=!isPaused;
		System.out.println("Paused:" + isPaused);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
