package view;

public class CellView {

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isAlive() {
        return alive;
    }

    private int height;
    private int width;
    private boolean alive;


    public CellView(int height, int width, boolean alive) {
        this.height = height;
        this.width = width;
        this.alive = alive;
    }
}
