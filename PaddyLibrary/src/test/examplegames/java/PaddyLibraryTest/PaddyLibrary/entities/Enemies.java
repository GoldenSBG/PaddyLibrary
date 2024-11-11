package PaddyLibraryTest.PaddyLibrary.entities;

import PaddyLibraryTest.PaddyLibrary.graphics.animation.SpriteAnimation;
import PaddyLibraryTest.PaddyLibrary.graphics.sprites.Spritesheet;
import PaddyLibraryTest.PaddyLibrary.input.SimpleKey;
import PaddyLibraryTest.PaddyLibrary.physics.Gravity;
import PaddyLibraryTest.PaddyLibrary.world.Platform;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Enemies {
    private int x, y, velX = 5, velY = 5;
    private boolean onGround = false;
    private Gravity gravity;
    private int groundLevel;
    private SpriteAnimation currentAnimation;
    private Map<String, SpriteAnimation> animations = new HashMap<>();
    private int health; // Lebenspunkte

    public Enemies(int startX, int startY, int groundLevel) {
        this.x = startX;
        this.y = startY;
        this.groundLevel = groundLevel;
        this.gravity = new Gravity(0.5f, 10);
        this.health = 100; // Setze Anfangsleben

        loadAnimations();
        currentAnimation = animations.get("Idleright");
    }

    private void loadAnimations() {
        animations.put("Idleright", loadAnimation("/player/player_Idle_right.png", 10));
        animations.put("Idleleft", loadAnimation("/player/player_Idle_left.png", 10));
    }

    private SpriteAnimation loadAnimation(String filePath, int frameRate) {
        Spritesheet sheet = new Spritesheet(filePath, 50, 50);
        return new SpriteAnimation(frameRate, sheet.split());
    }

    public void checkPlatformCollision(Platform platform) {
        if (x + 50 > platform.getX() && x < platform.getX() + platform.getWidth() &&
                y + 50 >= platform.getY() && y + 50 <= platform.getY() + 10) { // 10px Spielraum für die Kollision
            y = platform.getY() - 50;
            onGround = true;
            gravity.setVelocityY(0);
        }
    }

    public void update() {
        // Schwerkraft anwenden, falls der Gegner nicht am Boden ist
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

        // Bewegung (z.B. nach Links oder Rechts) könnte hier implementiert werden
        if (SimpleKey.getKeyPressed(KeyEvent.VK_A) || SimpleKey.getKeyPressed(KeyEvent.VK_LEFT)) {
            currentAnimation = animations.get("Idleleft");
            x -= velX;
        }
        if (SimpleKey.getKeyPressed(KeyEvent.VK_D) || SimpleKey.getKeyPressed(KeyEvent.VK_RIGHT)) {
            currentAnimation = animations.get("Idleright");
            x += velX;
        }

        // Aktualisiere die Animation
        currentAnimation.update();
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            despawn(); // Gegner despawn, wenn Leben 0
        }
    }

    private void despawn() {
        // Logik, um den Gegner aus dem Spiel zu entfernen
        // Hier könntest du den Gegner aus der Welt oder der Liste der Gegner entfernen
    }

    public void render(Graphics g) {
        currentAnimation.getCurrentSprite().render(x, y, 50, 50);

        // Rendern der Lebensanzeige
        renderHealthBar(g);
    }

    private void renderHealthBar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y - 10, 50, 5); // Hintergrund der Lebensanzeige
        g.setColor(Color.GREEN);
        g.fillRect(x, y - 10, 50 * health / 100, 5); // Vordergrund der Lebensanzeige
    }

    public void cameraRender(int cameraX, int cameraY) {
        currentAnimation.getCurrentSprite().render(x - cameraX, y - cameraY, 50, 50);
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
