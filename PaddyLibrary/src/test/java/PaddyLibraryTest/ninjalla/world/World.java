package PaddyLibraryTest.ninjalla.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class World {
    private ArrayList<Platform> platforms;
    private Random random;
    private int width;
    private int height;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        platforms = new ArrayList<>();
        random = new Random();
        generatePlatforms();
    }

    private void generatePlatforms() {
        // Wenn der Spieler einen bestimmten Bereich erreicht, füge neue Plattformen hinzu
        if (platforms.size() < 50) { // Maximale Anzahl von Plattformen
            for (int i = 0; i < 10; i++) { // Füge 10 neue Plattformen hinzu
                int x = random.nextInt(width - 50); // Zufällige X-Position
                int y = random.nextInt(height - 200) + 100; // Zufällige Y-Position
                platforms.add(new Platform(x, y, random.nextInt(100) + 50, 10)); // Breite zufällig zwischen 50 und 150 Pixeln
            }
        }
    }



    public void draw(Graphics g) {
        for (Platform platform : platforms) {
            platform.draw(g);
        }
    }

    public ArrayList<Platform> getPlatforms() {
        return platforms;
    }
}
