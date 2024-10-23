package PaddyLibrary.input;

import PaddyLibrary.utils.MouseUtils;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Simple and basic helper class for detecting mouse inputs. External mouse classes should inherit from this class and override
 * the {@code buttonPressed(int button)} method. Super methods can be added and modified in external classes to have more mouse
 * functionality.
 *
 *
 * @see MouseAdapter
 * @see MouseMotionListener
 *
 */

public class SimpleMouse extends MouseAdapter {

    private int mouseX, mouseY;
    private int button = -1;

    private int clicks = 0;

    private MouseEvent event;

    @Override
    public final void mousePressed(MouseEvent e) {
        event = e;
        button = e.getButton();
        buttonPressed(button);
        clicks = e.getClickCount();
    }

    @Override
    public final void mouseClicked(MouseEvent e) {
        event = e;
        button = e.getButton();
        buttonClicked(button);
        clicks = e.getClickCount();
    }

    @Override
    public final void mouseReleased(MouseEvent e) {
        event = e;
        button = -1;
        buttonReleased(e.getButton());
    }

    @Override
    public final void mouseMoved(MouseEvent e) {
        event = e;
        mouseX = e.getX();
        mouseY = e.getY();
    }

    /**
     * To check if a mouse button was pressed, implement this method.
     * {@link MouseEvent} or {@link MouseUtils} can be used to check for different mouse buttons.
     *
     * <p>Example:
     * <p>{@code
     * if (button == MouseUtils.LEFT_BUTTON)}
     *
     * @param button mouse button
     */
    public void buttonPressed(int button) {
    }

    /**
     * To check if a mouse button was clicked, implement this method.
     *
     * <p>Example:
     * <p>{@code
     * if (button == MouseUtils.LEFT_BUTTON)}
     * @param button mouse button
     */
    public void buttonClicked(int button) {
    }

    /**
     * To detect if a mouse button was released, implement this method.
     * {@link MouseEvent} or {@link MouseUtils} can be used to check for different mouse buttons.
     *
     * <p>Example:
     * <p>{@code
     * if (button == MouseUtils.LEFT_BUTTON)}
     *
     * @param button mouse button
     */
    public void buttonReleased(int button) {
    }

    /**
     * Returns the mouse x position.
     * @return mouseX
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     * Returns the mouse x position on screen.
     * @return mouseX on screen
     */
    public int getMouseXOnScreen() {
        return event.getXOnScreen();
    }

    /**
     * Returns the mouse y position.
     * @return mouseY
     */
    public int getMouseY() {
        return mouseY;
    }

    /**
     * Returns the mouse y position on screen.
     * @return mouseY on screen
     */
    public int getMouseYOnScreen() {
        return event.getYOnScreen();
    }

    /**
     * Returns the mouse's absolute position (x and y) on the <strong>screen</strong>.
     * @return mouse position
     *
     * @see Point
     */
    public Point getMousePointOnScreen() {
        return event.getLocationOnScreen();
    }

    /**
     * Returns the mouse's position (x and y) on the window.
     * @return mouse position
     *
     * @see Point
     */
    public Point getMousePoint() {
        return new Point(mouseX, mouseY);
    }

    /**
     * Returns the mouse button.
     * @return button
     */
    public int getButton() {
        return button;
    }

    /**
     * Returns the amount of clicks.
     * @return clicks
     */
    public int getClicks() {
        return clicks;
    }

    /**
     * Returns the mouse event.
     * @return event
     *
     * @see MouseEvent
     */
    public MouseEvent getEvent() {
        return event;
    }

    /**
     * Returns the event type.
     * @return event type
     *
     * @see MouseEvent
     */
    public int getEventType() {
        return event.getID();
    }

}