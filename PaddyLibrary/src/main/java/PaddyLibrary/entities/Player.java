package PaddyLibrary.entities;

import PaddyLibrary.graphics.animation.SpriteAnimation;
import PaddyLibrary.graphics.sprites.Spritesheet;
import PaddyLibrary.input.SimpleKey;
import PaddyLibrary.physics.Gravity;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private int x, y, velX = 5, velY = 5;
    private boolean onGround = false;
    private Gravity gravity;
    private int groundLevel;
    private SpriteAnimation currentAnimation;
    private Map<String, SpriteAnimation> animations = new HashMap<>();;

    public Player(int startX, int startY, int groundLevel) {
        this.x = startX;
        this.y = startY;
        this.groundLevel = groundLevel;
        this.gravity = new Gravity(0.5f, 10);

        loadAnimations();
        currentAnimation = animations.get("Idleright"); // Standardanimation
    }

    private void loadAnimations() {
        animations.put("Idleright", loadAnimation("/player/player_Idle_right.png", 10));
        animations.put("Idleleft", loadAnimation("/player/player_Idle_left.png", 10));
    }

    private SpriteAnimation loadAnimation(String filePath, int frameRate) {
        Spritesheet sheet = new Spritesheet(filePath, 50, 50);
        return new SpriteAnimation(frameRate, sheet.split());
    }

    public void update() {
        // Schwerkraft anwenden, falls Spieler nicht am Boden ist
        if (!onGround) {
            gravity.applyGravity();
            y += gravity.getVelocityY();
        }

        // Boden-Kollision prüfen
        if (y + 50 >= groundLevel) {
            y = groundLevel - 50;
            onGround = true;
            gravity.setVelocityY(0);
        } else {
            onGround = false;
        }

        if (SimpleKey.getKeyPressed(KeyEvent.VK_W) || SimpleKey.getKeyPressed(KeyEvent.VK_UP) || SimpleKey.getKeyPressed(KeyEvent.VK_SPACE) ) {
            if (onGround) {  // Sprunglogik nur vom Boden
                gravity.setVelocityY(-15);  // Sprungkraft setzen
                onGround = false;
            }
            y -= velY;
        }
        if (SimpleKey.getKeyPressed(KeyEvent.VK_S) || SimpleKey.getKeyPressed(KeyEvent.VK_DOWN)) y += velY;

        if (SimpleKey.getKeyPressed(KeyEvent.VK_A) || SimpleKey.getKeyPressed(KeyEvent.VK_LEFT)) {
            currentAnimation = animations.get("Idleleft");
            x -= velX;
        }
        if (SimpleKey.getKeyPressed(KeyEvent.VK_D) || SimpleKey.getKeyPressed(KeyEvent.VK_RIGHT)) {
            currentAnimation = animations.get("Idleright");
            x += velX;
        } else if (onGround) {
            currentAnimation = animations.get("Idleright");
        }
        currentAnimation.update();
    }

    public void render() {
        currentAnimation.getCurrentSprite().render(x, y, 50, 50);
    }

    public int getGroundLevel() {
        return groundLevel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}