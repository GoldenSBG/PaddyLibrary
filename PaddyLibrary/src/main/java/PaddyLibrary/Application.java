package PaddyLibrary;

import PaddyLibrary.graphics.window.SimpleWindow;
import PaddyLibrary.input.SimpleKey;
import PaddyLibrary.utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;

/**
 * <strong>Packaged class containing main game loop, graphics rendering, thread handling, etc.</strong>
 *
 * The most important class for {@code SJGL} programs.
 *
 * <p>The main {@code Application}. This class sets up an application layer containing essential features such as rendering graphics, game loop,
 * thread handling, etc. Every class that has the main method should at least extend the application class. In order to successfully run
 * an {@code Application} class, there should be a main window initialized and created (context should be set up as the last argument).
 *
 * <p>To draw graphics using {@link Graphics2D}, override the {@code render(Graphics2D g)} method. To update the program every specified frames
 * per second, override the {@code tick()} method.
 *
 * <p> To safely terminate the application, set the close operation of the window {@link SimpleWindow} to {@code WindowUtils.TERMINATE_WINDOW}.
 * When the main window gets closed, the program will safely close.
 *
 * <p><STRONG>Note:</STRONG> {@code Application} inherits from {@link Canvas} so be sure not to override or call any super methods.
 * Additionally, it implements {@link Runnable} interface.
 *
 * @author yiwuen
 *
 * @see Canvas Canvas - used to create a graphics context
 * @see Runnable Runnable - used for thread handling, rendering graphics, and updating application
 *
 */

@SuppressWarnings("serial")
public class Application extends Canvas implements Runnable {

    private SimpleWindow mainWindow;

    private int numBuffers = 3;

    private int closeOperation;

    private boolean running = false;
    private Thread thread;

    private boolean enableLog = false;

    /**
     * Program exit status.
     */
    public static int exitStatus;

    /**
     * Accesses the {@link Graphics2D} object {@code g}. This {@link Graphics2D} object is initialized once {@code Application} is initialized. Otherwise
     * it will result in null errors. This reference should not be used to render graphics and should be done using the Graphics2D argument provided in
     * the {@code render(g)} method.
     */
    public static Graphics2D g;

    /**
     * Simply constructs a {@link SimpleWindow} based on the specified properties. This method will automatically set the created window as the main window
     * using {@code setMainWindow(window)}. To get the main window (created window using this method), use {@code getMainWindow()}.
     * @param width Width of the window
     * @param height Height of the window
     * @param title Title of the window
     * @param application Main application and graphics/thread context
     * @return {@link SimpleWindow} mainWindow
     *
     * @see SimpleWindow
     */
    public final SimpleWindow CreateWindow(final int width, final int height, final String title, final Application application) {
        SimpleWindow simpleWindow = new SimpleWindow(width, height, title, application);
        setMainWindow(simpleWindow);
        return simpleWindow;
    }

    /**
     * This method can optionally be overrided. Overriding this method requires a return value of any. The value determines how many frames
     * is updated per second. By default, it is 60.
     * @return int Frames per second.
     */
    public double frames() {
        return 60.0;
    }

    /**
     * Returns whether or not to immediately request focus of the window. By default, this method returns true.
     * @return <STRONG>boolean</STRONG> Enable request focus
     */
    public boolean enableRequestFocus() {
        return true;
    }

    /**
     * Enables or disables the frames and updates log. By default, it is disabled.
     *
     * @param enable Enable or disable frames and updates log
     */
    public final void enableLog(boolean enable) {
        enableLog = enable;
    }

    /**
     * Sets the application icon for the window {@link SimpleWindow}. This accesses the display {@link JFrame} and sets the icon for it.
     * @param relativePath Relative path to the icon image
     * @param simpleWindow Window
     */
    public final void setApplicationIcon(String relativePath, SimpleWindow simpleWindow) {
        ImageIcon img = new ImageIcon(getClass().getResource(relativePath));
        simpleWindow.getDisplay().setIconImage(img.getImage());
    }

