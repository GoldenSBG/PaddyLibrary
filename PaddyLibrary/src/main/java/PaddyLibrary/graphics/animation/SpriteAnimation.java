package PaddyLibrary.graphics.animation;


import PaddyLibrary.Application;
import PaddyLibrary.graphics.sprites.Sprite;
import PaddyLibrary.graphics.sprites.Spritesheet;

/**
 * Simple animation class for animating an array of sprites (by looping through them at a constant speed).
 *
 * @author yiwuen
 *
 * @see Sprite
 * @see Spritesheet
 * @see Animation
 *
 */

public class SpriteAnimation extends Animation {

    private Sprite[] sprites;

    /**
     * Constructs an animation with a specified speed and an array of sprites to animate, or loop through.
     *
     * @param speed Speed of the animation. The higher the number is, the slower the animation.
     * @param sprites Array of sprites to animate (loops through)
     */
    public SpriteAnimation(int speed, Sprite[] sprites) {
        this.speed = speed;
        this.sprites = sprites;
    }

    /**
     * Updates, or plays the animation. In order to display the animation, render the current sprite. Call this method in the {@code tick()}
     * method of {@link Application} or any type of {@code update()} method.
     *
     * <p> <strong>Note:</strong> once the animation is updated, it will loop through the array of sprites forever until program is terminated.
     */
    public void update() {
        time++;
        if (time % speed == 0) {
            if (playing)
                frame++;
            frame %= sprites.length;
        }
    }

    /**
     * Returns the current sprite in the animation. Useful for getting the sprite to render in order to display the animation.
     *
     * @return Sprite sprite
     */
    public Sprite getCurrentSprite() {
        return sprites[frame];
    }

}