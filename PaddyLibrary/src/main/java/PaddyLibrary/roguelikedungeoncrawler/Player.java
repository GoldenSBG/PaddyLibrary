package PaddyLibrary.roguelikedungeoncrawler;

public class Player {
    private int x;
    private int y;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}