package PaddyLibraryTest.PaddyLibrary.input;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple keyboard class. Provides a method to check if a key was pressed.
 *
 *
 * @see KeyAdapter
 *
 */

public class SimpleKey {

    /**
     * {@link Map} of {@code pressedKeys}.
     */
    public static final Map<Integer, Boolean> pressedKeys = new HashMap<>();

    static {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(event -> {
            synchronized (SimpleKey.class) {
                if (event.getID() == KeyEvent.KEY_PRESSED)
                    pressedKeys.put(event.getKeyCode(), true);
                else if (event.getID() == KeyEvent.KEY_RELEASED)
                    pressedKeys.put(event.getKeyCode(), false);
                return false;
            }
        });
    }

    /**
     * <strong>Universal method</strong>
     *
     * <p>
     * Returns true if the key at {@code keycode} ({@link KeyEvent}) was pressed.
     * This method should be checked in the {@code tick()} method in order for it to
     * start checking for key input.
     *
     * @param keyCode Key code for the pressed key
     * @return <STRONG>boolean</STRONG> pressed
     *
     * @see KeyboardFocusManager
     */
    public static boolean getKeyPressed(int keyCode) {
        return pressedKeys.getOrDefault(keyCode, false);
    }

}