package PaddyLibrary;

import PaddyLibrary.graphics.Renderer;
import PaddyLibrary.graphics.animation.SpriteAnimation;
import PaddyLibrary.graphics.sprites.Spritesheet;
import PaddyLibrary.input.SimpleKey;
import PaddyLibrary.input.SimpleMouse;
import PaddyLibrary.utils.MouseUtils;
import PaddyLibrary.utils.WindowUtils;

import java.awt.Font;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Test extends Application {

    private class MyMouse extends SimpleMouse {

        @Override
        public void buttonPressed(int button) {
            if (button == MouseUtils.LEFT_BUTTON) {
                x = getMouseX() - 20;
                y = getMouseY() - 20;
            }
        }

    }

    private int x = 200, y = 100, velX = 5, velY = 5;

    private Spritesheet sheet = new Spritesheet("/player/img_1.png");
    private SpriteAnimation spriteAnimation = new SpriteAnimation(10, sheet.split());

    public Test() {
        CreateWindow(850, 580, "test", this).display(WindowUtils.TERMINATE_WINDOW, true, true);

        setMainApplicationIcon("/player/img.png");

        MyMouse mouse = new MyMouse();
        addMouseInput(mouse);
        addMouseMotionInput(mouse);

        enableLog(false);
    }

    public void render() {
        Renderer.Color3f(1.0f, 0, 0);
        Renderer.Rect(0, 0, getWidth(), getHeight());

        spriteAnimation.getCurrentSprite().render(x, y, 50, 50); //(Bild muss mind 50x50 gross sein + halt ein sprite mit mehreren Bilder

        Renderer.Color3f(1.0f, 1.0f, 1.0f);
        Renderer.DrawString("Test", new Font("Balloons!", 0, 40), getWidth() / 2 - 100, 50);
    }

    public void update() {
        setQuitKey(0, true);

        spriteAnimation.update();

        if (SimpleKey.getKeyPressed(KeyEvent.VK_W) | SimpleKey.getKeyPressed(KeyEvent.VK_UP)) y -= velY;
        if (SimpleKey.getKeyPressed(KeyEvent.VK_S) | SimpleKey.getKeyPressed(KeyEvent.VK_DOWN)) y += velY;
        if (SimpleKey.getKeyPressed(KeyEvent.VK_A) | SimpleKey.getKeyPressed(KeyEvent.VK_LEFT)) x -= velX;
        if (SimpleKey.getKeyPressed(KeyEvent.VK_D) | SimpleKey.getKeyPressed(KeyEvent.VK_RIGHT)) x += velX;
    }

    public static void main(String[] args) {
        new Test().launch(args);
    }

}
