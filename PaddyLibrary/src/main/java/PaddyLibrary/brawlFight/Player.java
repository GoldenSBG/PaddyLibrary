package PaddyLibrary.brawlFight;

import javax.swing.*;
import java.awt.*;

public class Player {
    private int x, y;
    private int width, height;
    private int dx, dy;
    private boolean jumping, attacking;
    private Rectangle hitbox;
    private Rectangle attackBox;
    private final int MOVE_SPEED = 5;
    private final int JUMP_SPEED = 15;
    private final int GRAVITY = 1;
    private final int ATTACK_RANGE = 60;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.width = 50;
        this.height = 80;
        this.dx = 0;
        this.dy = 0;
        this.jumping = false;
        this.attacking = false;
        this.hitbox = new Rectangle(x, y, width, height);
        this.attackBox = new Rectangle();
    }

    public void moveLeft() {
        dx = -MOVE_SPEED;
    }

    public void moveRight() {
        dx = MOVE_SPEED;
    }

    public void jump() {
        if (!jumping) {
            jumping = true;
            dy = -JUMP_SPEED;
        }
    }

    public void attack() {
        attacking = true;
        // Angriffskollisionsbox berechnen
        if (dx > 0) { // Moving right
            attackBox.setBounds(x + width, y, ATTACK_RANGE, height);
        } else if (dx < 0) { // Moving left
            attackBox.setBounds(x - ATTACK_RANGE, y, ATTACK_RANGE, height);
        } else {
            attackBox.setBounds(x + width / 2 - ATTACK_RANGE / 2, y, ATTACK_RANGE, height);
        }
    }

    public void stopMoving() {
        dx = 0;
    }

    public void update() {
        // Schwerkraft anwenden
        if (jumping) {
            dy += GRAVITY;
        }

        // Bewegung aktualisieren
        x += dx;
        y += dy;

        // Kollision mit Boden
        if (y >= 400) { // Beispielwert für den Boden
            y = 400;
            jumping = false;
            dy = 0;
        }

        // Hitbox und Angriffskollisionsbox aktualisieren
        hitbox.setLocation(x, y);
        if (attacking) {
            attackBox.setLocation(x, y);
            attacking = false;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
        if (attacking) {
            g.setColor(Color.RED);
            g.drawRect(attackBox.x, attackBox.y, attackBox.width, attackBox.height);
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Rectangle getAttackBox() {
        return attackBox;
    }
}
