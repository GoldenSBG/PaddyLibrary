package PaddyLibraryTest.ninjalla.utils;

import java.util.Random;

public class Level {
    private String[][] map;  // 2D-Karte des Levels
    private int width;
    private int height;

    // Konstruktor
    public Level() {
        this.width = 10;  // Breite des Levels
        this.height = 5;  // Höhe des Levels
        this.map = new String[height][width];
    }

    // Generiere eine zufällige Karte für das Level
    public void generate() {
        System.out.println("Level wird generiert...");

        Random rand = new Random();

        // Beispielhafter Aufbau: "O" für Plattformen, "-" für leere Stellen
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (rand.nextInt(2) == 0) {
                    map[i][j] = "O";  // Plattform
                } else {
                    map[i][j] = "-";  // Leerer Raum
                }
            }
        }

        renderLevel();
    }

    // Aktualisiere das Level (z.B. Gegner bewegen sich oder neue Items erscheinen)
    public void update() {
        System.out.println("Level wird aktualisiert...");
        // In Zukunft könnten hier Gegner oder Plattformen sich bewegen
    }

    // Zeichne das Level (Konsolen-basierte Darstellung)
    public void renderLevel() {
        System.out.println("Aktuelles Level:");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

