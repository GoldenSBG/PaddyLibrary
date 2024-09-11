package PaddyLibrary.snakeGame;

public class Snake {
    private int[] x;
    private int[] y;
    private int bodyParts;

    public Snake(int maxUnits) {
        x = new int[maxUnits];
        y = new int[maxUnits];
        bodyParts = 3;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public int getBodyParts() {
        return bodyParts;
    }

    public void grow() {
        bodyParts++;
    }
}
