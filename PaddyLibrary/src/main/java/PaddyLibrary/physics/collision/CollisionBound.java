package PaddyLibrary.physics.collision;

import java.awt.Rectangle;

/**
 * Default class for <strong>rectangle</strong> collision bounds.
 * @author yiwuen
 *
 * @see Rectangle
 *
 */
public class CollisionBound {

    protected Rectangle bounds;

    protected int x, y, width, height;

    /**
     * Constructs a {@code CollisionBound} with a x and y position and with a width and height.
     * @param x X position of the collision bound
     * @param y Y position of the collision bound
     * @param width Width of the collision bound
     * @param height Height of the collision bound
     */
    public CollisionBound(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(this.x, this.y, this.width, this.height);
    }

    /**
     * Constructs an empty {@code CollisionBound} with default properties (x, y, width, and height) set to 0.
     */
    public CollisionBound() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;

        bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Checks if the {@link Rectangle} bounds is colliding, or intersecting, with another {@code CollisionBound} ({@link Rectangle}).
     * @param bounds
     * @return boolean Colliding or not colliding
     */
    public boolean isColliding(CollisionBound bounds) {
        if (this.bounds.intersects(bounds.getBounds()))
            return true;
        else
            return false;
    }

    /**
     * Returns the {@link Rectangle} bounds.
     * @return
     */
    public final Rectangle getBounds() {
        return bounds;
    }

    /**
     * Returns the x position of the collision bound.
     * @return int x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x position of the collision bound.
     * @param x New x position
     */
    public void setX(int x) {
        this.x = x;
        bounds.x = this.x;
    }

    /**
     * Returns the y position of the collision bound.
     * @return int y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y position of the collision bound.
     * @param y New y position
     */
    public void setY(int y) {
        this.y = y;
        bounds.y = this.y;
    }

    /**
     * Returns the width of the collision bound.
     * @return int width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width of the collision bound.
     * @param width New width
     */
    public void setWidth(int width) {
        this.width = width;
        bounds.width = this.width;
    }

    /**
     * Returns the height of the collision bound.
     * @return int height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of the collision bound.
     * @param height New height
     */
    public void setHeight(int height) {
        this.height = height;
        bounds.height = this.height;
    }

}