    /**
     * Sets the <strong>main</strong> application icon for the <strong>main</strong> window {@link SimpleWindow}.
     * This accesses the display {@link JFrame} and sets the icon for it. If the main window is not initialized, declare the
     * {@code setApplicationIcon(String, SimpleWindow)} method to set the icon of a specified window.
     * @param relativePath Relative path to the icon image
     */
    public final void setMainApplicationIcon(String relativePath) {
        ImageIcon img = new ImageIcon(getClass().getResource(relativePath));
        mainWindow.getDisplay().setIconImage(img.getImage());
    }

    /**
     * When this method is called preferably in the {@code update()} or {@code tick()} method, pressing the escape key will safely terminate
     * the program based on the given {@code exitStatus}. This method logs the program termination info to the console.
     *
     * @param exitStatus Exit status code. If {@code exitStatus} was any number besides 0, the program will fail to terminate safely. Otherwise, the program
     * will successfully terminate safely.
     * @param log If true, this method will log program termination information into the console. Otherwise it will not.
     */
    public final void setQuitKey(int exitStatus, boolean log) {
        Application.exitStatus = exitStatus;
        if (SimpleKey.getKeyPressed(KeyEvent.VK_ESCAPE)) {
            if (log) {
                if (closeOperation == WindowUtils.TERMINATE_WINDOW) {
                    if (Application.exitStatus == 0)
                        System.out.println("[TERMINATED SUCCESSFULLY] Program terminated safely.");
                    else
                        System.err.println("[TERMINATED UNSUCCESSFULLY] Program failed to terminate safely.");
                } else {
                    if (Application.exitStatus == 0)
                        System.out.println("[TERMINATED SUCCESSFULLY] Program terminated safely.");
                    else
                        System.err.println("[TERMINATED UNSUCCESSFULLY] Program failed to terminate safely.");
                }
                terminate(Application.exitStatus);
            } else
                terminate(Application.exitStatus);
        }
    }

    /**
     * Terminates the program. Equivalent to {@code System.exit(n)}.
     * @param exitStatus Exit status code. If {@code exitStatus} was any number besides 0, the program will fail to terminate safely. Otherwise, the program
     * will successfully terminate safely.
     */
    public void terminate(int exitStatus) {
        Application.exitStatus = exitStatus;
        Runtime.getRuntime().exit(Application.exitStatus);
    }

    protected final void checkArguments(String[] args) {
        if (args.length == 0)
            System.out.println("[LAUNCHED SUCCESSFULY] Program launched with no arguments.");
        else if (args.length > 0)
            System.out.println("[LAUNCHED SUCCESSFULY] Program launched with arguments.");
        for (String arg : args) {
            switch(arg) {
                case "sjgl-printSecret":
                    System.out.println("source code: https://github.com/yiwuen/Simple-Java-Game-Library-SJGL");
                    break;
                default:
                    System.err.println("[ARGUMENT ERROR] Program launched with unknown program arguments.");
                    break;
            }
        }
    }

