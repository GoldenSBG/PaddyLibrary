package PaddyLibraryTest.snakeGame;

public class Food {
    private int x;
    private int y;
    private int unitSize;

    public Food(int width, int height, int unitSize) {
        this.unitSize = unitSize;
        placeFood(width, height);
    }

    public void placeFood(int width, int height) {
        x = (int) (Math.random() * (width / unitSize)) * unitSize;
        y = (int) (Math.random() * (height / unitSize)) * unitSize;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
