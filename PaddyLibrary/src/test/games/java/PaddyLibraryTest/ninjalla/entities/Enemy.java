package PaddyLibraryTest.ninjalla.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
    private int x;
    private int y;
    private int width = 30;
    private int height = 30;
    private int health = 100;

    public Enemy(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    public void draw(Graphics g) {
        // Zeichne den Gegner als grünes Rechteck
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    // Getter und Setter für die Position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Beispielmethode für Bewegung
    public void move() {
        // Einfache Bewegung nach links
        x -= 1; // Bewegung nach links
        // Logik hinzufügen, um Kollision mit dem Fenster zu überprüfen
        if (x < 0) {
            x = 800; // Reset-Position, wenn der Gegner den Bildschirm verlässt
        }
    }

    // Methode für den Schaden des Gegners
    public void takeDamage(int amount) {
        health -= amount;
        if (health <= 0) {
            // Hier könnte man Logik hinzufügen, um den Gegner zu entfernen
            health = 0; // Verhindert negative Gesundheit
        }
    }
}
