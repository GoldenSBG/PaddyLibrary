package PaddyLibrary.world;

import PaddyLibrary.graphics.Renderer;

public class Platform {
    private int x, y, width, height;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render() {
        Renderer.Color3f(0.5f, 0.25f, 0.25f); // Braune Plattformfarbe
        Renderer.Rect(x, y, width, height);
    }

    public boolean isColliding(int playerX, int playerY, int playerWidth, int playerHeight) {
        return playerX + playerWidth > x && playerX < x + width &&
                playerY + playerHeight >= y && playerY + playerHeight <= y + height;
    }

    public int getTop() {
        return y;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