    /**
     * <strong>Synchronized</strong>
     *
     * <p>Launches the main thread with arguments. This enables rendering graphics and updating. To ensure there aren't any errors,
     * call this method after the main window has been initialized (recommended to call this method in the {@code main(String[])} method.
     * Pass {@code args} as the arguments in the {@code main(String[])} method.
     *
     * <p> To add extra functionality to your program, you can add program arguments. Every program argument for SJGL begins with the prefix
     * "sjgl" followed by a hyphen and the command name. Program arguments should only be added when necessary. Program argument commands
     * can be found on the official documentation. Inputting no program arguments doesn't return an error and the launch will still be
     * successful.
     *
     * @param args Program arguments
     */
    public final synchronized void launch(String[] args) {
        checkArguments(args);
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * <strong>Synchronized</strong>
     *
     * <p>Stops the main thread. If the thread is running while other process are running, surround this method with try and catch to prevent
     * any errors. {@code stop()} method should be used once the main thread isn't processing/synchronized.
     *
     * <p> <strong>This method shouldn't be used as it isn't safe thread-handling. The game loop handles stopping thread
     * in the {@code run()} method implemented from the {@link Runnable} interface.</strong> Use when necessary.
     */
    public final synchronized void stop() {
        if (!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void run() {
        if (enableRequestFocus())
            requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ticks = 1000000000.0 / frames();
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ticks;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            draw();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                if (enableLog)
                    System.out.println("FPS: " + frames + "; updates: " + updates);
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    private final void tick() {
        update();
    }

    private final void draw() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(numBuffers);
            return;
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        Application.g = g;

        render();

        g.dispose();
        bs.show();
    }

    /**
     * Sets the amount of buffers the {@code BufferStrategy} will create.
     * @param numBuffers Number of buffers. Set to 3 by default (recommended 2-3).
     *
     * @see BufferStrategy
     * @see Canvas
     */
    public void setNumBuffers(int numBuffers) {
        this.numBuffers = numBuffers;
    }

    /**
     * To be overrided. Implementing this method will draw graphics onto the screen using {@link Renderer}. The game loop and rendering context
     * is already set up so drawing graphics can easily be done with a couple of lines.
     *
     * <p> <strong>Note:</strong> Any graphics rendering should be done in {@code render()} method.
     */
    public void render() {
    }

    /**
     * To be overrided. Implementing this method will update the program the specified amount of frames every second.
     */
    public void update() {
    }

    /**
     * Adds a keyboard input listener {@link KeyAdapter} to {@code Application} class.
     *
     * @param keyInput Keyboard input reference
     */
    public final void addKeyInput(KeyAdapter keyInput) {
        addKeyListener(keyInput);
    }

    /**
     * Adds a mouse input listener {@link MouseAdapter} to {@code Application} class.
     *
     * @param mouseInput Mouse input reference
     */
    public final void addMouseInput(MouseAdapter mouseInput) {
        addMouseListener(mouseInput);
    }

    /**
     * Adds a mouse motion input listener {@link MouseAdapter} to {@code Application} class.
     *
     * @param mouseInput Mouse motion input reference
     */
    public final void addMouseMotionInput(MouseAdapter mouseInput) {
        addMouseMotionListener(mouseInput);
    }

    /**
     * Adds a mouse wheel input listener {@link MouseAdapter} to {@code Application} class.
     *
     * @param mouseInput Mouse wheel input reference
     */
    public final void addMouseWheelInput(MouseAdapter mouseInput) {
        addMouseWheelListener(mouseInput);
    }

    /**
     * Returns the main thread.
     * @return <strong>thread</strong> Main thread
     */
    public final Thread getThread() {
        return thread;
    }

    /**
     * Returns the status of the current thread/program.
     * @return <strong>boolean</strong> running
     */
    public final boolean isRunning() {
        return running;
    }

    /**
     * Sets the status of the current thread/program. This method shouldn't be used as it is unsafe but use it as necessary.
     */
    public final void setRunning(boolean running) throws Exception {
        this.running = running;
    }

    /**
     * Sets the main window of the {@code Application}.
     * @param mainWindow Main window {@link SimpleWindow}
     */
    public final void setMainWindow(SimpleWindow mainWindow) {
        this.mainWindow = mainWindow;
        closeOperation = this.mainWindow.getCloseOperation();
    }

    /**
     * Returns the main window of the {@code Application}.
     * @return the main window
     *
     * @see SimpleWindow
     */
    public final SimpleWindow getMainWindow() {
        return mainWindow != null ? mainWindow : null;
    }

    /**
     * Returns the main window's close operation. Equivalent to {@link SimpleWindow}'s {@code getCloseOperation()}.
     * @return int closeOperation
     */
    public int getCloseOperation() {
        return closeOperation;
    }

    /**
     * Returns the current width of the main window of the application.
     * @return the current width of the application
     *
     * @see Component
     */
    public int getWidth() {
        return super.getWidth();
    }

    /**
     * Returns the current height of the main window of the application.
     * @return the current height of the application
     *
     * @see Component
     */
    public int getHeight() {
        return super.getHeight();
    }

}