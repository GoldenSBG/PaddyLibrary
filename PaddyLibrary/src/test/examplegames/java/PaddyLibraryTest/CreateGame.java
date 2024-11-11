package PaddyLibraryTest;

import PaddyLibrary.Application;
import PaddyLibrary.graphics.Renderer;
import PaddyLibrary.graphics.animation.SpriteAnimation;
import PaddyLibrary.graphics.sprites.Spritesheet;
import PaddyLibrary.input.SimpleKey;
import PaddyLibrary.input.SimpleMouse;
import PaddyLibrary.physics.Gravity;
import PaddyLibrary.utils.MouseUtils;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class CreateGame extends Application {
    private int x = 200, y = 100, velX = 5, velY = 5;

    private class MyMouse extends SimpleMouse {
        @Override
        public void buttonPressed(int button) {
            if (button == MouseUtils.LEFT_BUTTON) {
                x = getMouseX() - 20;
                y = getMouseY() - 20;
            }
        }
    }

    private Map<String, SpriteAnimation> animations = new HashMap<>();
    private SpriteAnimation currentAnimation;

    private Gravity gravity = new Gravity(0.5f, 10);
    private int groundLevel = 500;
    private boolean onGround = false;

    public CreateGame(int width, int height, String title,  int closeOperation, boolean visibility, boolean center, boolean resizable) {
        CreateWindow(width, height, title, this).display(closeOperation, visibility, center);

        setMainApplicationIcon("/player/img.png");

        MyMouse mouse = new MyMouse();
        addMouseInput(mouse);
        addMouseMotionInput(mouse);

        loadAnimations();  // alle animations aufeinmal laden
        currentAnimation = animations.get("Idleright");
        enableLog(false);
    }

    private void loadAnimations() {
        animations.put("Idleright", loadAnimation("/player/player_Idle_right.png", 10));
        animations.put("Idleleft", loadAnimation("/player/player_Idle_left.png", 10));
    }

    private SpriteAnimation loadAnimation(String filePath, int frameRate) {
        Spritesheet sheet = new Spritesheet(filePath, 50, 50);
        return new SpriteAnimation(frameRate, sheet.split());
    }

    public void render() {
        Renderer.Color3f(1.0f, 0, 0);
        Renderer.Rect(0, 0, getWidth(), getHeight());

        currentAnimation.getCurrentSprite().render(x, y, 50, 50);

        Renderer.Color3f(0.5f, 0.25f, 0); // Brauner Boden
        Renderer.Rect(0, groundLevel, getWidth(), getHeight() - groundLevel);
    }

    public void update() {
        setQuitKey(0, true);

        if (!onGround) {
            gravity.applyGravity();
            y += gravity.getVelocityY();
        }

        if (y + 50 >= groundLevel) {
            y = groundLevel - 50;
            onGround = true;
            gravity.setVelocityY(0);
        } else {
            onGround = false;
        }

        if (SimpleKey.getKeyPressed(KeyEvent.VK_W) | SimpleKey.getKeyPressed(KeyEvent.VK_UP)) y -= velY;
        if (SimpleKey.getKeyPressed(KeyEvent.VK_S) | SimpleKey.getKeyPressed(KeyEvent.VK_DOWN)) y += velY;
        if (SimpleKey.getKeyPressed(KeyEvent.VK_A) | SimpleKey.getKeyPressed(KeyEvent.VK_LEFT)) {
            currentAnimation = animations.get("Idleleft");
            x -= velX;
        }
        if (SimpleKey.getKeyPressed(KeyEvent.VK_D) | SimpleKey.getKeyPressed(KeyEvent.VK_RIGHT)) {
            currentAnimation = animations.get("Idleright");
            x += velX;
        }

        currentAnimation.update();
    }

    public static void main(String[] args) {
        new Test().launch(args);
    }
}
