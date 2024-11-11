package PaddyLibraryTest.PaddyLibrary.graphics.animation;

/**
 * Base class for animations. There are many types of animations but one common animation is {@link SpriteAnimation}.
 *
 *
 * @see SpriteAnimation
 *
 */

public class Animation {

    protected int time, speed, frame;

    protected boolean playing = true;

    /**
     * Updates, or plays the animation. This method is overrided by built-in subclasses.
     */
    public void update() {
    }

    /**
     * Sets {@code playing}.
     *
     * @param playing Playing the animation
     */
    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    /**
     * Checks if the animation is currently playing.
     * @return boolean playing
     */
    public boolean isPlaying() {
        return playing;
    }

    /**
     * Returns the current frame in the animation.
     * @return int frame
     */
    public int getFrame() {
        return frame;
    }

}