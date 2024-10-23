package PaddyLibrary.graphics.sprites;

import PaddyLibrary.Application;
import PaddyLibrary.utils.PathUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The sprite class renders an image based on the path given or image loaded.
 *
 *
 * @see BufferedImage
 *
 */

public class Sprite {

    private BufferedImage sprite;

    private int x, y, width, height;

    /**
     * Creates a sprite based on the path given.
     *
     * @param path
     */
    public Sprite(String path) {
        sprite = PathUtils.GetImage(path);
    }

    /**
     * Creates a sprite based on the {@code BufferedImage} loaded.
     *
     * @param sprite {@code BufferedImage} sprite
     *
     * @see BufferedImage
     */
    public Sprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    /**
     * Creates an empty sprite based on the dimensions. The image type of the {@code BufferedImage} is {@code BufferedImage.TYPE_INT_ARGB}.
     *
     * @param x X position of the sprite
     * @param y Y position of the sprite
     * @param width Width of the sprite
     * @param height Height of the sprite
     *
     * @see BufferedImage
     */
    public Sprite(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        sprite = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Horizontally flips the sprite.
     *
     * @return {@link Sprite} horizontally flipped sprite
     */
    public Sprite getHorizontalFlipped() {
        BufferedImage img = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int xx = sprite.getWidth() - 1; xx > 0; xx--) {
            for (int yy = 0; yy < sprite.getHeight(); yy++) {
                img.setRGB(sprite.getWidth() - xx, yy, sprite.getRGB(xx, yy));
            }
        }

        return new Sprite(img);
    }

    /**
     * Vertically flips the sprite.
     *
     * @return {@link Sprite} vertically flipped sprite
     */
    public Sprite getVerticalFlipped() {
        BufferedImage img = new BufferedImage(sprite.getWidth(), sprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int xx = 0; xx < sprite.getWidth(); xx++) {
            for (int yy = sprite.getHeight() - 1; yy > 0; yy--) {
                img.setRGB(xx, sprite.getHeight() - yy, sprite.getRGB(xx, yy));
            }
        }

        return new Sprite(img);
    }

    /**
     * Renders the sprite.
     *
     * @param x X position of the sprite
     * @param y Y position of the sprite
     * @param width Width of the sprite
     * @param height Height of the sprite
     *
     * @see BufferedImage
     * @see Graphics2D
     */
    public void render(int x, int y, int width, int height) {
        Application.g.drawImage(sprite, x, y, width, height, null);
    }

    /**
     * Returns the sprite loaded.
     * @return BufferedImage sprite
     */
    public BufferedImage getSprite() {
        return sprite;
    }

    /**
     * Returns the x position of the sprite.
     *
     * @return int x
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y position of the sprite.
     *
     * @return int y
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the width of the sprite.
     *
     * @return int width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the sprite.
     *
     * @return int width
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the x position of the sprite.
     * @param x X position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y position of the sprite.
     * @param y Y position
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the width of the sprite.
     * @param width Width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the height of the sprite.
     * @param height Height
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
